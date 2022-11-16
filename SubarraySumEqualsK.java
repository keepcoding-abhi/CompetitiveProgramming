public class SubarraySumEqualsK {

    /*
    Sum of a sub array is the difference of the sum from the beginning of the array to the end of the sub array and
    the sum till the beginning of the sub-array.
    We store the sum of all the sub arrays starting from 0-index and ending till current index in the sum variable.
    Now check whether the sum that is required to make k is already present in the map. If it is, then increase the
    sub array sum count.
    */
    public int subarraySum(int[] nums, int k) {

        int subArrayCount = 0;

        int sum = 0;
        HashMap<Integer, Integer> sumCounts = new HashMap<Integer, Integer>();
        sumCounts.put(0, 1);

        for(int index = 0 ; index < nums.length ; index++) {
            sum += nums[index];

            if(sumCounts.containsKey(sum - k)) {
                subArrayCount += sumCounts.get(sum - k);
            }

            sumCounts.put(sum, sumCounts.getOrDefault(sum, 0) + 1);
        }

        return subArrayCount;
    }
    // 0 : 1, 1 : 1, 3 : 1, 6 : 1
    //
    // 3 : 2

    // Brute force solution.
    public int subarraySum(int[] nums, int k) {

        int subArrayCount = 0;

        int currentSum = 0;
        for(int leftIndex = 0, len = nums.length ; leftIndex < len ; leftIndex++) {

            for(int rightIndex = leftIndex ; rightIndex < len ; rightIndex++) {
                currentSum += nums[rightIndex];

                if(currentSum == k) {
                    subArrayCount++;
                }
            }

            currentSum = 0;
        }

        return subArrayCount;
    }
}
