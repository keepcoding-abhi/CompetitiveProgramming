public class MinimumStepsToMakeTwoStringsAnagram {

    // Time Complexity : O(n), Space complexity : O(1)
    // Counting the number of characters in the string t which are extra, that is, not required for it to be an anagram of s.
    public int minSteps(String s, String t) {
        int[] sCharFreqs = getCharFreqs(s);
        int[] tCharFreqs = getCharFreqs(t);

        int stepsRequired = 0;
        for(int index = 0 ; index < 26 ; index++) {
            stepsRequired += Math.max(0, tCharFreqs[index] - sCharFreqs[index]);
        }

        return stepsRequired;
    }

    private int[] getCharFreqs(String word) {
        int[] charFreqs = new int[26];

        for(int index = 0, len = word.length() ; index < len ; index++) {
            charFreqs[word.charAt(index) - 'a']++;
        }

        return charFreqs;
    }

    // Using one array instead of 2. The array contains the number of time a character has occurred more than what is required
    // in an anagram.
    public int minSteps(String s, String t) {
        int[] extraCharsInT = new int[26];

        for(int index = 0, len = s.length() ; index < len ; index++) {
            extraCharsInT[t.charAt(index) - 'a']++;
            extraCharsInT[s.charAt(index) - 'a']--;
        }

        int stepsRequired = 0;
        for(int index = 0 ; index < 26 ; index++) {
            int currentCharExtraOccurrences = extraCharsInT[index];
            if(currentCharExtraOccurrences > 0) {
                stepsRequired += currentCharExtraOccurrences;
            }
        }

        return stepsRequired;
    }
}
