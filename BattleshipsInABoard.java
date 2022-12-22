public class BattleshipsInABoard {

    // Time complexity : O(m * n), Space : O(1)
    public int countBattleships(char[][] board) {

        int numberOfBattleships = 0;

        for(int rowIndex = 0, numRows = board.length, numCols = board[0].length ; rowIndex < numRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < numCols ; colIndex++) {
                if(board[rowIndex][colIndex] == 'X') {
                    if(rowIndex > 0 && board[rowIndex - 1][colIndex] == 'X') {
                        continue;
                    }

                    if(colIndex > 0 && board[rowIndex][colIndex - 1] == 'X') {
                        continue;
                    }

                    numberOfBattleships++;
                }
            }
        }

        return numberOfBattleships;
    }
}
