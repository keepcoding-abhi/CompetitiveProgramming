import java.util.Arrays;

public class CombinationSum4 {

    /*
    Time : O(Target * nums.length)
    Space : O(Target)
    Bottom-up version of next solution.
     */
    public int combinationSum4(int[] nums, int target) {
        int[] savedResults = new int[target + 1];
        savedResults[0] = 1;
        for(int currentTarget = 1 ; currentTarget <= target ; currentTarget++) {
            for(int index = 0 ; index < nums.length ; index++) {
                int difference = currentTarget - nums[index];
                if(difference >= 0) {
                    savedResults[currentTarget] += (savedResults[difference]);
                }
            }
        }

        return savedResults[target];
    }

    /*
    Time : O(Target * nums.length)
    Space : O(Target)
    Memoized version of the next solution.
    Instead of saving results from each index, we're saving results for each target from 0 to actual target.
     */
    public int combinationSum4(int[] nums, int target) {
        int[] savedResults = new int[target + 1];
        Arrays.fill(savedResults, -1);
        return findSums(nums, target, savedResults);
    }

    private int findSums(int[] nums, int target, int[] savedResults) {
        int possibleSums;
        if(target == 0) {
            possibleSums = 1;
        }
        else if(target < 0) {
            possibleSums = 0;
        }
        else if(savedResults[target] != -1) {
            possibleSums = savedResults[target];
        }
        else {
            possibleSums = 0;
            for(int index = 0, len = nums.length ; index < len ; index++) {
                int currentNum = nums[index];

                possibleSums += findSums(nums, target - currentNum, savedResults);
            }

            savedResults[target] = possibleSums;
        }

        return possibleSums;
    }

    /*
    Time : exponential, e.g. O(2^n)
    Space : O(2^n)
    Allowing each number of the array to occupy any position in the sequence by running the for loop in
    recursive function from 0 to n.
     */
    public int combinationSum4(int[] nums, int target) {
        return findSums(nums, target);
    }

    private int findSums(int[] nums, int target) {
        int possibleSums;
        if(target == 0) {
            possibleSums = 1;
        }
        else if(target < 0) {
            possibleSums = 0;
        }
        else {
            possibleSums = 0;
            for(int index = 0, len = nums.length ; index < len ; index++) {
                int currentNum = nums[index];

                possibleSums += findSums(nums, target - currentNum);
            }
        }

        return possibleSums;
    }
}
