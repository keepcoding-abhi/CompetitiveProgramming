public class LongestCommonSubstring {

    /*
    Time : O(m*n)
    Space : O(m*n)
    Bottom up DP.
     */
    private static String findLCSBottomUp(String s1, String s2) {

        int s1Len = s1.length(), s2Len = s2.length();
        int maxSoFar = 0;
        int startIndex = -1, endIndex = -1;
        int[][] savedResults = new int[s1Len + 1][s2Len + 1];

        for(int s1Index = s1Len - 1 ; s1Index > -1 ; s1Index--) {
            for(int s2Index = s2Len - 1 ; s2Index > -1 ; s2Index--) {
                if(s1.charAt(s1Index) == s2.charAt(s2Index)) {
                    int currentLen = 1;
                    currentLen += savedResults[s1Index + 1][s2Index + 1];
                    savedResults[s1Index][s2Index] = currentLen;

                    if(currentLen > maxSoFar) {
                        maxSoFar = currentLen;
                        startIndex = s1Index;
                        endIndex = s1Index + currentLen;
                    }
                }
            }
        }

        return s1.substring(startIndex, endIndex);
    }

    /*
    Time : O(m*n^2)
    Space : O(m*n)
     */
    static class Cell {
        int rowIndex, colIndex;

        Cell(int rowIndex, int colIndex) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
        }
    }
    public static void main(String args[]) {
        System.out.println(findLongestCommonSubstring("aab", "aab"));
    }

    private static String findLongestCommonSubstring(String s1, String s2) {
        int s1Len = s1.length(), s2Len = s2.length();

        Cell[][] matrix = new Cell[s1Len][s2Len];

        int[] indices = findLCS(s1, s2, 0, 0, matrix);
        if(indices[0] == -1) {
            return "";
        }
        return s1.substring(indices[0], indices[1]);
    }

    private static int[] findLCS(String s1, String s2, int s1Start, int s2Start, Cell[][] savedResults) {
        int s1Size = s1.length(), s2Size = s2.length();

        int[] result = new int[]{-1, -1};

        if(s1Start < s1Size && s2Start < s2Size) {

            if(savedResults[s1Start][s2Start] == null) {
                int maxLen = -1;

                if(s1.charAt(s1Start) == s2.charAt(s2Start)) {
                    int s1Index = s1Start, s2Index = s2Start;
                    while(s1Index < s1Size && s2Index < s2Size && (s1.charAt(s1Index) == s2.charAt(s2Index))) {
                        s1Index++;
                        s2Index++;
                    }

                    int currentLen = s1Index - s1Start;
                    result[0] = s1Start;
                    result[1] = s1Index;
                    maxLen = currentLen;
                }

                int[] ignoreS1Char = findLCS(s1, s2, s1Start + 1, s2Start, savedResults);
                int[] ignoreS2Char = findLCS(s1, s2, s1Start, s2Start + 1, savedResults);

                if(ignoreS1Char[0] != -1) {
                    int ignoreS1CharLen = ignoreS1Char[1] - ignoreS1Char[0];
                    if(ignoreS1CharLen > maxLen) {
                        result[0] = ignoreS1Char[0];
                        result[1] = ignoreS1Char[1];
                        maxLen = ignoreS1CharLen;
                    }
                }

                if(ignoreS2Char[0] != -1) {
                    int ignoreS2CharLen = ignoreS2Char[1] - ignoreS2Char[0];
                    if(ignoreS2CharLen > maxLen) {
                        result[0] = ignoreS2Char[0];
                        result[1] = ignoreS2Char[1];
                    }
                }

                Cell newCell = new Cell(result[0], result[1]);
                savedResults[s1Start][s2Start] = newCell;
            }
            else {
                Cell resultInfo = savedResults[s1Start][s2Start];
                result[0] = resultInfo.rowIndex;
                result[1] = resultInfo.colIndex;
            }
        }

        return result;
    }
}
