import java.util.LinkedList;
import java.util.List;

public class CountBinarySubstrings {

    /*
        Time : O(n)
        Space : O(1)
        Doing just one scan from left to right.
     */
    public int countBinarySubstrings(String s) {

        int numberOfSubstrings = 0, sLen = s.length();

        int currentIndex = 1, currentCharOccurrences = 1, prevCharOccurrences = 0;

        for(currentIndex = 1 ; currentIndex < sLen ; currentIndex++) {
            char currentChar = s.charAt(currentIndex);

            if(currentChar == s.charAt(currentIndex - 1)) {
                currentCharOccurrences++;
            }
            else {

                numberOfSubstrings += Math.min(currentCharOccurrences, prevCharOccurrences);

                prevCharOccurrences = currentCharOccurrences;
                currentCharOccurrences = 1;
            }
        }

        numberOfSubstrings += Math.min(currentCharOccurrences, prevCharOccurrences);

        return numberOfSubstrings;
    }

    /*
    Time : O(n) best case, O(n^2) worst case
    Space : O(1)
    Counting the number of consecutive occurrences of first character and then the second character and taking the
    minimum of two. Restarting the counting from the beginning of the second character.
     */
    public int countBinarySubstrings(String s) {

        int numberOfSubstrings = 0;

        for(int index = 0, sLen = s.length() ; index < sLen ; index++) {
            char currentChar = s.charAt(index);
            int numOccurrencesOfFirst = 1;

            int nextCharIndex = index + 1;
            while(nextCharIndex < sLen && s.charAt(nextCharIndex) == currentChar) {
                nextCharIndex++;
            }

            if(nextCharIndex < sLen) {
                numOccurrencesOfFirst = nextCharIndex - index;
                char secondChar = s.charAt(nextCharIndex);
                int secondCharStart = nextCharIndex;
                nextCharIndex++;
                while(nextCharIndex < sLen && s.charAt(nextCharIndex) == secondChar) {
                    nextCharIndex++;
                }

                numberOfSubstrings += (nextCharIndex - secondCharStart);
                index = secondCharStart - 1;
            }
        }

        return numberOfSubstrings;
    }

    /*
    Time : O(n)
    Space : O(n)
    Storing the occurrences of consecutive zeros and ones in an array. And adding up the minimum between consecutive
    counts.
     */
    public int countBinarySubstrings(String s) {

        List<Integer> occurrences = new LinkedList<Integer>();

        int currentCharOccurrenceCount = 1;
        for(int index = 1, sLen = s.length() ; index < sLen ; index++) {
            if(s.charAt(index) == s.charAt(index - 1)) {
                currentCharOccurrenceCount++;
            }
            else {
                occurrences.add(currentCharOccurrenceCount);
                currentCharOccurrenceCount = 1;
            }
        }

        occurrences.add(currentCharOccurrenceCount);

        int prevCharCount = 0;
        int numSubstrings = 0;

        for(int currCount : occurrences) {
            numSubstrings += Math.min(prevCharCount, currCount);
            prevCharCount = currCount;
        }

        return numSubstrings;
    }
}
