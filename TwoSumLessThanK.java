import java.util.Arrays;

public class TwoSumLessThanK {

    /*
    Sorting the array and using two pointers which start from the beginning and end of the array respectively.
    Increment the left pointer if a larger number is required and decrement the right pointer if a smaller number is required.
    This ensures that all possible combinations which add up to k are tried.
    Time : O(nLog(n)) The two pointer approach for finding two elements of interest takes O(n) time.
    Space : O(n) or O(Log(n)) depending on the implementation of the sorting algorithm.
     */
    public int twoSumLessThanK(int[] nums, int k) {
        int result = -1;

        Arrays.sort(nums);

        int leftPointer = 0, rightPointer = nums.length - 1;
        while(leftPointer < rightPointer) {
            int currentSum = nums[leftPointer] + nums[rightPointer];

            if(currentSum < k) {
                result = Math.max(result, currentSum);
                if(result == k - 1) {
                    break;
                }
                leftPointer++;
            }
            else {
                rightPointer--;
            }
        }

        return result;
    }

    /*
    Brute force solution, computes the sum of each possible pair and checks whether it's less than k.
    The largest such sum is selected as the result.
    Time : O(n^2), Space : O(1).
     */
    public int twoSumLessThanK(int[] nums, int k) {
        int result = -1;

        for(int index = 0 ; index < nums.length ; index++) {
            int firstNum = nums[index];
            for(int index1 = index + 1 ; index1 < nums.length ; index1++) {
                int currentSum = firstNum + nums[index1];

                if(currentSum < k) {
                    result = Math.max(result, currentSum);
                }
            }
        }

        return result;
    }
}
