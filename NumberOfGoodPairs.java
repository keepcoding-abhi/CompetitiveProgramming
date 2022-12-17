public class NumberOfGoodPairs {

    public int numIdenticalPairs(int[] nums) {
        int numberOfGoodPairs = 0;

        HashMap<Integer, Integer> elementFrequencies = new HashMap<Integer, Integer>();

        for(int index = 0, len = nums.length ; index < len ; index++) {

            int currentEl = nums[index];
            int occurrencesOfCurrentEl = elementFrequencies.getOrDefault(currentEl, 0);

            if(occurrencesOfCurrentEl > 0) {
                numberOfGoodPairs += occurrencesOfCurrentEl;
            }

            elementFrequencies.put(currentEl, occurrencesOfCurrentEl + 1);
        }

        return numberOfGoodPairs;
    }

}
