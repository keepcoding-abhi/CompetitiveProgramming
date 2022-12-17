public class HouseRobber2 {

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
    
}
