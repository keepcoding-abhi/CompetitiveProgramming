public class SearchA2DMatrix {

    //1) First performing binary search column-wise and then row-wise. Outperformed the second solution.
    public boolean searchMatrix(int[][] matrix, int target) {

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        boolean found = false;
        int lower = 0, upper = numRows - 1;

        while (lower <= upper) {
            int mid = (lower + upper) / 2;

            int currentElement = matrix[mid][0];

            if (currentElement == target) {
                found = true;
                break;
            } else if (currentElement < target) {
                lower = mid + 1;
            } else {
                upper = mid - 1;
            }
        }

        if (!found) {

            if (upper == -1) {
                upper = 0;
            }

            int correctRow = upper;
            lower = 0;
            upper = numCols - 1;

            while (lower <= upper) {
                int mid = (lower + upper) / 2;

                int currentElement = matrix[correctRow][mid];

                if (currentElement == target) {
                    found = true;
                    break;
                } else if (currentElement > target) {
                    upper = mid - 1;
                } else {
                    lower = mid + 1;
                }
            }
        }

        return found;
    }

    //2) Doing binary search across rows and columns in the same loop.
    public boolean searchMatrix(int[][] matrix, int target) {

        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int lower = 0, upper = numRows * numCols - 1;

        boolean found = false;

        while(lower <= upper) {
            int mid = (lower + upper) / 2;

            int matrixRow = mid / numCols;
            int matrixCol = mid % numCols;

            int currentElement = matrix[matrixRow][matrixCol];

            if(currentElement < target) {
                lower = mid + 1;
            }
            else if(currentElement > target) {
                upper = mid - 1;
            }
            else {
                found = true;
                break;
            }
        }

        return found;
    }
}
