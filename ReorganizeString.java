import java.util.PriorityQueue;

public class ReorganizeString {
}

/*
Time: O(n)
Space: O(k)

Populate the characters in re-organized string by first filling up the even positions and then the odd positions.
Start by filling out the most frequently occurring character first, otherwise that character could end up being
in adjacent locations.
 */
class Solution {
    public String reorganizeString(String s) {

        int sLen = s.length();
        String result;

        if(sLen == 1) {
            result = s;
        }
        else {
            int[] freqs = new int[26];
            int maxFreq = 0, maxFreqCharIndex = -1;

            for(int index = 0 ; index < sLen ; index++) {
                int currCharIndex = s.charAt(index) - 'a';
                int currCharFreq = freqs[currCharIndex] + 1;
                freqs[currCharIndex] = currCharFreq;

                if(currCharFreq > maxFreq) {
                    maxFreq = currCharFreq;
                    maxFreqCharIndex = currCharIndex;
                }
            }

            if(maxFreq > ((sLen + 1) / 2)) {
                result = "";
            }
            else {
                char[] resultBuilder = new char[sLen];

                int currCharIndex = maxFreqCharIndex;
                int currCharOccurrences = maxFreq;
                int resIndex = 0;

                while(currCharOccurrences > 0) {
                    resultBuilder[resIndex] = (char)(currCharIndex + 'a');
                    currCharOccurrences--;

                    resIndex += 2;
                }

                freqs[currCharIndex] = 0;

                for(int charIndex = 0 ; charIndex < 26 ; charIndex++) {
                    int freqsOfCurrChar = freqs[charIndex];
                    char currChar = (char)('a' + charIndex);

                    while(freqsOfCurrChar > 0) {

                        if(resIndex >= sLen) {
                            resIndex = 1;
                        }

                        resultBuilder[resIndex] = currChar;
                        freqsOfCurrChar--;
                        resIndex += 2;
                    }
                }

                result = new String(resultBuilder);
            }
        }

        return result;
    }

    private int findNextCharIndex(int[] freqs, int startIndex) {

        int resIndex = -1;

        for(int index = startIndex ; index < freqs.length ; index++) {
            if(freqs[index] > 0) {
                resIndex = index;
                break;
            }
        }

        return resIndex;
    }
}

/*
Time:   O(n * Log(k))
Space:  O(k)



Record the frequencies of each character. Use a priority queue to place those characters that occur most frequently
first and also to ensure that different characters are placed at consecutive locations.
 */
class Solution {
    public String reorganizeString(String s) {
        int[] freqs = new int[26];
        int sLen = s.length();
        int maxFreq = 0;

        for(int index = 0 ; index < sLen ; index++) {
            int currCharFreq = freqs[s.charAt(index) - 'a'] + 1;
            freqs[s.charAt(index) - 'a'] = currCharFreq;
            maxFreq = Math.max(maxFreq, currCharFreq);
        }

        String result;
        if(maxFreq > ((sLen + 1) / 2)) {
            result = "";
        }
        else {
            PriorityQueue<int[]> charsToBeAdded = new PriorityQueue<int[]>(26,
                    (int[] char1Info, int[] char2Info) -> {
                        return char2Info[1] - char1Info[1];
                    });

            for(int index = 0 ; index < 26 ; index++) {
                if(freqs[index] > 0) {
                    charsToBeAdded.add(new int[]{index, freqs[index]});
                }
            }

            char lastChar = 0;
            char[] resultRep = new char[sLen];
            int resultIndex = 0;

            while(!charsToBeAdded.isEmpty()) {
                int[] nextChar = charsToBeAdded.poll();
                char currentChar = (char)(nextChar[0] + 'a');

                if(currentChar == lastChar) {
                    int[] nextToNextChar = charsToBeAdded.poll();
                    currentChar = (char)('a' + nextToNextChar[0]);
                    resultRep[resultIndex++] = currentChar;
                    lastChar = currentChar;
                    nextToNextChar[1]--;

                    if(nextToNextChar[1] > 0) {
                        charsToBeAdded.add(nextToNextChar);
                    }

                    charsToBeAdded.add(nextChar);
                }
                else {
                    resultRep[resultIndex++] = currentChar;
                    lastChar = currentChar;
                    nextChar[1]--;
                    if(nextChar[1] > 0) {
                        charsToBeAdded.add(nextChar);
                    }
                }
            }

            result = new String(resultRep);
        }

        return result;
    }
}
