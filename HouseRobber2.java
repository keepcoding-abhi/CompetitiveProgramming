public class HouseRobber2 {

    /*
    Time : O(n)
    Space : O(1)
    When first house gets included last house can't and vice versa. Find the maximum between the two cases.
     */
    public int rob(int[] nums) {
        int numberOfHouses = nums.length;
        int maxRobbery = 0;

        if(numberOfHouses > 2) {
            int maxRobberyWithFirstHouseButNotLast = computeMaxRobbery(nums, 0, numberOfHouses - 2);
            int maxRobberyWithLastHouseButNotFirst = computeMaxRobbery(nums, 1, numberOfHouses - 1);

            maxRobbery = Math.max(maxRobberyWithFirstHouseButNotLast, maxRobberyWithLastHouseButNotFirst);
        }
        else if(numberOfHouses == 2) {
            maxRobbery = Math.max(nums[0], nums[1]);
        }
        else if(numberOfHouses == 1) {
            maxRobbery = nums[0];
        }

        return maxRobbery;
    }

    private int computeMaxRobbery(int[] nums, int startIndex, int endIndex) {

        int sumWithNextToNextNeighbor = nums[endIndex];
        int sumWithNeighbor = Math.max(nums[endIndex - 1], nums[endIndex]);

        for(int index = endIndex - 2 ; index >= startIndex ; index--) {
            int maxSum = Math.max(nums[index] + sumWithNextToNextNeighbor, sumWithNeighbor);

            sumWithNextToNextNeighbor = sumWithNeighbor;
            sumWithNeighbor = maxSum;

        }

        return sumWithNeighbor;
    }

    /*
    Time : O(n)
    Space : O(n)
    Depending on whether zeroth house was added check whether you're going to add the last house.
     */
    public int rob(int[] nums) {
        int[][] savedResults = new int[nums.length][2];

        for(int index = 0 ; index < nums.length ; index++) {
            savedResults[index][0] = -1;
            savedResults[index][1] = -1;
        }

        savedResults[nums.length - 1][0] = 0;
        return computeMaxRobbery(nums, 0, 1, savedResults);
    }

    private int computeMaxRobbery(int[] nums, int index, int includeLast, int[][] savedResults) {
        int maxRobbery = 0;

        if (index == 0) {
            int includingFirstHouse = nums[0] + computeMaxRobbery(nums, index + 2, 0, savedResults);
            int notIncludingFirst = computeMaxRobbery(nums, index + 1, 1, savedResults);
            maxRobbery = Math.max(includingFirstHouse, notIncludingFirst);
        } else if (index < nums.length) {

            maxRobbery = savedResults[index][includeLast];
            if (maxRobbery == -1) {
                int includingCurrent = nums[index] + computeMaxRobbery(nums, index + 2, includeLast, savedResults);

                int notIncludingCurrent = computeMaxRobbery(nums, index + 1, includeLast, savedResults);

                maxRobbery = Math.max(includingCurrent, notIncludingCurrent);
                savedResults[index][includeLast] = maxRobbery;
            }
        }

        return maxRobbery;
    }
    
}
