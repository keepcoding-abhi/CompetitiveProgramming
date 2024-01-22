import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets {

    // Faster solution.
    /*

    Time: O(n * 2^n), O(n) time required to copy each subset in the output list
    Space: O(n) required for recursion and for storing the current subset

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

    // Using the fact that an element either exists in a subset or it doesn't in the recursion.
    /*
    Time: O(n * 2^n)
    Space: O(n) required for recursion and for storing the current subset
     */
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

    /*
    Time: O(n * 2^n)
    Space: O(n), space required to hold the references to newly created subsets before they are added into the results

    Iterative approach. When a new element is found, add this element to each of the previous subsets to generate
    the new subsets.
     */
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> results = new LinkedList<List<Integer>>();

        List<Integer> currentSubset = new LinkedList<Integer>();

        results.add(currentSubset);

        for(int num : nums) {
            List<List<Integer>> newElements = new LinkedList<List<Integer>>();

            for(List<Integer> subset : results) {
                List<Integer> newSubset = new LinkedList<Integer>(subset);
                newSubset.add(num);

                newElements.add(newSubset);
            }

            results.addAll(newElements);
        }

        return results;
    }

    /*
    Time: O(n * 2^n)
    Space: O(n), size of bit mask

    Generating a bit mask and interpreting a 1 at a position as the presence of a number from the array.
     */
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> results = new LinkedList<List<Integer>>();

        for(int bitmaskRep = (int) (Math.pow(2, nums.length + 1) - 1) ; bitmaskRep >= Math.pow(2, nums.length) ; bitmaskRep--) {
            String bitmask = Integer.toBinaryString(bitmaskRep).substring(1);

            results.add(getSubsetFromBitmask(bitmask, nums));
        }

        return results;
    }

    private List<Integer> getSubsetFromBitmask(String bitmask, int[] nums) {
        List<Integer> subset = new LinkedList<Integer>();

        for(int index = 0 ; index < bitmask.length() ; index++) {
            if(bitmask.charAt(index) == '1') {
                subset.add(nums[index]);
            }
        }

        return subset;
    }

}
