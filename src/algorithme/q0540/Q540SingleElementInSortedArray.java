package algorithme.q0540;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

public class Q540SingleElementInSortedArray {
	public static void main(String[] args) {
		Object[] agrsArr = { 
				new int[] { 1, 1, 2, 2, 5 }, 
				new int[] { 2, 2, 5, 9, 9 }, 
				new int[] { 3, 3, 5 },
				new int[] { 5, 7, 7, 8, 8 } };
		TimerUtils.batchRunAll(Q540SingleElementInSortedArray.class, 100000, agrsArr);
		TimerUtils.batchRun(Q540SingleElementInSortedArray.class, "singleNonDuplicate1", 1, agrsArr);
	}

	@RunTimer
	public int singleNonDuplicate(int[] nums) {
		for (int i = 0; i < nums.length / 2; i++) {
			if (nums[2 * i] < nums[2 * i + 1])
				return nums[2 * i];
		}
		return nums[nums.length - 1];
	}

	@RunTimer
	public int singleNonDuplicate1(int[] nums) {
		if (nums.length == 1)
			return nums[0];

		int lo = 0, hi = nums.length - 1;
		while (lo < hi) {
			int mid = (hi + lo) / 2;
			if (mid % 2 == 1 && nums[mid] == nums[mid - 1])
				lo = mid + 1;
			else if ( mid % 2 == 0 && nums[mid] == nums[mid + 1])
				lo = mid + 1;
			else
				hi = mid;
		}
		return nums[hi];
	}
	@RunTimer
	public int singleNonDuplicate2(int[] nums) {
		int lo = 0, hi = nums.length - 1;
        
        while(lo < hi){
            int mid = (lo + hi) / 2;
            if(mid % 2 == 0){
                if( nums[mid] == nums[mid + 1])
                    lo = mid + 1;
                else
                    hi = mid;
            } else{
                if( nums[mid] == nums[mid - 1])
                    lo = mid + 1;
                else
                    hi = mid;
            }
        }
        
        return nums[hi];
	}
}
