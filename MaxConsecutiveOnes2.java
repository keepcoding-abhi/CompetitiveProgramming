public class MaxConsecutiveOnes2 {

    // Using sliding window. Manipulate the two ends of the sliding window in such a way that the number of zeros between
    // them is always 1.
    public int findMaxConsecutiveOnes(int[] nums) {

        int len = nums.length, leftPtr = 0, rightPtr = 0, maxLen = 0;
        boolean flipped = false;

        while(rightPtr < len) {
            if(nums[rightPtr] == 0) {
                if(!flipped) {
                    flipped = true;
                }
                else {
                    maxLen = Math.max(maxLen, rightPtr - leftPtr);

                    while(nums[leftPtr] != 0) {
                        leftPtr++;
                    }

                    leftPtr++;
                }
            }

            rightPtr++;
        }
        maxLen = Math.max(maxLen, rightPtr - leftPtr);

        return maxLen;
    }

    // NOt reducing the size of the sliding window.
    public int findMaxConsecutiveOnes(int[] nums) {

        int len = nums.length, leftPtr = 0, rightPtr = 0, maxLen = 0;
        int zerosAdded = 0;

        while(rightPtr < len) {
            if(nums[rightPtr] == 0) {
                zerosAdded++;
            }

            if(zerosAdded > 1) {
                if(nums[leftPtr] == 0) {
                    zerosAdded--;
                }

                leftPtr++;
            }

            rightPtr++;
        }

        return rightPtr - leftPtr;
    }

}
