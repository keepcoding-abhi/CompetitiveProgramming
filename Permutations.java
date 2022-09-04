class Solution {
    public void nextPermutation(int[] nums) {
        int totalElements = nums.length;
        int inputPermutationSorted[] = new int[totalElements];

        for(int index = 0 ; index < totalElements ; index++) {
            inputPermutationSorted[index] = nums[index];
        }

        Arrays.sort(inputPermutationSorted);

        List<int[]> sortedPermutations = new ArrayList<int[]>();
        generatePermutations(inputPermutationSorted, 0, sortedPermutations);

        int indexOfCurrentPermutation = searchPermutation(inputPermutationSorted, sortedPermutations);

        int[] result;
        if(indexOfCurrentPermutation == sortedPermutations.size() - 1) {
            result = sortedPermutations.get(0);
        }
        else {
            result = sortedPermutations.get(indexOfCurrentPermutation + 1);
        }

        for(int index = 0 ; index < totalElements ; index++) {
            nums[index] = result[index];
        }
    }

    public int searchPermutation(int[] inputPermutation, List<int[]> sortedPermutations) {

        int sortedPermIndex = 0;
        for(int index = 0, totalElements = inputPermutation.length ; index < totalElements ; index++) {
            int currentElement = inputPermutation[index];

            while(sortedPermIndex < sortedPermutations.size()) {
                int[] currentPerm = sortedPermutations.get(sortedPermIndex);

                if(currentPerm[index] == currentElement) {
                    break;
                }

                sortedPermIndex++;
            }
        }

        return sortedPermIndex;
    }

    public void generatePermutations(int[] nums, int beginIndex, List<int[]> sortedPermutations) {

        int totalElements = nums.length;
        if(beginIndex == totalElements - 1) {
            int currentPerm[] = nums.clone();
            sortedPermutations.add(currentPerm);
        }
        else {
            for(int index = beginIndex ; index < totalElements ; index++) {
                swap(nums, beginIndex, index);
                generatePermutations(nums, beginIndex + 1, sortedPermutations);
                swap(nums, beginIndex, index);
            }
        }
    }

    public void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}