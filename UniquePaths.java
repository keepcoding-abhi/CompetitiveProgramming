public class UniquePaths {

    //1) DP-based solution. Each cell in the pathsMatrix contains the number of ways to reach that cell from (0, 0).
    //  Initializing all the cells which can only be reached from one-way first.
    public int uniquePaths(int m, int n) {

        int[][] pathsMatrix = new int[m][n];

        for(int rowIndex = 0 ; rowIndex < m ; rowIndex++) {
            pathsMatrix[rowIndex][0] = 1;
        }

        for(int colIndex = 1 ; colIndex < n ; colIndex++) {
            pathsMatrix[0][colIndex] = 1;
        }

        for(int rowIndex = 1 ; rowIndex < m ; rowIndex++) {
            for(int colIndex = 1 ; colIndex < n ; colIndex++) {
                pathsMatrix[rowIndex][colIndex] = pathsMatrix[rowIndex][colIndex - 1] + pathsMatrix[rowIndex - 1][colIndex];
            }
        }

        return pathsMatrix[m - 1][n - 1];
    }

    //2) Using matrix to store intermediate results.
    public int uniquePaths(int m, int n) {

        int[][] pathsMatrix = new int[m][n];
        return traverseGraph(pathsMatrix, 0, 0, m, n);
    }

    private int traverseGraph(int[][] pathsMatrix, int currentRow, int currentCol, int numRows, int numCols) {

        int result;
        if(currentRow == numRows - 1 && currentCol == numCols - 1) {
            result = 1;
        }
        else if(currentRow < numRows && currentCol < numCols) {

            result = pathsMatrix[currentRow][currentCol];
            if(result == 0) {

                int downStep = traverseGraph(pathsMatrix, currentRow + 1, currentCol, numRows, numCols);
                int rightStep = traverseGraph(pathsMatrix, currentRow, currentCol + 1, numRows, numCols);
                result = downStep + rightStep;
                pathsMatrix[currentRow][currentCol] = result;
            }
        }
        else {
            result = 0;
        }

        return result;
    }

    //3) Brute Force solution. Exceeds time limit.
    public int uniquePaths(int m, int n) {

        return traverseGraph(0, 0, m, n);

    }

    private int traverseGraph(int currentRow, int currentCol, int numRows, int numCols) {

        int result;
        if(currentRow == numRows - 1 && currentCol == numCols - 1) {
            result = 1;
        }
        else if(currentRow < numRows && currentCol < numCols) {
            int downStep = traverseGraph(currentRow + 1, currentCol, numRows, numCols);
            int rightStep = traverseGraph(currentRow, currentCol + 1, numRows, numCols);
            result = downStep + rightStep;
        }
        else {
            result = 0;
        }

        return result;
    }

}
