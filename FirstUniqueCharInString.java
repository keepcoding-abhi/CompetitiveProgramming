public class FirstUniqueCharInString {
}

//1) Using java.util.HashMap to store the frequency.

class Solution {
    public int firstUniqChar(String s) {
        int lengthOfString = s.length();

        HashMap<Character, Integer> charAndFrequencies = new HashMap<Character, Integer>();

        for(int index = 0 ; index < lengthOfString ; index++) {

            char currentChar = s.charAt(index);
            charAndFrequencies.put(currentChar, charAndFrequencies.getOrDefault(currentChar, 0) + 1);
        }

        int indexOfFirstNonRepeatingChar = -1;
        for(int index = 0 ; index < lengthOfString ; index++) {

            char currentChar = s.charAt(index);
            if(charAndFrequencies.get(currentChar) == 1) {
                indexOfFirstNonRepeatingChar = index;
                break;
            }
        }

        return indexOfFirstNonRepeatingChar;
    }
}

//2) Using array to store the frequency.

class Solution {
    public int firstUniqChar(String s) {
        int lengthOfString = s.length();


        int[] frequencyCounts = new int[26];

        for(int index = 0 ; index < lengthOfString ; index++) {

            char currentChar = s.charAt(index);
            frequencyCounts[currentChar - 'a'] += 1;
        }

        int indexOfFirstNonRepeatingChar = -1;

        for(int index = 0 ; index < lengthOfString ; index++) {
            char currentChar = s.charAt(index);

            if(frequencyCounts[currentChar - 'a'] == 1) {
                indexOfFirstNonRepeatingChar = index;
                break;
            }
        }

        return indexOfFirstNonRepeatingChar;
    }
}