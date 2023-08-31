public class PalindromicSubstrings {

    /*
    Time : O(n^2)
    Space : O(1)
    Consider each character as the center of palindrome and count by adding a character from each end.
     */
    public int countSubstrings(String s) {
        int count = 0, sLen = s.length();

        for(int index = 0 ; index < sLen ; index++) {
            count += countPalinsFromCenter(s, index);
        }

        return count;
    }

    private int countPalinsFromCenter(String s, int centerIndex) {
        int palinCount = 0;

        int leftPtr = centerIndex, rightPtr = centerIndex, sLen = s.length();

        while(leftPtr > -1 && rightPtr < sLen) {
            if(s.charAt(leftPtr) == s.charAt(rightPtr)) {
                palinCount++;
                leftPtr--;
                rightPtr++;
            }
            else {
                break;
            }
        }

        leftPtr = centerIndex;
        rightPtr = centerIndex + 1;
        while(leftPtr > -1 && rightPtr < sLen) {
            if(s.charAt(leftPtr) == s.charAt(rightPtr)) {
                palinCount++;
                leftPtr--;
                rightPtr++;
            }
            else {
                break;
            }
        }

        return palinCount;
    }

    /*
    Time : O(n^2)
    Space : O(n^2)
    Saving the start and end index of palindromes in a table and using those values
    to generate detect bigger palindromes.
    If s[left] == s[right], and [left + 1, right - 1] is known to be a substring, [left, right] is also a substring.
     */
    public int countSubstrings(String s) {
        int sLen = s.length(), count = sLen;

        int[][] savedRes = new int[sLen][sLen];

        for(int index = 0 ; index < sLen ; index++) {
            savedRes[index][index] = 1;
        }

        for(int index = 0 ; index < sLen - 1 ; index++) {
            if(s.charAt(index) == s.charAt(index + 1)) {
                count++;
                savedRes[index][index + 1] = 1;
            }
        }

        for(int gap = 2 ; gap < sLen ; gap++) {
            for(int index = 0 ; index < sLen - gap ; index++) {
                if((savedRes[index + 1][index + gap - 1] == 1) && (s.charAt(index) == s.charAt(index + gap))) {
                    count++;
                    savedRes[index][index + gap] = 1;
                }
            }
        }

        return count;
    }

    /*
    Time : O(n^3)
    Space : O(1)
    Generate every possible substring, and check if it is a palindrome.
     */
    public int countSubstrings(String s) {
        int count = 0;

        for(int index = 0, sLen = s.length() ; index < sLen ; index++) {
            for(int endIndex = index ; endIndex < sLen ; endIndex++) {
                if(isPalin(s, index, endIndex)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isPalin(String s, int start, int end) {
        int leftPtr = start, rightPtr = end;

        boolean palin = true;

        while(leftPtr < rightPtr) {
            if(s.charAt(leftPtr) != s.charAt(rightPtr)) {
                palin = false;
                break;
            }

            leftPtr++;
            rightPtr--;
        }

        return palin;
    }
}
