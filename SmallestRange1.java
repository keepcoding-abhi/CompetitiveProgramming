public class SmallestRange1 {
    /*
    Time : O(n)
    Space : O(1)
    If smallestNumber + k >= largestNumber - k, then we can bring all the numbers to the same level and score would be 0.
    Otherwise, the minimum score would be obtained by (largestNumber - k) - (smallestNumber + k)
     */
    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE, max = -1;

        for(int index = 0 ; index < nums.length ; index++) {
            int currentNum = nums[index];

            min = Math.min(currentNum, min);
            max = Math.max(currentNum, max);
        }

        int minScore = 0;
        if(min + k >= max - k) {
            minScore = 0;
        }
        else {
            minScore = (max - k) - (min + k);
        }

        return minScore;
    }
}
