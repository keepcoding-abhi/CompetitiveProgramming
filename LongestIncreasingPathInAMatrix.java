public class LongestIncreasingPathInAMatrix {
}

/*
Time : O(m * n)
Space : O(m * n)

Topological sorting based solution.
Directed edge from a small valued node to its neighbor.
First computing the indegrees of all nodes. Each cell is a node.
Saving nodes which have a zero indegree, and processing each of these nodes by decrementing the indegrees of their
neighbors and saving those neighbors whose indegrees have become 1.
and creating a topological order.
 */
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int longestPath = 0, nRows = matrix.length, nCols = matrix[0].length;
        int[][] indegrees = new int[nRows][nCols];

        for(int r = 0 ; r < nRows ; r++) {
            for(int c = 0 ; c < nCols ; c++) {
                int currentEl = matrix[r][c];

                if(r > 0 && matrix[r - 1][c] > currentEl) {
                    indegrees[r - 1][c]++;
                }

                if(r < nRows - 1 && matrix[r + 1][c] > currentEl) {
                    indegrees[r + 1][c]++;
                }

                if(c > 0 && matrix[r][c - 1] > currentEl) {
                    indegrees[r][c - 1]++;
                }

                if(c < nCols - 1 && matrix[r][c + 1] > currentEl) {
                    indegrees[r][c + 1]++;
                }
            }
        }

        List<int[]> leaves = new LinkedList<int[]>();

        for(int r = 0 ; r < nRows ; r++) {
            for(int c = 0 ; c < nCols ; c++) {
                if(indegrees[r][c] == 0) {
                    leaves.add(new int[]{r, c});
                }
            }
        }

        while(!leaves.isEmpty()) {
            List<int[]> newLeaves = new LinkedList<int[]>();

            for(int[] currLeaf : leaves) {
                int r = currLeaf[0], c = currLeaf[1];
                int currentEl = matrix[r][c];

                if(r > 0 && matrix[r - 1][c] > currentEl) {
                    if((--indegrees[r - 1][c]) == 0) {
                        newLeaves.add(new int[]{r - 1, c});
                    }
                }

                if(r < nRows - 1 && matrix[r + 1][c] > currentEl) {
                    if((--indegrees[r + 1][c]) == 0) {
                        newLeaves.add(new int[]{r + 1, c});
                    }
                }

                if(c > 0 && matrix[r][c - 1] > currentEl) {
                    if((--indegrees[r][c - 1]) == 0) {
                        newLeaves.add(new int[]{r, c - 1});
                    }
                }

                if(c < nCols - 1 && matrix[r][c + 1] > currentEl) {
                    if((--indegrees[r][c + 1]) == 0) {
                        newLeaves.add(new int[]{r, c + 1});
                    }
                }
            }

            leaves = newLeaves;
            longestPath++;
        }

        return longestPath;
    }
}

/*
    Time : O(m * n)
    Space : O(m * n)

    Top-down memoized version.
 */
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int longestPath = 0, nRows = matrix.length, nCols = matrix[0].length;
        int[][] visited = new int[nRows][nCols];
        int[][] savedRes = new int[nRows][nCols];

        for(int r = 0 ; r < nRows ; r++) {
            for(int c = 0 ; c < nCols ; c++) {
                longestPath = Math.max(longestPath, getLongestIncPath(matrix, r, c, visited, savedRes));
            }
        }

        return longestPath;
    }

    private int getLongestIncPath(int[][] matrix, int currRow, int currCol, int[][] visited, int[][] savedRes) {

        int nRows = matrix.length, nCols = matrix[0].length;
        int pathLen = 0;

        if(currRow < nRows && currCol < nCols) {

            if(savedRes[currRow][currCol] == 0) {
                int currEl = matrix[currRow][currCol];
                visited[currRow][currCol] = 1;

                if(currRow < nRows - 1 && (visited[currRow + 1][currCol] == 0)) {
                    int rightNeighbor = matrix[currRow + 1][currCol];

                    if(rightNeighbor > currEl) {
                        pathLen = Math.max(pathLen, getLongestIncPath(matrix, currRow + 1, currCol, visited, savedRes));
                    }
                }

                if(currRow > 0 && (visited[currRow - 1][currCol] == 0)) {
                    int leftNeighbor = matrix[currRow - 1][currCol];

                    if(leftNeighbor > currEl) {
                        pathLen = Math.max(pathLen, getLongestIncPath(matrix, currRow - 1, currCol, visited, savedRes));
                    }
                }

                if(currCol < nCols - 1 && (visited[currRow][currCol + 1] == 0)) {
                    int bottomNeighbor = matrix[currRow][currCol + 1];

                    if(bottomNeighbor > currEl) {
                        pathLen = Math.max(pathLen, getLongestIncPath(matrix, currRow, currCol + 1, visited, savedRes));
                    }
                }

                if(currCol > 0 && (visited[currRow][currCol - 1] == 0)) {
                    int upNeighbor = matrix[currRow][currCol - 1];

                    if(upNeighbor > currEl) {
                        pathLen = Math.max(pathLen, getLongestIncPath(matrix, currRow, currCol - 1, visited, savedRes));
                    }
                }

                pathLen++;
                visited[currRow][currCol] = 0;
                savedRes[currRow][currCol] = pathLen;
            }
            else {
                pathLen = savedRes[currRow][currCol];
            }
        }

        return pathLen;
    }
}
