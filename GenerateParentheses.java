import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses {
}

/*
Time: O(2^n)
Space: O(n)

Keep track of the number of left and right parentheses that have been added and never let the right parentheses exceed the left ones.
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        char[] currentParens = new char[2*n];
        List<String> generatedParens = new LinkedList<String>();

        createParenStrings(generatedParens, currentParens, 0, 0, 0);

        return generatedParens;
    }

    private void createParenStrings(List<String> generatedParens, char[] currentCombination, int leftAdded, int rightAdded, int currentIndex) {
        int combinationLen = currentCombination.length;

        if(currentIndex == combinationLen) {
            generatedParens.add(new String(currentCombination));
        }
        else {
            if(leftAdded > rightAdded) {
                currentCombination[currentIndex] = ')';
                createParenStrings(generatedParens, currentCombination, leftAdded, rightAdded + 1, currentIndex + 1);
            }

            if(leftAdded < combinationLen / 2) {
                currentCombination[currentIndex] = '(';
                createParenStrings(generatedParens, currentCombination, leftAdded + 1, rightAdded, currentIndex + 1);
            }
        }
    }
}

/*
    Time: O(2^n)
    Space: O(n^2), O(n) recursive call and strings of lengthO(n) are saved in each recursive call

    Divide the problem into generating valid string with 0 left parentheses and n, 1 left parentheses and n-1
    and so on.
*/
class Solution {
    public List<String> generateParenthesis(int n) {
        return generateParenRecursively(n);
    }

    private List<String> generateParenRecursively(int n) {

        List<String> generatedParens = new LinkedList<String>();

        if(n == 0) {
            generatedParens.add("");
        }
        else {
            for(int partition = 0 ; partition < n ; partition++) {
                List<String> leftSide = generateParenRecursively(partition);
                List<String> rightSide = generateParenRecursively(n - partition - 1);

                for(String left : leftSide) {
                    for(String right : rightSide) {
                        generatedParens.add("(" + left + ")" + right);
                    }
                }
            }
        }

        return generatedParens;
    }
}

/*

 */
class Solution {
    public List<String> generateParenthesis(int n) {
        char[] currentStr = new char[n * 2];
        List<String> allPatterns = new LinkedList<String>();

        generateParenthesis(0, 0, n, currentStr, allPatterns);

        return allPatterns;
    }

    private void generateParenthesis(int leftsAdded, int leftsClosed, int pairsPossible, char[] currentStr, List<String> allPatterns) {

        if((leftsAdded + leftsClosed) == currentStr.length) {
            allPatterns.add(new String(currentStr));
        }
        else {
            int currIndex = leftsAdded + leftsClosed;

            if(leftsClosed < leftsAdded) {
                currentStr[currIndex] = ')';
                generateParenthesis(leftsAdded, leftsClosed + 1, pairsPossible, currentStr, allPatterns);
            }

            if(leftsAdded < pairsPossible) {
                currentStr[currIndex] = '(';
                generateParenthesis(leftsAdded + 1, leftsClosed, pairsPossible, currentStr, allPatterns);
            }

        }
    }
}