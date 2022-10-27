public class JumpGame {


    //1) Fastest solution. Only keeping tracke of the next destination traversing from right to left.
    public boolean canJump(int[] nums) {
        int currentDestination = nums.length - 1;

        for(int currentIndex = currentDestination - 1 ; currentIndex > -1 ; currentIndex--) {
            int currentJumpLength = nums[currentIndex];

            if(currentIndex + currentJumpLength >= currentDestination) {
                currentDestination = currentIndex;
            }

        }

        return (currentDestination == 0) ? true : false;
    }

    //2 Traversing in a reverse order. From destination to beginning. Checking whether the destination can be reached from a node.
    public boolean canJump(int[] nums) {
        int destination = nums.length - 1;

        boolean[] goodIndices = new boolean[nums.length];
        goodIndices[destination] = true;

        for(int currentIndex = destination - 1 ; currentIndex > -1 ; currentIndex--) {
            int currentJumpLength = nums[currentIndex];

            if(currentIndex + currentJumpLength >= destination) {
                goodIndices[currentIndex] = true;
            }
            else {
                while(currentJumpLength > 0) {

                    if(goodIndices[currentIndex + currentJumpLength]) {
                        goodIndices[currentIndex] = true;
                        break;
                    }

                    currentJumpLength--;
                }
            }

        }

        return goodIndices[0];
    }

    //1) Brute force solution. Passes all test cases but still slow. Instead of intializing step size by 1 and incrementing it,
    // initializing the step size with the maximum value of steps.
    public boolean canJump(int[] nums) {
        return findPathToEnd(nums, 0, new HashSet<Integer>());
    }

    private boolean findPathToEnd(int[] nums, int currentPosition, HashSet<Integer> deadEnds) {

        boolean reachedEnd = false;
        int destination = nums.length - 1;

        if(currentPosition == destination) {
            reachedEnd = true;
        }
        else if(currentPosition < destination && !deadEnds.contains(currentPosition)) {
            int maxJumpLength = nums[currentPosition];

            int currentJumpLength = maxJumpLength;
            while(!reachedEnd && currentJumpLength > 0) {
                reachedEnd = findPathToEnd(nums, currentPosition + currentJumpLength, deadEnds);
                currentJumpLength--;
            }

            if(!reachedEnd && currentJumpLength == 0) {
                deadEnds.add(currentPosition);
            }
        }

        return reachedEnd;
    }

    //2) Brute force solution but keeping track of dead ends. Passes all test cases but still exceeds time limit.
    public boolean canJump(int[] nums) {
        return findPathToEnd(nums, 0, new HashSet<Integer>());
    }

    private boolean findPathToEnd(int[] nums, int currentPosition, HashSet<Integer> deadEnds) {

        boolean reachedEnd = false;
        int destination = nums.length - 1;

        if(currentPosition == destination) {
            reachedEnd = true;
        }
        else if(!deadEnds.contains(currentPosition)) {
            int maxJumpLength = nums[currentPosition];

            int currentJumpLength = 1;
            while(!reachedEnd && currentJumpLength <= maxJumpLength) {
                reachedEnd = findPathToEnd(nums, currentPosition + currentJumpLength, deadEnds);
                currentJumpLength++;
            }

            if(!reachedEnd && currentJumpLength > maxJumpLength) {
                deadEnds.add(currentPosition);
            }
        }

        return reachedEnd;
    }

    //3) Brute Force solution. Exceeds time limit on test cases.
    public boolean canJump(int[] nums) {
        return findPathToEnd(nums, 0);
    }

    private boolean findPathToEnd(int[] nums, int currentPosition) {

        boolean reachedEnd = false;
        int destination = nums.length - 1;

        if(currentPosition == destination) {
            reachedEnd = true;
        }
        else {
            int maxJumpLength = nums[currentPosition];

            int currentJumpLength = 1;
            while(!reachedEnd && currentJumpLength <= maxJumpLength) {
                reachedEnd = findPathToEnd(nums, currentPosition + currentJumpLength);
                currentJumpLength++;
            }
        }

        return reachedEnd;
    }

}
