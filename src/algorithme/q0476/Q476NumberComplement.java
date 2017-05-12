package algorithme.q0476;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 476. Number Complement
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer¡¯s binary representation.
Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 * 
 * @author 60238
 *
 */
public class Q476NumberComplement {
	public static void main(String[] args) {
		int times = 100000;
		
		Object[] argsArr = new Object[]{5, 2, 1024, Integer.MAX_VALUE - 1};
		
		TimerUtils.batchRunAll(Q476NumberComplement.class, times, argsArr);
	}

	@RunTimer
	public int findComplement0(int num) {
		int c = 1;
		while ((num >> c) != 0)
			c++;
		return (num ^ 0xffffffff) << (32 - c) >>> (32 - c);
	}
	
	@RunTimer
	public int findComplement1(int num) {
		String binStr = Integer.toBinaryString(num);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < binStr.length(); i++) {
			sb.append(binStr.charAt(i) == '0' ? '1' : '0');
		}
		try {
			return Integer.valueOf(sb.toString(), 2);
		} catch (Exception ex) {
			return -1;
		}
	}

	@RunTimer
	public int findComplement2(int num) {
		return num ^ (Integer.highestOneBit(num) << 1) - 1;
	}

	@RunTimer
	public int findComplement3(int num) {
		int copy = num, i = 0;
		while (copy != 0) {
			copy >>= 1;
			num ^= (1 << i++);
		}
		return num;
	}

	@RunTimer
	public int findComplement4(int num) {
		int i = 0;
		int j = 0;

		while (i < num) {
			i += Math.pow(2, j);
			j++;
		}

		return i - num;
	}
	

	@RunTimer
	public int findComplement5(int num) {
		int c = 1;
		while ((num >> c) != 0)
			c++;
		return num ^ ((1<<c)-1);
	}
}
