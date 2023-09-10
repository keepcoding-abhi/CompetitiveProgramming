public class LongestCommonSubsequence {

    /*
    Time : O(m*n)
    Space : O(min(m, n))

    Reducing an array from the next approach.
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int t1Len = text1.length(), t2Len = text2.length();

        int[] savedRes = new int[t2Len + 1];
        int maxLen = 0;

        for(int t1Index = t1Len - 1 ; t1Index > -1 ; t1Index--) {
            char t1Char = text1.charAt(t1Index);
            int prevVal = 0;
            for(int t2Index = t2Len - 1 ; t2Index > -1 ; t2Index--) {
                char t2Char = text2.charAt(t2Index);

                int longestLen = 0;
                if(t1Char == t2Char) {
                    longestLen = 1 + prevVal;
                }
                else {
                    longestLen = Math.max(savedRes[t2Index + 1], savedRes[t2Index]);
                }

                prevVal = savedRes[t2Index];

                savedRes[t2Index] = longestLen;
            }
        }

        return savedRes[0];
    }

    /*
    Reducing the space requirements from previous approach.
    Time : O(m * n)
    Space : O(min(m, n))
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int t1Len = text1.length(), t2Len = text2.length();

        String minLenStr, otherStr;

        if(t1Len < t2Len) {
            minLenStr = text1;
            otherStr = text2;
        }
        else {
            minLenStr = text2;
            otherStr = text1;
        }

        int[] lengthsWithCurrentCharOfOtherStr = new int[minLenStr.length() + 1];
        int[] lengthsWithPrevCharOfStr = new int[minLenStr.length() + 1];
        int otherStrLen = otherStr.length(), minStrLen = minLenStr.length();

        for(int index = otherStrLen - 1 ; index > -1 ; index--) {
            char otherStrChar = otherStr.charAt(index);
            for(int minStrIndex = minStrLen - 1 ; minStrIndex > -1 ; minStrIndex--) {
                char minStrChar = minLenStr.charAt(minStrIndex);

                if(minStrChar == otherStrChar) {
                    lengthsWithCurrentCharOfOtherStr[minStrIndex] = 1 + lengthsWithPrevCharOfStr[minStrIndex + 1];
                }
                else {
                    lengthsWithCurrentCharOfOtherStr[minStrIndex] = Math.max(lengthsWithCurrentCharOfOtherStr[minStrIndex + 1], lengthsWithPrevCharOfStr[minStrIndex]);
                }
            }

            int[] temp = lengthsWithPrevCharOfStr;
            lengthsWithPrevCharOfStr = lengthsWithCurrentCharOfOtherStr;
            lengthsWithCurrentCharOfOtherStr = temp;
        }

        return lengthsWithPrevCharOfStr[0];
    }

    // Time : O(m*n)
    // Space : O(m * n)
    // Create a 2D matrix where each cell represents a subsequence that starts at the corresponding
    // letters of the two strings.
    // When the current character matches, then add 1 to the length of the string which didn't have any of these two
    // characters previously which will be the one diagonally downwards. Otherwise choose the maximum length
    // from the subsequences which didn't have any one of the two current characters.
    public int longestCommonSubsequence(String text1, String text2) {
        int t1Len = text1.length(), t2Len = text2.length();
        int[][] recordedSeqs = new int[t1Len + 1][t2Len + 1];

        for(int t1Index = t1Len - 1 ; t1Index > -1 ; t1Index--) {
            char t1Char = text1.charAt(t1Index);
            for(int t2Index = t2Len - 1 ; t2Index > -1 ; t2Index--) {
                char t2Char = text2.charAt(t2Index);

                if(t1Char == t2Char) {
                    recordedSeqs[t1Index][t2Index] = 1 + recordedSeqs[t1Index + 1][t2Index + 1];
                }
                else {
                    recordedSeqs[t1Index][t2Index] = Math.max(recordedSeqs[t1Index + 1][t2Index], recordedSeqs[t1Index][t2Index + 1]);
                }
            }
        }

        return recordedSeqs[0][0];
    }

    // Time : O(m * n)
    // Space : O(m * n)
    // Memoized version of teh below solution
    public int longestCommonSubsequence(String text1, String text2) {
        int t1Len = text1.length(), t2Len = text2.length();

        int[][] recordedSeqs = new int[t1Len][t2Len];

        for(int index1 = 0 ; index1 < t1Len ; index1++) {
            for(int index2 = 0 ; index2 < t2Len ; index2++) {
                recordedSeqs[index1][index2] = -1;
            }
        }
        return longestCommonSubsequence(text1, text2, 0, 0, recordedSeqs);
    }

    public int longestCommonSubsequence(String text1, String text2, int t1Start, int t2Start, int[][] recordedSeqs) {
        int t1Len = text1.length(), t2Len = text2.length();
        int longestSeq = 0;

        if(t1Start < t1Len && t2Start < t2Len) {
            if(recordedSeqs[t1Start][t2Start] != -1) {
                longestSeq = recordedSeqs[t1Start][t2Start];
            }
            else {
                char t1Char = text1.charAt(t1Start), t2Char = text2.charAt(t2Start);

                if(t1Char == t2Char) {
                    longestSeq = 1 + longestCommonSubsequence(text1, text2, t1Start + 1, t2Start + 1, recordedSeqs);
                }
                else {
                    longestSeq = Math.max(longestCommonSubsequence(text1, text2, t1Start + 1, t2Start, recordedSeqs), longestCommonSubsequence(text1, text2, t1Start, t2Start + 1, recordedSeqs));
                }

                recordedSeqs[t1Start][t2Start] = longestSeq;
            }

        }

        return longestSeq;
    }

    // Time : O(2^(m+n))
    // Space : O(2^(m + n))
    // When the first character of the two strings is the same then adding it is only going to increase the length of
    // common subsequence and there are no side effects.
    // When it's different, then either one of the two first characters or none of them will be added in the
    // common subsequence.
    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence(text1, text2, 0, 0);
    }

    public int longestCommonSubsequence(String text1, String text2, int t1Start, int t2Start) {
        int t1Len = text1.length(), t2Len = text2.length();
        int longestSeq = 0;

        if(t1Start < t1Len && t2Start < t2Len) {
            char t1Char = text1.charAt(t1Start), t2Char = text2.charAt(t2Start);

            if(t1Char == t2Char) {
                longestSeq = 1 + longestCommonSubsequence(text1, text2, t1Start + 1, t2Start + 1);
            }
            else {
                longestSeq = Math.max(longestCommonSubsequence(text1, text2, t1Start + 1, t2Start), longestCommonSubsequence(text1, text2, t1Start, t2Start + 1));
            }
        }

        return longestSeq;
    }

    // Time : O(m*n*n), in the worst case each of the O(m*n) entry of the table would be filled. O(n) time
    // for performing lookup.
    // Space : O(m*n) for the table
    // O(m * n) recursive calls could be made and in each call O(n) time is taken to search a character in text2.
    // Memoized version of the next solution.
    public int longestCommonSubsequence(String text1, String text2) {
        int t1Len = text1.length(), t2Len = text2.length();

        int[][] recordedSeqs = new int[t1Len][t2Len];

        for(int index1 = 0 ; index1 < t1Len ; index1++) {
            for(int index2 = 0 ; index2 < t2Len ; index2++) {
                recordedSeqs[index1][index2] = -1;
            }
        }

        return longestCommonSubsequence(text1, text2, 0, 0, recordedSeqs);
    }

    public int longestCommonSubsequence(String text1, String text2, int t1Start, int t2Start, int[][] savedLens) {
        int t1Len = text1.length() - t1Start, t2Len = text2.length() - t2Start;
        int longestSeq = 0;

        if(t1Len > 0 && t2Len > 0) {
            if(savedLens[t1Start][t2Start] != -1) {
                longestSeq = savedLens[t1Start][t2Start];
            }
            else {
                char currentChar = text1.charAt(t1Start);

                int t2StartAfterCurrentChar = text2.indexOf(currentChar, t2Start);

                if(t2StartAfterCurrentChar != -1) {
                    int lengthWithCurrentChar = longestCommonSubsequence(text1, text2, t1Start + 1, t2StartAfterCurrentChar + 1, savedLens) + 1;
                    longestSeq = Math.max(longestSeq, lengthWithCurrentChar);
                }

                int lengthWithoutCurrentChar = longestCommonSubsequence(text1, text2, t1Start + 1, t2Start, savedLens);
                longestSeq = Math.max(longestSeq, lengthWithoutCurrentChar);
                savedLens[t1Start][t2Start] = longestSeq;
            }
        }

        return longestSeq;
    }

    // O((2^m) * n) each letter can be included in the sub-sequence or not. And O(n) time is required to
    // look-up for that letter in the other string.
    // Scan a string from left to right and find the matching character of each letter in the first string
    // in the second string. Either the common letter will be part of the longest common subsequence or it isn't.
    // Exceeds Time Limit.
    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence(text1, text2, 0, 0);
    }

    public int longestCommonSubsequence(String text1, String text2, int t1Start, int t2Start) {
        int t1Len = text1.length() - t1Start, t2Len = text2.length() - t2Start;
        int longestSeq = 0;

        if(t1Len > 0 && t2Len > 0) {
            char currentChar = text1.charAt(t1Start);

            int t2StartAfterCurrentChar = text2.indexOf(currentChar, t2Start);

            if(t2StartAfterCurrentChar != -1) {
                int lengthWithCurrentChar = longestCommonSubsequence(text1, text2, t1Start + 1, t2StartAfterCurrentChar + 1) + 1;
                longestSeq = Math.max(longestSeq, lengthWithCurrentChar);
            }

            int lengthWithoutCurrentChar = longestCommonSubsequence(text1, text2, t1Start + 1, t2Start);
            longestSeq = Math.max(longestSeq, lengthWithoutCurrentChar);
        }

        return longestSeq;
    }
}
