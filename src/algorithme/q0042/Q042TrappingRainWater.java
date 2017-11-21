package algorithme.q0042;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 42. Trapping Rain Water
 * 
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * 
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In
 * this case, 6 units of rain water (blue section) are being trapped. Thanks
 * Marcos for contributing this image!
 * 
 * 
 * https://leetcode.com/problems/trapping-rain-water/solution/
 * 
 * @author 60238
 *
 */
public class Q042TrappingRainWater {
	static Random rd = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		int times = 1000000;

		Object[] argsArr = new Object[10];
		int size = 20, top = 20;
		for (int i = 0; i < argsArr.length; i++) {
			argsArr[i] = generateheight(size, top);
			System.out.println(Arrays.toString((int[]) argsArr[i]));
		}
		TimerUtils.batchRunAll(Q042TrappingRainWater.class, times, argsArr);
	}

	private static int[] generateheight(int size, int top) {
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rd.nextInt(top);
		}
		return arr;
	}

	@RunTimer
	public int trap1(int[] height) {
		if (height == null || height.length <= 2)
			return 0;

		int volume = 0;

		for (int i = 1; i < height.length - 1; i++) { // 跳过第一列及最后一列
			int highLeft = -1;
			int highRight = -1;
			int count = 1; // 满足条件的墙计数器
			boolean left = false, right = false; // 标记左侧、右侧是否有更高的墙
			for (int j = i - 1; j >= 0; j--) { // 搜寻左侧墙是否有比当前值大的
				if (height[j] == height[i]) // 若在遇到高墙之前先遇到左侧有相等的，说明已计算过体积，不再重复计算
					break;
				if (height[j] > height[i]) {
					left = true;
					highLeft = j;
					break; // 找到更高的墙则停止
				}
				count++; // 不是更高墙则计数器加1
			}
			if (!left)
				continue; // 若左侧没有更高的墙，则跳过

			for (int j = i + 1; j < height.length; j++) { // 搜寻右侧墙是否有比当前值大的
				if (height[j] > height[i]) {
					right = true;
					highRight = j;
					break; // 找到更高的墙则停止
				}
				count++;
			}

			if (!right)
				continue; // 若右侧没有更高的墙，则跳过

			// 当左右侧都找到更高的墙时，计算分体积块
			int lower = Math.min(height[highLeft], height[highRight]);
			volume += count * (lower - height[i]);

			// System.out.print("volInc:\t" + count * (lower - height[i]));
			// System.out.print("\t|| height[" + i + "]:\t" + height[i]);
			// System.out.print("\t|| highLeft:\t" + highLeft);
			// System.out.print("\t|| highRight:\t" + highRight);
			// System.out.println("\t|| count:\t" + count);

		}

		return volume;
	}

	@RunTimer
	public int trap2(int[] height) {
		int ans = 0;
		int size = height.length;
		for (int i = 1; i < size - 1; i++) {
			int max_left = 0, max_right = 0;
			for (int j = i; j >= 0; j--) { // Search the left part for max bar
											// size
				max_left = Math.max(max_left, height[j]);
			}
			for (int j = i; j < size; j++) { // Search the right part for max
												// bar size
				max_right = Math.max(max_right, height[j]);
			}
			ans += Math.min(max_left, max_right) - height[i];
		}
		return ans;
	}

	@RunTimer
	public int trap3(int[] height) {
		if (height == null)
			return 0;
		int ans = 0;
		int size = height.length;
		int[] left_max = new int[size], right_max = new int[size];
		left_max[0] = height[0];
		for (int i = 1; i < size; i++) {
			left_max[i] = Math.max(height[i], left_max[i - 1]);
		}
		right_max[size - 1] = height[size - 1];
		for (int i = size - 2; i >= 0; i--) {
			right_max[i] = Math.max(height[i], right_max[i + 1]);
		}
		for (int i = 1; i < size - 1; i++) {
			ans += Math.min(left_max[i], right_max[i]) - height[i];
		}
		return ans;
	}

	@RunTimer
	public int trap4(int[] height) {
		int ans = 0, current = 0;
		Stack<Integer> st = new Stack<>();
		while (current < height.length) {
			while (!st.empty() && height[current] > height[st.peek()]) {
				int top = st.pop();

				if (st.empty())
					break;
				int distance = current - st.peek() - 1;
				int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
				ans += distance * bounded_height;
			}
			st.push(current++);
		}
		return ans;
	}

	@RunTimer
	public int trap5(int[] height) {
		int l = 0;
		int r = height.length - 1;
		int max = 0;
		int leftmax = 0;
		int rightmax = 0;

		while (l <= r) {
			leftmax = Math.max(leftmax, height[l]);
			rightmax = Math.max(rightmax, height[r]);
			if (leftmax < rightmax) {
				max += (leftmax - height[l]); // leftmax is smaller than
												// rightmax, so the
												// (leftmax-A[a]) water can be
												// stored
				l++;
			} else {
				max += (rightmax - height[r]);
				r--;
			}
		}
		return max;
	}

	@RunTimer
	public int trap6(int[] height) {
		int i = 0, j = height.length - 1, result = 0, plank = 0;
		while (i <= j) {
			plank = plank < Math.min(height[i], height[j]) ? Math.min(height[i], height[j]) : plank;
			result = height[i] >= height[j] ? result + (plank - height[j--]) : result + (plank - height[i++]);
		}
		return result;
	}

	@RunTimer
	public int trap7(int[] height) {
		if (height.length < 3)
			return 0;

		int ans = 0;
		int l = 0, r = height.length - 1;

		// find the left and right edge which can hold water
		while (l < r && height[l] <= height[l + 1])
			l++;
		while (l < r && height[r] <= height[r - 1])
			r--;

		while (l < r) {
			int left = height[l];
			int right = height[r];
			if (left <= right) {
				// add volum until an edge larger than the left edge
				while (l < r && left >= height[++l]) {
					ans += left - height[l];
				}
			} else {
				// add volum until an edge larger than the right volum
				while (l < r && height[--r] <= right) {
					ans += right - height[r];
				}
			}
		}
		return ans;
	}
}
