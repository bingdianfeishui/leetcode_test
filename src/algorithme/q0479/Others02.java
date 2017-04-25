package algorithme.q0479;

import com.lee.timer.RunTimer;

/**
 * There are two key points of this problem: (1) how to efficiently generate the
 * possible palindrome number in a greedy way? (2) how to efficiently check
 * whether the palindrome number can be made by two n-digit numbers product?
 * 
 * Here are my answers to the two points. (1) First, think about how many digits
 * of the possible palindrome number could be? It is could be a 2n-digit number,
 * and also could be a (2n-1)-digit number. For example, if n is 2, the smallest
 * product of two 2-digits numbers is 100, which has 3 digits; and the largest
 * product of two 2-digits numbers is 9801. So we can first the check the
 * 2n-digit palindromes, then check the (2n-1)-digit palindromes; because the
 * 2n-digit palindromes always larger than the (2n-1)-digit palindromes. If
 * there is no valid 2n-digit palindrome number, nor (2n-1)-digit palindrome
 * number, then it can be sure that there will be no valid number (in this case,
 * -1 is returned).
 * 
 * Second, try to generate the 2n-digit palindromes (see function
 * getParlindrome) and the (2n-1)-digit palindromes ( see function
 * getOddParlindrome) greedily.
 * 
 * (2) To check whether the (palindrome) number can be made by two n-digit
 * numbers product, I use the square root as the start points. Because I think
 * the two n-digit factors should be near to the square root. (see function
 * isValid)
 *
 */

public class Others02 {
	public static void main(String[] args) {
		Others02 pal = new Others02();
		long t1= System.currentTimeMillis();
		for (int i = 1; i <= 8; i++) {
			System.out.println(pal.largestPalindrome(i));
		}
		System.out.println("Time(ms):"+(System.currentTimeMillis()-t1));
	}

	protected long getParlindrome(int high, int n) {
		long result = high * (long) (Math.pow(10, n));

		int tmp = 0;
		while (high > 0) {
			tmp = tmp * 10 + high % 10;
			high = high / 10;
		}

		return result + tmp;
	}

	protected long getOddParlindrome(int high, int mid, int n) {
		if (n == 0) {
			return mid;
		}

		long result = high * (long) (Math.pow(10, n + 1)) + mid;

		int tmp = 0;
		while (high > 0) {
			tmp = tmp * 10 + high % 10;
			high = high / 10;
		}

		return result + tmp;
	}

	protected boolean isValid(long val, int high, int low) {
		long root = (long) Math.sqrt(val);
		if (root > high || root < low) {
			return false;
		}

		long myh = root;
		long myl = root;
		long tmp = myh * myl;

		while (tmp != val) {
			if (tmp < val) {
				myh += 1;
				if (myh > high) {
					return false;
				}
			} else {
				myl -= 1;
				if (myl < low) {
					return false;
				}
			}

			tmp = myh * myl;
		}

		return true;
	}
	
	@RunTimer
	public int largestPalindrome(int n) {
		if(n==1) return 9;
		long product = 0;
		int high = (int) Math.pow(10, n) - 1;
		int low = (int) Math.pow(10, n - 1);

		// product = 2*n digits
		for (int i = high; i >= low; i--) {
			product = getParlindrome(i, n);
			if (isValid(product, high, low)) {
				return (int) (product % 1337);
			}
		}

		// product = 2*n-1 digits
		for (int i = low - 1; i >= (int) (Math.pow(10, n - 2)); i--) {
			for (int k = 9; k >= 0; k--) {
				product = getOddParlindrome(i, k, n - 1);
				if (isValid(product, high, low)) {
					return (int) (product % 1337);
				}
			}
		}

		return -1;
	}
}
