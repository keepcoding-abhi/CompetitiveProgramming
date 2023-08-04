public class LongestPalindromicSubstring {

    /*
    Time : O(n^2)
    Space : O(1)

    Consider each possible center point of the palindrome in input string.
    There will be two different center points for even and odd length palindromes.
     */
    public String longestPalindrome(String s) {
        int longestPalinLen = 1;
        int startIndexOfRes = 0, endIndexOfRes = 0;
        int sLen = s.length();

        for(int index = 0 ; index < sLen ; index++) {
            int[] palinFromCurrentCenter = expandFromCenter(s, index, index);

            if(palinFromCurrentCenter[0] > longestPalinLen) {
                longestPalinLen = palinFromCurrentCenter[0];
                startIndexOfRes = palinFromCurrentCenter[1];
                endIndexOfRes = palinFromCurrentCenter[2];
            }
        }

        for(int index = 0 ; index < sLen - 1 ; index++) {
            int[] palinFromCurrentCenter = expandFromCenter(s, index, index + 1);

            if(palinFromCurrentCenter[0] > longestPalinLen) {
                longestPalinLen = palinFromCurrentCenter[0];
                startIndexOfRes = palinFromCurrentCenter[1];
                endIndexOfRes = palinFromCurrentCenter[2];
            }
        }

        return s.substring(startIndexOfRes, endIndexOfRes + 1);
    }

    private int[] expandFromCenter(String str, int startIndex, int endIndex) {

        int sLen = str.length();
        while(startIndex > -1 && endIndex < sLen) {
            if(str.charAt(startIndex) == str.charAt(endIndex)) {
                startIndex--;
                endIndex++;
            }
            else {
                break;
            }
        }

        startIndex++;
        endIndex--;
        int[] result = new int[3];

        result[0] = endIndex - startIndex + 1;
        result[1] = startIndex;
        result[2] = endIndex;

        return result;
    }

    /*
    Time : O(n^2)
    Space : O(n^2)
    Using a table to keep track of the substrings that start and end at a particular index.
     */
    public String longestPalindrome(String s) {
        int startIndexOfRes = 0, endIndexOfRes = 0;
        int maxLen = 1, sLen = s.length();
        int[][] result = new int[sLen][sLen];

        for(int index = 0 ; index < sLen ; index++) {
            result[index][index] = 1;
        }

        for(int startIndex = sLen - 1 ; startIndex > -1 ; startIndex--) {
            for(int endIndex = startIndex + 1 ; endIndex < sLen ; endIndex++) {
                if(s.charAt(startIndex) == s.charAt(endIndex)) {
                    if(startIndex + 1 == endIndex || result[startIndex + 1][endIndex - 1] == 1) {
                        result[startIndex][endIndex] = 1;
                        int currentLen = endIndex - startIndex + 1;
                        if(currentLen > maxLen) {
                            maxLen = currentLen;
                            startIndexOfRes = startIndex;
                            endIndexOfRes = endIndex;
                        }
                    }
                }
            }
        }

        return s.substring(startIndexOfRes, endIndexOfRes + 1);
    }

    /*
    Time : O(n^3)
    Space : O(1)
    Check all substrings.
     */
    public String longestPalindrome(String s) {
        int startIndexOfRes = 0, endIndexOfRes = 0;
        int maxLen = 1;

        for(int startIndex = 0, sLen = s.length() ; startIndex < sLen ; startIndex++) {
            for(int endIndex = startIndex + 1 ; endIndex < sLen ; endIndex++) {
                if(checkPalin(s, startIndex, endIndex)) {
                    int currentLen = endIndex - startIndex + 1;
                    if(currentLen > maxLen) {
                        startIndexOfRes = startIndex;
                        endIndexOfRes = endIndex;
                        maxLen = currentLen;
                    }
                }
            }
        }

        return s.substring(startIndexOfRes, endIndexOfRes + 1);
    }

    private boolean checkPalin(String s, int startIndex, int endIndex) {

        boolean palin = true;

        while(startIndex < endIndex) {
            if(s.charAt(startIndex) != s.charAt(endIndex)) {
                palin = false;
                break;
            }

            startIndex++;
            endIndex--;
        }

        return palin;
    }
}
