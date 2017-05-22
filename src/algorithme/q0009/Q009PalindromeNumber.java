package algorithme.q0009;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

public class Q009PalindromeNumber {
	public static void main(String[] args) {
		int times = 100000;

		Object[] argsArr = new Object[] { -1, 0, 9, 10, 11, 121, 113, 101050101, 1234567, Integer.MAX_VALUE,
				Integer.MIN_VALUE };
		TimerUtils.batchRunAll(Q009PalindromeNumber.class, times, argsArr);

	}

	//��ȡ�Գ�λ�ж�
	@RunTimer
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		if (x < 10)
			return true;
		int len = 1;
		int tmp = x;
		while ((tmp /= 10) != 0)
			len++;
		for (int i = 0; i < len / 2; i++) {
			if ((x / (int) Math.pow(10, i) % 10) != (x / (int) Math.pow(10, len - 1 - i) % 10))
				return false;
		}

		return true;
	}

	//��ȡ�Գ�λ���жϣ�����Math.pow(a, b)
	@RunTimer
	public boolean isPalindrome2(int x) {
		if (x < 0)
			return false;
		if (x < 10)
			return true;
		int len = 1;
		int tmp = x;
		while ((tmp /= 10) != 0)
			len++;
		for (int i = 0; i < len / 2; i++) {
			int low = 0, high = 0;
			int j = 0;
			tmp = x;
			while (j++ < i)
				tmp /= 10;
			low = tmp % 10;

			j = 0;
			tmp = x;
			while (j++ < (len - 1 - i))
				tmp /= 10;
			high = tmp % 10;

			if (low != high)
				return false;
		}

		return true;
	}

	//����Գ���Ȼ���ж��Ƿ���ȡ��������ȡ�
	@RunTimer
	public boolean isPalindrome3(int x) {
		if (x < 0)
			return false;
		if (x < 10)
			return true;
		int ret = 0;
		int tmp = x;
		while (tmp != 0) {
			ret = ret * 10 + tmp % 10;
			tmp /= 10;
		}

		return ret == x;
	}
}
