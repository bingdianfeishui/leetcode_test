package algorithme.q0520;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

public class DetectCapital {

	public static void main(String[] args) {
		int times = 100000;
		Object[] words = new Object[]{
				 "Hello", "alaska", "D", "peaCe"
		};

		TimerUtils.batchRunAll(DetectCapital.class, times, words);
	}
	
	@RunTimer
	public boolean detectCapitalUse(String word) {
		int num = 0;
		char[] ar = word.toCharArray();
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] < 'a') // capital����
				num++;
		}
		return num == 0 || num == ar.length || (num == 1 && ar[0] < 'a');
	}
	
	@RunTimer
	public boolean detectCapitalUse1(String word) {
		int num = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) < 'a') // capital����
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
			if (ar[1] <= 'Z') { // ȫ��д
				for (int i = 2; i < ar.length; i++) {
					if (ar[i] > 'Z') {
						return false;
					}
				}
			} else { // ������ĸ��д
				for (int i = 2; i < ar.length; i++) {
					if (ar[i] <= 'Z') {
						return false;
					}
				}
			}
		} else { // ȫСд
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
