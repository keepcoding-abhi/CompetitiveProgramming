import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
}

/*
Time : O(m*n)
Space : O(m*n) maximum possible recursive calls.
Finding a 1 in the grid and using DFS to label all 1s that belong to its island to 0.
 */
class Solution {
    public int numIslands(char[][] grid) {
        int nRows = grid.length, nCols = grid[0].length, currentGroupNumber = 0;

        int[][] intermediateGridRep = new int[nRows][nCols];

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
                if(grid[rowIndex][colIndex] == '1') {
                    fillWithDFS(grid, rowIndex, colIndex);
                    currentGroupNumber++;
                }
            }
        }

        return currentGroupNumber;
    }

    private void fillWithDFS(char[][] grid, int currentRow, int currentCol) {

        if(currentRow >= 0 && currentCol >=0 && currentRow < grid.length && currentCol < grid[0].length && grid[currentRow][currentCol] == '1') {
            grid[currentRow][currentCol] = '0';

            fillWithDFS(grid, currentRow + 1, currentCol);
            fillWithDFS(grid, currentRow, currentCol + 1);
            fillWithDFS(grid, currentRow - 1, currentCol);
            fillWithDFS(grid, currentRow, currentCol - 1);
        }

    }
}

/*
Filling the graph with BFS
Time : O(m*n)
Space : O(min(m, n)) Image to understand the reasoning behind space complexity : https://imgur.com/gallery/M58OKvB
 */
class Solution {
    public int numIslands(char[][] grid) {
        int nRows = grid.length, nCols = grid[0].length, currentGroupNumber = 0;

        int[][] intermediateGridRep = new int[nRows][nCols];

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
                if(grid[rowIndex][colIndex] == '1') {
                    fillWithBFS(grid, rowIndex, colIndex);
                    currentGroupNumber++;
                }
            }
        }

        return currentGroupNumber;
    }

    private void fillWithBFS(char[][] grid, int startRow, int startCol) {

        int nRows = grid.length, nCols = grid[0].length;
        Queue<int[]> enqueuedCells = new LinkedList<int[]>();
        enqueuedCells.add(new int[]{startRow, startCol});

        while(!enqueuedCells.isEmpty()) {
            int[] currentCell = enqueuedCells.remove();
            int currentRow = currentCell[0];
            int currentCol = currentCell[1];

            if(currentRow > 0 && grid[currentRow - 1][currentCol] == '1') {
                grid[currentRow - 1][currentCol] = '0';
                enqueuedCells.add(new int[]{currentRow - 1, currentCol});
            }

            if(currentCol > 0 && grid[currentRow][currentCol - 1] == '1') {
                grid[currentRow][currentCol - 1] = '0';
                enqueuedCells.add(new int[]{currentRow, currentCol - 1});
            }

            if(currentRow < nRows - 1 && grid[currentRow + 1][currentCol] == '1') {
                grid[currentRow + 1][currentCol] = '0';
                enqueuedCells.add(new int[]{currentRow + 1, currentCol});
            }

            if(currentCol < nCols - 1 && grid[currentRow][currentCol + 1] == '1') {
                grid[currentRow][currentCol + 1] = '0';
                enqueuedCells.add(new int[]{currentRow, currentCol + 1});
            }

        }

    }
}
