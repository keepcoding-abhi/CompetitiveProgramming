public class TargetSum {
}

/*
Time : O(n * target)
Space : O(n)

Bottom up iterative version.
 */
class Solution {

    public int findTargetSumWays(int[] nums, int target) {

        int maxSum = 0;

        for(int num : nums) {
            maxSum += num;
        }

        int ways = 0;
        if((target > maxSum) || (target < 0 && target < (-1 * maxSum))) {
            ways = 0;
        }
        else {

            int sums = (2 * maxSum) + 1;
            int[] prevRes = new int[sums];

            prevRes[maxSum + nums[0]]++;
            prevRes[maxSum - nums[0]]++;

            for(int arrIndex = 1 ; arrIndex < nums.length ; arrIndex++) {
                int currentNum = nums[arrIndex];

                int[] nextStageRes = new int[sums];

                for(int sum = 0 ; sum < sums ; sum++) {
                    if(prevRes[sum] > 0) {
                        if(sum + currentNum < sums) {
                            nextStageRes[sum + currentNum] = nextStageRes[sum + currentNum] + prevRes[sum];
                        }

                        if(sum - currentNum > -1) {
                            nextStageRes[sum - currentNum] = nextStageRes[sum - currentNum] + prevRes[sum];
                        }
                    }
                }

                prevRes = nextStageRes;
            }

            ways = prevRes[target + maxSum];
        }

        return ways;
    }
}

/*
Time : O(n * target)
Space : O(n * target)

Top down memoized version of next approach.
 */
class Solution {
    int maxSum = 0;

    public int findTargetSumWays(int[] nums, int target) {

        maxSum = 0;

        for(int num : nums) {
            maxSum += num;
        }

        int nRows = (2 * maxSum) + 1;
        int[][] savedRes = new int[nums.length][nRows];

        for(int index = 0 ; index < nums.length ; index++) {
            for(int index1 = 0 ; index1 < nRows ; index1++) {
                savedRes[index][index1] = -1;
            }
        }

        return computeWays(nums, 0, 0, target, savedRes);
    }

    private int computeWays(int[] nums, int index, int currSum, int target, int[][] savedRes) {
        int ways = 0;

        if(index < nums.length) {

            if(savedRes[index][currSum + maxSum] == -1) {
                int currentNum = nums[index];

                ways = ways + computeWays(nums, index + 1, currSum + currentNum, target, savedRes);

                ways = ways + computeWays(nums, index + 1, currSum - currentNum,target, savedRes);

                savedRes[index][currSum + maxSum] = ways;
            }
            else {
                ways = savedRes[index][currSum + maxSum];
            }
        }
        else if(target == currSum) {
            ways = 1;
        }

        return ways;
    }
}

/*
Time : O(2^n)
Space : O(n)

Brute force solution assigning every possible sign to a number.
 */
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return computeWays(nums, 0, target);
    }

    private int computeWays(int[] nums, int index, int target) {
        int ways = 0;

        if(index < nums.length) {
            int currentNum = nums[index];

            ways = ways + computeWays(nums, index + 1, target + currentNum);

            ways = ways + computeWays(nums, index + 1, target - currentNum);
        }
        else if(target == 0) {
            ways = 1;
        }

        return ways;
    }
}
