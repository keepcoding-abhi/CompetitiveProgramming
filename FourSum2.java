public class FourSum2 {
    // Time : O(n^2)
    // Space : O(n^2)
    // Divide the input arrays into two groups and store the sums and counts of the sums from every possible
    // tuple into a hashmap. Compute the sums from second group and count their complements from first group using
    // the hashmap.
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> firstAndSecondSums = new HashMap<Integer, Integer>();
        int count = 0, n = nums1.length;

        for(int index = 0 ; index < n ; index++) {
            int firstNum = nums1[index];
            for(int index1 = 0 ; index1 < n ; index1++) {
                int currentSum = firstNum + nums2[index1];

                firstAndSecondSums.put(currentSum, firstAndSecondSums.getOrDefault(currentSum, 0) + 1);
            }
        }

        for(int index = 0 ; index < n ; index++) {
            int firstNum = nums3[index];
            for(int index1 = 0 ; index1 < n ; index1++) {
                int currentSum = firstNum + nums4[index1];

                count += firstAndSecondSums.getOrDefault(-1 * currentSum, 0);
            }
        }

        return count;
    }
}
