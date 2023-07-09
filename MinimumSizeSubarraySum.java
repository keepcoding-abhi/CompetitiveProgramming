public class MinimumSizeSubarraySum {

    /*
    Time : O(n)
    Space : O(1)

    Maintaining two pointers marking the beginning and end of current sub-array.
    If current sub-array has sum greater than or equal to target record its length and update the minimunm subarray length.
    Then remove the left element.
    Otherwise, add the next element from right.
     */
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int minSubArrayLen = Integer.MAX_VALUE;

            int leftIndex = 0, rightIndex = 0;
            int currentSum = nums[0];

            while(rightIndex < nums.length) {
                if(currentSum >= target) {
                    int currentSubArrayLen = rightIndex - leftIndex + 1;
                    minSubArrayLen = Math.min(minSubArrayLen, currentSubArrayLen);
                    currentSum -= nums[leftIndex];
                    leftIndex++;
                }
                else {
                    rightIndex++;
                    if(rightIndex < nums.length) {
                        currentSum += nums[rightIndex];
                    }
                }
            }

            if(currentSum >= target) {
                int currentSubArrayLen = rightIndex - leftIndex + 1;
                minSubArrayLen = Math.min(minSubArrayLen, currentSubArrayLen);
                currentSum -= nums[leftIndex];
                leftIndex++;
            }

            if(minSubArrayLen == Integer.MAX_VALUE) {
                minSubArrayLen = 0;
            }

            return minSubArrayLen;
        }
    }

    /*
    A better implementation of the above algorithm.
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minSubArrayLen = Integer.MAX_VALUE;

        int leftIndex = 0;
        int currentSum = 0;

        for(int rightIndex = 0 ;rightIndex < nums.length ; rightIndex++) {
            currentSum += nums[rightIndex];

            while(currentSum >= target) {
                minSubArrayLen = Math.min(minSubArrayLen, rightIndex - leftIndex + 1);
                currentSum -= nums[leftIndex];
                leftIndex++;
            }
        }

        if(minSubArrayLen == Integer.MAX_VALUE) {
            minSubArrayLen = 0;
        }

        return minSubArrayLen;
    }

    /*
    Time : O(nLog(n))
    Space : O(n)
    Calculating the prefixSums array. Then for each index where the sum is greater than the target finding
    the index in left sub-array of currentSum - target using binary search. Since the input array has only positive
    numbers the prefixSums array will always be strictly increasing.
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minSubArrayLen = Integer.MAX_VALUE;

        int[] prefixSums = new int[nums.length];
        int currentSum = 0;

        for(int index = 0 ; index < nums.length ; index++) {
            currentSum += nums[index];
            prefixSums[index] = currentSum;
        }

        int rightIndex = nums.length - 1;

        while(rightIndex > -1) {
            int currentSubArraySum = prefixSums[rightIndex];
            if(currentSubArraySum < target) {
                break;
            }
            else if(currentSubArraySum == target) {
                minSubArrayLen = Math.min(minSubArrayLen, rightIndex + 1);
                break;
            }
            else {
                int maximumAcceptableSum = currentSubArraySum - target;
                int lastIndexOfTarget = binarySearch(prefixSums, 0, rightIndex - 1, maximumAcceptableSum);

                int currentSubArrayLen = rightIndex - lastIndexOfTarget;
                minSubArrayLen = Math.min(minSubArrayLen, currentSubArrayLen);
                rightIndex--;
            }
        }

        if(minSubArrayLen == Integer.MAX_VALUE) {
            minSubArrayLen = 0;
        }

        return minSubArrayLen;
    }

    int binarySearch(int[] nums, int start, int end, int target) {

        int leftIndex = start, rightIndex = end;
        while(leftIndex <= rightIndex) {
            int midIndex = (leftIndex + rightIndex) / 2;
            int midEl = nums[midIndex];

            if(midEl < target) {
                leftIndex = midIndex + 1;
            }
            else if(midEl > target) {
                rightIndex = midIndex - 1;
            }
            else {
                leftIndex = midIndex + 1;
                break;
            }
        }

        return leftIndex - 1;
    }

    /*
    Time : O(n^2)
    Space : O(1)
    Brute force solution.
    Exceeds time limit.
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minSubArrayLen = Integer.MAX_VALUE;

        for(int startIndex = 0 ; startIndex < nums.length ; startIndex++) {
            int currentSum = 0;
            for(int endIndex = startIndex ; endIndex < nums.length ; endIndex++) {
                currentSum += nums[endIndex];
                if(currentSum >= target) {
                    minSubArrayLen = Math.min(minSubArrayLen, (endIndex - startIndex) + 1);
                }
            }
        }

        if(minSubArrayLen == Integer.MAX_VALUE) {
            minSubArrayLen = 0;
        }

        return minSubArrayLen;
    }
}
