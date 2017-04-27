package algorithme.q0001;

import com.lee.timer.TimerUtils;

public class Q001TwoSum {
	public static void main(String[] args) {
		int times = 100000;

		Object[] argsArr = new Object[] {
				new String[]{"abcd", "aebdc"},
				new String[]{"qwer", "1erwq"},
		};

		TimerUtils.batchRunAll(Q001TwoSum.class, times, argsArr);
	}
}
