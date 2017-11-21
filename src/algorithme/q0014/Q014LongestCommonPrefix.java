package algorithme.q0014;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 14. Longest Common Prefix Write a function to find the longest common prefix
 * string amongst an array of strings.
 * 
 * @author 60238
 *
 */
public class Q014LongestCommonPrefix {
	public static void main(String[] args) {
		int times = 100000;
		Object[] argsArr = new Object[] { 
				new String[] { "ab2c99999999999999999999", "abcxx999999999999999x", "abcde", "abc6789" },
				new String[] { "abcdefx999999999999999", "abcxx999999999999999x999999999999999x", "abcde", "abc6789" },
				new String[] { "aca", "cba" }, 
				new String[] { "abc" }, 
				new String[] {} };

		TimerUtils.batchRunAll(Q014LongestCommonPrefix.class, times, argsArr);
	}

	@RunTimer
	public String longestCommonPrefix(String[] strs) {
		// 先拿到最小长度
		if (strs.length == 0)
			return "";
		if (strs.length == 1)
			return strs[0];

		int minLength = Integer.MAX_VALUE, index = -1;
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].length() < minLength) {
				minLength = strs[i].length();
				index = i;
			}
		}
		if (minLength == 0)
			return "";
		int len = minLength;
		for (int i = 0; i < strs.length; i++) {
			int same = 0;
			for (int j = 0; j < len; j++) {
				if (strs[i].charAt(j) == strs[index].charAt(j))
					same++;
				else
					break;
			}
			len = Math.min(len, same);
		}
		return strs[index].substring(0, len);
	}

	@RunTimer
	public String longestCommonPrefix2(String[] strs) {
		if (strs.length == 0)
			return "";
		if (strs.length == 1)
			return strs[0];

		String first = strs[0];
		int len = first.length();
		for (int i = 1; i < strs.length; i++) {
			int same = 0, min = Math.min(len, strs[i].length());
			for (int j = 0; j < min; j++) {
				if (first.charAt(j) != strs[i].charAt(j))
					break;
				same++;
			}
			if (same == 0)
				return "";
			len = Math.min(len, same);
		}

		return first.substring(0, len);
	}
	
	@RunTimer
	public String longestCommonPrefix3(String[] strs) {
        int n = strs.length;
        String res = "";
        if(n == 0)return res;
        for(int pos = 0; pos < strs[0].length(); pos++)//最长前缀的长度不超过strs[0].size()，逐个字符的比较
        {
            for(int k = 1; k < n; k++)//strs[0]的第pos个字符分别和strs[1...n-1]的第pos个字符比较
            {
                if(strs[k].length() == pos || strs[k].charAt(pos) != strs[0].charAt(pos))
                    return res;
            }
            res += strs[0].charAt(pos);
        }
        return res;
	}
}
