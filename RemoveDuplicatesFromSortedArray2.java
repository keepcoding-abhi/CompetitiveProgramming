public class RemoveDuplicatesFromSortedArray2 {

    public int removeDuplicates(int[] nums) {
        int oneDuplicateIndex = 0;
        int totalElements = nums.length;

        if(totalElements > 1) {
            int lastElementFreq = 1;

            for(int index = 1 ; index < totalElements ; index++) {
                if(nums[index] == nums[index - 1] && lastElementFreq == 1) {
                    nums[++oneDuplicateIndex] = nums[index];
                    lastElementFreq++;
                }
                else if(nums[index] != nums[index - 1]) {
                    nums[++oneDuplicateIndex] = nums[index];
                    lastElementFreq = 1;
                }
            }
        }

        return oneDuplicateIndex + 1;
    }

}
