package algorithme.q0389;

import java.util.HashSet;
import java.util.Set;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

public class Q389FindTheDifference {
	public static void main(String[] args) {
		int times = 100000;

		Object[][] argsArr = new Object[][] {
				new String[]{"zxcvbnm,./ZXCVzxcvbnm,./ZXCVBNM<>?BNM<>?ab1234567890qwertyuiop[]!@#$%^&*()_+cd",
						",./ZXCVBNM<>?BNM<>?zxcvbnm890qwertyuiop[]!@#$%^&ab1234567zxcvbnm" + "~" + ",./ZXCV*()_+cd"},
				new String[]{"q1234567890zxcvbnm,./ZXCVBNM<>?qwertyzxcvbnm,./ZXCVBNM<>?uiop[]!@#$%^&*()_+wer", 
						"12345678zxcvbnmzxcvbnm,./ZXCVBNM<>?,./ZXCVBNM<>?90qwertyuiop[]!@#$%^&*()_+erwq" + "~"},
		};

		TimerUtils.batchRunAll(Q389FindTheDifference.class, times, argsArr);
	}
	
	@RunTimer
	public char findTheDifference(String s, String t) {
        for(int i = 0; i < t.length(); i++){
        	if(!s.contains(String.valueOf(t.charAt(i))))
        		return t.charAt(i);
        }
        return ' ';
    }
	
	@RunTimer
	public char findTheDifference1(String s, String t) {
		Set<Character> set = new HashSet<>();
		for(char c:s.toCharArray())
			set.add(c);
		for(char c: t.toCharArray())
			if(!set.contains((Character)c)) return c;
		return ' ';
	}
	
	@RunTimer
	public char findTheDifference2(String s, String t) {
         int ret = 0;
         for(int i = 0; i < s.length(); i++){
             ret = ret ^ s.charAt(i) ^ t.charAt(i);
         }
         return (char) (ret ^ t.charAt(t.length() - 1));
	}

	@RunTimer
	public char findTheDifference3(String s, String t) {
         return (char)(t.chars().sum() - s.chars().sum());
	}
	
	@RunTimer
	public char findTheDifference4(String s, String t) {
        return (char) (s + t).chars().reduce(0, (x, y) -> x ^ y);
	}
}
