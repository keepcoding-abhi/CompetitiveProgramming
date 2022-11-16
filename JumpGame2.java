public class JumpGame2 {

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
