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