public class EditDistance {
}

/*
Time : O(m * n)
Space : O(m)

Space optimized version of next approach.
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int w1Len = word1.length(), w2Len = word2.length();
        int[] nextW1IndexResults = new int[w2Len + 1];

        for(int c = 0 ; c < w2Len ; c++) {
            nextW1IndexResults[c] = w2Len - c;
        }

        for(int r = w1Len - 1 ; r > -1 ; r--) {
            char word1Char = word1.charAt(r);
            int[] currW1IndexResults = new int[w2Len + 1];
            currW1IndexResults[w2Len] = w1Len - r;

            for(int c = w2Len - 1 ; c > -1 ; c--) {
                char word2Char = word2.charAt(c);

                int minDistance;
                if(word1Char == word2Char) {
                    minDistance = nextW1IndexResults[c + 1];
                }
                else {
                    minDistance = nextW1IndexResults[c + 1];
                    minDistance = Math.min(minDistance, nextW1IndexResults[c]);
                    minDistance = Math.min(minDistance, currW1IndexResults[c + 1]);
                    minDistance++;
                }

                currW1IndexResults[c] = minDistance;
            }

            nextW1IndexResults = currW1IndexResults;
        }

        return nextW1IndexResults[0];
    }
}

/*
Time : O(m * n)
Space : O(m * n)

Bottom up iterative version of next approach.
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int w1Len = word1.length(), w2Len = word2.length();
        int[][] savedRes = new int[w1Len + 1][w2Len + 1];

        for(int c = 0 ; c < w2Len ; c++) {
            savedRes[w1Len][c] = w2Len - c;
        }

        for(int r = 0 ; r < w1Len ; r++) {
            savedRes[r][w2Len] = w1Len - r;
        }

        for(int r = w1Len - 1 ; r > -1 ; r--) {
            char word1Char = word1.charAt(r);

            for(int c = w2Len - 1 ; c > -1 ; c--) {
                char word2Char = word2.charAt(c);

                int minDistance;
                if(word1Char == word2Char) {
                    minDistance = savedRes[r + 1][c + 1];
                }
                else {
                    minDistance = savedRes[r + 1][c + 1];
                    minDistance = Math.min(minDistance, savedRes[r + 1][c]);
                    minDistance = Math.min(minDistance, savedRes[r][c + 1]);
                    minDistance++;
                }

                savedRes[r][c] = minDistance;
            }
        }

        return savedRes[0][0];
    }
}

/*
Time : O(m * n)
Space : O(m * n)

If the current chars in two strings match move on to next chars, otherwise
perform the three possible actions.
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int w1Len = word1.length(), w2Len = word2.length();
        int[][] savedRes = new int[w1Len][w2Len];

        for(int r = 0 ; r < w1Len ; r++) {
            for(int c = 0 ; c < w2Len ; c++) {
                savedRes[r][c] = -1;
            }
        }

        return getEditDistance(word1, word2, 0, 0, savedRes);
    }

    private int getEditDistance(String word1, String word2, int word1Index, int word2Index, int[][] savedRes) {
        int word1Len = word1.length(), word2Len = word2.length();
        int editDistance;

        if(word2Index < word2Len) {

            if(word1Index < word1Len) {

                if(savedRes[word1Index][word2Index] == -1) {
                    char word1Char = word1.charAt(word1Index);
                    char word2Char = word2.charAt(word2Index);

                    if(word1Char == word2Char) {
                        editDistance = getEditDistance(word1, word2, word1Index + 1, word2Index + 1, savedRes);
                    }
                    else {

                        editDistance = Integer.MAX_VALUE;
                        // insert
                        editDistance = Math.min(editDistance, getEditDistance(word1, word2, word1Index, word2Index + 1, savedRes));

                        // replace
                        editDistance = Math.min(editDistance, getEditDistance(word1, word2, word1Index + 1, word2Index + 1, savedRes));

                        // delete
                        editDistance = Math.min(editDistance, getEditDistance(word1, word2, word1Index + 1, word2Index, savedRes));

                        editDistance++;
                    }

                    savedRes[word1Index][word2Index] = editDistance;
                }
                else {
                    editDistance = savedRes[word1Index][word2Index];
                }
            }
            else {
                editDistance = (word2Len - word2Index);
            }
        }
        else {
            editDistance = (word1Len - word1Index);
        }

        return editDistance;
    }
}
