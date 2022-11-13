public class MaxConsecutiveOnes3 {

    // Using sliding window. The size of the sliding window is adjusted so that the number of zeroes in the window <= k.
    public int longestOnes(int[] nums, int k) {
        int leftPointer = 0, rightPointer = 0, ksLeft = k, len = nums.length;
        int maxLen = 0;

        while(rightPointer < len) {
            if(nums[rightPointer] == 0) {
                if(ksLeft > 0) {
                    rightPointer++;
                    ksLeft--;
                }
                else {
                    maxLen = Math.max(maxLen, rightPointer - leftPointer);
                    while(nums[leftPointer] != 0) {
                        leftPointer++;
                    }
                    ksLeft++;
                    leftPointer++;
                }
            }
            else {
                rightPointer++;
            }
        }

        maxLen = Math.max(maxLen, rightPointer - leftPointer);

        return maxLen;
    }

    // Reducing the number of computations by not decreasing the size of the sliding window.
    public int longestOnes(int[] nums, int k) {
        int leftPointer = 0, rightPointer = 0, ksLeft = k, len = nums.length;

        for( ; rightPointer < len ; rightPointer++) {

            if(nums[rightPointer] == 0) {
                ksLeft--;
            }

            if(ksLeft < 0) {
                if(nums[leftPointer] == 0) {
                    ksLeft++;
                }

                leftPointer++;
            }
        }

        return rightPointer - leftPointer;
    }
}
