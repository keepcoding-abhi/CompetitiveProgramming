import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
}

/*
Time: O(n + m)
Space: O(1)

Maintain a sliding window and use the number of characters from t matched by the sliding window to check the validity
of sliding window
 */
class Solution {
    public String minWindow(String s, String t) {
        /*
        One variable will store the number of characters from t that have been
        matched in the current window of s
        */

        int numberOfMatches = 0, sLen = s.length(), tLen = t.length();
        String minWindow;

        if(sLen >= tLen) {
            int[] charFreqsInT = getFreqsInStr(t);
            int[] charFreqsInWindow = new int[52];

            int leftPtr = 0, rightPtr = 0;
            int minWindowLeft = -1, minWindowRight = -1;

            while(rightPtr < sLen) {
                int nextCharIndex = getCharIndex(s.charAt(rightPtr));

                if(charFreqsInT[nextCharIndex] > 0) {
                    charFreqsInWindow[nextCharIndex]++;

                    if(charFreqsInWindow[nextCharIndex] <= charFreqsInT[nextCharIndex]) {
                        numberOfMatches++;
                    }
                }

                while(numberOfMatches == tLen && leftPtr <= rightPtr) {
                    int currentWindowLen = (rightPtr - leftPtr) + 1;

                    int minWindowLen = (minWindowLeft == -1) ? Integer.MAX_VALUE : (minWindowRight - minWindowLeft + 1);

                    if(currentWindowLen < minWindowLen) {
                        minWindowLeft = leftPtr;
                        minWindowRight = rightPtr;
                    }

                    int removedCharIndex = getCharIndex(s.charAt(leftPtr));
                    leftPtr++;

                    if(charFreqsInWindow[removedCharIndex] > 0) {
                        charFreqsInWindow[removedCharIndex]--;
                    }

                    if(charFreqsInWindow[removedCharIndex] < charFreqsInT[removedCharIndex]) {
                        numberOfMatches--;
                    }
                }

                rightPtr++;
            }

            // if(numberOfMatches == tLen) {
            //     if(minWindowLeft == -1) {
            //         minWindowLeft = leftPtr;
            //         minWindowRight = rightPtr - 1;
            //     }
            //     else {
            //         int currentWindowLen = rightPtr - leftPtr;
            //         int minWindowLen = minWindowRight - minWindowLeft + 1;

            //         if(currentWindowLen < minWindowLen) {
            //             minWindowLeft = leftPtr;
            //             minWindowRight = rightPtr - 1;
            //         }
            //     }
            // }

            if(minWindowLeft == -1) {
                minWindow = "";
            }
            else {
                minWindow = s.substring(minWindowLeft, minWindowRight + 1);
            }
        }
        else {
            minWindow = "";
        }

        return minWindow;
    }

    private int[] getFreqsInStr(String s) {
        int[] freqs = new int[52];
        int sLen = s.length();

        for(int index = 0 ; index < sLen ; index++) {
            int currentCharIndex = getCharIndex(s.charAt(index));
            freqs[currentCharIndex]++;
        }

        return freqs;
    }

    private int getCharIndex(char ch) {
        int index;
        if(ch <= 'Z') {
            index = ch - 'A';
        }
        else {
            index = (ch - 'a') + 26;
        }

        return index;
    }
}

/*
    Time : O(|s| + |t|)
    Space : O(|s| + |t|)

    Using the sliding window approach. Incrementing the right index and adding new character to the window.
    If at any point the window has all the characters in t, then keep removing characters from left till you reach
    a point where at least one character from t is missing in window.

    We use a map to count the characters in t. And a counter is used to check how many unique characters in t have been
    captured by the window.
 */
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> freqsInT = new HashMap<Character, Integer>();

        for(int index = 0, tLen = t.length() ; index < tLen ; index++) {
            char currentChar = t.charAt(index);
            freqsInT.put(currentChar, freqsInT.getOrDefault(currentChar, 0) + 1);
        }

        int uniqueCharsInT = freqsInT.size();
        int tsCharactersInSubStr = 0;

        int leftIndex = 0, rightIndex = 0;
        int[] ans = new int[3];
        ans[0] = Integer.MAX_VALUE;
        ans[1] = -1;
        ans[2] = -1;

        int sLen = s.length();

        Map<Character, Integer> freqsInSubStr = new HashMap<Character, Integer>();

        while(rightIndex < sLen) {
            char nextChar = s.charAt(rightIndex);
            int countOfCurrentCharInSubstr = freqsInSubStr.getOrDefault(nextChar, 0) + 1;
            freqsInSubStr.put(nextChar, countOfCurrentCharInSubstr);

            if(countOfCurrentCharInSubstr == freqsInT.getOrDefault(nextChar, 0)) {
                tsCharactersInSubStr++;
            }

            while(leftIndex <= rightIndex && tsCharactersInSubStr == uniqueCharsInT) {
                char leftChar = s.charAt(leftIndex);

                int currentLen = rightIndex - leftIndex + 1;

                if(currentLen < ans[0]) {
                    ans[0] = currentLen;
                    ans[1] = leftIndex;
                    ans[2] = rightIndex;
                }

                int countOfLeftCharInSubstr = freqsInSubStr.get(leftChar) - 1;
                freqsInSubStr.put(leftChar, countOfLeftCharInSubstr);

                if(freqsInT.getOrDefault(leftChar, 0) > countOfLeftCharInSubstr) {
                    tsCharactersInSubStr--;
                }

                leftIndex++;
            }

            rightIndex++;
        }

        String result;
        if(ans[1] == -1) {
            result = "";
        }
        else {
            result = s.substring(ans[1], ans[2] + 1);
        }

        return result;
    }
}

/*
Time : O(n^3)
Space : O(n) : n is length of s
Exceeds time limit.
 */
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> freqsInT = getFreqs(t);
        int minLen = Integer.MAX_VALUE;
        int startIndexOfSubstring = -1, endIndexOfSubstring = -1;

        for(int index = 0, sLen = s.length() ; index < sLen ; index++) {

            Map<Character, Integer> freqsInSubstring = new HashMap<Character, Integer>();

            for(int endIndex = index ; endIndex < sLen ; endIndex++) {
                char currentChar = s.charAt(endIndex);
                freqsInSubstring.put(currentChar, freqsInSubstring.getOrDefault(currentChar, 0) + 1);

                if(subsetOf(freqsInT, freqsInSubstring)) {
                    int currentLen = endIndex - index + 1;

                    if(currentLen < minLen) {
                        minLen = currentLen;
                        startIndexOfSubstring = index;
                        endIndexOfSubstring = endIndex;
                    }

                }
            }
        }

        String result;
        if(startIndexOfSubstring == -1) {
            result = "";
        }
        else {
            result = s.substring(startIndexOfSubstring, endIndexOfSubstring + 1);
        }

        return result;
    }

    private boolean subsetOf(Map<Character, Integer> smallerStrFreqs, Map<Character, Integer> largerStrFreqs) {
        boolean subset = true;

        for(Map.Entry<Character, Integer> entry : smallerStrFreqs.entrySet()) {
            char smallerStrChar = entry.getKey();
            int smallerStrCharFreq = entry.getValue();

            if(smallerStrCharFreq > largerStrFreqs.getOrDefault(smallerStrChar, 0)) {
                subset = false;
                break;
            }
        }

        return subset;
    }

    private Map<Character, Integer> getFreqs(String s) {
        Map<Character, Integer> freqs = new HashMap<Character, Integer>();

        for(int index = 0, len = s.length() ; index < len ; index++) {
            char currentChar = s.charAt(index);
            freqs.put(currentChar, freqs.getOrDefault(currentChar, 0) + 1);
        }

        return freqs;
    }
}
