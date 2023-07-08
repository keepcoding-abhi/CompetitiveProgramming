public class SubarraySumsDivisibleByK {

    /*
    Time : O(n)
    Space : O(k)
    Compute the prefixSums. Now we're interested in finding i and j such that (prefixSums[j] - prefixSums[i]) % k == 0
    This is possible only when prefixSums[j] % k == prefixSums[i] % k
    Therefore, while computing prefixSums also compute remainder from division by k and save the number of
    time a particular remainder has been encountered in an array/map. Use the results from this table to find the
    number of sub-arrays that end at a particular index.
     */
    public int subarraysDivByK(int[] nums, int k) {
        int numberOfSubArrays = 0;

        int[] remainderCounts = new int[k];
        remainderCounts[0] = 1;
        int currentSum = 0;

        for(int index = 0 ; index < nums.length ; index++) {
            currentSum += nums[index];

            // +k % k is done to get correct result in the case of negative numbers.
            int remainder = ((currentSum % k) + k) % k;

            numberOfSubArrays += remainderCounts[remainder];
            remainderCounts[remainder]++;
        }

        return numberOfSubArrays;
    }

    /*
    Time : O(n^2)
    Space : O(1)
    Save the sums of numbers from nums[0] to nums[i] in prefixSums[i].
    Use prefixSums[j] - prefixSums[i] to calculate the sum of sub-array from i + 1 to j.
     */
    public int subarraysDivByK(int[] nums, int k) {
        int numberOfSubArrays = 0;

        int[] prefixSums = new int[nums.length];
        int currentSum = 0;

        for(int index = 0 ; index < nums.length ; index++) {
            currentSum += nums[index];
            prefixSums[index] = currentSum;
        }

        for(int endIndex = 0 ; endIndex < nums.length ; endIndex++) {

            int sumTillEndIndex = prefixSums[endIndex];

            if((sumTillEndIndex % k) == 0) {
                numberOfSubArrays++;
            }

            for(int startIndex = 0 ; startIndex < endIndex ; startIndex++) {
                if(((sumTillEndIndex - prefixSums[startIndex]) % k) == 0) {
                    numberOfSubArrays++;
                }
            }
        }

        return numberOfSubArrays;
    }

    /*
    Time : O(n^2)
    Space : O(1)
    Brute force approach. Exceeds time limit.
    Computing the sum of each possible sub array and checking whether it is divisible by K.
     */
    public int subarraysDivByK(int[] nums, int k) {
        int numberOfSubArrays = 0;

        for(int startIndex = 0 ; startIndex < nums.length ; startIndex++) {
            int currentSum = 0;
            for(int endIndex = startIndex ; endIndex < nums.length ; endIndex++) {
                currentSum += nums[endIndex];

                if(currentSum % k == 0) {
                    numberOfSubArrays++;
                }
            }
        }

        return numberOfSubArrays;
    }
}
