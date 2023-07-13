import java.util.Arrays;

public class SmallestRange2 {

    /*
    Time : O(n)
    Space : O(1)
    Through the loops we investigate all cases where all numbers except the first and last number get
    added and subtracted. In all cases first number in array gets added and the last one gets subtracted.
    The way we initialize accounts for the case where both first and last number get added/subtracted.
     */
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int minScore = nums[nums.length - 1] - nums[0];

        for(int index = 1 ; index < nums.length ; index++) {
            int prev = nums[index - 1], curr = nums[index];

            int min = Math.min(nums[0] + k, curr - k);
            int max = Math.max(nums[nums.length - 1] - k, prev + k);

            minScore = Math.min(minScore, max - min);
        }

        return minScore;
    }

}

/*
Time : O(2^n), at each element we decide whether to add or subtract
Space : O(n), n is the length of recursion as we stop when currentIndex == n

Brute force solution, incrementing and decrementing the value of current number and computing the maximum and
minimum value in each case.
 */
class Solution {
    public int smallestRangeII(int[] nums, int k) {
        return getMinAndMax(nums, 0, k);
    }

    private int getMinAndMax(int[] nums, int currentIndex, int k) {

        int minScore = Integer.MAX_VALUE;

        if(currentIndex < nums.length) {
            int originalVal = nums[currentIndex];
            nums[currentIndex] = originalVal + k;

            minScore = getMinAndMax(nums, currentIndex + 1, k);

            nums[currentIndex] = originalVal - k;
            minScore = Math.min(minScore, getMinAndMax(nums, currentIndex + 1, k));

            nums[currentIndex] = originalVal;
        }
        else {
            int minVal = nums[0], maxVal = nums[0];

            for(int num : nums) {
                minVal = Math.min(minVal, num);
                maxVal = Math.max(maxVal, num);
            }

            minScore = maxVal - minVal;
        }

        return minScore;
    }
}