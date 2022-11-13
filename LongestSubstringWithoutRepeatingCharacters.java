public class LongestSubstringWithoutRepeatingCharacters {

    // Efficient implementation using sliding window.
    public int lengthOfLongestSubstring(String s) {

        int longestSubstrLen;
        int strlen = s.length();

        if(strlen > 1) {
            int currentSubstrStart = 0, currentSubstrEnd = 1;
            int currentSubstrLen = 1;
            longestSubstrLen = 1;
            Integer[] lastOccurrences = new Integer[128];

            lastOccurrences[s.charAt(0)] = 0;

            while(currentSubstrEnd < strlen) {

                char currentChar = s.charAt(currentSubstrEnd);
                Integer prevOccur = lastOccurrences[currentChar];

                if(prevOccur != null && prevOccur >= currentSubstrStart) {
                    currentSubstrLen = currentSubstrEnd - currentSubstrStart;
                    longestSubstrLen = Math.max(currentSubstrLen, longestSubstrLen);

                    currentSubstrStart = prevOccur + 1;

                }

                lastOccurrences[currentChar] = currentSubstrEnd;
                currentSubstrEnd++;
            }

            currentSubstrLen = currentSubstrEnd - currentSubstrStart;
            longestSubstrLen = Math.max(currentSubstrLen, longestSubstrLen);
        }
        else {
            longestSubstrLen = strlen;
        }

        return longestSubstrLen;
    }

}
