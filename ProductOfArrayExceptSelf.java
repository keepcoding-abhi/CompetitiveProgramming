public class ProductOfArrayExceptSelf {

    // Iteratively storing the product of all elements to the left of an element in the answer array.
    // In the second loop a variable is used to store the product of all elements to the right of a position.
    // The elements in answer array are multiplied with the value of this variable.
    // Space complexity : O(1), Time : O(n)
    public int[] productExceptSelf(int[] nums) {

        int len = nums.length;
        int[] answer = new int[len];

        answer[0] = 1;
        for(int index = 1 ; index < len ; index++) {
            answer[index] = answer[index - 1] * nums[index - 1];
        }

        int rightSubarrayProduct = 1;
        for(int index = len - 2 ; index > -1 ; index--) {
            rightSubarrayProduct *= nums[index + 1];
            answer[index] *= rightSubarrayProduct;
        }

        return answer;
    }

    // Using two arrays to store the product of all the elements to the left and right of the current element.
    // T : O(n), S : O(n)
    public int[] productExceptSelf(int[] nums) {

        int len = nums.length;
        int[] answer = new int[len];

        int[] left = new int[len], right = new int[len];

        left[0] = 1;
        for(int index = 1 ; index < len ; index++) {
            left[index] = left[index - 1] * nums[index - 1];
        }

        right[len - 1] = 1;
        for(int index = len - 2 ; index > -1 ; index--) {
            right[index] = right[index + 1] * nums[index + 1];
        }

        for(int index = 0 ; index < len ; index++) {
            answer[index] = left[index] * right[index];
        }

        return answer;
    }

}
