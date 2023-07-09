public class TransposeMatrix {
    /*
    Time : O(n*m)
    Space : O(1)
     */
    public int[][] transpose(int[][] matrix) {
        int nRowsOrig = matrix.length, nColsOrig = matrix[0].length;

        int[][] transpose = new int[nColsOrig][nRowsOrig];

        for(int rowIndex = 0 ; rowIndex < nRowsOrig ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < nColsOrig ; colIndex++) {
                transpose[colIndex][rowIndex] = matrix[rowIndex][colIndex];
            }
        }

        return transpose;
    }
}
