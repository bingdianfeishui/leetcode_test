package algorithme.q0007;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 7. Reverse Integer
 * 
 * Reverse digits of an integer.
 * 
 * Example1: x = 123, return 321 Example2: x = -123, return -321
 * 
 * click to show spoilers.
 * 
 * Have you thought about this? Here are some good questions to ask before
 * coding. Bonus points for you if you have already thought through this!
 * 
 * If the integer's last digit is 0, what should the output be? ie, cases such
 * as 10, 100.
 * 
 * Did you notice that the reversed integer might overflow? Assume the input is
 * a 32-bit integer, then the reverse of 1000000003 overflows. How should you
 * handle such cases?
 * 
 * For the purpose of this problem, assume that your function returns 0 when the
 * reversed integer overflows.
 * 
 * Note: The input is assumed to be a 32-bit signed integer. Your function
 * should return 0 when the reversed integer overflows.
 * 
 * @author Lee
 *
 */
public class Q007ReverseInteger {
	public static void main(String[] args) {
		int times = 100000;

		Object[] argsArr = new Object[] { 
				0, -5, 5, 100, -100, 10001, -101, 123456, -123456, 1000000003, -2147483648 };
//		System.out.println(Math.abs(-2147483648)); //Math.abs(-2147483648)=-2147483648
		TimerUtils.batchRunAll(Q007ReverseInteger.class, times, argsArr);
	}

	@RunTimer
	public int reverse1(int x) {
		if (x < 10 && x > -10)
			return x;
		try {
			String str = new StringBuffer().append(Math.abs(x)).reverse().toString();
			return x < 0 ? -Integer.valueOf(str) : Integer.valueOf(str);
		} catch (Exception e) {
			return 0;
		}
	}

	@RunTimer
	public int reverse2(int x) {
		if (x < 10 && x > -10)
			return x;
		if (x == Integer.MIN_VALUE)
			return 0;
		char[] chars = String.valueOf(Math.abs(x)).toCharArray();
		int len = chars.length;
		for (int i = 0; i < len / 2; i++) {
			char c = chars[i];
			chars[i] = chars[len - i - 1];
			chars[len - i - 1] = c;
		}
		Long l = Long.valueOf(String.valueOf(chars));
		if (l > Integer.MAX_VALUE)
			return 0;
		return x < 0 ? -l.intValue() : l.intValue();
	}

	@RunTimer
	public int reverse3(int x) {
		int result = 0;

		while (x != 0) {
			int tail = x % 10;
			int newResult = result * 10 + tail;
			if ((newResult - tail) / 10 != result) {
				return 0;
			}
			result = newResult;
			x = x / 10;
		}

		return result;
	}

	@RunTimer
	public int reverse4(int x) {
		long answer = 0;
		while (x != 0) {
			answer = 10 * answer + x % 10;
			x /= 10;
		}
		return (answer > Integer.MAX_VALUE || answer < Integer.MIN_VALUE) ? 0 : (int) answer;
	}
}
