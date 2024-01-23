import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
    /*
    Time: O(n!)
    Space: O(n), depth of recursion stack

    Generate all possible combinations by swapping.
     */
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new LinkedList<List<Integer>>();

        generatePermutations(nums, 0, result);

        return result;
    }

    private void generatePermutations(int[] nums, int startIndex, List<List<Integer>> perms) {

        if(startIndex == nums.length) {
            List<Integer> nextPerm = new ArrayList<Integer>(nums.length);

            for(int num : nums) {
                nextPerm.add(num);
            }

            perms.add(nextPerm);
        }
        else {
            for(int swapWith = startIndex ; swapWith < nums.length ; swapWith++) {
                swap(nums, startIndex, swapWith);
                generatePermutations(nums, startIndex + 1, perms);
                swap(nums, startIndex, swapWith);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}