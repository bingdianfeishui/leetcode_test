package algorithme.q0409;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 409. Longest Palindrome Given a string which consists of lowercase or
 * uppercase letters, find the length of the longest palindromes that can be
 * built with those letters.
 * 
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 
 * Note: Assume the length of given string will not exceed 1,010.
 * 
 * Example:
 * 
 * Input: "abccccdd"
 * 
 * Output: 7
 * 
 * Explanation: One longest palindrome that can be built is "dccaccd", whose
 * length is 7.
 * 
 * @author Lee
 *
 */
public class Q409LongestPalindrome {
	public static void main(String[] args) {
		int times = 100000;

		Object[] argsArr = new Object[] { 
				null,
				"",
				"aaa", 
				"bbbb",
				"abccccdd", 
				"abAAAc",
				"zabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxy",
				"kztakrekvefgchersuoiuatzlmwynzjhdqqftjcqmntoyckqfawikkdrnfgbwtdpbkymvwoumurjdzygyzsbmwzpcxcdmmpwzmeibligwiiqbecxwyxigikoewwrc"
		};

		TimerUtils.batchRunAll(Q409LongestPalindrome.class, times, argsArr);
	}

	//No. 95 testcase 出错，不知道为毛
	@RunTimer
	public int longestPalindrome(String s) {
		if (s == null || s.length() == 0) return 0;

		Map<Character, Integer> map = new HashMap<>();
		int key = 0;
		for(Character c: s.toCharArray()){
			key ^= c;
			if(map.containsKey(c)) 
				map.put(c, map.get(c) + 1);
			else 
				map.put(c, 1);
		}
		
		int len = 0;
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			len += (entry.getValue()>>1<<1);
		}
		
		return key != 0 ? len + 1 : len;
	}
	
	@RunTimer
	public int longestPalindrome1(String s) {
		if (s == null || s.length() == 0) return 0;

		Map<Character, Integer> map = new HashMap<>();
		int len = 0;
		for(Character c: s.toCharArray()){
			if(map.containsKey(c)) {
				Integer n = map.get(c) + 1;
				if(n % 2 == 0){
					len += 2;
				}
				map.put(c, n);
			}
			else {
				map.put(c, 1);
			}
		}
		
		return len<s.length() ? len + 1 : len;
	}
	
	@RunTimer
	public int longestPalindrome2(String s) {
		if (s == null || s.length() == 0) return 0;
    	Set<Character> set = new HashSet<>();
    	int count = 0;
    	char[] chars = s.toCharArray();
    	for(char c: chars) {
    		if(set.remove(c)) {
    			count++;
    		} else {
    			set.add(c);
    		}
    	}
    	return set.isEmpty() ? count  * 2 : count * 2 + 1;
	}
	
	@RunTimer
	public int longestPalindrome3(String s) {
        if(s == null || s.length() == 0) return 0;
        int[] lowcase = new int[26];
        int[] upcase = new int[26];
        for(char c : s.toCharArray()){
        	if(c <= 'Z'){	//upper case
        		upcase[c-'A'] ++;
        	}else{	//lower case
        		lowcase[c-'a'] ++;
        	}
        }
        int count = 0;
        for (int i = 0; i < upcase.length; i++) {
			count += (lowcase[i]>>1)<<1;
			count += (upcase[i]>>1)<<1;
		}
        return count < s.length() ? count + 1 : count;
	}
	
	@RunTimer
	public int longestPalindrome4(String s) {
		if (s == null || s.length() == 0) return 0;
        int[] lowcase = new int[26];
        int[] upcase = new int[26];
        int count = 0;
        for(char c : s.toCharArray()){
        	if(c <= 'Z'){	//upper case
        		if(upcase[c - 'A'] != 0 ){
        			upcase[c - 'A'] = 0;
        			count += 2;
        		}
        		else upcase[c - 'A']++;
        	}else{	//lower case
        		if(lowcase[c - 'a'] != 0 ){
        			lowcase[c - 'a'] = 0;
        			count += 2;
        		}
        		else lowcase[c - 'a']++;
        	}
        }
        return count < s.length() ? count + 1 : count;
	}
	
	@RunTimer
	public int longestPalindrome5(String s) {
		if (s == null || s.length() == 0) return 0;
	    int[] letters = new int[58];
	    int longest = 0;
		for (char c : s.toCharArray()) letters[c - 'A']++;
		for (int l : letters) longest += ((l % 2 == 0) ? l : l - 1);
		return longest < s.length() ? longest + 1 : longest;
	}
	
}
