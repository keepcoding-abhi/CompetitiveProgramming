import java.util.LinkedList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
}

class Solution {

    /*
    Time: O(n* (4^(n)))
    Space: O(n)

    4^(n) combinations possible, O(n) time required to create strings
    Backtracking solution, substituting each possible character.
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<String>();

        if(digits.length() > 0) {
            generateCombinations(digits, new char[digits.length()], 0, result);
        }

        return result;
    }

    private void generateCombinations(String digits, char[] currentWord, int index, List<String> words) {
        if(index == currentWord.length) {
            words.add(new String(currentWord));
        }
        else {
            int currentNum = digits.charAt(index) - '0';
            char[] charsFromCurrentNum = getCharsFromNum(currentNum);

            for(char currentChar : charsFromCurrentNum) {
                currentWord[index] = currentChar;
                generateCombinations(digits, currentWord, index + 1, words);
            }
        }
    }

    private char[] getCharsFromNum(int num) {
        char[] result = null;

        switch(num) {
            case 2:
                result = new char[]{'a', 'b', 'c'};
                break;
            case 3:
                result = new char[]{'d', 'e', 'f'};
                break;
            case 4:
                result = new char[]{'g', 'h', 'i'};
                break;
            case 5:
                result = new char[]{'j', 'k', 'l'};
                break;
            case 6:
                result = new char[]{'m', 'n', 'o'};
                break;
            case 7:
                result = new char[]{'p', 'q', 'r', 's'};
                break;
            case 8:
                result = new char[]{'t', 'u', 'v'};
                break;
            case 9:
                result = new char[]{'w', 'x', 'y', 'z'};
                break;
        }

        return result;
    }

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
