package algorithme.q0500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.lee.timer.RunTimer;
import com.lee.timer.TimerUtils;

public class Q500KeyboardRow {
	public static void main(String[] args) {
		int times = 100000;
		Object[] wordsArr = new Object[]{
				new String[] { "Hello", "Alaska", "Dad", "Peace" },
				new String[] { "A", "D", "avc", "ASdf" }
		};

		TimerUtils.batchRunAll(Q500KeyboardRow.class, times, wordsArr);
	}

	@RunTimer
	public String[] findWords0(String[] words) {
		Map<Character, Integer> map = initMap();
		List<String> list = new ArrayList<>();
		for (String str : words) {
			char[] cs = str.trim().toUpperCase().toCharArray();
			Integer index = map.get(cs[0]);
			for (char c : cs) {
				if (!map.get(c).equals(index)) {
					index = -1;
					break;
				}
			}
			if (index != -1)
				list.add(str);
		}
		String[] arr = new String[list.size()];
		return list.toArray(arr);
	}
	private Map<Character, Integer> initMap() {
		Map<Character, Integer> map = new HashMap<>();
		String[] rows = { "QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM" };
		for (int i = 0; i < rows.length; i++) {
			for (char c : rows[i].toCharArray()) {
				map.put(c, i);
			}
		}
		return map;
	}

	@RunTimer
	public String[] findWords2(String[] words) {
		List<String> list = new ArrayList<>();
		int[] mapIndex = { 2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3 };
		outer: for (String str : words) {
			int tmp = mapIndex[Character.toLowerCase(str.charAt(0)) - 'a'];
			for (char c : str.toCharArray()) {
				if (tmp != mapIndex[Character.toLowerCase(c) - 'a'])
					continue outer;
			}
			list.add(str);
		}
		return list.toArray(new String[0]);
	}

	@RunTimer
	public String[] findWords3(String[] words) {
		return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*"))
				.toArray(String[]::new);
	}
	
	
	@RunTimer
    public String[] findWords4(String[] words) {
						////     zyxwvutsrqponmlkjihgfedcba
    	final int ROW1 = 0b00000001010110111100000100010000;
    	final int ROW2 = 0b00000000000001000000111011101001;
    	final int ROW3 = 0b00000010101000000011000000000110;
        String [] validWords = new String[words.length];
        int validWordsFound = 0;
        int wordLetterIndexes = 0;
        for (String word: words) {
            wordLetterIndexes = getLetterIndexes(word);
            if ((wordLetterIndexes & ROW1) == wordLetterIndexes
                || (wordLetterIndexes & ROW2) == wordLetterIndexes
                || (wordLetterIndexes & ROW3) == wordLetterIndexes) {
                    validWords[validWordsFound++] = word;
                }
        }
        return Arrays.copyOf(validWords, validWordsFound);
    }
    
    private int getLetterIndexes(String word) {
        int letters = 0;
        char c;
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            letters |= 1 << ((c < 'a') ? c - 'A' : c - 'a');
//            letters |= 1 << (Character.toLowerCase(c) - 'a');
        }
        return letters;
    }
}
