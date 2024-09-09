package DataStructures;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BidirectionalBFS {
    /*
    Time: O(n^2)
    Space: O(n^2)

    Starting BFS from source and destination to minimize the steps required to find the distance between the two.
     */
    class BFS {
        private int[][] visitedCellDists, grid;
        private int nodesInCurrLevel, nodesInNextLevel, levelNumber;
        private int nRows, nCols;
        private Deque<Integer> nodesToVisit;
        private static final int[][] dirs = new int[][]{{0,1}, {1, 0},
                {0, -1}, {-1, 0}};

        public BFS(int nRows, int nCols, int startRow, int startCol, int[][] grid) {
            visitedCellDists = new int[nRows][nCols];
            this.grid = grid;
            this.nRows = nRows;
            this.nCols = nCols;

            for(int[] cells : visitedCellDists) {
                Arrays.fill(cells, -1);
            }

            nodesInCurrLevel = 1;
            nodesInNextLevel = 0;
            levelNumber = 0;
            visitedCellDists[startRow][startCol] = 0;
            nodesToVisit = new LinkedList<Integer>();
            nodesToVisit.addLast(startRow * nCols + startCol);
        }

        public int extendBidirectionalBFS(BFS otherDirection) {

            int status = -1;

            if(!nodesToVisit.isEmpty()) {
                int currCell = nodesToVisit.removeFirst();
                int currRow = currCell / nCols, currCol =
                        currCell % nCols;

                if(otherDirection.visitedCellDists[currRow][currCol]
                        != -1) {
                    status = this.visitedCellDists[currRow][currCol] +
                            otherDirection.visitedCellDists[currRow][currCol];
                }
                else {
                    status = 0;

                    for(int[] dir : dirs) {
                        int nextRow = currRow + dir[0];
                        int nextCol = currCol + dir[1];

                        if(nextRow > -1 && nextRow < nRows &&
                                nextCol > -1 && nextCol < nCols) {
                            if(this.visitedCellDists[nextRow][nextCol]
                                    == -1 && grid[nextRow][nextCol] == 0) {
                                this.visitedCellDists[nextRow][nextCol] =
                                        levelNumber + 1;
                                nodesToVisit.add(nextRow * nCols +
                                        nextCol);
                                nodesInNextLevel++;
                            }
                        }
                    }
                }

                nodesInCurrLevel--;
                if(nodesInCurrLevel == 0) {
                    nodesInCurrLevel = nodesInNextLevel;
                    nodesInNextLevel = 0;
                    levelNumber++;
                }
            }

            return status;
        }
    }

    class Solution {
        public int shortestPath(int[][] grid) {
            int nRows = grid.length, nCols = grid[0].length;
            int shortestPath;

            if(grid[nRows - 1][nCols - 1] == 1 || grid[0][0] == 1) {
                shortestPath = -1;
            }
            else if(nRows == 1 && nCols == 1) {
                if(grid[0][0] == 0) {
                    shortestPath = 0;
                }
                else {
                    shortestPath = -1;
                }
            }
            else {
                BFS bfsFromStart = new BFS(nRows, nCols, 0, 0, grid);
                BFS bfsFromEnd = new BFS(nRows, nCols, nRows - 1,
                        nCols - 1, grid);

                int bfsStatus = 0;
                boolean workingFromStart = true;

                while(bfsStatus == 0) {
                    if(workingFromStart) {
                        bfsStatus = bfsFromStart.extendBidirectionalBFS(bfsFromEnd);
                    }
                    else {
                        bfsStatus = bfsFromEnd.extendBidirectionalBFS(bfsFromStart);
                    }

                    workingFromStart = !workingFromStart;
                }

                shortestPath = bfsStatus;
            }

            return shortestPath;
        }
    }
}
