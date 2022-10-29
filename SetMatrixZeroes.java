public class SetMatrixZeroes {

    // 1) Using first row and first column to store whether the remaining row/col must be marked as zero.
    //    Time complexity : O(mn) Space complexity = O(1)
    public void setZeroes(int[][] matrix) {

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        boolean setFirstRowToZero = false;
        boolean setFirstColToZero = false;

        for(int colIndex = 0 ; colIndex < numCols ; colIndex++) {
            if(matrix[0][colIndex] == 0) {
                setFirstRowToZero = true;
                break;
            }
        }

        for(int rowIndex = 0 ; rowIndex < numRows ; rowIndex++) {
            if(matrix[rowIndex][0] == 0) {
                setFirstColToZero = true;
                break;
            }
        }

        for(int rowIndex = 1 ; rowIndex < numRows ; rowIndex++) {
            for(int colIndex = 1 ; colIndex < numCols ; colIndex++) {
                if(matrix[rowIndex][colIndex] == 0) {
                    matrix[0][colIndex] = 0;
                    matrix[rowIndex][0] = 0;
                }
            }
        }

        for(int rowIndex = 1 ; rowIndex < numRows ; rowIndex++) {
            for(int colIndex = 1 ; colIndex < numCols ; colIndex++) {

                if(matrix[0][colIndex] == 0 || matrix[rowIndex][0] == 0) {
                    matrix[rowIndex][colIndex] = 0;
                }
            }
        }

        if(setFirstRowToZero) {
            for(int colIndex = 0 ; colIndex < numCols ; colIndex++) {
                matrix[0][colIndex] = 0;
            }
        }

        if(setFirstColToZero) {
            for(int rowIndex = 0 ; rowIndex < numRows ; rowIndex++) {
                matrix[rowIndex][0] = 0;
            }
        }
    }

    // 2) Using hash tables to store the indices of rows and columns which must be made zero.
    // O(mn) time complexity. O(m + n) space complexity.
    public void setZeroes(int[][] matrix) {

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        HashSet<Integer> zeroRows = new HashSet<Integer>();
        HashSet<Integer> zeroCols = new HashSet<Integer>();

        for(int rowIndex = 0 ; rowIndex < numRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < numCols ; colIndex++) {
                if(matrix[rowIndex][colIndex] == 0) {
                    zeroRows.add(rowIndex);
                    zeroCols.add(colIndex);
                }
            }
        }

        Iterator<Integer> iterator = zeroRows.iterator();
        while(iterator.hasNext()) {
            int currentRow = iterator.next();

            for(int colIndex = 0 ; colIndex < numCols ; colIndex++) {
                matrix[currentRow][colIndex] = 0;
            }
        }

        iterator = zeroCols.iterator();
        while(iterator.hasNext()) {
            int currentCol = iterator.next();

            for(int rowIndex = 0 ; rowIndex < numRows ; rowIndex++) {
                matrix[rowIndex][currentCol] = 0;
            }
        }

    }

}
