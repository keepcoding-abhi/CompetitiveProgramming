import java.util.Arrays;

public class DecodeWays {

    /*
    Time : O(n)
    Space : O(1)
    Bottom-up DP solution with constant space.
     */
    public int numDecodings(String s) {
        int sLen = s.length();

        int count;

        if(sLen == 1) {
            count = (s.charAt(0) == '0') ? 0 : 1;
        }
        else {
            int countFromTwoCharactersAhead = 1;

            int countFromNextChar = (s.charAt(sLen - 1) == '0') ? 0 : 1;

            for(int index = sLen - 2; index > -1 ; index--) {
                char firstChar = s.charAt(index);

                int currentCount = 0;
                if(firstChar != '0') {
                    currentCount += countFromNextChar;

                    char secondChar = s.charAt(index + 1);

                    if((firstChar == '1') || (firstChar == '2' && secondChar >= '0' && secondChar <= '6')) {
                        currentCount += countFromTwoCharactersAhead;
                    }
                }

                countFromTwoCharactersAhead = countFromNextChar;
                countFromNextChar = currentCount;
            }

            count = countFromNextChar;
        }

        return count;
    }

    /*
    Time : O(n)
    Space : O(n)
    Bottom-up DP solution
     */
    public int numDecodings(String s) {
        int sLen = s.length();

        int count;

        if(sLen == 1) {
            count = (s.charAt(0) == '0') ? 0 : 1;
        }
        else {
            int[] counts = new int[sLen + 2];
            counts[sLen] = 1;

            counts[sLen - 1] = (s.charAt(sLen - 1) == '0') ? 0 : 1;

            for(int index = sLen - 2; index > -1 ; index--) {
                char firstChar = s.charAt(index);

                int currentCount = 0;
                if(firstChar != '0') {
                    currentCount += counts[index + 1];

                    char secondChar = s.charAt(index + 1);

                    if((firstChar == '1') || (firstChar == '2' && secondChar >= '0' && secondChar <= '6')) {
                        currentCount += counts[index + 2];
                    }
                }

                counts[index] = currentCount;
            }

            count = counts[0];
        }

        return count;
    }

    /*
    Time : O(n)
    Space : O(n)
    Top down version of next solution.
     */
    public int numDecodings(String s) {
        int[] counts = new int[s.length()];
        Arrays.fill(counts, -1);
        return getNumberOfDecodings(s, 0, counts);
    }

    private int getNumberOfDecodings(String s, int startIndex, int[] counts) {

        int count = 0;
        int sLen = s.length();
        if(startIndex == sLen) {
            count = 1;
        }
        else {

            count = counts[startIndex];

            if(count == -1) {
                count = 0;
                char firstChar = s.charAt(startIndex);

                if(firstChar != '0') {
                    count += getNumberOfDecodings(s, startIndex + 1, counts);
                    if(startIndex < sLen - 1) {
                        char secondChar = s.charAt(startIndex + 1);
                        if((firstChar == '1') || (firstChar == '2' && secondChar >= '0' && secondChar <= '6'))
                            count += getNumberOfDecodings(s, startIndex + 2, counts);
                    }
                }

                counts[startIndex] = count;
            }
        }

        return count;
    }

    /*
    Time : O(2^n), T(n) = T(n - 1) + T(n - 2)
    Space : O(n)
    If the subsequent character is valid, check the rest of the string.
     */
    public int numDecodings(String s) {
        return getNumberOfDecodings(s, 0);
    }

    private int getNumberOfDecodings(String s, int startIndex) {

        int count = 0;
        int sLen = s.length();
        if(startIndex == sLen) {
            count = 1;
        }
        else {
            char firstChar = s.charAt(startIndex);

            if(firstChar != '0') {
                count += getNumberOfDecodings(s, startIndex + 1);
                if(startIndex < sLen - 1) {
                    char secondChar = s.charAt(startIndex + 1);
                    if((firstChar == '1') || (firstChar == '2' && secondChar >= '0' && secondChar <= '6'))
                        count += getNumberOfDecodings(s, startIndex + 2);
                }
            }
        }

        return count;
    }
}
