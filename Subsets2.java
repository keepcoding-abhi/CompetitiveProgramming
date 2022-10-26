public class Subsets2 {

    //1) Fast solution.
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> subsets = new LinkedList<List<Integer>>();
        LinkedList<Integer>currentList = new LinkedList<Integer>();

        createSubsets(subsets, nums, 0, currentList);

        return subsets;
    }

    private void createSubsets(List<List<Integer>> subsets, int[] nums, int start, LinkedList<Integer> currentList) {

        subsets.add(new ArrayList<Integer>(currentList));

        for(int index = start, totalElements = nums.length ; index < totalElements ; index++) {

            currentList.addFirst(nums[index]);
            createSubsets(subsets, nums, index + 1, currentList);
            currentList.removeFirst();

            while(index < nums.length - 1 && nums[index + 1] == nums[index]) {
                index++;
            }
        }
    }

    //2) Faster than solution 3, not relying on contains method on subsets to check duplicate sets.
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> subsets = new LinkedList<List<Integer>>();
        subsets.add(new ArrayList<Integer>());

        LinkedList<Integer> currentList = new LinkedList<Integer>();

        createSubsets(subsets, nums, 0, currentList);

        return subsets;
    }

    private void createSubsets(List<List<Integer>> subsets, int[] nums, int start, LinkedList<Integer> currentList) {

        currentList.addLast(nums[start]);
        subsets.add(new ArrayList<Integer>(currentList));

        if(start < nums.length - 1) {
            createSubsets(subsets, nums, start + 1, currentList);
        }

        currentList.removeLast();
        start++;

        while(start < nums.length && nums[start] == nums[start - 1]) {
            start++;
        }
        if(start < nums.length) {
            createSubsets(subsets, nums, start, currentList);
        }

    }

    //3) Brute-force solution. Sorting to ensure that duplicate subsets are not created.
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> subsets = new LinkedList<List<Integer>>();
        subsets.add(new LinkedList<Integer>());

        LinkedList<Integer>currentList = new LinkedList<Integer>();
        createSubsets(subsets, nums, 0, currentList);

        return subsets;
    }

    private void createSubsets(List<List<Integer>> subsets, int[] nums, int currentIndex, List<Integer> currentList) {

        if(currentIndex < nums.length) {

            currentList.add(nums[currentIndex]);
            if(!subsets.contains(currentList)) {
                subsets.add(new LinkedList<Integer>(currentList));
            }

            createSubsets(subsets, nums, currentIndex + 1, currentList);
            currentList.remove(currentList.size() - 1);
            createSubsets(subsets, nums, currentIndex + 1, currentList);

        }
    }
}
