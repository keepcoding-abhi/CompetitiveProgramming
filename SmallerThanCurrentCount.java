public class SmallerThanCurrentCount {

    // Dynamic Programming solution.
    // Store the count of numbers having value less than the current number in an array.
    // Time : O(n), space : O(1).
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] frequencies = new int[101];
        int[] smallerThanCurrentCount = new int[len];

        for(int index = 0 ; index < len ; index++) {
            int currentNum = nums[index];
            frequencies[currentNum]++;
        }

        for(int index = 0 ; index < 100 ; index++) {
            frequencies[index + 1] += frequencies[index];
        }

        for(int index = 0 ; index < len ; index++) {
            int currentNum = nums[index];

            if(currentNum > 0) {
                smallerThanCurrentCount[index] = frequencies[currentNum - 1];
            }
        }

        return smallerThanCurrentCount;
    }

    // Brute force solution.
    // Time : O(n^2), space : O(1).
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] smallerThanCurrentCount = new int[len];

        for(int index = 0 ; index < len ; index++) {
            int currentNum = nums[index], currentCount = 0;

            for(int index1 = 0 ; index1 < len ; index1++) {
                if(currentNum > nums[index1]) {
                    currentCount++;
                }
            }

            smallerThanCurrentCount[index] = currentCount;
        }

        return smallerThanCurrentCount;
    }
}
