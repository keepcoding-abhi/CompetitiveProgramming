public class ZigZagConversions {
}


class Solution {
    public String convert(String s, int numRows) {

        if(numRows == 1) {
            return s;
        }

        StringBuilder[] partialResultBuilder = new StringBuilder[numRows];

        for(int index = 0 ; index < numRows ; index++) {
            partialResultBuilder[index] = new StringBuilder();
        }

        for(int index = 0, length = s.length(), rowCounter = 0, stepSize = -1 ; index < length ; index++) {

            char currentChar = s.charAt(index);
            partialResultBuilder[rowCounter].append(currentChar);

            if(rowCounter == 0) {
                stepSize = 1;
            }
            else if(rowCounter == numRows - 1) {
                stepSize = -1;
            }

            rowCounter += stepSize;
        }

        StringBuilder finalResultBuilder = partialResultBuilder[0];
        for(int index = 1 ; index < numRows ; index++) {
            finalResultBuilder.append(partialResultBuilder[index]);
        }

        return finalResultBuilder.toString();
    }
}


class Solution_1 {
    public String convert(String s, int numRows) {

        if(numRows == 1)
            return s;

        int lengthOfInput = s.length();

        StringBuilder resultBuilder = new StringBuilder(lengthOfInput);
        int cycleLength = 2 * numRows - 2;

        for(int currentRow = 0 ; currentRow < numRows ; currentRow++) {
            for(int currentCharIndex = currentRow ; currentCharIndex < lengthOfInput ; ) {
                resultBuilder.append(s.charAt(currentCharIndex));

                currentCharIndex += cycleLength;
                int nextIndexInSameCycle = currentCharIndex - 2 * currentRow;
                if(currentRow != 0 && currentRow != numRows - 1 && nextIndexInSameCycle < lengthOfInput) {
                    resultBuilder.append(s.charAt(nextIndexInSameCycle));
                }
            }
        }

        return resultBuilder.toString();
    }
}