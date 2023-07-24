import java.util.Arrays;

public class HouseRobber {

    /*
    Time : O(n)
    Space : O(1)
     */
    // Bottom-up iterative solution using constant space.
    public int rob(int[] nums) {

        int maxRobbery = 0;
        int numberOfHouses = nums.length;

        if(numberOfHouses > 2) {

            int maxSumFromNeighborsNeighbor = nums[numberOfHouses - 1];
            int maxSumFromNextNeighbor = Math.max(nums[numberOfHouses - 2], nums[numberOfHouses - 1]);

            for(int currentHouse = numberOfHouses - 3 ; currentHouse > -1 ; currentHouse--) {

                int temp = Math.max(nums[currentHouse] + maxSumFromNeighborsNeighbor, maxSumFromNextNeighbor);

                maxSumFromNeighborsNeighbor = maxSumFromNextNeighbor;
                maxSumFromNextNeighbor = temp;
            }

            maxRobbery = maxSumFromNextNeighbor;
        }
        else if(numberOfHouses == 2) {
            maxRobbery = Math.max(nums[0], nums[1]);
        }
        else if(numberOfHouses == 1){
            maxRobbery = nums[0];
        }

        return maxRobbery;
    }

    /*
    Time : O(n)
    Space : O(n)
     */
    // Bottom-up iterative solution using linear space.
    public int rob(int[] nums) {

        int maxRobbery = 0;
        int numberOfHouses = nums.length;

        if(numberOfHouses > 2) {

            int[] calculatedSums = new int[numberOfHouses];

            calculatedSums[numberOfHouses - 1] = nums[numberOfHouses - 1];
            calculatedSums[numberOfHouses - 2] = Math.max(nums[numberOfHouses - 2], nums[numberOfHouses - 1]);

            for(int currentHouse = numberOfHouses - 3 ; currentHouse > -1 ; currentHouse--) {
                calculatedSums[currentHouse] = Math.max(calculatedSums[currentHouse + 1], nums[currentHouse] + calculatedSums[currentHouse + 2]);
            }
            maxRobbery = calculatedSums[0];
        }
        else if(numberOfHouses == 2) {
            maxRobbery = Math.max(nums[0], nums[1]);
        }
        else if(numberOfHouses == 1){
            maxRobbery = nums[0];
        }

        return maxRobbery;
    }

    /*
    Time : O(n)
    Space : O(n)
    Top down recursive solution.
     */
    public int rob(int[] nums) {
        int[] savedResults = new int[nums.length];
        Arrays.fill(savedResults, -1);
        return maximizeRobbery(nums, 0, savedResults);
    }

    private int maximizeRobbery(int[] nums, int index, int[] savedResults) {

        int maxMoneyRobbed = 0;
        if(index < nums.length) {

            maxMoneyRobbed = savedResults[index];
            if(maxMoneyRobbed == -1) {
                int currentHouseRobbed = nums[index] + maximizeRobbery(nums, index + 2, savedResults);
                int currentHouseNotRobbed = maximizeRobbery(nums, index + 1, savedResults);
                maxMoneyRobbed = Math.max(currentHouseRobbed, currentHouseNotRobbed);

                savedResults[index] = maxMoneyRobbed;
            }
        }

        return maxMoneyRobbed;
    }

    /*
    Time : O(2^n)
    Space : O(n)
    Find the amount that can be robbed when current house is robbed and when it isn't select the maximum between them.
     */
    //Brute force solution. Exceeds time limit.
    public int rob(int[] nums) {
        return maxRobbery(0, nums, 0);
    }

    private int maxRobbery(int currentHouse, int[] nums, int currentSum) {
        int maxSum = currentSum;

        if(currentHouse < nums.length) {
            maxSum = Math.max(maxRobbery(currentHouse + 2, nums, nums[currentHouse] + currentSum), maxRobbery(currentHouse + 1, nums, currentSum));
        }

        return maxSum;
    }
}
