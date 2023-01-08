public class CheckIfSentenceIsPangram {

    // Using the 26 bits of an integer to record the occurrence of a character.
    public boolean checkIfPangram(String sentence) {
        int charRecorder = 0;

        for(int index = 0 , strLen = sentence.length() ; index < strLen ; index++) {
            char currentChar = sentence.charAt(index);

            charRecorder |= (1 << (currentChar - 'a'));
        }

        boolean isPangram = false;
        int allCharsPresentState = (1 << 26) - 1;

        if(charRecorder == allCharsPresentState) {
            isPangram = true;
        }

        return isPangram;
    }

    // Using array to indicate occurrence of a character.
    // Time complexity : O(n), space : O(n)
    public boolean checkIfPangram(String sentence) {
        int[] charFreqs = new int[26];

        for(int index = 0, strLen = sentence.length() ; index < strLen ; index++) {
            charFreqs[sentence.charAt(index) - 'a'] = 1;
        }

        boolean isPangram = true;

        for(int index = 0 ; index < 26 ; index++) {
            if(charFreqs[index] == 0) {
                isPangram = false;
                break;
            }
        }

        return isPangram;
    }
}
