import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PacificAtlanticWaterFlow {
}

/*
Time : O(n * m) in the worst case we'll traverse each cell of the "heights" matrix. This will happen when the value
of each cell is same.
Space : O(n*m) required to keep track of the nodes that are reachable from each ocean.
Applying DFS from a reachable node of each ocean to trace other reachable nodes.
Later adding the nodes that are reachable from both oceans into the result.
 */
class Solution1 {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int nRows = heights.length, nCols = heights[0].length;
        List<List<Integer>> flowableCells = new LinkedList<List<Integer>>();
        int[][] pacificReachable = new int[nRows][nCols], atlanticReachable = new int[nRows][nCols];

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            pacificReachable[rowIndex][0] = 1;
            atlanticReachable[rowIndex][nCols - 1] = 1;

            applyDFS(rowIndex, 0, heights, pacificReachable);
            applyDFS(rowIndex, nCols - 1, heights, atlanticReachable);
        }

        for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
            pacificReachable[0][colIndex] = 1;
            atlanticReachable[nRows - 1][colIndex] = 1;

            applyDFS(0, colIndex, heights, pacificReachable);
            applyDFS(nRows - 1, colIndex, heights, atlanticReachable);
        }

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
                if(pacificReachable[rowIndex][colIndex] != 0 && atlanticReachable[rowIndex][colIndex] != 0) {
                    List<Integer> newCell = new ArrayList<Integer>(2);
                    newCell.add(rowIndex);
                    newCell.add(colIndex);

                    flowableCells.add(newCell);
                }
            }
        }

        return flowableCells;
    }

    private void applyDFS(int currentRow, int currentCol, int[][] heights, int[][] reachable) {
        int currentHeight = heights[currentRow][currentCol];

        if(currentRow > 0 && heights[currentRow - 1][currentCol] >= currentHeight && reachable[currentRow - 1][currentCol] == 0) {
            reachable[currentRow - 1][currentCol] = 1;
            applyDFS(currentRow - 1, currentCol, heights, reachable);
        }

        if(currentCol > 0 && heights[currentRow][currentCol - 1] >= currentHeight && reachable[currentRow][currentCol - 1] == 0) {
            reachable[currentRow][currentCol - 1] = 1;
            applyDFS(currentRow, currentCol - 1, heights, reachable);
        }

        if(currentRow < heights.length - 1 && heights[currentRow + 1][currentCol] >= currentHeight && reachable[currentRow + 1][currentCol] == 0) {
            reachable[currentRow + 1][currentCol] = 1;
            applyDFS(currentRow + 1, currentCol, heights, reachable);
        }

        if(currentCol < heights[0].length - 1 && heights[currentRow][currentCol + 1] >= currentHeight && reachable[currentRow][currentCol + 1] == 0) {
            reachable[currentRow][currentCol + 1] = 1;
            applyDFS(currentRow, currentCol + 1, heights, reachable);
        }
    }
}

/*
Time : O(n*m)
Space : O(n*m)
Using BFS for performing the graph traversal.
 */
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int nRows = heights.length, nCols = heights[0].length;
        List<List<Integer>> flowableCells = new LinkedList<List<Integer>>();
        int[][] pacificReachable = new int[nRows][nCols], atlanticReachable = new int[nRows][nCols];

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            pacificReachable[rowIndex][0] = 1;
            atlanticReachable[rowIndex][nCols - 1] = 1;

            applyBFS(rowIndex, 0, heights, pacificReachable);
            applyBFS(rowIndex, nCols - 1, heights, atlanticReachable);
        }

        for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
            pacificReachable[0][colIndex] = 1;
            atlanticReachable[nRows - 1][colIndex] = 1;

            applyBFS(0, colIndex, heights, pacificReachable);
            applyBFS(nRows - 1, colIndex, heights, atlanticReachable);
        }

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
                if(pacificReachable[rowIndex][colIndex] != 0 && atlanticReachable[rowIndex][colIndex] != 0) {
                    List<Integer> newCell = new ArrayList<Integer>(2);
                    newCell.add(rowIndex);
                    newCell.add(colIndex);

                    flowableCells.add(newCell);
                }
            }
        }

        return flowableCells;
    }

    private void applyBFS(int startRow, int startCol, int[][] heights, int[][] reachable) {
        int nRows = heights.length, nCols = heights[0].length;
        Deque<Integer> enqueuedCells = new LinkedList<Integer>();

        enqueuedCells.add(startRow * nCols + startCol);

        while(!enqueuedCells.isEmpty()) {
            int currentCell = enqueuedCells.remove();
            int currentRow = currentCell / nCols;
            int currentCol = currentCell % nCols;
            int currentHeight = heights[currentRow][currentCol];

            if(currentRow > 0 && heights[currentRow - 1][currentCol] >= currentHeight && reachable[currentRow - 1][currentCol] == 0) {
                reachable[currentRow - 1][currentCol] = 1;
                enqueuedCells.add((currentRow - 1) * nCols + currentCol);
            }

            if(currentCol > 0 && heights[currentRow][currentCol - 1] >= currentHeight && reachable[currentRow][currentCol - 1] == 0) {
                reachable[currentRow][currentCol - 1] = 1;
                enqueuedCells.add((currentRow) * nCols + currentCol - 1);
            }

            if(currentRow < heights.length - 1 && heights[currentRow + 1][currentCol] >= currentHeight && reachable[currentRow + 1][currentCol] == 0) {
                reachable[currentRow + 1][currentCol] = 1;
                enqueuedCells.add((currentRow + 1) * nCols + currentCol);
            }

            if(currentCol < heights[0].length - 1 && heights[currentRow][currentCol + 1] >= currentHeight && reachable[currentRow][currentCol + 1] == 0) {
                reachable[currentRow][currentCol + 1] = 1;
                enqueuedCells.add((currentRow) * nCols + currentCol + 1);
            }
        }
    }
}

/*
Memoized version of the next approach.
Time : O(m*n)
Space : O(m*n)
 */
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int nRows = heights.length, nCols = heights[0].length;
        List<List<Integer>> flowableCells = new LinkedList<List<Integer>>();
        int[][] pacificReachable = new int[nRows][nCols], atlanticReachable = new int[nRows][nCols];

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            pacificReachable[rowIndex][0] = 1;
            atlanticReachable[rowIndex][nCols - 1] = 1;
        }

        for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
            pacificReachable[0][colIndex] = 1;
            atlanticReachable[nRows - 1][colIndex] = 1;
        }

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
                if(isCurrentCellReachable(rowIndex, colIndex, heights, pacificReachable) && isCurrentCellReachable(rowIndex, colIndex, heights, atlanticReachable)) {
                    List<Integer> currentCell = new ArrayList<Integer>(2);
                    currentCell.add(rowIndex);
                    currentCell.add(colIndex);

                    flowableCells.add(currentCell);
                }
            }
        }

        return flowableCells;
    }

    private boolean isCurrentCellReachable(int rowIndex, int colIndex, int[][] heights, int[][] reachableHistory) {
        boolean reachable = false;
        int nRows = heights.length, nCols = heights[0].length;
        int currentCellStatus = reachableHistory[rowIndex][colIndex];

        if(currentCellStatus == 1) {
            reachable = true;
        }
        else {

            int currentHeight = heights[rowIndex][colIndex];
            heights[rowIndex][colIndex] = -1;

            if(rowIndex > 0 && heights[rowIndex - 1][colIndex] != -1 && heights[rowIndex - 1][colIndex] <= currentHeight) {
                reachable = isCurrentCellReachable(rowIndex - 1, colIndex, heights, reachableHistory);
            }

            if(!reachable && colIndex > 0 && heights[rowIndex][colIndex - 1] != -1 && heights[rowIndex][colIndex - 1] <= currentHeight) {
                reachable = isCurrentCellReachable(rowIndex, colIndex - 1, heights, reachableHistory);
            }

            if(!reachable && colIndex < nCols - 1 && heights[rowIndex][colIndex + 1] != -1 && heights[rowIndex][colIndex + 1] <= currentHeight) {
                reachable = isCurrentCellReachable(rowIndex, colIndex + 1, heights, reachableHistory);
            }

            if(!reachable && rowIndex < nRows - 1 && heights[rowIndex + 1][colIndex] != -1 && heights[rowIndex + 1][colIndex] <= currentHeight) {
                reachable = isCurrentCellReachable(rowIndex + 1, colIndex, heights, reachableHistory);
            }

            heights[rowIndex][colIndex] = currentHeight;
            reachableHistory[rowIndex][colIndex] = reachable ? 1 : -1;
        }

        return reachable;
    }
}

/*
Time : O((m*n)^(m*n)), the whole matrix could be explored for each cell.
Space : O(m*n) in the worst case O(m*n) recursive calls could be made.

Brute Force solution. Exploring all possible ways to reach the shore from each cell.
 */
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int nRows = heights.length, nCols = heights[0].length;
        List<List<Integer>> flowableCells = new LinkedList<List<Integer>>();

        for(int rowIndex = 0 ; rowIndex < nRows ; rowIndex++) {
            for(int colIndex = 0 ; colIndex < nCols ; colIndex++) {
                if(canReachPacific(rowIndex, colIndex, heights) && canReachAtlantic(rowIndex, colIndex, heights)) {
                    List<Integer> currentCell = new ArrayList<Integer>(2);
                    currentCell.add(rowIndex);
                    currentCell.add(colIndex);

                    flowableCells.add(currentCell);
                }
            }
        }

        return flowableCells;
    }

    private boolean canReachAtlantic(int rowIndex, int colIndex, int[][] heights) {
        boolean atlanticReachable = false;
        int nRows = heights.length, nCols = heights[0].length;

        if(rowIndex == nRows - 1 || colIndex == nCols - 1) {
            atlanticReachable = true;
        }
        else {

            int currentHeight = heights[rowIndex][colIndex];
            heights[rowIndex][colIndex] = -1;

            if(rowIndex > 0 && heights[rowIndex - 1][colIndex] != -1 && heights[rowIndex - 1][colIndex] <= currentHeight) {
                atlanticReachable = canReachAtlantic(rowIndex - 1, colIndex, heights);
            }

            if(!atlanticReachable && colIndex > 0 && heights[rowIndex][colIndex - 1] != -1 && heights[rowIndex][colIndex - 1] <= currentHeight) {
                atlanticReachable = canReachAtlantic(rowIndex, colIndex - 1, heights);
            }

            if(!atlanticReachable && colIndex < nCols - 1 && heights[rowIndex][colIndex + 1] != -1 && heights[rowIndex][colIndex + 1] <= currentHeight) {
                atlanticReachable = canReachAtlantic(rowIndex, colIndex + 1, heights);
            }

            if(!atlanticReachable && rowIndex < nRows - 1 && heights[rowIndex + 1][colIndex] != -1 && heights[rowIndex + 1][colIndex] <= currentHeight) {
                atlanticReachable = canReachAtlantic(rowIndex + 1, colIndex, heights);
            }

            heights[rowIndex][colIndex] = currentHeight;
        }

        return atlanticReachable;
    }

    private boolean canReachPacific(int rowIndex, int colIndex, int[][] heights) {
        boolean pacificReachable = false;
        int nRows = heights.length, nCols = heights[0].length;

        if(rowIndex == 0 || colIndex == 0) {
            pacificReachable = true;
        }
        else {

            int currentHeight = heights[rowIndex][colIndex];
            heights[rowIndex][colIndex] = -1;

            if(rowIndex > 0 && heights[rowIndex - 1][colIndex] != -1 && heights[rowIndex - 1][colIndex] <= currentHeight) {
                pacificReachable = canReachPacific(rowIndex - 1, colIndex, heights);
            }

            if(!pacificReachable && colIndex > 0 && heights[rowIndex][colIndex - 1] != -1 && heights[rowIndex][colIndex - 1] <= currentHeight) {
                pacificReachable = canReachPacific(rowIndex, colIndex - 1, heights);
            }

            if(!pacificReachable && colIndex < nCols - 1 && heights[rowIndex][colIndex + 1] != -1 && heights[rowIndex][colIndex + 1] <= currentHeight) {
                pacificReachable = canReachPacific(rowIndex, colIndex + 1, heights);
            }

            if(!pacificReachable && rowIndex < nRows - 1 && heights[rowIndex + 1][colIndex] != -1 && heights[rowIndex + 1][colIndex] <= currentHeight) {
                pacificReachable = canReachPacific(rowIndex + 1, colIndex, heights);
            }

            heights[rowIndex][colIndex] = currentHeight;
        }

        return pacificReachable;
    }
}
