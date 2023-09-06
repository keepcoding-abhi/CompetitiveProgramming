public class MaximumSubarrayAfterOneOperation {

    /*
    Time : O(n)
    Space : O(n)
    Optimized version of next solution.
     */
    public int maxSumAfterOperation(int[] nums) {
        int[] savedResults = new int[nums.length];

        savedResults[0] = nums[0] * nums[0];

        int maxSubarrayWithoutOp = nums[0];
        int result = nums[0] * nums[0];

        for(int index = 1 ; index < nums.length ; index++) {

            int currentNum = nums[index];

            int sumAfterSquare = Math.max(maxSubarrayWithoutOp + currentNum * currentNum, Math.max(currentNum * currentNum, savedResults[index - 1] + currentNum));

            savedResults[index] = sumAfterSquare;

            result = Math.max(sumAfterSquare, result);

            if(maxSubarrayWithoutOp < 0) {
                maxSubarrayWithoutOp = 0;
            }

            maxSubarrayWithoutOp += currentNum;
        }

        return result;
    }

    /*
    Time : O(n)
    Space : O(n)

    Saving the maximum subarray without performing operation on any number,
    And the maximum subarray with one operation performed till the last index.
    Finding the best way to proceed from current index, either ignore the last two sub arrays or extend one of them
     */
    public int maxSumAfterOperation(int[] nums) {
        int[][] savedResults = new int[nums.length][2];

        savedResults[0][0] = nums[0];
        savedResults[0][1] = nums[0] * nums[0];

        int maxSubarrayAfterOne = savedResults[0][1];

        for(int index = 1 ; index < nums.length ; index++) {

            int currentNum = nums[index];

            savedResults[index][0] = Math.max(currentNum, savedResults[index - 1][0] + currentNum);

            int maxSumBySquaringCurrent = Math.max(currentNum * currentNum, Math.max(savedResults[index - 1][0] + (currentNum * currentNum), savedResults[index - 1][1] + currentNum));

            savedResults[index][1] = maxSumBySquaringCurrent;
            maxSubarrayAfterOne = Math.max(maxSubarrayAfterOne, maxSumBySquaringCurrent);
        }

        return maxSubarrayAfterOne;
    }
}
