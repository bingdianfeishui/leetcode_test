package algorithme.q0479;

import java.util.Arrays;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

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
