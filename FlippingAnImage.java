public class FlippingAnImage {

    // Time : O(row * col), space : O(1)
    // Flipping using XOR
    public int[][] flipAndInvertImage(int[][] image) {
        int rows = image.length, cols = image[0].length;

        for(int index = 0 ; index < rows ; index++) {
            int lowerIndex = 0, upperIndex = cols - 1;

            while(lowerIndex <= upperIndex) {
                int temp = image[index][lowerIndex] ^ 1;
                image[index][lowerIndex] = image[index][upperIndex] ^ 1;
                image[index][upperIndex] = temp;

                lowerIndex++;
                upperIndex--;
            }

        }

        return image;
    }

    // Time : O(row * col), space : O(1).
    // Flipping using ternary operator.
    public int[][] flipAndInvertImage(int[][] image) {
        int rows = image.length, cols = image[0].length;

        for(int index = 0 ; index < rows ; index++) {
            int lowerIndex = 0, upperIndex = cols - 1;

            while(lowerIndex <= upperIndex) {
                int temp = image[index][lowerIndex];
                image[index][lowerIndex] = (image[index][upperIndex] == 0 ? 1 : 0);
                image[index][upperIndex] = (temp == 0 ? 1 : 0);

                lowerIndex++;
                upperIndex--;
            }

        }

        return image;
    }
}
