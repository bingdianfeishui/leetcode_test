package algorithme.q0448;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 448. Find All Numbers Disappeared in an Array
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
 * @author Lee
 *
 */
public class Q448FindAllNumbersDisappearedInAnArray {
	public static void main(String[] args) {
		int times = 100000;
		Object[] argsArr = new Object[] { 
				new int[] { 1, 1 }, 
				new int[] { 4, 3, 2, 7, 8, 2, 3, 1 },
				new int[] { 2, 5, 5, 5, 5 }, };
		TimerUtils.batchRunAll(Q448FindAllNumbersDisappearedInAnArray.class, times, argsArr);
	}

	//先排序后挨个判断
	@RunTimer
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> list = new ArrayList<>();
		if (nums == null || nums.length <= 1)
			return list;

		Arrays.sort(nums);
		int len = nums.length;
		int min = nums[0];
		int max = nums[len - 1];
		while (min > 1)
			list.add(--min);

		for (int i = 1; i < len; i++) {
			int pre = nums[i - 1];
			while (nums[i] > ++pre)
				list.add(pre);
		}

		while (len > max)
			list.add(++max);
		return list;
	}

	//通过HashSet判断
	@RunTimer
	public List<Integer> findDisappearedNumbers1(int[] nums) {
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for (int n : nums)
			set.add(n);
		for (int i = 1; i <= nums.length; i++) {
			if (!set.contains(i))
				list.add(i);
		}
		return list;
	}

	//通过一个boolean标志位数组判断
	@RunTimer
	public List<Integer> findDisappearedNumbers2(int[] nums) {
		List<Integer> list = new ArrayList<>();
		boolean[] flag = new boolean[nums.length];
		for (int i = 0; i < nums.length; i++)
			flag[nums[i] - 1] = true;

		for (int i = 0; i < flag.length; i++)
			if (!flag[i])
				list.add(i + 1);
		return list;
	}

	@RunTimer
	public List<Integer> findDisappearedNumbers3(int[] nums) {
		int[] temp = Arrays.copyOf(nums, nums.length);
		List<Integer> list = new ArrayList<>();
		int len = temp.length;
		for (int i = 0; i < temp.length; i++){
			int index = temp[i] % (len+1);
			temp[index - 1] += (len+1);
		}

		for (int i = 0; i < temp.length; i++)
			if (temp[i] <= len)
				list.add(i + 1);
		return list;
	}
	
	@RunTimer
	public List<Integer> findDisappearedNumbers4(int[] nums) {
		int[] temp = Arrays.copyOf(nums, nums.length);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < temp.length; i++){
			int index = Math.abs(temp[i]);
			if(temp[index - 1] > 0)
				temp[index - 1] = -temp[index - 1];
		}

		for (int i = 0; i < temp.length; i++)
			if (temp[i] > 0)
				list.add(i + 1);
		return list;
	}
	
	@RunTimer
	public List<Integer> findDisappearedNumbers5(int[] nums) {
		int[] temp = Arrays.copyOf(nums, nums.length);
		List<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<temp.length; i++){
            int num = temp[i];
           while(num!=temp[num-1]){
               int t = temp[num-1];
               temp[num-1] = temp[i];
               temp[i] = t;
              num = temp[i];
           }
        }
//        System.out.println(Arrays.toString(temp));
    for(int i=0; i<temp.length; i++){
        if(temp[i]!=i+1) list.add(i+1);
    }
    return list;
}
}
