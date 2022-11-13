public class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {

        int maxSeqLen = 0;
        int currSeqLen = 0;

        for(int index = 0, len = nums.length ; index < len ; index++) {

            if(nums[index] == 0) {
                maxSeqLen = Math.max(maxSeqLen, currSeqLen);
                currSeqLen = 0;
            }
            else {
                currSeqLen++;
            }
        }

        maxSeqLen = Math.max(maxSeqLen, currSeqLen);

        return maxSeqLen;
    }

}
