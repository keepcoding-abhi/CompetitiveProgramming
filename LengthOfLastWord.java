public class LengthOfLastWord {
}

class Solution {
    public int lengthOfLastWord(String s) {

        int lengthOfLastWord = 0;
        boolean lastWasSpace = true;
        for(int currentIndex = 0, strlen = s.length() ; currentIndex < strlen ; currentIndex++) {
            int currentChar = s.charAt(currentIndex);

            if(currentChar == ' ' || currentChar == '\t') {
                lastWasSpace = true;
            }
            else {

                if(lastWasSpace) {
                    lengthOfLastWord = 0;
                    lastWasSpace = false;
                }

                lengthOfLastWord++;
            }
        }

        return lengthOfLastWord;
    }
}