public class ValidSudoku {
}

/*
Time: O(n^2)
Space: O(n)

Using bit-masking to record whether a number has occurred in the current row, column or sub-grid in the past.
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {

        int nRows = board.length, nCols = board[0].length;
        int[] rowEls = new int[nRows];
        int[] colEls = new int[nRows];
        int[] subGridEls = new int[nRows];

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {

                char currentChar = board[rowIndex][colIndex];
                if(currentChar == '.') {
                    continue;
                }

                int currentEl = currentChar - '1';

                if((rowEls[rowIndex] & (1 << currentEl)) != 0) {
                    return false;
                }

                rowEls[rowIndex] = (rowEls[rowIndex] | (1 << currentEl));

                if((colEls[colIndex] & (1 << currentEl)) != 0) {
                    return false;
                }

                colEls[colIndex] = (colEls[colIndex] | (1 << currentEl));

                int gridIndex = (rowIndex / 3) * 3 + (colIndex / 3);

                if((subGridEls[gridIndex] & (1 << currentEl)) != 0) {
                    return false;
                }

                subGridEls[gridIndex] = subGridEls[gridIndex] | (1 << currentEl);
            }
        }

        return true;
    }
}
