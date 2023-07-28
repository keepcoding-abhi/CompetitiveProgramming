import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

    /*
    Time : O(n)
    Space : O(1)

    Sliding window using direct access table.
     */
    public int lengthOfLongestSubstring(String s) {
        int longestSubstringLen = 0;

        if(s != null && s.length() > 0) {
            int sLen = s.length();
            int[] charsIndex = new int[128];
            Arrays.fill(charsIndex, -1);

            int leftIndex = 0, rightIndex = 1;
            charsIndex[s.charAt(0)] = 0;
            longestSubstringLen = 1;

            while(rightIndex < sLen) {
                char nextChar = s.charAt(rightIndex);
                int lastIndex = charsIndex[nextChar];

                if(lastIndex >= leftIndex) {
                    leftIndex = lastIndex + 1;
                }

                charsIndex[nextChar] = rightIndex;

                int currentLen = rightIndex - leftIndex + 1;
                longestSubstringLen = Math.max(longestSubstringLen, currentLen);
                rightIndex++;
            }

        }

        return longestSubstringLen;
    }

    /*
    Time : O(n)
    Space :  O(n)

    Sliding window using hashmap.
     */
    public int lengthOfLongestSubstring(String s) {
        int longestSubstringLen = 0;

        if(s != null && s.length() > 0) {
            int sLen = s.length();
            Map<Character, Integer> charsIndex = new HashMap<Character, Integer>();

            int leftIndex = 0, rightIndex = 1;
            charsIndex.put(s.charAt(0), 0);
            longestSubstringLen = 1;

            while(rightIndex < sLen) {
                char nextChar = s.charAt(rightIndex);
                int lastIndex = charsIndex.getOrDefault(nextChar, -1);

                if(lastIndex >= leftIndex) {
                    leftIndex = lastIndex + 1;
                }

                charsIndex.put(nextChar, rightIndex);

                int currentLen = rightIndex - leftIndex + 1;
                longestSubstringLen = Math.max(longestSubstringLen, currentLen);
                rightIndex++;
            }

        }

        return longestSubstringLen;
    }

    /*
    Time : O(n)
    Space : O(n)
    Sliding window approach using set
     */
    public int lengthOfLongestSubstring(String s) {
        int longestSubstringLen = 0;

        if(s != null && s.length() > 0) {
            int sLen = s.length();
            Set<Character> charsPresent = new HashSet<Character>();

            int leftIndex = 0, rightIndex = 1;
            charsPresent.add(s.charAt(0));
            longestSubstringLen = 1;

            while(rightIndex < sLen) {
                char nextChar = s.charAt(rightIndex);

                while(charsPresent.contains(nextChar)) {
                    char removeChar = s.charAt(leftIndex);
                    leftIndex++;
                    charsPresent.remove(removeChar);
                }

                charsPresent.add(nextChar);

                int currentLen = rightIndex - leftIndex + 1;
                longestSubstringLen = Math.max(longestSubstringLen, currentLen);
                rightIndex++;
            }

        }

        return longestSubstringLen;
    }

    // Efficient implementation using sliding window.
    /*
    Time : O(n)
    Space : O(1)
     */
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
