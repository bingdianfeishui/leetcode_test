package algorithme.q0344;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 344. Reverse String
 * Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".
 * @author Lee
 *
 */
public class Q344ReverseString {
	public static void main(String[] args) {
		int times = 100000;
		Object[] argsArr = new Object[] {"a", "ab", "abcde", "0123456789" };

		TimerUtils.batchRunAll(Q344ReverseString.class, times, argsArr);
	}
	
	@RunTimer
	public String reverseString1(String s) {
        return new StringBuffer().append(s).reverse().toString();
    }
	
	@RunTimer
	public String reverseString2(String s) {
		char[] cs = new char[s.length()];
		for(int i = 0; i< s.length(); i++){
			cs[i] = s.charAt(s.length() - 1 - i);
		}
		
		return String.valueOf(cs);
	}
	
	@RunTimer
	public String reverseString3(String s) {
		char[] cs = s.toCharArray();
		
		int i=0, j = cs.length - 1;
		while(i < j){
			char tmp = cs[i];
			cs[i++] = cs[j];
			cs[j--] = tmp;
		}
		
		return String.valueOf(cs);
	}
	
	@RunTimer
	public String reverseString4(String s) {
//		if(s.length() < 2) return s;
		char[] cs = s.toCharArray();
		int max = cs.length - 1;
		for(int i = 0; i < cs.length /2; i++){
			char tmp = cs[i];
			cs[i] = cs[max - i];
			cs[max - i] = tmp;
		}
		
		return String.valueOf(cs);
	}

	
}
