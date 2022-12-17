public class HouseRobber {

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

    // Top down recursive solution.
    public int rob(int[] nums) {
        int numberOfHouses = nums.length;
        int[] calculatedSums = new int[numberOfHouses];

        for(int index = 0 ; index < numberOfHouses ; index++) {
            calculatedSums[index] = -1;
        }

        return maxRobbery(0, nums, 0, calculatedSums);
    }

    private int maxRobbery(int currentHouse, int[] cashInHouses, int currentSum, int[] calculatedSums) {
        int maxSum = currentSum;

        if(currentHouse < cashInHouses.length) {

            if(calculatedSums[currentHouse] < 0) {
                calculatedSums[currentHouse] = Math.max(maxRobbery(currentHouse + 1, cashInHouses, currentSum, calculatedSums), cashInHouses[currentHouse] + maxRobbery(currentHouse + 2, cashInHouses, currentSum, calculatedSums));
            }

            maxSum = calculatedSums[currentHouse];
        }

        return maxSum;
    }

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
