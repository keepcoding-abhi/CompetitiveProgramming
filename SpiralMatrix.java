import java.util.ArrayList;
import java.util.List;

/*
Time : O(m*n)
Space : O(1)
Traverse the perimeter and keep track of the number of rows and columns left to be traversed in the matrix.
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int nRows = matrix.length, nCols = matrix[0].length;

        List<Integer> result = new ArrayList<Integer>(nRows * nCols);

        int startRow = 0, startCol = 0, endRow = nRows - 1, endCol = nCols - 1;

        while(startRow <= endRow && startCol <= endCol) {

            for(int colIndex = startCol ; colIndex <= endCol ; colIndex++) {
                result.add(matrix[startRow][colIndex]);
            }

            for(int rowIndex = startRow + 1 ; rowIndex <= endRow ; rowIndex++) {
                result.add(matrix[rowIndex][endCol]);
            }

            if(startRow != endRow) {
                for(int colIndex = endCol - 1 ; colIndex >= startCol ; colIndex--) {
                    result.add(matrix[endRow][colIndex]);
                }
            }

            if(startCol != endCol) {
                for(int rowIndex = endRow - 1 ; rowIndex > startRow ; rowIndex--) {
                    result.add(matrix[rowIndex][startCol]);
                }
            }

            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }

        return result;
    }
}
