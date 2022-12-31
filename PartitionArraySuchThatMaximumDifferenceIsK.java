public class PartitionArraySuchThatMaximumDifferenceIsK {

    // Time : O(nLog(n)), Space : O(1)
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);

        int numberOfSubsqs = 0;
        int leftIndex = 0;

        for(int index = 1, len = nums.length ; index < len ; index++) {
            if(nums[index] - nums[leftIndex] > k) {
                leftIndex = index;
                numberOfSubsqs++;
            }
        }

        return ++numberOfSubsqs;
    }
}
