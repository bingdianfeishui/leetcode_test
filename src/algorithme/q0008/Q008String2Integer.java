package algorithme.q0008;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 8. String to Integer (atoi)
 * Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

{@link <a href="https://leetcode.com/problems/string-to-integer-atoi/#">spoilers alert... click to show requirements for atoi.</a>}}
 * @author Lee
 *
 */
public class Q008String2Integer {
	public static void main(String[] args) {
		int times = 100000;

		Object[] argsArr = new Object[] { 
				//null, 				//0
				"",             	//0
				"+",           		//0
				"a",            	//0
				"00",           	//0
				"-0 0",         	//0
				"00 123",       	//0
				" -00123",      	//-123
				"+123 456",     	//123
				"-54a321",      	//-54
				"-2147483648",  	//-2147483648
				"+2147483647",  	//2147483647
				"-2147483649",  	//-2147483648
				"2147483648",   	//2147483647
				"    10522545459",	//2147483647
		 };
		TimerUtils.batchRunAll(Q008String2Integer.class, times, argsArr);
	}
	
	@RunTimer
    public int myAtoi(String str) {
		if(str == null || str.length() == 0) return 0;
        
		//去前导空格
		int index = 0;	//有效的数字字符开始位置指针
		while(str.charAt(index) == ' ' && index < str.length()) index++;
		
		//判断符号
        boolean positive = true;
        if(str.charAt(index) == '-' || str.charAt(index) == '+') {
        	positive = str.charAt(index) == '+';
        	index++;
        }
        
        //依次判断各个字符
        int result = 0;
        for (int i = index; i < str.length(); i++) {
			int num = str.charAt(i) - '0';
			if(num < 0 || num > 9) break;	//遇到非数字字符则停止，所以不支持逗号分隔的数字及科学计数法等，1,000|10e3.
			result = result * 10 + num;		
			if(i - index >= 10 || result < 0) {	//若超过10位或结果已小于0，则表示已经溢出
				if(!positive && result == Integer.MIN_VALUE) return Integer.MIN_VALUE;
				return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
		}
        
    	return positive ? result : -result;
    }

	/**
	 * 参考Integer方法
	 * @see Integer#parseInt(String, int)
	 * @param str
	 * @return
	 */
	@RunTimer
    public int myAtoi2(String str) {
		if(str == null || str.length() == 0) return 0;
        
        int index = 0;
        while(str.charAt(index)==' ' && index < str.length()) index++;
        
        boolean positive = true;
        if(str.charAt(index) == '-' || str.charAt(index) == '+') {
        	positive = str.charAt(index) == '+';
        	index++;
        }
        
        int result = 0;
        for (int i = index; i < str.length(); i++) {
			int num = str.charAt(i) - '0';
			if(num < 0 || num > 9) break;
			if(result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			result = result * 10 + num;
		}
        
    	return positive ? result : -result;
    }
	
	@RunTimer
	public int myAtoi3(String str) {
	    if (str.isEmpty()) return 0;
	    int sign = 1, base = 0, i = 0;
	    while (str.charAt(i) == ' ')
	        i++;
	    if (str.charAt(i) == '-' || str.charAt(i) == '+')
	        sign = str.charAt(i++) == '-' ? -1 : 1;
	    while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
	        if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
	            return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
	        }
	        base = 10 * base + (str.charAt(i++) - '0');
	    }
	    return base * sign;
	}
}
