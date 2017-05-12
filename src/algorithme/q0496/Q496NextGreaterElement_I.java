package algorithme.q0496;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;


/**
 * 496. Next Greater Element I
 * 
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1¡¯s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.
 * @author Lee
 *
 */
public class Q496NextGreaterElement_I {

	public static void main(String[] args) {
		int times = 100000;
		Object[][] argsArr = new Object[][] {
				{ new int[] { 4, 1, 2 }, new int[] { 1, 3, 4, 2, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 } },
				{ new int[] { 2, 4 }, new int[] { 1, 2, 3, 4 } } };

		TimerUtils.batchRunAll(Q496NextGreaterElement_I.class, times, argsArr);
	}

	
	

	
	@RunTimer
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++)
			map.put(nums[i], i);

		int[] arr = new int[findNums.length];
		int c = 0;
		for (int num : findNums) {
			Integer index = map.get(num);
			for (int i = index + 1; i < nums.length; i++) {
				if (nums[i] > num) {
					arr[c++] = nums[i];
					index = null;
					break;
				}
			}
			if (index != null)
				arr[c++] = -1;
		}
		return arr;
	}

	@RunTimer
	public int[] nextGreaterElement1(int[] findNums, int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();

		for (int num : nums) {
			while (!stack.isEmpty() && stack.peek() < num) {
				map.put(stack.pop(), num);
			}
			stack.push(num);
		}

		int[] res = new int[findNums.length];
		for (int i = 0; i < findNums.length; i++) {
			res[i] = map.getOrDefault(findNums[i], -1);
		}
		return res;
	}

	@RunTimer
	public int[] nextGreaterElement2(int[] findNums, int[] nums) {

		int[] ar = new int[findNums.length];

		for (int i = 0; i < findNums.length; i++) {
			int a = findnum(findNums[i], nums);
			if (a == -1)
				ar[i] = -1;
			else
				ar[i] = nums[a];
		}
		return ar;
	}

	public static int findnum(int s, int ar[]) {
		int startIndex = findIndex(s, ar);
		// int index=0;
		if (startIndex == ar.length - 1)
			return -1;

		// int j;

		for (int i = startIndex, j = i + 1; j < ar.length; j++) {
			if (ar[j] > s)
				return j;
		}
		return -1;
	}

	private static int findIndex(int s, int ar[]) {
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] == s)
				return i;
		}
		return -1;
	}
	
	@RunTimer
	public int[] nextGreaterElement3(int[] findNums, int[] nums) {
		int[] arr = new int[findNums.length];
		int c = 0;
		for (int j = 0; j < findNums.length; j++) {
			int index = -1;
			for (int i = 0; i < nums.length; i++){
				if(nums[i] == findNums[j]){
					index = i;
					break;
				}
			}
			
			for (int i = index + 1; i < nums.length; i++) {
				if (nums[i] > findNums[j]) {
					arr[c++] = nums[i];
					index = -1;
					break;
				}
			}
			if (index != -1)
				arr[c++] = -1;
		}
		return arr;
	}
	
	@RunTimer
	public int[] nextGreaterElement4(int[] findNums, int[] nums) {
		int[] arr = new int[findNums.length];
		int c = 0;
		for (int num : findNums) {
			int index = -1;
			for (int i = 0; i < nums.length; i++){
				if(nums[i] == num){
					index = i;
					break;
				}
			}
			
			for (int i = index + 1; i < nums.length; i++) {
				if (nums[i] > num) {
					arr[c++] = nums[i];
					index = -1;
					break;
				}
			}
			if (index != -1)
				arr[c++] = -1;
		}
		return arr;
	}
	
	@RunTimer
	public int[] nextGreaterElement5(int[] findNums, int[] nums) {
		Stack<Integer> stack = new Stack<>();
        int[] arr = new int[findNums.length];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            while(!stack.isEmpty() && nums[i] > stack.peek()){
                map.put(stack.pop(), nums[i]);
            }
            stack.push(nums[i]);
        }
        for(int i = 0; i < findNums.length; i++){
            arr[i] = map.getOrDefault(findNums[i], -1);
        }
        return arr;
    }
}
