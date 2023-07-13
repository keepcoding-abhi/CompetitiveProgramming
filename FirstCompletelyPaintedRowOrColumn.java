import java.util.HashMap;
import java.util.Map;

public class FirstCompletelyPaintedRowOrColumn {

    /*
    Time : O(m * n + (m + n) * (m * n))
    Space : O(m*n + (m + n))
    Brute force solution. Storing the row and column indices of each number in a map.
    Upon seeing the number in array fetching the row and column indices and storing the number of times
    an element in each row and column has been painted in another array. Checking the counts array at each iteration
    after traversing an element in the array. If any row has been painted the number of column time or vice versa,
    it means that the row has been painted completely.
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        Map<Integer, Integer> rowIndices = new HashMap<Integer, Integer>();
        Map<Integer, Integer> colIndices = new HashMap<Integer, Integer>();

        int m = mat.length, n = mat[0].length;

        for(int rowIndex = 0 ; rowIndex < m ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < n ; colIndex++) {
                int currentNumber = mat[rowIndex][colIndex];

                rowIndices.put(currentNumber, rowIndex);
                colIndices.put(currentNumber, colIndex);
            }
        }

        int[] paintedRowCount = new int[m], paintedColCount = new int[n];
        int firstIndex = 0;

        for(int index = 0 ; index < arr.length ; index++) {
            int currentNum = arr[index];
            int rowIndexOfCurrentNum = rowIndices.get(currentNum);
            int colIndexOfCurrentNum = colIndices.get(currentNum);

            paintedRowCount[rowIndexOfCurrentNum]++;
            paintedColCount[colIndexOfCurrentNum]++;

            if(paintedRowCount[rowIndexOfCurrentNum] == n || paintedColCount[colIndexOfCurrentNum] == m) {
                firstIndex = index;
                break;
            }
        }

        return firstIndex;
    }
}
