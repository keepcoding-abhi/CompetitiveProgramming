public class TheMaze {
}

/*
Time : O(m*n*(m + n)), in the worst case we'll visit each cell in the matrix and at each location we might
traverse through the entire row/column.
Space : O(m*n)
 */
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] savedResults = new int[maze.length][maze[0].length];
        return findPath(maze, start[0], start[1], destination[0], destination[1], savedResults);
    }

    private boolean findPath(int[][] maze, int currentRow, int currentCol, int targetRow, int targetCol, int[][] savedResults) {

        boolean pathExists;

        if(currentRow < 0 || currentRow >= maze.length || currentCol < 0 || currentCol >= maze[0].length) {
            pathExists = false;
        }
        else if(currentRow == targetRow && currentCol == targetCol) {
            pathExists = true;
        }
        else if(maze[currentRow][currentCol] != 0) {
            pathExists = false;
        }
        else if(savedResults[currentRow][currentCol] != 0) {
            pathExists = false;
        }
        else {
            maze[currentRow][currentCol] = 2;
            int rowAfterGoingDown = goDown(maze, currentRow, currentCol);
            pathExists = (rowAfterGoingDown != currentRow) ? findPath(maze, rowAfterGoingDown, currentCol, targetRow, targetCol, savedResults) : false;

            if(!pathExists) {
                int rowAfterGoingUp = goUp(maze, currentRow, currentCol);
                pathExists = (rowAfterGoingUp != currentRow) ? findPath(maze, rowAfterGoingUp, currentCol, targetRow, targetCol, savedResults) : false;

                if(!pathExists) {
                    int colAfterGoingRight = goRight(maze, currentRow, currentCol);
                    pathExists = (colAfterGoingRight != currentCol) ? findPath(maze, currentRow, colAfterGoingRight, targetRow, targetCol, savedResults) : false;

                    if(!pathExists) {
                        int colAfterGoingLeft = goLeft(maze, currentRow, currentCol);
                        pathExists = (colAfterGoingLeft != currentCol) ? findPath(maze, currentRow, colAfterGoingLeft, targetRow, targetCol, savedResults) : false;
                    }
                }
            }

            if(!pathExists) {
                savedResults[currentRow][currentCol] = -1;
            }

            maze[currentRow][currentCol] = 0;
        }

        return pathExists;
    }

    private int goLeft(int[][] maze, int row, int col) {
        int currentCol = col;

        while(currentCol > -1 && (maze[row][currentCol] == 0 || maze[row][currentCol] == 2)) {
            currentCol--;
        }

        return currentCol + 1;
    }

    private int goRight(int[][] maze, int row, int col) {
        int currentCol = col;

        while(currentCol < maze[0].length && (maze[row][currentCol] == 0 || maze[row][currentCol] == 2)) {
            currentCol++;
        }

        return currentCol - 1;
    }

    private int goUp(int[][] maze, int row, int col) {
        int currentRow = row;

        while(currentRow > -1 && (maze[currentRow][col] == 0 || maze[currentRow][col] == 2)) {
            currentRow--;
        }

        return currentRow + 1;
    }

    private int goDown(int[][] maze, int row, int col) {
        int currentRow = row;

        while(currentRow < maze.length && (maze[currentRow][col] == 0 || maze[currentRow][col] == 2)) {
            currentRow++;
        }

        return currentRow - 1;
    }
}

/*
Time : O(mn * (m + n))
Space : O(mn)
Breadth First Search solution.
 */
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        return findPath(maze, start, destination);
    }

    private boolean findPath(int[][] maze, int[] start, int[] destination) {

        boolean pathExists = false;

        int nRows = maze.length, nCols = maze[0].length;
        int[][] visitedCells = new int[nRows][nCols];

        visitedCells[start[0]][start[1]] = 1;
        Deque<int[]> enqueuedCells = new LinkedList<int[]>();
        enqueuedCells.addLast(start);

        int targetRow = destination[0], targetCol = destination[1];

        while(!enqueuedCells.isEmpty()) {
            int[] currentCell = enqueuedCells.removeFirst();

            int currentRow = currentCell[0], currentCol = currentCell[1];

            if(currentRow == targetRow && currentCol == targetCol) {
                pathExists = true;
                break;
            }

            int rowAfterGoingUp = goUp(maze, currentRow, currentCol);

            if(rowAfterGoingUp != currentRow && visitedCells[rowAfterGoingUp][currentCol] != 1) {
                enqueuedCells.addLast(new int[]{rowAfterGoingUp, currentCol});
                visitedCells[rowAfterGoingUp][currentCol] = 1;
            }

            int rowAfterGoingDown = goDown(maze, currentRow, currentCol);

            if(rowAfterGoingDown != currentRow && visitedCells[rowAfterGoingDown][currentCol] != 1) {
                enqueuedCells.addLast(new int[]{rowAfterGoingDown, currentCol});
                visitedCells[rowAfterGoingDown][currentCol] = 1;
            }

            int colAfterGoingRight = goRight(maze, currentRow, currentCol);

            if(colAfterGoingRight != currentCol && visitedCells[currentRow][colAfterGoingRight] != 1) {
                enqueuedCells.addLast(new int[]{currentRow, colAfterGoingRight});
                visitedCells[currentRow][colAfterGoingRight] = 1;
            }

            int colAfterGoingLeft = goLeft(maze, currentRow, currentCol);

            if(colAfterGoingLeft != currentCol && visitedCells[currentRow][colAfterGoingLeft] != 1) {
                enqueuedCells.addLast(new int[]{currentRow, colAfterGoingLeft});
                visitedCells[currentRow][colAfterGoingLeft] = 1;
            }
        }

        return pathExists;
    }

    private int goLeft(int[][] maze, int row, int col) {
        int currentCol = col;

        while(currentCol > -1 && (maze[row][currentCol] == 0 || maze[row][currentCol] == 2)) {
            currentCol--;
        }

        return currentCol + 1;
    }

    private int goRight(int[][] maze, int row, int col) {
        int currentCol = col;

        while(currentCol < maze[0].length && (maze[row][currentCol] == 0 || maze[row][currentCol] == 2)) {
            currentCol++;
        }

        return currentCol - 1;
    }

    private int goUp(int[][] maze, int row, int col) {
        int currentRow = row;

        while(currentRow > -1 && (maze[currentRow][col] == 0 || maze[currentRow][col] == 2)) {
            currentRow--;
        }

        return currentRow + 1;
    }

    private int goDown(int[][] maze, int row, int col) {
        int currentRow = row;

        while(currentRow < maze.length && (maze[currentRow][col] == 0 || maze[currentRow][col] == 2)) {
            currentRow++;
        }

        return currentRow - 1;
    }
}