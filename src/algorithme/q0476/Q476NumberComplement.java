package algorithme.q0476;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

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
