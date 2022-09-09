public class LetterCombinationsOfAPhoneNumber {
}

class Solution {
    public List<String> letterCombinations(String digits) {

        int numberOfDigits = digits.length();
        List<String> result = new LinkedList<String>();

        if(numberOfDigits > 0) {
            List<char[]> possibleCharacters = new ArrayList<char[]>(numberOfDigits);

            for(int index = 0 ; index < numberOfDigits ; index++) {
                possibleCharacters.add(getCharsForNumber(digits.charAt(index)));
            }

            char[] currentString = new char[numberOfDigits];

            generateCombinations(result, currentString, possibleCharacters, 0);
        }
        return result;
    }

    private void generateCombinations(List<String> result, char[] currentString, List<char[]> possibleChars, int currentLevel) {

        if(currentLevel == currentString.length) {
            result.add(new String(currentString));
        }
        else {
            char[] possibleCharsOnCurrentLevel = possibleChars.get(currentLevel);
            for(int index = 0, numberOfPossibleChars = possibleCharsOnCurrentLevel.length ; index < numberOfPossibleChars ; index++) {
                currentString[currentLevel] = possibleCharsOnCurrentLevel[index];
                generateCombinations(result, currentString, possibleChars, currentLevel + 1);
            }
        }
    }

    private char[] getCharsForNumber(char digit) {

        char[] allChars = null;

        switch(digit) {
            case '2':
                allChars = new char[]{'a', 'b', 'c'};
                break;
            case '3':
                allChars = new char[]{'d', 'e', 'f'};
                break;
            case '4':
                allChars = new char[]{'g', 'h', 'i'};
                break;
            case '5':
                allChars = new char[]{'j', 'k', 'l'};
                break;
            case '6':
                allChars = new char[]{'m', 'n', 'o'};
                break;
            case '7':
                allChars = new char[]{'p', 'q', 'r', 's'};
                break;
            case '8':
                allChars = new char[]{'t', 'u', 'v'};
                break;
            case '9':
                allChars = new char[]{'w', 'x', 'y', 'z'};
                break;
        }

        return allChars;
    }
}

//2) Slower solution without recursion
class Solution {
    public List<String> letterCombinations(String digits) {

        int numberOfDigits = digits.length();
        List<String> result = new LinkedList<String>();

        if(numberOfDigits > 0) {
            List<char[]> possibleCharacters = new ArrayList<char[]>(numberOfDigits);

            for(int index = 0 ; index < numberOfDigits ; index++) {
                possibleCharacters.add(getCharsForNumber(digits.charAt(index)));
            }

            char[] currentString = new char[numberOfDigits];

            result.add("");
            generateCombinations(result, possibleCharacters);
        }
        return result;
    }

    private void generateCombinations(List<String> result, List<char[]> possibleChars) {

        for(int index = 1, totalDigits = possibleChars.size() ; index <= totalDigits ; index++) {

            while(result.get(0).length() != index) {
                String currentString = result.remove(0);

                char[] currentChars = possibleChars.get(index - 1);

                for(char currentChar : currentChars) {
                    result.add(currentString + currentChar);
                }
            }
        }

    }

    private char[] getCharsForNumber(char digit) {

        char[] allChars = null;

        switch(digit) {
            case '2':
                allChars = new char[]{'a', 'b', 'c'};
                break;
            case '3':
                allChars = new char[]{'d', 'e', 'f'};
                break;
            case '4':
                allChars = new char[]{'g', 'h', 'i'};
                break;
            case '5':
                allChars = new char[]{'j', 'k', 'l'};
                break;
            case '6':
                allChars = new char[]{'m', 'n', 'o'};
                break;
            case '7':
                allChars = new char[]{'p', 'q', 'r', 's'};
                break;
            case '8':
                allChars = new char[]{'t', 'u', 'v'};
                break;
            case '9':
                allChars = new char[]{'w', 'x', 'y', 'z'};
                break;
        }

        return allChars;
    }
}
