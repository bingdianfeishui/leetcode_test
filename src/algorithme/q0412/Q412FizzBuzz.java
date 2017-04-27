package algorithme.q0412;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

public class Q412FizzBuzz {
	public static void main(String[] args) {
		int times = 100000;
		Object[] argsArr = new Object[] { 5, 31 };

		TimerUtils.batchRunAll(Q412FizzBuzz.class, times, argsArr);
	}

	@RunTimer
	public List<String> fizzBuzz(int n) {
		String[] arr = new String[n];
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0) {
				if (i % 5 == 0)
					arr[i - 1] = "FizzBuzz";
				else
					arr[i - 1] = "Fizz";
			} else if (i % 5 == 0)
				arr[i - 1] = "Buzz";
			else
				arr[i - 1] = String.valueOf(i);
		}
		return Arrays.asList(arr);
	}

	@RunTimer
	public List<String> fizzBuzz0(int n) {
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0) {
				if (i % 5 == 0)
					list.add("FizzBuzz");
				else
					list.add("Fizz");
			} else if (i % 5 == 0)
				list.add("Buzz");
			else
				list.add(String.valueOf(i));
		}
		return list;
	}

	@RunTimer
	public List<String> fizzBuzz1(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				sb.append("BuzzFizz,");
			} else if (i % 3 == 0)
				sb.append("Fizz,");
			else if (i % 5 == 0)
				sb.append("Buzz,");
			else
				sb.append(i + ",");
		}
		return Arrays.asList(sb.toString().split(","));
	}

	@RunTimer
	public List<String> fizzBuzz2(int n) {
		String[] arr = new String[n];
		int fizz = 0;
		int buzz = 0;
		for (int i = 1; i <= n; i++) {
			fizz++;
			buzz++;
			if (fizz == 3) {
				if (buzz == 5) {
					arr[i - 1] = "FizzBuzz";
					fizz = 0;
					buzz = 0;
				} else {
					arr[i - 1] = "Fizz";
					fizz = 0;
				}
			} else if (buzz == 5) {
				arr[i - 1] = "Buzz";
				buzz = 0;
			} else
				arr[i - 1] = i + "";
		}

		return Arrays.asList(arr);
	}

	@RunTimer
	public List<String> fizzBuzz3(int n) {
		List<String> list = new ArrayList<>();
		int fizz = 0;
		int buzz = 0;
		for (int i = 1; i <= n; i++) {
			fizz++;
			buzz++;
			if (fizz == 3 && buzz == 5) {
				list.add("FizzBuzz");
				fizz = 0;
				buzz = 0;
			} else if (fizz == 3) {
				list.add("Fizz");
				fizz = 0;
			} else if (buzz == 5) {
				list.add("Buzz");
				buzz = 0;
			} else
				list.add(Integer.toString(i));
		}

		return list;
	}

	@RunTimer
	public List<String> fizzBuzz4(int n) {
		final IntFunction<String> func = i -> i % 15 == 0 ? "FizzBuzz"
				: (i % 3 == 0 ? "Fizz" : (i % 5 == 0 ? "Buzz" : String.valueOf(i)));
		return IntStream.rangeClosed(1, n).mapToObj(func).collect(Collectors.toList());
	}
}
