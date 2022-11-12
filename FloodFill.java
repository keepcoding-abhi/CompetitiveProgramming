public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int oldColor = image[sr][sc];
        if(oldColor != color) {
            changeColor(image, sr, sc, image[sr][sc], color);
        }

        return image;
    }

    private void changeColor(int[][] image, int currentRow, int currentCol, int oldColor, int newColor) {

        int numRows = image.length, numCols = image[0].length;

        if(currentRow > -1 && currentRow < numRows && currentCol > -1 && currentCol < numCols) {
            if(image[currentRow][currentCol] == oldColor) {
                image[currentRow][currentCol] = newColor;

                changeColor(image, currentRow - 1, currentCol, oldColor, newColor);
                changeColor(image, currentRow, currentCol + 1, oldColor, newColor);
                changeColor(image, currentRow + 1, currentCol, oldColor, newColor);
                changeColor(image, currentRow, currentCol - 1, oldColor, newColor);

            }
        }
    }

}
