package algorithme.q0485;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

public class Q485MaxConsecutiveOnes {
	public static void main(String[] args) {
		int times = 100000;

		Object[] argsArr = new Object[] { 
				new int[] { 1, 1, 1 }, 
				new int[] { 0, 1, 1, 1 }, 
				new int[] { 0, 1, 1, 1, 0 },
				new int[] { 1, 1, 1, 0, 0 }, 
				new int[] { 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1 },
				new int[] { 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1 }, };

		TimerUtils.batchRunAll(Q485MaxConsecutiveOnes.class, times, argsArr);
	}
	
	
	@RunTimer
	public int findMaxConsecutiveOnes1(int[] nums) {
		int begin = -1;
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				max = Math.max(max, i - begin -1);
				begin = i;
			}

		}
		max = Math.max(max, nums.length - begin -1);

		return max;
	}

	@RunTimer
	public int findMaxConsecutiveOnes2(int[] nums) {
		int max = 0;
		int tmp = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 1)
				tmp++;
			else
				tmp = 0;

			if (tmp > max)
				max = tmp;
		}
		return max;
	}

	@RunTimer
	public int findMaxConsecutiveOnes3(int[] nums) {
		// 考虑从两侧同时遍历数组 use the idea just like quicksort
		int leftNum = 0;
		int rightNum = 0;
		int maxConsecutive = 0;
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			leftNum = 0;
			rightNum = 0;
			while (left < right && nums[left] == 0) {
				left++;
			}
			while (left < right && nums[right] == 0) {
				right--;
			}
			while (left < right && nums[left] == 1) {
				left++;
				leftNum++;
			}
			while (left < right && nums[right] == 1) {
				right--;
				rightNum++;
			}
			maxConsecutive = maxConsecutive > (leftNum > rightNum ? leftNum : rightNum) ? maxConsecutive
					: (leftNum > rightNum ? leftNum : rightNum);
		}
		if (left == right && nums[left] == 1) {
			leftNum = leftNum + 1;
		}
		maxConsecutive = maxConsecutive > leftNum ? maxConsecutive : leftNum;
		return maxConsecutive;
	}
	
	@RunTimer
	public int findMaxConsecutiveOnes4(int[] nums) {
        int len = nums.length;
        int prevIndex = -1;
        int maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                int diff = i - prevIndex -1;
                maxLength = Math.max(maxLength, diff);
                prevIndex = i;
            }
        }
        maxLength = Math.max(maxLength, len - prevIndex - 1);
        return maxLength;
    }
	
}
