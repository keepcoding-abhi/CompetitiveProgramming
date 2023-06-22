import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {

    /*
    Time : O(n)
    Space : O(1)
    Saving time by using array instead of map.
     */
    public int longestPalindrome(String s) {
        int[] charFreqs = new int[52];

        int palinLen = 0;

        for(int index = 0, sLen = 0 ; index < s.length() ; index++) {
            char currentChar = s.charAt(index);

            if(currentChar <= 'Z') {
                charFreqs[currentChar - 'A']++;
            }
            else {
                charFreqs[(currentChar - 'a') + 26]++;
            }
        }

        boolean oddCharAvailable = false;

        for(int occurrences : charFreqs) {
            palinLen += ((occurrences / 2) * 2);

            if(occurrences % 2 != 0) {
                oddCharAvailable = true;
            }
        }

        if(oddCharAvailable) {
            palinLen += 1;
        }

        return palinLen;
    }

    /*
    Time : O(n)
    Space : O(1), in the worst case 52 entries in the map.
    Count the number of occurrences of each character.
     */
    public int longestPalindrome(String s) {
        Map<Character, Integer> charFreqs = new HashMap<Character, Integer>();

        int palinLen = 0;

        for(int index = 0, sLen = 0 ; index < s.length() ; index++) {
            char currentChar = s.charAt(index);

            charFreqs.put(currentChar, charFreqs.getOrDefault(currentChar, 0) + 1);
        }

        boolean oddCharAvailable = false;

        for(Map.Entry<Character, Integer> entry : charFreqs.entrySet()) {
            int occurrences = entry.getValue();

            palinLen += ((occurrences / 2) * 2);

            if(occurrences % 2 != 0) {
                oddCharAvailable = true;
            }
        }

        if(oddCharAvailable) {
            palinLen += 1;
        }

        return palinLen;
    }
}
