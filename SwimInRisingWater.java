import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SwimInRisingWater {
}

/*
Time: O(n^2 * Log(n^2))
Space: O(n^2)

Traverse the most feasible cell using a priority queue to keep track of the next viable cell
 */
class Solution {
    public int swimInWater(int[][] grid) {

        int nRows = grid.length, nCols = grid[0].length;

        boolean[][] visitStatus = new boolean[nRows][nCols];

        PriorityQueue<int[]> nodesToVisit = new PriorityQueue<int[]>(
                nRows * nCols,
                (int[] cell1, int[] cell2) -> {
                    return cell1[0] - cell2[0];
                });

        nodesToVisit.add(new int[]{grid[0][0], 0, 0});

        int currentTime = 0;
        while(!nodesToVisit.isEmpty()) {
            int[] currNodeInfo = nodesToVisit.poll();

            if(currentTime < currNodeInfo[0]) {
                currentTime = currNodeInfo[0];
            }

            int currRow = currNodeInfo[1], currCol = currNodeInfo[2];

            if(currRow == nRows - 1 && currCol == nCols - 1) {
                break;
            }

            if(currRow < nRows - 1) {
                if(visitStatus[currRow + 1][currCol] == false) {
                    visitStatus[currRow + 1][currCol] = true;
                    nodesToVisit.add(new int[]{grid[currRow + 1][currCol],
                            currRow + 1, currCol});
                }
            }

            if(currCol < nCols - 1) {
                if(visitStatus[currRow][currCol + 1] == false) {
                    visitStatus[currRow][currCol + 1] = true;
                    nodesToVisit.add(new int[]{grid[currRow][currCol + 1],
                            currRow, currCol + 1});
                }
            }

            if(currRow > 0) {
                if(visitStatus[currRow - 1][currCol] == false) {
                    visitStatus[currRow - 1][currCol] = true;
                    nodesToVisit.add(new int[]{grid[currRow - 1][currCol],
                            currRow - 1, currCol});
                }
            }

            if(currCol > 0) {
                if(visitStatus[currRow][currCol - 1] == false) {
                    visitStatus[currRow][currCol - 1] = true;
                    nodesToVisit.add(new int[]{grid[currRow][currCol - 1],
                            currRow, currCol - 1});
                }
            }
        }

        return currentTime;
    }
}

/*
Time: O(n^2 * Log(n^2))
Space: O(n^2)

The time required is equal to the largest level of water in any cell which was visited from 0, 0 to
the bottom right corner.

The problem boils down to finding the right value of the water level. All values after the minimum value will be
valid, while those less than minimum value would be insufficient to swim from top left to bottom right.

Performing binary search to find the right value of water level
 */
class Solution {
    public int swimInWater(int[][] grid) {

        int nGrids = grid.length;
        int lowerWaterLevel = 0, upperWaterLevel = nGrids * nGrids;

        while(lowerWaterLevel < upperWaterLevel) {
            int currWaterLevel = (lowerWaterLevel + upperWaterLevel) / 2;

            if(isValid(currWaterLevel, grid)) {
                upperWaterLevel = currWaterLevel;
            }
            else {
                lowerWaterLevel = currWaterLevel + 1;
            }
        }

        return upperWaterLevel;
    }

    private boolean isValid(int waterLevel, int[][] grid) {

        boolean valid = false;

        List<int[]> nodesToVisit = new LinkedList<int[]>();
        int nRows = grid.length, nCols = grid[0].length;

        if(grid[0][0] <= waterLevel) {
            nodesToVisit.add(new int[]{0, 0});
        }

        boolean[][] visitStatus = new boolean[nRows][nCols];
        visitStatus[0][0] = true;

        while(!nodesToVisit.isEmpty()) {
            int[] currNode = nodesToVisit.removeFirst();

            int currRow = currNode[0], currCol = currNode[1];


            if(currRow == nRows - 1 && currCol == nCols - 1) {
                valid = true;
                break;
            }

            if(currRow < nRows - 1) {
                if(visitStatus[currRow + 1][currCol] == false && grid[currRow + 1][currCol] <= waterLevel) {
                    visitStatus[currRow + 1][currCol] = true;
                    nodesToVisit.add(new int[]{(currRow + 1), currCol});
                }
            }

            if(currCol < nCols - 1) {
                if(visitStatus[currRow][currCol + 1] == false && grid[currRow][currCol + 1] <= waterLevel) {
                    visitStatus[currRow][currCol + 1] = true;
                    nodesToVisit.add(new int[]{currRow, (currCol + 1)});
                }
            }

            if(currRow > 0) {
                if(visitStatus[currRow - 1][currCol] == false && grid[currRow - 1][currCol] <= waterLevel) {
                    visitStatus[currRow - 1][currCol] = true;
                    nodesToVisit.add(new int[]{(currRow - 1), currCol});
                }
            }

            if(currCol > 0) {
                if(visitStatus[currRow][currCol - 1] == false && grid[currRow][currCol - 1] <= waterLevel) {
                    visitStatus[currRow][currCol - 1] = true;
                    nodesToVisit.add(new int[]{currRow, (currCol - 1)});
                }
            }
        }

        return valid;
    }
}