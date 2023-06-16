public class MaximumProductSubarray {

    // Time : O(n)
    // Space : O(1)
    // 3 numbers are tracked for each sub-array as we go from left to right.
    // First is the current number, second is the product of current number with the maximum product recorded so far.
    // Third is the product of current number with the minimum product recorded so far.
    // Minimum number is relevant because it could be a negative quantity and when the current number is negative
    // then the minimum product could give the highest product.
    // If the current number is a zero, reset the value as we need to start recording a new sub-array from this point.
    public int maxProduct(int[] nums) {

        int highestProduct = nums[0];
        int maxSoFar = nums[0], minSoFar = nums[0];

        for(int index = 1 ; index < nums.length ; index++) {
            int currentNum = nums[index];

            int productWithMax = currentNum * maxSoFar;
            int productWithMin = currentNum * minSoFar;

            maxSoFar = Math.max(currentNum, Math.max(productWithMax, productWithMin));
            minSoFar = Math.min(currentNum, Math.min(productWithMax, productWithMin));

            if(currentNum == 0) {
                maxSoFar = 0;
                minSoFar = 0;
            }

            if(maxSoFar > highestProduct) {
                highestProduct = maxSoFar;
            }
        }

        return highestProduct;
    }

    /*
    Time : O(n^2)
    Space : O(1)
    Finding the product of each possible sub-array.
     */
    public int maxProduct(int[] nums) {
        int highestProduct = nums[0];

        for(int index = 0, len = nums.length ; index < len ; index++) {
            int currentProduct = nums[index];

            if(currentProduct > highestProduct) {
                highestProduct = currentProduct;
            }

            for(int index1 = index + 1 ; index1 < len ; index1++) {
                currentProduct *= nums[index1];

                if(currentProduct > highestProduct) {
                    highestProduct = currentProduct;
                }

            }
        }

        return highestProduct;
    }
}
