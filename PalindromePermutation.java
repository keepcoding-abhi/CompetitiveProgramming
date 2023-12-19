public class PalindromePermutation {

    /*
    Time: O(n)
    Space: O(1)

    If the given string has even length, then there must be no character occurring an odd number of times.
    If the string has odd length, then there must be exactly one character occurring an odd number of times.
     */
    class Solution {
        public boolean canPermutePalindrome(String s) {
            boolean canPermute = true;
            int[] freqs = new int[26];
            int sLen = s.length();

            for(int index = 0 ; index < sLen ; index++) {
                char currentChar = s.charAt(index);

                freqs[currentChar - 'a']++;
            }

            int numberOfOddLenChars = 0;

            for(int freq : freqs) {
                if(freq % 2 != 0) {
                    numberOfOddLenChars++;
                }
            }

            if(sLen % 2 == 0) {
                if(numberOfOddLenChars != 0) {
                    canPermute = false;
                }
            }
            else {
                if(numberOfOddLenChars != 1) {
                    canPermute = false;
                }
            }

            return canPermute;
        }
    }
}
