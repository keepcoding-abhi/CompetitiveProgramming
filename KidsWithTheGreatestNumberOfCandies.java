public class KidsWithTheGreatestNumberOfCandies {

    // Find the maximum number of candies and then check which kid will have greater than or equal to this amount after
    // all extra candies are given to him/her.
    // Time : O(n), Space : O(1)
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int currentMaxCandies = candies[0];
        int numberOfKids = candies.length;

        for(int index = 1 ; index < numberOfKids ; index++) {
            int currentKidCandyCount = candies[index];

            if(currentKidCandyCount > currentMaxCandies) {
                currentMaxCandies = currentKidCandyCount;
            }
        }

        List<Boolean> result = new ArrayList<Boolean>(numberOfKids);
        for(int index = 0 ; index < numberOfKids ; index++) {
            if((candies[index] + extraCandies) >= currentMaxCandies) {
                result.add(true);
            }
            else {
                result.add(false);
            }
        }

        return result;
    }
}
