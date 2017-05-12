package algorithme.q0479;

import java.util.Arrays;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
479. Largest Palindrome Product

Find the largest palindrome made from the product of two n-digit numbers.

Since the result could be very large, you should return the largest palindrome mod 1337.

Example:

Input: 2

Output: 987

Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

Note:

The range of n is [1,8].
 * @see https://leetcode.com/problems/largest-palindrome-product/#/description
 * @author 60238
 *
 */
public class Q479LargestPalindromeProduct {
	public static void main(String[] args) {
//		for (int i = 1; i <= 8; i++) {
//			System.out.println("=======" + i + "=======");
//			System.out.println(TimerUtils.run(LargestPalindromeProduct.class, "largestPalindrome",1, new Object[] { i }));
//		}
		
		Object[] objects = TimerUtils.batchRun(Q479LargestPalindromeProduct.class, "largestPalindrome", 1, new Object[][]{{1},{2},{3},{4}});
		System.out.println(Arrays.toString(objects));
	}

	// ======== solution begin =============
	// ======== solution begin =============
	@RunTimer
	public int largestPalindrome(int n) {
		if (n == 1)
			return 9;

		int max = (int) (Math.pow(10, n) - 1);
		int min = max % 10;

		for (int i = max; i > min; i--) {
			long palin = createPalindrome(i);
			int sqrt = (int) Math.sqrt(palin);
			int hi = sqrt, lo = sqrt;
			while (hi <= max && lo > min) {
				long mul = hi * lo;
				if (mul > palin)
					lo--;
				else if (mul < palin)
					hi++;
				else
					return (int) (palin%1337);
			}
			// int tmp = max;
			// while (tmp > min) {
			// if (palin % tmp == 0) {
			// int ret = (int) (palin / tmp);
			// if (ret > min && ret <= max) {
			// // System.out.println(palin +" "+tmp+" "+ret);
			// return (int) (palin % 1337);
			// }
			// }
			// tmp--;
			// }
		}
		return 9;
	}

	protected long createPalindrome(int num) {
		String rev = new StringBuilder().append(num).reverse().toString();
		return Long.valueOf(num + rev);
	}
	// ======== solution end ===============

	// ======== solution begin =============

	// ======== solution end ===============
}
