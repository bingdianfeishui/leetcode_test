package algorithme.q0520;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

/**
 * 520. Detect Capital
 * 
 * Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False
Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.

 * @author Lee
 *
 */
public class Q520DetectCapital {

	public static void main(String[] args) {
		int times = 100000;
		Object[] words = new Object[]{
				 "Hello", "alaska", "D", "peaCe"
		};

		TimerUtils.batchRunAll(Q520DetectCapital.class, times, words);
	}
	
	@RunTimer
	public boolean detectCapitalUse(String word) {
		int num = 0;
		char[] ar = word.toCharArray();
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] < 'a') // capital数量
				num++;
		}
		return num == 0 || num == ar.length || (num == 1 && ar[0] < 'a');
	}
	
	@RunTimer
	public boolean detectCapitalUse1(String word) {
		int num = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) < 'a') // capital数量
				num++;
		}
		return num == 0 || num == word.length() || (num == 1 && word.charAt(0) < 'a');
	}

	@RunTimer
	public boolean detectCapitalUse2(String word) {
		if (word.length() <= 1)
			return true;

		char[] ar = word.toCharArray();
		boolean isLegal = true;
		if (ar[0] <= 'Z') {
			if (ar[1] <= 'Z') { // 全大写
				for (int i = 2; i < ar.length; i++) {
					if (ar[i] > 'Z') {
						return false;
					}
				}
			} else { // 仅首字母大写
				for (int i = 2; i < ar.length; i++) {
					if (ar[i] <= 'Z') {
						return false;
					}
				}
			}
		} else { // 全小写
			for (int i = 1; i < ar.length; i++) {
				if (ar[i] <= 'Z') {
					return false;
				}
			}
		}
		return isLegal;
	}
	
	@RunTimer
	public boolean detectCapitalUse3(String word) {
		if(word.toUpperCase().equals(word)) return true;
		if(word.toLowerCase().equals(word)) return true;
		if(word.substring(1).toLowerCase().equals(word.substring(1))) return true;
		return false;
	}
	
	@RunTimer
	public boolean detectCapitalUse4(String word) {
		return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
	}
}
