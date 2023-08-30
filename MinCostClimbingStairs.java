import java.util.Arrays;

public class MinCostClimbingStairs {

    /*
    Time : O(n)
    Space : O(1)
    Bottom up approach. Saving the minimum costs only from the next two steps at a time.
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] savedMinCosts = new int[cost.length];

        int costFromNeighbor = cost[cost.length - 2], costFromNeighborsNeighbor = cost[cost.length - 1];

        for(int index = cost.length - 3 ; index > -1 ; index--) {
            int costFromCurrent = Math.min(costFromNeighbor, costFromNeighborsNeighbor) + cost[index];

            costFromNeighborsNeighbor = costFromNeighbor;
            costFromNeighbor = costFromCurrent;
        }

        return Math.min(costFromNeighbor, costFromNeighborsNeighbor);
    }

    /*
    Time : O(n)
    Space : O(n)
    Top-down version of the next approach.
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] savedMinCosts = new int[cost.length];
        Arrays.fill(savedMinCosts, -1);
        savedMinCosts[cost.length - 1] = cost[cost.length - 1];

        return Math.min(getMinCost(cost, 0, savedMinCosts), getMinCost(cost, 1, savedMinCosts));
    }

    private int getMinCost(int[] cost, int currStep, int[] savedMinCosts) {

        int minCost = 0;

        if(currStep < cost.length) {

            if(savedMinCosts[currStep] != -1) {
                minCost = savedMinCosts[currStep];
            }
            else {
                minCost = getMinCost(cost, currStep + 1, savedMinCosts);
                minCost = Math.min(getMinCost(cost, currStep + 2, savedMinCosts), minCost);

                minCost += cost[currStep];
                savedMinCosts[currStep] = minCost;
            }

        }

        return minCost;
    }

    /*
    Time : O(2^n)
    Space : O(n)
    Brute force solution, exceeds time limit.
     */
    public int minCostClimbingStairs(int[] cost) {
        return Math.min(getMinCost(cost, 0), getMinCost(cost, 1));
    }

    private int getMinCost(int[] cost, int currStep) {

        int minCost = 0;

        if(currStep < cost.length) {
            minCost = getMinCost(cost, currStep + 1);
            minCost = Math.min(getMinCost(cost, currStep + 2), minCost);

            minCost += cost[currStep];
        }

        return minCost;
    }
}
