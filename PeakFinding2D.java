public class PeakFinding2D {

    //1) O(n Log(m)) solution. Global maximum on either row or column and divide and conquer on the other.
    class Solution {
        public int[] findPeakGrid(int[][] mat) {
            int numberOfRows = mat.length;
            int numberOfColumns = mat[0].length;

            int rowLowerBound = 0, rowUpperBound = numberOfRows - 1;

            int[] peak = new int[]{-1, -1};

            while(rowLowerBound <= rowUpperBound) {
                int currentRow = (rowLowerBound + rowUpperBound) / 2;

                int maxElementInCurrentRow = mat[currentRow][0];
                int indexOfMaxElementInCurrentRow = 0;
                for(int j = 1 ; j < numberOfColumns ; j++) {
                    if(mat[currentRow][j] > maxElementInCurrentRow) {
                        maxElementInCurrentRow = mat[currentRow][j];
                        indexOfMaxElementInCurrentRow = j;
                    }
                }

                if(currentRow - 1 > -1 && mat[currentRow - 1][indexOfMaxElementInCurrentRow] > maxElementInCurrentRow) {
                    rowUpperBound = currentRow - 1;
                }
                else if(currentRow + 1 < numberOfRows && mat[currentRow + 1][indexOfMaxElementInCurrentRow] > maxElementInCurrentRow) {
                    rowLowerBound = currentRow + 1;
                }
                else {
                    peak[0] = currentRow;
                    peak[1] = indexOfMaxElementInCurrentRow;
                    break;
                }
            }

            return peak;
        }
    }

    //2) Incorrect O(Log(n)Log(m)) solution. Instead of global maximum we're choosing the peak in the currentRow.
    // But the peak is not the largest element in that row.
    // This largest element if ignored could turn out to be the neighbor of the peak we're eventually going to select.
    // Once, we select an element of the currentRow. We either terminate the algorithm if that element is 2D peak or jump to the other
    // half from that row. In other words, we will never be visiting the same row again.
    class Solution {
        public int[] findPeakGrid(int[][] mat) {
            int numberOfRows = mat.length;
            int numberOfColumns = mat[0].length;

            int rowLowerBound = 0, rowUpperBound = numberOfRows - 1;

            int[] peak = new int[]{-1, -1};

            while(rowLowerBound <= rowUpperBound) {
                int currentRow = (rowLowerBound + rowUpperBound) / 2;

                int columnLowerBound = 0, columnUpperBound = numberOfColumns - 1;
                int peakColumnIndex = -1, currentRowsPeakElement = -1;

                while(columnLowerBound <= columnUpperBound) {
                    int currentColumn = (columnLowerBound + columnUpperBound) / 2;

                    int currentCell = mat[currentRow][currentColumn];
                    if(currentColumn - 1 > -1 && currentCell < mat[currentRow][currentColumn - 1]) {
                        columnUpperBound = currentColumn - 1;
                    }
                    else if (currentColumn + 1 < numberOfColumns && currentCell < mat[currentRow][currentColumn + 1]) {
                        columnLowerBound = currentColumn + 1;
                    }
                    else {
                        peakColumnIndex = currentColumn;
                        currentRowsPeakElement = currentCell;
                        break;
                    }
                }


                if(currentRow - 1 > -1 && mat[currentRow - 1][peakColumnIndex] > currentRowsPeakElement) {
                    rowUpperBound = currentRow - 1;
                }
                else if(currentRow + 1 < numberOfRows && mat[currentRow + 1][peakColumnIndex] > currentRowsPeakElement) {
                    rowLowerBound = currentRow + 1;
                }
                else {
                    peak[0] = currentRow;
                    peak[1] = peakColumnIndex;
                    break;
                }
            }

            return peak;
        }
    }
}
