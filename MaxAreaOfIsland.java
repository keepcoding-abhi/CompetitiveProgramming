import java.util.Deque;
import java.util.LinkedList;

public class MaxAreaOfIsland {

    /*
    Time: O(m*n)
    Space: O(m*n)

    Find an unvisited island and traverse all of its cells using DFS.
     */
    public int maxAreaOfIsland(int[][] grid) {

        int nRows = grid.length, nCols = grid[0].length;
        int maxArea = 0;

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
                if(grid[rowIndex][colIndex] == 1) {
                    maxArea = Math.max(maxArea, countAreaOfIsland(grid, rowIndex, colIndex));
                }
            }
        }

        return maxArea;
    }

    private int countAreaOfIsland(int[][] grid, int row, int col) {
        int nRows = grid.length, nCols = grid[0].length;
        int area = 0;

        if(row < nRows && col < nCols && row > -1 && col > -1 && grid[row][col] == 1) {
            grid[row][col] = -1;
            area = 1;

            area += countAreaOfIsland(grid, row + 1, col);
            area += countAreaOfIsland(grid, row, col + 1);
            area += countAreaOfIsland(grid, row - 1, col);
            area += countAreaOfIsland(grid, row, col - 1);
        }

        return area;
    }

    /*
    Time: O(m*n)
    Space: O(m*n)

    Traversing the island in an iterative order using stack.
     */
    public int maxAreaOfIsland(int[][] grid) {

        int nRows = grid.length, nCols = grid[0].length;
        int maxArea = 0;

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
                if(grid[rowIndex][colIndex] == 1) {
                    maxArea = Math.max(maxArea, countAreaOfIsland(grid, rowIndex, colIndex));
                }
            }
        }

        return maxArea;
    }

    private int countAreaOfIsland(int[][] grid, int row, int col) {
        int nRows = grid.length, nCols = grid[0].length;
        int area = 0;

        Deque<Integer> cellsToVisit = new LinkedList<Integer>();

        cellsToVisit.push((row * nCols) + col);
        grid[row][col] = -1;

        while(!cellsToVisit.isEmpty()) {
            int currentCell = cellsToVisit.pop();
            area++;

            int currRow = currentCell / nCols;
            int currCol = currentCell % nCols;

            if(currRow > 0 && grid[currRow - 1][currCol] == 1) {
                grid[currRow - 1][currCol] = -1;
                cellsToVisit.push((currRow - 1) * nCols + currCol);
            }

            if(currCol > 0 && grid[currRow][currCol - 1] == 1) {
                grid[currRow][currCol - 1] = -1;
                cellsToVisit.push((currRow) * nCols + (currCol - 1));
            }

            if(currRow < nRows - 1 && grid[currRow + 1][currCol] == 1) {
                grid[currRow + 1][currCol] = -1;
                cellsToVisit.push((currRow + 1) * nCols + (currCol));
            }

            if(currCol < nCols - 1 && grid[currRow][currCol + 1] == 1) {
                grid[currRow][currCol + 1] = -1;
                cellsToVisit.push((currRow) * nCols + (currCol + 1));
            }
        }

        return area;
    }
}
