public class ShuffleTheArray {

    // In-place algorithm, using bit manipulation to hide a value within another.
    // Time : O(nums.length), Space : O(1).
    public int[] shuffle(int[] nums, int n) {

        for(int index = n ; index < nums.length ; index++) {
            nums[index - n] = nums[index - n] | (nums[index] << 10);
        }

        int mask = ((int)Math.pow(2, 10)) - 1;
        for(int index = n - 1 ; index > -1 ; index--) {
            int combinedNumber = nums[index];

            int xVal = (combinedNumber & mask);
            int yVal = (combinedNumber >> 10);
            nums[2*index + 1] = yVal;
            nums[2*index] = xVal;
        }

        return nums;
    }

    // Storing the result in input array.
    // Time : O(nums.length), Space : O(nums.length / 2)
    public int[] shuffle(int[] nums, int n) {
        int firstElIndex = 0, secondElIndex = n;
        int[] secondHalf = new int[n];

        for(int index = 0 ; index < n ; index++) {
            secondHalf[index] = nums[index + n];
        }

        for(int index = n - 1 ; index > -1; index--) {
            nums[2*index] = nums[index];
            nums[2*index + 1] = secondHalf[index];
        }

        return nums;
    }

    // Creating new array to store the result.
    // Time : O(nums.length), Space = O(nums.length)
    public int[] shuffle(int[] nums, int n) {
        int firstElIndex = 0, secondElIndex = n;
        int[] result = new int[2*n];
        int index = 0;

        while(firstElIndex < n) {
            result[index++] = nums[firstElIndex++];
            result[index++] = nums[secondElIndex++];
        }

        return result;
    }
}
