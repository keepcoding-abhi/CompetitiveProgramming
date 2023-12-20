import java.util.LinkedList;
import java.util.List;

public class PalindromePermutation2 {

    /*
    Time: O(n!) in the worst case we will be generating O(n!) permutations
    Space: O(n), the depth of recursion tree is O(n)

    First check whether palindrome permutation is possible. If yes, perform backtracking to generate the palindromic
    string.
     */
    public List<String> generatePalindromes(String s) {

        List<String> result = new LinkedList<String>();

        int[] freqs = getFreqs(s);
        int sLen = s.length();

        if(checkPalindromicPermutation(freqs, sLen)) {
            char[] currentStr = new char[sLen];

            if(sLen % 2 != 0) {

                char middleChar = 'a';

                for(int index = 0 ; index < freqs.length ; index++) {

                    int freq = freqs[index];

                    if((freq % 2) != 0) {
                        middleChar = (char)('a' + index);
                        freqs[index]--;
                        break;
                    }
                }

                currentStr[(sLen / 2)] = middleChar;
            }

            generatePermutations(result, freqs, 0, 0, currentStr);
        }

        return result;
    }

    private void generatePermutations(List<String> permutations, int[] freqs, int freqStartIndex, int strIndex, char[] currentStr) {
        int strLen = currentStr.length;

        int lastIndex = strLen / 2;

        if(strIndex >= lastIndex) {
            permutations.add(new String(currentStr));
        }
        else {
            for(int freqIndex = freqStartIndex ; freqIndex < freqs.length ; freqIndex++) {
                if(freqs[freqIndex] != 0) {
                    char currentChar = (char)('a' + freqIndex);
                    currentStr[strIndex] = currentChar;
                    currentStr[strLen - 1 - strIndex] = currentChar;
                    freqs[freqIndex] -= 2;

                    generatePermutations(permutations, freqs, 0, strIndex + 1, currentStr);

                    freqs[freqIndex] += 2;
                }
            }
        }
    }

    private int[] getFreqs(String s) {
        int[] freqs = new int[26];

        for(int index = 0, sLen = s.length() ; index < sLen ; index++) {
            freqs[s.charAt(index) - 'a']++;
        }

        return freqs;
    }

    private boolean checkPalindromicPermutation(int[] freqs, int sLen) {
        boolean palindromeExists = true;

        if(sLen % 2 == 0) {
            for(int freq : freqs) {
                if(freq % 2 != 0) {
                    palindromeExists = false;
                    break;
                }
            }
        }
        else {
            int oddLenChars = 0;
            for(int freq : freqs) {
                if(freq % 2 != 0) {
                    oddLenChars++;
                }
            }

            if(oddLenChars > 1) {
                palindromeExists = false;
            }
        }

        return palindromeExists;
    }
}
