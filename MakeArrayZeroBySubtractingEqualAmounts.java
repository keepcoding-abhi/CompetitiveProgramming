public class MakeArrayZeroBySubtractingEqualAmounts {

    // Counting the number of distinct non-zero elements in the array.
    // Time and space complexity : O(n)
    public int minimumOperations(int[] nums) {

        Set<Integer> distinctEls = new HashSet<Integer>();

        for(int num : nums) {
            if(num != 0) {
                distinctEls.add(num);
            }
        }

        return distinctEls.size();
    }

    // Brute force approach. Simply translating the problem statement.
    // Time complexity : exponential, O(largest element in array)
    // Space complexity : constant.
    public int minimumOperations(int[] nums) {

        int numOps = 0;
        int smallestEl = getSmallestEl(nums);

        while(smallestEl != -1) {
            subtractVal(nums, smallestEl);
            numOps++;
            smallestEl = getSmallestEl(nums);
        }

        return numOps;
    }

    private void subtractVal(int[] nums, int val) {

        for(int index = 0, len = nums.length ; index < len ; index++) {
            nums[index] -= val;
        }
    }

    private int getSmallestEl(int[] nums) {
        int smallestEl = Integer.MAX_VALUE;

        for(int index = 0, len = nums.length ; index < len ; index++) {
            int currentEl = nums[index];

            if(currentEl > 0 && currentEl < smallestEl) {
                smallestEl = currentEl;
            }
        }

        return ((smallestEl == Integer.MAX_VALUE) ? -1 : smallestEl);
    }
}
