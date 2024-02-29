public class MaximumSubarray {

    // efficient implementation
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];

        int leftIndex = 0, rightIndex = 0, currentSum = 0;
        int len = nums.length;

        while(rightIndex < len) {
            currentSum += nums[rightIndex];
            maxSum = (maxSum > currentSum) ? maxSum : currentSum;

            if(currentSum <= 0) {
                leftIndex = rightIndex + 1;
                currentSum = 0;
            }

            rightIndex++;
        }

        return maxSum;
    }
}

/*
Time: O(n * Log(n))
Space: O(Log(n))

Divide and Conquer Solution. Divide the array from middle and recursively find the maximum sum in each half.
To find the sum of the sub-array including the middle element, find maxSum starting from the last element in left half
and the first element in right half assuming that all elements are part of the maxSum.
 */
class Solution {
    public int maxSubArray(int[] nums) {
        return maxSubArrayDAndC(nums, 0, nums.length - 1);
    }

    private int maxSubArrayDAndC(int[] nums, int left, int right) {

        int result = Integer.MIN_VALUE;

        if(left <= right) {

            int mid = (left + right) / 2;
            int maxSumLeft = maxSubArrayDAndC(nums, left, mid - 1);
            int maxSumRight = maxSubArrayDAndC(nums, mid + 1, right);

            int maxSumWithMid = nums[mid] + Math.max(findMaxSumRight(nums, mid + 1), 0) + Math.max(findMaxSumLeft(nums, mid - 1), 0);

            result = Math.max(maxSumLeft, maxSumRight);
            result = Math.max(result, maxSumWithMid);
        }

        return result;
    }

    private int findMaxSumRight(int[] nums, int start) {
        int index = start, maxSum = Integer.MIN_VALUE, currentSum = 0;

        while(index < nums.length) {
            currentSum += nums[index];
            maxSum = Math.max(currentSum, maxSum);
            index++;
        }

        return maxSum;
    }

    private int findMaxSumLeft(int[] nums, int start) {

        int maxSum = Integer.MIN_VALUE, currentSum = 0, index = start;

        while(index > -1) {
            currentSum += nums[index];
            maxSum = Math.max(maxSum, currentSum);

            index--;
        }

        return maxSum;
    }
}

//1) O(n^2) Brute force solution. Find the sum of largest sub-array which starts at all positions of the array.

class Solution {
    public int maxSubArray(int[] nums) {
        int globalLargestSum = nums[0];

        for(int beginIndex = 0, totalElements = nums.length ; beginIndex < totalElements ; beginIndex++) {

            int currentSum = nums[beginIndex], largestSum = currentSum;
            for(int endIndex = beginIndex + 1 ; endIndex < totalElements ; endIndex++) {
                currentSum += nums[endIndex];

                if(currentSum > largestSum) {
                    largestSum = currentSum;
                }
            }

            if(largestSum > globalLargestSum) {
                globalLargestSum = largestSum;
            }
        }

        return globalLargestSum;
    }
}

//2) O(n) solution. Scan through the array. At each element we decide whether the current element will be added to the existing subarray
// or a new sub-array will be started from the current element. If adding the current element to the existing sub-array increases its sum
// then we add to existing sub-array. Otherwise, we start a new sub-array from the current element.

class Solution {
    public int maxSubArray(int[] nums) {
        int globalLargestSum = nums[0];

        int currentSubArraySum = nums[0];

        for(int index = 1, totalElements = nums.length ; index < totalElements ; index++) {
            int currentElement = nums[index];

            int currentSubArraySumAfterAdding = currentSubArraySum + currentElement;

            if(currentSubArraySumAfterAdding > currentElement) {
                currentSubArraySum = currentSubArraySumAfterAdding;
            }
            else {
                currentSubArraySum = currentElement;
            }

            if(currentSubArraySum > globalLargestSum) {
                globalLargestSum = currentSubArraySum;
            }
        }

        return globalLargestSum;
    }
}