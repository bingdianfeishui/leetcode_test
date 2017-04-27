package algorithme.q0461;

import java.util.Arrays;
import java.util.List;

import com.lee.timer.BatchRunResult;
import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

public class Q461HammingDistance {
	public static void main(String[] args) {
		Object[][] argsArr = new Object[][] { 
				{ 1, 4 }, 
				{ 2345, 8792 }, 
				{ 2727346, 1948672 }, 
				{ 0, 196 },
				{ 1024, 16 }, 
				{0x7fffffff, 0x7777_7777} };
		int times = 200;
		List<BatchRunResult> list = TimerUtils.batchRunAll(Q461HammingDistance.class, times, argsArr);
		System.out.println(list);
		
		Object[] objects1 = TimerUtils.batchRun(Q461HammingDistance.class, "hammingDistance", times, argsArr);
		System.out.println(Arrays.toString(objects1));
//
//		Object[] objects2 = TimerUtils.batchRun(HammingDistance.class, "hammingDistance1", times, argsArr);
//		System.out.println(Arrays.toString(objects2));
//
//		Object[] objects3 = TimerUtils.batchRun(HammingDistance.class, "hammingDistance2", times, argsArr);
//		System.out.println(Arrays.toString(objects3));
//
//		Object[] objects4 = TimerUtils.batchRun(HammingDistance.class, "hammingDistance3", times, argsArr);
//		System.out.println(Arrays.toString(objects4));
//
//		Object[] objects5 = TimerUtils.batchRun(HammingDistance.class, "hammingDistance4", times, argsArr);
//		System.out.println(Arrays.toString(objects5));
//
//		Object[] objects6 = TimerUtils.batchRun(HammingDistance.class, "hammingDistance5", times, argsArr);
//		System.out.println(Arrays.toString(objects6));
//		
//		Object[] objects7 = TimerUtils.batchRun(HammingDistance.class, "hammingDistance6", times, argsArr);
//		System.out.println(Arrays.toString(objects7));
	}

	//My
	//异或结果转换为字符串处理
	@RunTimer
	public int hammingDistance(int x, int y) {
		int xor = x ^ y;
		if (xor == 0)
			return 0;
		char[] cs = Integer.toBinaryString(xor).toCharArray();
		int i = 0;
		for (char c : cs) {
			if (c == '1')
				i++;
		}
		return i;
	}

	//My
	//异或结果移位判断
	@RunTimer
	public int hammingDistance1(int x, int y) {
		int xor = x ^ y;
		if (xor == 0)
			return 0;
		int i = 0;
		while (xor != 0) {
			if ((xor & 1) == 1)
				i++;
			xor >>>= 1;
		}
		return i;

	}

	@RunTimer
	public int hammingDistance2(int x, int y) {
		int xor = x ^ y;
		int c = xor & 1;
		while (xor != 0)
			c += (xor >>>= 1) & 1;
		return c;
	}

	@RunTimer
	public int hammingDistance3(int x, int y) {
		int xor = x ^ y;
		int c = 0;
		for (int i = 0; i < 32; i++)
			c += (xor >>> i) & 1;
		return c;
	}

	@RunTimer
	public int hammingDistance4(int x, int y) {
		return Integer.bitCount(x ^ y);
	}

	@RunTimer
	public int hammingDistance5(int x, int y) {
		int i = x ^ y;
		i = i - ((i >>> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
		i = (i + (i >>> 4)) & 0x0f0f0f0f;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		return i & 0x3f;
	}

	@RunTimer
	public int hammingDistance6(int x, int y) {
		int num = 0;
		while (x > 0 || y > 0) {
			if ((x & 1) != (y & 1)) {
				num++;
			}
			x = x >> 1;
			y = y >> 1;
		}
		return num;
	}
}
