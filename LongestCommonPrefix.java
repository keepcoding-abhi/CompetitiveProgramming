public class LongestCommonPrefix {
    /*
    Time : O(n*m)  -- n number of strings, m length of shortest string
    Space : O(1)

    Scanning through all strings before adding a new character in the common prefix.
     */
    public String longestCommonPrefix(String[] strs) {

        StringBuilder commonPrefix = new StringBuilder();

        int currentIndex = 0, nStrs = strs.length;
        boolean prefixValid = true;

        while(prefixValid) {

            if(currentIndex >= strs[0].length()) {
                break;
            }

            char currentChar = strs[0].charAt(currentIndex);

            for(int strIndex = 1 ; strIndex < nStrs ; strIndex++) {
                String currentString = strs[strIndex];

                if(currentIndex < currentString.length() && currentString.charAt(currentIndex) == currentChar) {
                    continue;
                }
                else {
                    prefixValid = false;
                    break;
                }
            }

            if(prefixValid) {
                commonPrefix.append(currentChar);
            }

            currentIndex++;
        }

        return commonPrefix.toString();
    }

    /*
    Divide and conquer approach.
    Time : O(nLog(n))
    SPace : O(S*Log(n)) : S is the sum of all characetrs in all struings.
     */

    public String longestCommonPrefix(String[] strs) {

        return longestCommonPrefixRecursive(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefixRecursive(String[] strs, int startIndex, int endIndex) {

        String commonPrefix = "";
        if(endIndex == startIndex + 1) {
            commonPrefix = longestPrefixBetweenTwo(strs[startIndex], strs[endIndex]);
        }
        else if(startIndex == endIndex) {
            commonPrefix = strs[startIndex];
        }
        else if(startIndex < endIndex) {
            int mid = (startIndex + endIndex) / 2;
            String prefixFromLeft = longestCommonPrefixRecursive(strs, startIndex, mid);
            String prefixFromRight = longestCommonPrefixRecursive(strs, mid + 1, endIndex);
            commonPrefix = longestPrefixBetweenTwo(prefixFromLeft, prefixFromRight);
        }

        return commonPrefix;
    }


    private String longestPrefixBetweenTwo(String str1, String str2) {
        StringBuilder prefix = new StringBuilder();

        int minLength = Math.min(str1.length(), str2.length());

        for(int index = 0 ; index < minLength ; index++) {
            char str1Char = str1.charAt(index);
            char str2Char = str2.charAt(index);

            if(str1Char == str2Char) {
                prefix.append(str1Char);
            }
            else {
                break;
            }
        }

        return prefix.toString();
    }
}
