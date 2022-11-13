public class UniquePaths2 {

    // Most efficient solution. Uses just one dimensional array to record the number of paths from source to the cells in
    // current row.
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int numberOfPaths = 0;

        if(obstacleGrid[0][0] != 1) {

            int numCols = obstacleGrid[0].length;
            int numRows = obstacleGrid.length;

            int[] pathsFromSource = new int[numCols];
            pathsFromSource[0] = 1;

            for(int rowIndex = 0 ; rowIndex < numRows ; rowIndex++) {
                for(int colIndex = 0 ; colIndex < numCols ; colIndex++) {
                    if(obstacleGrid[rowIndex][colIndex] == 1) {
                        pathsFromSource[colIndex] = 0;
                    }
                    else if(colIndex > 0) {
                        pathsFromSource[colIndex] += pathsFromSource[colIndex - 1];
                    }
                }
            }

            numberOfPaths = pathsFromSource[numCols - 1];
        }

        return numberOfPaths;
    }

    // Going from source to destination by starting from the source and then visiting its neighbors.
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int numberOfPaths = 0;
        int numRows = obstacleGrid.length, numCols = obstacleGrid[0].length;

        if(obstacleGrid[numRows - 1][numCols - 1] != 1 && obstacleGrid[0][0] != 1) {

            boolean obstacleFound = false;
            for(int rowIndex = 0 ; rowIndex < numRows ; rowIndex++) {
                if(obstacleGrid[rowIndex][0] == 1) {
                    obstacleGrid[rowIndex][0] = 0;
                    obstacleFound = true;
                }
                else {
                    obstacleGrid[rowIndex][0] = obstacleFound ? 0 : 1;
                }
            }

            obstacleFound = false;
            for(int colIndex = 1 ; colIndex < numCols ; colIndex++) {

                if(obstacleGrid[0][colIndex] == 1) {
                    obstacleGrid[0][colIndex] = 0;
                    obstacleFound = true;
                }
                else {
                    obstacleGrid[0][colIndex] = obstacleFound ? 0 : 1;
                }
            }

            for(int rowIndex = 1 ; rowIndex < numRows ; rowIndex++) {
                for(int colIndex = 1 ; colIndex < numCols ; colIndex++) {
                    if(obstacleGrid[rowIndex][colIndex] == 1) {
                        obstacleGrid[rowIndex][colIndex] = 0;
                    }
                    else {
                        obstacleGrid[rowIndex][colIndex] = obstacleGrid[rowIndex][colIndex - 1] + obstacleGrid[rowIndex - 1][colIndex];
                    }
                }
            }

            numberOfPaths = obstacleGrid[numRows - 1][numCols - 1];
        }

        return numberOfPaths;
    }

    // In the two approaches given below we find a path from every cell to the destination.
    // Starting from the destination and its neighbors.
    // Using table to record the number of possible paths from a cell.
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int numberOfPaths = 0;
        int numRows = obstacleGrid.length, numCols = obstacleGrid[0].length;

        if(obstacleGrid[numRows - 1][numCols - 1] != 1) {
            int[][] pathsPossible = new int[numRows][numCols];

            for(int rowIndex = numRows - 1 ; rowIndex > -1 ; rowIndex--) {

                if(obstacleGrid[rowIndex][numCols - 1] == 1) {
                    break;
                }
                else {
                    pathsPossible[rowIndex][numCols - 1] = 1;
                }

            }

            for(int colIndex = numCols - 1 ; colIndex > -1 ; colIndex--) {

                if(obstacleGrid[numRows - 1][colIndex] == 1) {
                    break;
                }
                else {
                    pathsPossible[numRows - 1][colIndex] = 1;
                }

            }

            for(int rowIndex = numRows - 2 ; rowIndex > -1 ; rowIndex--) {
                for(int colIndex = numCols - 2 ; colIndex > -1 ; colIndex--) {
                    if(obstacleGrid[rowIndex][colIndex] == 1) {
                        pathsPossible[rowIndex][colIndex] = 0;
                    }
                    else {
                        pathsPossible[rowIndex][colIndex] = pathsPossible[rowIndex + 1][colIndex] + pathsPossible[rowIndex][colIndex + 1];
                    }
                }
            }

            numberOfPaths = pathsPossible[0][0];
        }

        return numberOfPaths;
    }

    // Using the existing table instead of creating a new one.
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int numberOfPaths = 0;
        int numRows = obstacleGrid.length, numCols = obstacleGrid[0].length;

        if(obstacleGrid[numRows - 1][numCols - 1] != 1) {

            boolean obstacleFound = false;
            for(int rowIndex = numRows - 1 ; rowIndex > -1 ; rowIndex--) {

                if(obstacleGrid[rowIndex][numCols - 1] == 1) {
                    obstacleGrid[rowIndex][numCols - 1] = 0;
                    obstacleFound = true;
                }
                else {
                    obstacleGrid[rowIndex][numCols - 1] = obstacleFound ? 0 : 1;
                }
            }

            obstacleFound = false;
            for(int colIndex = numCols - 2 ; colIndex > -1 ; colIndex--) {

                if(obstacleGrid[numRows - 1][colIndex] == 1) {
                    obstacleGrid[numRows - 1][colIndex] = 0;
                    obstacleFound = true;
                }
                else {
                    obstacleGrid[numRows - 1][colIndex] = obstacleFound ? 0 : 1;
                }
            }

            for(int rowIndex = numRows - 2 ; rowIndex > -1 ; rowIndex--) {
                for(int colIndex = numCols - 2 ; colIndex > -1 ; colIndex--) {

                    if(obstacleGrid[rowIndex][colIndex] == 1) {
                        obstacleGrid[rowIndex][colIndex] = 0;
                    }
                    else {
                        obstacleGrid[rowIndex][colIndex] = obstacleGrid[rowIndex + 1][colIndex] + obstacleGrid[rowIndex][colIndex + 1];
                    }
                }
            }

            numberOfPaths = obstacleGrid[0][0];
        }

        return numberOfPaths;
    }

    // Brute force solution, exceeds time limit.
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int numberOfPaths = 0;
        if(obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1] != 1) {
            numberOfPaths = findNumberOfPaths(obstacleGrid, 0, 0);
        }

        return numberOfPaths;
    }

    private int findNumberOfPaths(int[][] grid, int currRow, int currCol) {

        int numRows = grid.length, numCols = grid[0].length;
        int numberOfPaths = 0;

        if(currRow >= 0 && currRow < numRows && currCol >= 0 && currCol < numCols) {
            if(currRow == numRows - 1 && currCol == numCols - 1) {
                numberOfPaths = 1;
            }
            else if(grid[currRow][currCol] == 1) {
                numberOfPaths = 0;
            }
            else {
                numberOfPaths = findNumberOfPaths(grid, currRow, currCol + 1);
                numberOfPaths += findNumberOfPaths(grid, currRow + 1, currCol);
            }
        }

        return numberOfPaths;
    }

}
