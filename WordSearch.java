public class WordSearch {
}
 /*
 Time : O( m * n * (3 ^ L))
 Space : O(L)
 For each possible start position, backtrack and find rest of the characters.
  */
class Solution {
    public boolean exist(char[][] board, String word) {

        int nRows = board.length, nCols = board[0].length;

        boolean exists = false;
        char firstChar = word.charAt(0);

        for(int rowIndex = 0 ; !exists && rowIndex < nRows ; rowIndex++) {
            for(int colIndex = 0 ; !exists && colIndex < nCols ; colIndex++) {
                if(board[rowIndex][colIndex] == firstChar) {
                    board[rowIndex][colIndex] = 0;
                    exists = checkExistence(board, word, 1, rowIndex, colIndex);
                    board[rowIndex][colIndex] = firstChar;
                }
            }
        }

        return exists;
    }

    private boolean checkExistence(char[][] board, String word, int strIndex, int rowIndex, int colIndex) {

        int wordLen = word.length();
        boolean exists = false;

        if(strIndex == wordLen) {
            exists = true;
        }
        else {
            char originalVal = board[rowIndex][colIndex];
            board[rowIndex][colIndex] = 0;
            char currentChar = word.charAt(strIndex);

            if(rowIndex > 0 && board[rowIndex - 1][colIndex] == currentChar) {
                exists = checkExistence(board, word, strIndex + 1, rowIndex - 1, colIndex);
            }

            if(!exists && rowIndex < board.length - 1 && board[rowIndex + 1][colIndex] == currentChar) {
                exists = checkExistence(board, word, strIndex + 1, rowIndex + 1, colIndex);
            }

            if(!exists && colIndex > 0 && board[rowIndex][colIndex - 1] == currentChar) {
                exists = checkExistence(board, word, strIndex + 1, rowIndex, colIndex - 1);
            }

            if(!exists && colIndex < board[0].length - 1 && board[rowIndex][colIndex + 1] == currentChar) {
                exists = checkExistence(board, word, strIndex + 1, rowIndex, colIndex + 1);
            }

            board[rowIndex][colIndex] = originalVal;
        }

        return exists;
    }
}
