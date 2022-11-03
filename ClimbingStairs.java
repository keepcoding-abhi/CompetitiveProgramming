public class ClimbingStairs {

    //1) Bottom-up DP solution.
    public int climbStairs(int n) {
        int[] waysToReachDestination = new int[n + 1];
        waysToReachDestination[n] = 1;
        waysToReachDestination[n - 1] = 1;

        for(int currentPosition = n - 2 ; currentPosition > - 1 ; currentPosition--) {
            int oneStepWays = currentPosition + 1 <= n ? waysToReachDestination[currentPosition + 1] : 0;
            int twoStepWays = currentPosition + 2 <= n ? waysToReachDestination[currentPosition + 2] : 0;
            waysToReachDestination[currentPosition] = oneStepWays + twoStepWays;
        }

        return waysToReachDestination[0];
    }

    //2) DP optimized solution. Fastest.
    public int climbStairs(int n) {
        int[] waysToReachDestination = new int[n + 1];
        for(int index = 0 ; index < n ; index++) {
            waysToReachDestination[index] = -1;
        }
        waysToReachDestination[n] = 1;

        return takeStep(n, 0, waysToReachDestination);
    }

    private int takeStep(int destination, int currentPosition, int[] waysToReachDestination) {
        int ways = 0;

        if(currentPosition == destination) {
            ways = 1;
        }
        else if(currentPosition < destination) {

            int oneStepWays = (currentPosition + 1 <= destination) ? waysToReachDestination[currentPosition + 1] : 0;

            int twoStepWays = (currentPosition + 2 <= destination) ? waysToReachDestination[currentPosition + 2] : 0;

            if(oneStepWays == -1) {
                oneStepWays = takeStep(destination, currentPosition + 1, waysToReachDestination);
                waysToReachDestination[currentPosition + 1] = oneStepWays;
            }

            if(twoStepWays == -1) {
                twoStepWays = takeStep(destination, currentPosition + 2, waysToReachDestination);
                waysToReachDestination[currentPosition + 2] = twoStepWays;
            }

            ways = oneStepWays + twoStepWays;
        }

        return ways;
    }

    //3) Top-down recursive solution exceeds time limit
    public int climbStairs(int n) {
        return takeStep(n, 0);
    }

    private int takeStep(int destination, int currentPosition) {
        int ways = 0;

        if(currentPosition == destination) {
            ways = 1;
        }
        else if(currentPosition < destination) {
            ways = takeStep(destination, currentPosition + 1) + takeStep(destination, currentPosition + 2);
        }

        return ways;
    }



}
