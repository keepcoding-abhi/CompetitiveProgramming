public class Subsets {

    //1) Faster solution.
    /*
    Reduced the number of recursions by introducing a for-loop.
    In the for loop we give each element in the array an opporunity to be the ath element in the set and populate the
    subset with remaining elements in the array.
     */
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> allSubsets = new LinkedList<List<Integer>>();

        LinkedList<Integer> currentSet = new LinkedList<Integer>();

        if(nums.length > 0) {
            generateSubsets(allSubsets, nums, 0, currentSet);
        }

        return allSubsets;
    }

    private void generateSubsets(List<List<Integer>> allSubsets, int[] nums, int start, LinkedList<Integer> currentSet) {

        allSubsets.add(new LinkedList<Integer>(currentSet));

        for(int index = start, totalElements = nums.length ; index < totalElements ; index++) {
            currentSet.addLast(nums[index]);
            generateSubsets(allSubsets, nums, index + 1, currentSet);
            currentSet.removeLast();
        }
    }

    //1) Using the fact that an element either exists in a subset or it doesn't in the recursion.
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> allSubsets = new LinkedList<List<Integer>>();
        allSubsets.add(new ArrayList<Integer>());

        LinkedList<Integer> currentSet = new LinkedList<Integer>();
        generateSubsets(allSubsets, nums, 0, currentSet);

        return allSubsets;
    }

    private void generateSubsets(List<List<Integer>> allSubsets, int[] nums, int start, LinkedList<Integer> currentSet) {

        if(start < nums.length) {

            currentSet.addLast(nums[start]);
            allSubsets.add(new LinkedList<Integer>(currentSet));
            generateSubsets(allSubsets, nums, start + 1, currentSet);

            currentSet.removeLast();
            generateSubsets(allSubsets, nums, start + 1, currentSet);
        }
    }

}
