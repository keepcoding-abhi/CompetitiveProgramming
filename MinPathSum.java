public class MinPathSum {

    // Bottom-up DP solution. T : O(nm), S : O(nm)
    public int minPathSum(int[][] grid) {

        int numRows = grid.length, numCols = grid[0].length;
        int[][] minDistanceFromSource = new int[numRows][numCols];

        minDistanceFromSource[0][0] = grid[0][0];

        for(int rowIndex = 1 ; rowIndex < numRows ; rowIndex++) {
            minDistanceFromSource[rowIndex][0] = minDistanceFromSource[rowIndex - 1][0] + grid[rowIndex][0];
        }

        for(int colIndex = 1 ; colIndex < numCols ; colIndex++) {
            minDistanceFromSource[0][colIndex] = minDistanceFromSource[0][colIndex - 1] + grid[0][colIndex];
        }

        for(int rowIndex = 1 ; rowIndex < numRows ; rowIndex++) {
            for(int colIndex = 1 ; colIndex < numCols ; colIndex++) {
                int distanceFromTop = minDistanceFromSource[rowIndex - 1][colIndex];
                int distanceFromLeft = minDistanceFromSource[rowIndex][colIndex - 1];

                minDistanceFromSource[rowIndex][colIndex] = grid[rowIndex][colIndex] + Math.min(distanceFromTop, distanceFromLeft);
            }
        }

        return minDistanceFromSource[numRows - 1][numCols - 1];
    }

    // Top down DP solution.
    public int minPathSum(int[][] grid) {

        int numRows = grid.length, numCols = grid[0].length;
        int[][] minDistanceToDestination = new int[numRows][numCols];

        for(int rowIndex = 0 ; rowIndex < numRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < numCols ; colIndex++) {
                minDistanceToDestination[rowIndex][colIndex] = -1;
            }
        }
        minDistanceToDestination[numRows - 1][numCols - 1] = grid[numRows - 1][numCols - 1];

        return findMinPath(grid, 0, 0, minDistanceToDestination);
    }

    private int findMinPath(int[][] grid, int currRow, int currCol, int[][] minDistanceToDestination) {

        int numRows = grid.length, numCols = grid[0].length;
        int distanceToDestination;

        if(currRow < 0 || currRow >= grid.length || currCol < 0 || currCol >= grid[0].length) {
            distanceToDestination = Integer.MAX_VALUE;
        }
        else if(minDistanceToDestination[currRow][currCol] != -1) {
            distanceToDestination = minDistanceToDestination[currRow][currCol];
        }
        else {
            int distanceFromDown = findMinPath(grid, currRow + 1, currCol, minDistanceToDestination);
            int distanceFromRight = findMinPath(grid, currRow, currCol + 1, minDistanceToDestination);

            distanceToDestination = grid[currRow][currCol] + Math.min(distanceFromDown, distanceFromRight);
            minDistanceToDestination[currRow][currCol] = distanceToDestination;
        }

        return distanceToDestination;
    }

    // Brute force solution. Exceeds time limit.
    public int minPathSum(int[][] grid) {
        return findMinPath(grid, 0, 0);
    }

    private int findMinPath(int[][] grid, int currRow, int currCol) {

        int numRows = grid.length, numCols = grid[0].length;
        int distanceToDestination;

        if(currRow == numRows - 1 && currCol == numCols - 1) {
            distanceToDestination = grid[currRow][currCol];
        }
        else if(currRow < 0 || currRow >= grid.length || currCol < 0 || currCol >= grid[0].length) {
            distanceToDestination = Integer.MAX_VALUE;
        }
        else {
            int distanceFromDown = findMinPath(grid, currRow + 1, currCol);
            int distanceFromRight = findMinPath(grid, currRow, currCol + 1);

            distanceToDestination = grid[currRow][currCol] + Math.min(distanceFromDown, distanceFromRight);
        }

        return distanceToDestination;
    }
}
