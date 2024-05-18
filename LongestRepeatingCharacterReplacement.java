public class LongestRepeatingCharacterReplacement {

    /*
    Time : O(n * Log(n))
    Space : O(1) using a fixed length array to store frequencies of characters.

    The set of possible values of lengths of substrings is 1 ... n
    We're interested in the highest valid value. Do binary search and try middle values to reduce the search
    space from O(n) to O(Log(n)).
    At each stage, we check whether there is any substring of length same as middle length.
    This is done by sliding a window of a particular length from left to right and checking whether the
    substring composed by current position of the window is valid.
     */
    public int characterReplacement(String s, int k) {
        int longestLen = 1;

        int lowerBound = k, upperBound = s.length() + 1;

        /*
        when lowerBound = upperBound - 1
        lowerBound indicates valid length and upperBound represents invalid
        There's no way of going ahead from here.
         */
        while(lowerBound + 1 < upperBound) {
            int middleLen = (lowerBound + upperBound) / 2;

            if(checkSubstringsOfGivenLength(s, middleLen, k)) {
                lowerBound = middleLen;
            }
            else {
                upperBound = middleLen;
            }
        }

        return lowerBound;
    }

    /*
    Time: O(n)
    Space: O(1)

    Maintain a sliding window. Expand it when the next character can be fit inside the window, else drag it towards right.
    When the window is getting dragged towards right, there is no need to keep the mostFreqChar variable up to date with
    count of the most frequently occurring character in the window. We only care about the case when we find a character
    that occurs more frequently than the characters which have been seen yet.
     */
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, sLen = s.length();
        int longestLen = 0;
        int[] charFreqs = new int[26];
        int mostFreqChar = 0;

        while(right < sLen) {
            char currentChar = s.charAt(right);

            int freqOfCurrentChar = ++charFreqs[currentChar - 'A'];

            if(freqOfCurrentChar > mostFreqChar) {
                mostFreqChar = freqOfCurrentChar;
            }

            int currentWindowSize = right - left + 1;

            if(currentWindowSize - mostFreqChar <= k) {
                longestLen = Math.max(longestLen, currentWindowSize);
            }
            else {
                charFreqs[s.charAt(left) - 'A']--;
                left++;
            }

            right++;
        }

        return longestLen;
    }

    private boolean checkSubstringsOfGivenLength(String s, int len, int k) {
        boolean validSubstringExists = false;

        if(len <= k) {
            validSubstringExists = true;
        }
        else {
            int[] occurences = new int[26];

            int leftIndex = 0;
            int sameCharsRequired = len - k;
            int maxRepetitions = 1;

            for(int index = 0 ; index < len && maxRepetitions < sameCharsRequired ; index++) {
                int currentCharIndex = s.charAt(index) - 'A';

                occurences[currentCharIndex]++;
                if(occurences[currentCharIndex] > maxRepetitions) {
                    maxRepetitions = occurences[currentCharIndex];
                }
            }

            int sLen = s.length();
            int rightIndex = len;

            while(rightIndex < sLen && maxRepetitions < sameCharsRequired) {
                int addCharIndex = s.charAt(rightIndex) - 'A';
                int removeCharIndex = s.charAt(leftIndex) - 'A';

                occurences[addCharIndex]++;
                occurences[removeCharIndex]--;

                if(occurences[addCharIndex] > maxRepetitions) {
                    maxRepetitions = occurences[addCharIndex];
                }

                rightIndex++;
                leftIndex++;
            }

            if(maxRepetitions >= sameCharsRequired) {
                validSubstringExists = true;
            }
        }

        return validSubstringExists;
    }

    /*
    Time : O(n^2)
    Space : O(n)
    Analyze each possible string ad check whether it can be modified to contain occurrences of only one character.
     */
    public int characterReplacement(String s, int k) {
        int longestLen = 1;

        for(int index1 = 0, sLen = s.length() ; index1 < sLen ; index1++) {

            int[] occurences = new int[26];
            int mostFreqOccurences = 1;
            occurences[s.charAt(index1) - 'A'] = 1;

            for(int index2 = index1 + 1 ; index2 < sLen ; index2++) {

                int currentIndex = s.charAt(index2) - 'A';
                occurences[currentIndex]++;
                mostFreqOccurences = Math.max(mostFreqOccurences, occurences[currentIndex]);

                int currentLen = (index2 - index1) + 1;
                int numbersToReplace = currentLen - mostFreqOccurences;

                if(numbersToReplace <= k) {
                    longestLen = Math.max(longestLen, currentLen);
                }
            }
        }

        return longestLen;
    }

    /*
    Time: O(n)
    Space: O(n)

    Keeping track of the most frequently occurring character in the current window, and using it to check the
    validity of sliding window
     */
    public int characterReplacement(String s, int k) {
        int longestSubstringLen = k;
        int sLen = s.length();

        int mostFreqCharFreq = 0;
        int numberOfMostFreqChars = 0;

        int[] occrs = new int[26];
        for(int index = 0 ; index < k ; index++) {
            char currentChar = s.charAt(index);
            int freqOfCurrChar = ++occrs[currentChar - 'A'];

            if(freqOfCurrChar == mostFreqCharFreq) {
                numberOfMostFreqChars++;
            }
            else if(freqOfCurrChar > mostFreqCharFreq) {
                mostFreqCharFreq = freqOfCurrChar;
                numberOfMostFreqChars = 1;
            }
        }

        int left = 0, right = k;

        while(right < sLen) {
            char nextChar = s.charAt(right);

            int nextCharFreq = ++occrs[nextChar - 'A'];
            int currentWindowLen = (right - left) + 1;

            if(nextCharFreq == mostFreqCharFreq) {
                numberOfMostFreqChars++;
            }
            else if(nextCharFreq > mostFreqCharFreq) {
                mostFreqCharFreq = nextCharFreq;
            }

            if(currentWindowLen - mostFreqCharFreq <= k) {
                longestSubstringLen = Math.max(longestSubstringLen, currentWindowLen);
            }
            else {
                char removedChar = s.charAt(left);

                int freqOfRemovedChar = occrs[removedChar - 'A'];

                if(freqOfRemovedChar == mostFreqCharFreq) {
                    numberOfMostFreqChars--;
                }

                freqOfRemovedChar--;
                occrs[removedChar - 'A'] = freqOfRemovedChar;

                if(numberOfMostFreqChars == 0) {
                    mostFreqCharFreq = freqOfRemovedChar;
                }

                left++;
            }

            right++;
        }

        return longestSubstringLen;
    }
}
