import java.util.Arrays;

public class JumpGame2 {

    /*
    Time : O(n)
    Space : O(1)

    For each jump maintain the farthest value which can be reached from that jump.
    Now all indices before the farthest point can be reached in the same number of jumps.
     */
    class Solution {
        public int jump(int[] nums) {

            int jumpsMade = 0;

            if(nums.length > 1) {
                int farthestPoint = 0, nextFarthestPoint = 0;

                for(int index = 0 ; index < nums.length - 1 ; index++) {
                    int farthestFromCurrent = index + nums[index];

                    nextFarthestPoint = Math.max(nextFarthestPoint, farthestFromCurrent);

                    if(index == farthestPoint) {
                        farthestPoint = nextFarthestPoint;
                        jumpsMade++;
                    }
                }
            }

            return jumpsMade;
        }
    }

    /*
    Time : O(n^2)
    Space : O(n)

    Bottom-up iterative version of the next solution.
     */
    public int jump(int[] nums) {

        int[] savedResults = new int[nums.length];
        savedResults[nums.length - 1] = 0;

        for(int index = nums.length - 2 ; index > -1 ; index--) {
            int farthestReachablePoint = Math.min(nums[index] + index, nums.length - 1);

            int currMin = nums.length + 1;

            for(int nextIndex = index + 1 ; nextIndex <= farthestReachablePoint ; nextIndex++) {
                currMin = Math.min(currMin, savedResults[nextIndex]);
            }

            savedResults[index] = currMin + 1;
        }

        return savedResults[0];
    }

    /*
    Time : O(n^2)
    Space : O(n)

    Top down DP solution. At each index making all possible jumps at deciding on the one that takes least
    number of steps.
     */
    class Solution {
        public int jump(int[] nums) {

            int[] savedResults = new int[nums.length];
            Arrays.fill(savedResults, -1);
            savedResults[nums.length - 1] = 0;

            return minJumps(nums, 0, savedResults);
        }

        private int minJumps(int[] nums, int currIndex, int[] savedResults) {

            int minJumps = 0;
            if(currIndex <  nums.length - 1) {

                if(savedResults[currIndex] != -1) {
                    minJumps = savedResults[currIndex];
                }
                else {
                    int maxJumpsPossible = nums[currIndex];
                    minJumps = nums.length + 1;

                    for(int nextIndex = currIndex + 1, lastIndex = currIndex + maxJumpsPossible ; nextIndex <= lastIndex ; nextIndex++) {
                        minJumps = Math.min(minJumps, 1 + minJumps(nums, nextIndex, savedResults));
                    }

                    savedResults[currIndex] = minJumps;
                }

            }

            return minJumps;
        }
    }

    // Top-down dp solution.
    public int jump(int[] nums) {
        int[] minStepsTable = new int[nums.length];

        for(int index = 0 ; index < nums.length ; index++) {
            minStepsTable[index] = -1;
        }
        minStepsTable[nums.length - 1] = 0;

        minSteps(nums, 0, minStepsTable);
        return minStepsTable[0];
    }

    private void minSteps(int[] nums, int currentPosition, int[] minStepsTable) {

        int stepsRequired = 0;
        if(currentPosition < nums.length - 1) {

            if(minStepsTable[currentPosition] != -1) {
                stepsRequired = minStepsTable[currentPosition];
            }
            else {
                int maxJumpsPossible = nums[currentPosition];

                int minJumpsToDestination = Integer.MAX_VALUE;

                for(int currentJumpSize = 1 ; currentJumpSize <= maxJumpsPossible ; currentJumpSize++) {

                    int nextPosition = currentPosition + currentJumpSize;

                    if(nextPosition < nums.length) {
                        int minStepsWithCurrentJumpSize = minStepsTable[nextPosition];

                        if(minStepsWithCurrentJumpSize == -1) {
                            minSteps(nums, currentPosition + currentJumpSize, minStepsTable);
                            minStepsWithCurrentJumpSize = minStepsTable[nextPosition];
                        }

                        if(minStepsWithCurrentJumpSize < minJumpsToDestination) {
                            minJumpsToDestination = minStepsWithCurrentJumpSize;
                        }
                    }
                }

                if(minJumpsToDestination == Integer.MAX_VALUE) {
                    stepsRequired = minJumpsToDestination;
                }
                else {
                    stepsRequired = minJumpsToDestination + 1;
                }

                minStepsTable[currentPosition] = stepsRequired;
            }
        }
    }

    // Bottom-up solution
    public int jump(int[] nums) {
        int[] minStepsTable = new int[nums.length];
        minStepsTable[nums.length - 1] = 0;

        for(int currentPosition = nums.length - 2 ; currentPosition > -1 ; currentPosition--) {

            int minStepsRequired = Integer.MAX_VALUE;
            int maxJumpsPossible = nums[currentPosition];

            for(int currentJumpSize = 1 ; currentJumpSize <= maxJumpsPossible ; currentJumpSize++) {
                int nextPosition = currentPosition + currentJumpSize;

                if(nextPosition < nums.length) {
                    int stepsRequired = minStepsTable[nextPosition];

                    if(stepsRequired < minStepsRequired) {
                        minStepsRequired = stepsRequired;
                    }
                }
            }
            minStepsTable[currentPosition] = minStepsRequired == Integer.MAX_VALUE ? Integer.MAX_VALUE : minStepsRequired + 1;
        }
        return minStepsTable[0];
    }

}
