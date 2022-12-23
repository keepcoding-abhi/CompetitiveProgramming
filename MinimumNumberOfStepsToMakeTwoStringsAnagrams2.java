public class MinimumNumberOfStepsToMakeTwoStringsAnagrams2 {

    // Count the difference of the occurrences of each character in the two strings and add them up.
    // Time complexity : O(n), Space complexity : O(1)
    public int minSteps(String s, String t) {

        int[] charCounts = new int[26];

        for(int index = 0, sLen = s.length() ; index < sLen ; index++) {
            charCounts[s.charAt(index) - 'a']++;
        }

        for(int index = 0, tLen = t.length() ; index < tLen ; index++) {
            charCounts[t.charAt(index) - 'a']--;
        }

        int stepsRequired = 0;

        for(int index = 0 ; index < 26 ; index++) {
            stepsRequired += Math.abs(charCounts[index]);
        }

        return stepsRequired;
    }

}
