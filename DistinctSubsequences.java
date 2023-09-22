public class DistinctSubsequences {
}

/*
Time : O(s * t)
Space : O(t)

Saving space using a 1-dimensional array.
 */
class Solution {
    public int numDistinct(String s, String t) {
        int sLen = s.length(), tLen = t.length();

        int[] lensFromNextS = new int[tLen + 1];
        lensFromNextS[tLen] = 1;

        for(int sIndex = sLen - 1 ; sIndex > -1 ; sIndex--) {
            int[] lensFromCurrentS = new int[tLen + 1];
            lensFromCurrentS[tLen] = 1;

            for(int tIndex = tLen - 1 ; tIndex > -1 ; tIndex--) {
                char sChar = s.charAt(sIndex);
                char tChar = t.charAt(tIndex);

                lensFromCurrentS[tIndex] = lensFromNextS[tIndex];

                if(sChar == tChar) {
                    lensFromCurrentS[tIndex] += lensFromNextS[tIndex + 1];
                }
            }

            lensFromNextS = lensFromCurrentS;
        }

        return lensFromNextS[0];
    }
}

/*
Time : O(s * t)
Space : O(s * t)

Bottom-up iterative version of next approach.
 */
class Solution {
    public int numDistinct(String s, String t) {
        int sLen = s.length(), tLen = t.length();

        int[][] savedRes = new int[sLen + 1][tLen + 1];

        for(int sIndex = 0 ; sIndex <= sLen ; sIndex++) {
            savedRes[sIndex][tLen] = 1;
        }

        for(int sIndex = sLen - 1 ; sIndex > -1 ; sIndex--) {
            for(int tIndex = tLen - 1 ; tIndex > -1 ; tIndex--) {
                char sChar = s.charAt(sIndex);
                char tChar = t.charAt(tIndex);

                if(sChar == tChar) {
                    savedRes[sIndex][tIndex] += savedRes[sIndex + 1][tIndex + 1];
                }

                savedRes[sIndex][tIndex] += savedRes[sIndex + 1][tIndex];
            }
        }

        return savedRes[0][0];
    }
}

/*
Time : O(s * t)
Space : O(s * t)

Top down recursive and memoized version.
If the current chars of s and t match move both pointers ahead otherwise move only s pointer ahead.
 */
class Solution {
    public int numDistinct(String s, String t) {
        int sLen = s.length(), tLen = t.length();

        int[][] savedRes = new int[sLen][tLen];

        for(int sIndex = 0 ; sIndex < sLen ; sIndex++) {
            for(int tIndex = 0 ; tIndex < tLen ; tIndex++) {
                savedRes[sIndex][tIndex] = -1;
            }
        }
        return countSubseqs(s, t, 0, 0, savedRes);
    }

    private int countSubseqs(String s, String t, int sIndex, int tIndex, int[][] savedRes) {

        int sLen = s.length(), tLen = t.length(), seqs = 0;;

        if(tIndex < tLen) {
            if(sIndex < sLen) {

                int prevResult = savedRes[sIndex][tIndex];

                if(prevResult == -1) {
                    char sChar = s.charAt(sIndex), tChar = t.charAt(tIndex);

                    if(sChar == tChar) {
                        seqs += (countSubseqs(s, t, sIndex + 1, tIndex + 1, savedRes));
                    }

                    seqs += (countSubseqs(s, t, sIndex + 1, tIndex, savedRes));
                    savedRes[sIndex][tIndex] = seqs;
                }
                else {
                    seqs = prevResult;
                }
            }
        }
        else {
            seqs = 1;
        }

        return seqs;
    }
}
