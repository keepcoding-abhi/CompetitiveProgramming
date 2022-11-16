public class SpiralMatrix2 {

    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        int minRow = 0, minCol = 0, maxRow = matrix.length - 1, maxCol = matrix[0].length - 1;
        int currentCount = 1;

        while(minRow <= maxRow && minCol <= maxCol) {
            currentCount = traverseCircumference(matrix, minRow, minCol, maxRow, maxCol, currentCount);
            minRow++;
            minCol++;
            maxRow--;
            maxCol--;
        }

        return matrix;
    }

    private int traverseCircumference(int[][] matrix, int minRow, int minCol, int maxRow, int maxCol, int currentCount) {

        for(int colIndex = minCol ; colIndex <= maxCol ; colIndex++) {
            matrix[minRow][colIndex] = currentCount;
            currentCount++;
        }

        for(int rowIndex = minRow + 1 ; rowIndex <= maxRow ; rowIndex++) {
            matrix[rowIndex][maxCol] = currentCount;
            currentCount++;
        }

        if(minRow < maxRow) {
            for(int colIndex = maxCol - 1 ; colIndex >= minCol ; colIndex--) {
                matrix[maxRow][colIndex] = currentCount;
                currentCount++;
            }
        }

        if(minCol < maxCol) {
            for(int rowIndex = maxRow - 1 ; rowIndex > minRow ; rowIndex--) {
                matrix[rowIndex][minCol] = currentCount;
                currentCount++;
            }
        }

        return currentCount;
    }
}
