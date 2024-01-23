import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueens {

    /*
    Time: O(n!), n! valid solutions ae possible
    Space: O(n * n!), since an intermediate representation of integer array is used for each configuration of
    chess board

    Place the queen at each valid position and backtrack when there are no valid positions left.
     */
    public List<List<String>> solveNQueens(int n) {
        int[] colPos = new int[n];

        List<int[]> colPositions = new LinkedList<int[]>();

        generateNQueens(colPos, 0, n, colPositions);

        List<List<String>> results;
        if(colPositions.size() > 0) {
            results = formatChessBoard(colPositions);
        }
        else {
            results = new ArrayList<List<String>>(0);
        }

        return results;
    }

    private List<List<String>> formatChessBoard(List<int[]> queenPositions) {
        List<List<String>> boards = new ArrayList<List<String>>(queenPositions.size());

        int n = queenPositions.get(0).length;

        char[] template = new char[n];
        Arrays.fill(template, '.');


        for(int[] boardConfiguration : queenPositions) {
            List<String> board = new ArrayList<String>(n);

            for(int index = 0 ; index < n ; index++) {

                template[boardConfiguration[index]] = 'Q';
                board.add(new String(template));
                template[boardConfiguration[index]] = '.';
            }

            boards.add(board);
        }

        return boards;
    }

    private void generateNQueens(int[] colPositions, int currentRow, int n, List<int[]> results) {
        if(currentRow == n) {
            int[] newResult = new int[n];

            for(int index = 0 ; index < n ; index++) {
                newResult[index] = colPositions[index];
            }

            results.add(newResult);
        }
        else {
            for(int colIndex = 0 ; colIndex < n ; colIndex++) {
                colPositions[currentRow] = colIndex;
                if(checkQueenEligibility(currentRow, colPositions)) {

                    generateNQueens(colPositions, currentRow + 1, n, results);
                }
            }
        }
    }

    private boolean checkQueenEligibility(int currentRow, int[] colPositions) {
        boolean queenValid = true;

        int colPositionOfCurrentQueen = colPositions[currentRow];

        for(int index = 0 ; index < currentRow ; index++) {
            if((colPositions[index] == colPositionOfCurrentQueen) || (Math.abs(currentRow - index) == Math.abs(colPositions[index] - colPositionOfCurrentQueen))) {
                queenValid = false;
                break;
            }
        }

        return queenValid;
    }
}
