public class IslandPerimeter {

    // Traverses all the cells but the number of comparisions performed on each cell is halved as compared to the brute force approach.
    public int islandPerimeter(int[][] grid) {

        int numRows = grid.length, numCols = grid[0].length;
        int perimeter = 0;

        int firstLandRow = -1, firstLandCol = -1;
        for(int rowIndex = 0 ; rowIndex < numRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < numCols ; colIndex++) {
                if(grid[rowIndex][colIndex] == 1) {
                    perimeter += 4;

                    if(rowIndex != 0 && grid[rowIndex - 1][colIndex] == 1) {
                        perimeter -= 2;
                    }

                    if(colIndex != 0 && grid[rowIndex][colIndex - 1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }

        return perimeter;
    }

    // Uses recursion to travers all the adjoining pieces of land. But modifies the input in the end.
    public int islandPerimeter(int[][] grid) {

        int numRows = grid.length, numCols = grid[0].length;
        int perimeter = 0;

        int firstLandRow = -1, firstLandCol = -1;
        for(int rowIndex = 0 ; rowIndex < numRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < numCols ; colIndex++) {
                if(grid[rowIndex][colIndex] == 1) {
                    firstLandRow = rowIndex;
                    firstLandCol = colIndex;
                    break;
                }
            }
            if(firstLandRow != -1) {
                break;
            }
        }

        perimeter = computePerimeter(grid, firstLandRow, firstLandCol);

        return perimeter;
    }

    private int computePerimeter(int[][] grid, int currentRow, int currentCol) {

        int perimeter = 0, numCols = grid[0].length, numRows = grid.length;

        if(currentRow < 0 || currentCol < 0 || currentRow >= numRows || currentCol >= numCols) {
            perimeter = 1;
        }
        else if(grid[currentRow][currentCol] == 0) {
            perimeter = 1;
        }
        else if(grid[currentRow][currentCol] == -1) {
            perimeter = 0;
        }
        else {
            grid[currentRow][currentCol] = -1;
            perimeter += computePerimeter(grid, currentRow - 1, currentCol);
            perimeter += computePerimeter(grid, currentRow, currentCol - 1);
            perimeter += computePerimeter(grid, currentRow + 1, currentCol);
            perimeter += computePerimeter(grid, currentRow, currentCol + 1);
            // grid[currentRow][currentCol] = 1; This line causes the algorithm to fail on [[1,1][1,1]]
            // Perimeter of the same points will be calculated with different element being at the bottom of the stack.
        }

        return perimeter;
    }

    //Brute Force solution visits every cell in the matrix.
    public int islandPerimeter(int[][] grid) {

        int numRows = grid.length, numCols = grid[0].length;
        int perimeter = 0;

        for(int rowIndex = 0 ; rowIndex < numRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < numCols ; colIndex++) {
                if(grid[rowIndex][colIndex] == 1) {
                    if(rowIndex == 0 || grid[rowIndex - 1][colIndex] == 0) {
                        perimeter++;
                    }

                    if(rowIndex == numRows - 1 || grid[rowIndex + 1][colIndex] == 0) {
                        perimeter++;
                    }

                    if(colIndex == 0 || grid[rowIndex][colIndex - 1] == 0) {
                        perimeter++;
                    }

                    if(colIndex == numCols - 1 || grid[rowIndex][colIndex + 1] == 0) {
                        perimeter++;
                    }
                }
            }
        }

        return perimeter;
    }

}
