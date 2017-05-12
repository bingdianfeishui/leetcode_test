package algorithme.q0001;

import java.util.Arrays;
import java.util.HashMap;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 1. Two Sum
 * 
 	Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	
	You may assume that each input would have exactly one solution, and you may not use the same element twice.
	
	Example:
	Given nums = [2, 7, 11, 15], target = 9,
	
	Because nums[0] + nums[1] = 2 + 7 = 9,
	return [0, 1].
 * @see https://leetcode.com/problems/two-sum/#/description
 * @author 60238
 *
 */
public class Q001TwoSum {
	public static void main(String[] args) {
		int times = 1000000;

		Object[][] argsArr = new Object[][] { 
			new Object[] { new int[] { 1, 3, 6, 8, 9, 15, 10 }, 10 },
			new Object[] { new int[] { 2, 7, 11, 1, 3, 15 }, 9 }
		};

		TimerUtils.batchRunAll(Q001TwoSum.class, times, argsArr);
	}

	//排序后从两头找和，然后根据元素值循环找index
	@RunTimer
	public int[] twoSum(int[] nums, int target) {
		int[] temNum = nums.clone();
		int length = nums.length;
		Arrays.sort(temNum);
		int min = 0, max = length - 1;
		while (true) {
			int count = temNum[min] + temNum[max];
			if (count > target)
				max--;
			else if (count < target)
				min++;
			else
				break;
		}
		int[] result = new int[2];
		for (int i = 0, j = 0; i < length; i++) {
			if (temNum[min] == nums[i] || temNum[max] == nums[i]) {
				result[j] = i;
				j++;
			}
		}
		return result;

	}

	@RunTimer
	public int[] twoSum1(int[] nums, int target) {
		for (int first = 0; first < nums.length - 1; first++) {
			 if(nums[first] > target) continue;
			for (int second = first + 1; second < nums.length; second++) {
				 if(nums[second] > target) continue;
				if ((nums[first] + nums[second]) == target) {
					return new int[] { first, second };
				}
			}
		}
		return null;
	}



	@RunTimer
	public int[] twoSum2(int[] nums, int target) {
		HashMap<Integer, Integer> tracker = new HashMap<Integer, Integer>();
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			if (tracker.containsKey(nums[i])) {
				int left = tracker.get(nums[i]);
				return new int[] { left, i };
			} else {
				tracker.put(target - nums[i], i);
			}
		}
		return new int[2];

	}
	
	@RunTimer
	public int[] twoSum3(int[] nums, int target) {
    	if(nums == null)
    		return null;
    	int[] nums2 = Arrays.copyOf(nums, nums.length);
    	Arrays.sort(nums2);
    	int a = 0, b = 0;
    	int start = 0, end = nums2.length-1;
    	//find two nums
    	while(start<end){
    		int sum = nums2[start] + nums2[end];
    		if(sum < target)
    			start++;
    		else if(sum > target)
    			end--;
    		else{
    			a = nums2[start]; b = nums2[end];
    			break;
    		}
    	}
    	//find the index of two numbers
    	int[] res = new int[2];
    	for(int i = 0; i < nums.length; i++){
    		if(nums[i] == a){
    			res[0] = i;
    			break;
    		}
    	}
    	if(a != b){
    		for(int i = 0; i < nums.length; i++){
	    		if(nums[i] == b){
	    			res[1] = i;
	    			break;
	    		}
	    	}
    	} else{
    		for(int i = 0; i < nums.length; i++){
	    		if(nums[i] == b && i != res[0]){
	    			res[1] = i;
	    			break;
	    		}
	    	}
    	}
    	
    	return res;
    }


}
