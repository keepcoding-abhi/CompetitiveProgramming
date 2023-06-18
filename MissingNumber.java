public class MissingNumber {

    /*
    Time : O(n)
    Space : O(1)
    Using the fact that the XOR of any number with itself is zero.
    The array is expected to have all numbers in [0,n]. We have indices from [0,n-1]
    Use a variable to store the result of XOR operation on all numbers. And initialize it with n.
    After scanning the whole array this variable will represent the missing number.
     */
    public int missingNumber(int[] nums) {

        int xorOperation = nums.length;

        for(int index = 0 ; index < nums.length ; index++) {
            xorOperation = xorOperation ^ index ^ nums[index];
        }

        return xorOperation;
    }

    // Time : O(n)
    // Space : O(1)
    // Compute the sum of all numbers from 0 to n.
    // Subtract each number in the array from this sum. The missing value will be the element that remains
    // after all number in array have been subtracted.
    public int missingNumber(int[] nums) {

        int totalElements = nums.length;
        int expectedSum = (totalElements * (totalElements + 1)) / 2;
        int actualSum = 0;

        for(int index = 0 ; index < nums.length ; index++) {
            actualSum = actualSum + nums[index];
        }

        return expectedSum - actualSum;
    }
}
