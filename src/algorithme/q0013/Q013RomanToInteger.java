package algorithme.q0013;

import java.util.HashMap;
import java.util.Map;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 13. Roman to Integer Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * 罗马数字是阿拉伯数字传入之前使用的一种数码。罗马数字采用七个罗马字母作数字、即I（1）、X（10）、 基本字符 对应阿拉伯数字 I 1 V 5 X 10
 * L 50 C 100 D 500 M 1000
 * 
 * 计数方法： 相同的数字连写、所表示的数等于这些数字相加得到的数、如：Ⅲ=3； 小的数字在大的数字的右边、所表示的数等于这些数字相加得到的数、
 * 如：Ⅷ=8、Ⅻ=12； 小的数字（限于 I、X 和 C）在大的数字的左边、所表示的数等于大数减小数得到的数、如：Ⅳ=4、Ⅸ=9；
 * 正常使用时、连写的数字重复不得超过三次； 在一个数的上面画一条横线、表示这个数扩大 1000 倍。
 * 
 * 举例： ·个位数举例 I－1、II－2、III－3、IV－4、V－5、VI－6、VII－7、VIII－8、IX－9 ·十位数举例
 * X－10、XI－11、XII－12、XIII－13、XIV－14、XV－15、XVI－16、XVII－17、XVIII－18、XIX－19、XX－20、XXI－21、XXII－22、XXIX－29、XXX－30、XXXIV－34、XXXV－35、XXXIX－39、XL－40、L－50、LI－51、LV－55、LX－60、LXV－65、LXXX－80、XC－90、XCIII－93、XCV－95、XCVIII－98、XCIX－99
 * ·百位数举例
 * C－100、CC－200、CCC－300、CD－400、D－500、DC－600、DCC－700、DCCC－800、CM－900、CMXCIX－999
 * ·千位数举例
 * M－1000、MC－1100、MCD－1400、MD－1500、MDC－1600、MDCLXVI－1666、MDCCCLXXXVIII－1888、MDCCCXCIX－1899、MCM－1900、MCMLXXVI－1976、MCMLXXXIV－1984、MCMXC－1990、MM－2000、MMMCMXCIX－3999
 * 
 * @author 60238
 *
 */
public class Q013RomanToInteger {
	@SuppressWarnings("serial")
	private static Map<Character, Integer> map = new HashMap<Character, Integer>() {
		{
			put('I', 1);
			put('X', 10);
			put('C', 100);
			put('M', 1000);
			put('V', 5);
			put('L', 50);
			put('D', 500);
		}
	};

	public static void main(String[] args) {
		int times = 100000;
		Object[] argsArr = new Object[] {
				// [0, 1, 8, 9, 10, 12, 14, 16, 19, 99, 700, 1666, 3999]
				// "", "I", "VIII", "IX", "X", "XII", "XIV", "XVI", "XIX",
				// "XCIX", "DCC", "MDCLXVI","MMMCMXCIX"

				/*
				 * 621 10 11 12 13 14 15 16 17 18 19 20 21 22 29 30 34 35 39 40
				 * 50 51 55 60 65 80 90 93 95 98 99 200 300 400 500 600 700 800
				 * 900 999 1100 1400 1500 1600 1666 1888 1899 1900 1976 1984
				 * 1990 2000 3999
				 */

				"X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIX",
				"XXX", "XXXIV", "XXXV", "XXXIX", "XL", "L", "LI", "LV", "LX", "LXV", "LXXX", "XC", "XCIII", "XCV",
				"XCVIII", "XCIX", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM", "CMXCIX", "MC", "MCD", "MD", "MDC",
				"MDCLXVI", "MDCCCLXXXVIII", "MDCCCXCIX", "MCM", "MCMLXXVI", "MCMLXXXIV", "MCMXC", "MM", "MMMCMXCIX", };

		// int i = new Q013RomanToInteger().romanToInt("IV");
		// System.out.println(i);
		TimerUtils.batchRunAll(Q013RomanToInteger.class, times, argsArr);
	}

	@RunTimer
	public int romanToInt(String s) {
		if (s == null || s.length() == 0)
			return 0;
		if (s.length() == 1)
			return map.get(s.charAt(0));

		int[] nums = new int[s.length()];
		for (int i = 1; i < s.length(); i++) {
			int pre = map.get(s.charAt(i - 1));
			int now = map.get(s.charAt(i));
			if (pre < now) {
				nums[i - 1] = -pre;
			} else {
				nums[i - 1] = pre;
			}
			nums[i] = now;
		}

		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		return sum;
	}

	@RunTimer
	public int romanToInt2(String s) {
		if (s == null || s.length() == 0)
			return 0;
		if (s.length() == 1)
			return map.get(s.charAt(0));

		int sum = 0, pre = 0;
		for (int i = 0; i < s.length(); i++) {
			int now = map.get(s.charAt(i));
			sum += now;
			if (pre < now)
				sum -= pre * 2;
			pre = now;
		}

		return sum;
	}
	
	@RunTimer
	public int romanToInt3(String s) {
		int index = 0, sum = 0, pre = 0;
		while(index < s.length()) {
			int now = getNumByChar(s.charAt(index++));
			sum += now;
			if (pre < now)
				sum -= pre * 2;
			pre = now;
		}

		return sum;
	}

	private int getNumByChar(char ch) {
		switch (ch) {
		case 'I':
			return 1;
		case 'X':
			return 10;
		case 'C':
			return 100;
		case 'M':
			return 1000;
		case 'V':
			return 5;
		case 'L':
			return 50;
		case 'D':
			return 500;
		default:
			return 0;
		}
	}
	
	
	@RunTimer
	public int romanToInt4(String s) {
	    int sum=0;
	    if(s.indexOf("IV")!=-1){sum-=2;}
	    if(s.indexOf("IX")!=-1){sum-=2;}
	    if(s.indexOf("XL")!=-1){sum-=20;}
	    if(s.indexOf("XC")!=-1){sum-=20;}
	    if(s.indexOf("CD")!=-1){sum-=200;}
	    if(s.indexOf("CM")!=-1){sum-=200;}
	    
	    char c[]=s.toCharArray();
	    int count=0;
	    
	   for(;count<=s.length()-1;count++){
	       if(c[count]=='M') sum+=1000;
	       if(c[count]=='D') sum+=500;
	       if(c[count]=='C') sum+=100;
	       if(c[count]=='L') sum+=50;
	       if(c[count]=='X') sum+=10;
	       if(c[count]=='V') sum+=5;
	       if(c[count]=='I') sum+=1;
	       
	   }
	   
	   return sum;
	    
	}
	
	@RunTimer
	public static int romanToInt5(String s) {
		int res = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			switch (c) {
			case 'I':
				res += (res >= 5 ? -1 : 1);
				break;
			case 'V':
				res += 5;
				break;
			case 'X':
				res += 10 * (res >= 50 ? -1 : 1);
				break;
			case 'L':
				res += 50;
				break;
			case 'C':
				res += 100 * (res >= 500 ? -1 : 1);
				break;
			case 'D':
				res += 500;
				break;
			case 'M':
				res += 1000;
				break;
			}
		}
		return res;
	}
}
