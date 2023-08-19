import java.util.SortedSet;
import java.util.TreeSet;

public class ContainsDuplicate3 {

    /*
    Time : O(nLog(n)), for each number in nums we're checking whether any number in the range of permissible values
    exists in the tree which takes O(Log(n)) time.
    Space : O(min(n, indexDiff)), the tree will never contain more than indexDiff elements

    Using a sorted set / red-black tree since search, insert remove operations can be performed in logarithmic time.

     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        SortedSet<Integer> prevIndices = new TreeSet<Integer>();
        boolean nearbyPresent = false;

        for(int index = 0, len = nums.length ; index < len ; index++) {
            int currentNum = nums[index];

            int lowerBound = currentNum - valueDiff, upperBound = currentNum + valueDiff;

            if(prevIndices.subSet(lowerBound, upperBound + 1).size() > 0) {
                nearbyPresent = true;
                break;
            }

            prevIndices.add(currentNum);
            if(prevIndices.size() > indexDiff) {
                prevIndices.remove(nums[index - indexDiff]);
            }
        }

        return nearbyPresent;
    }

    /*
    Time : O(n)
    Space : O(n)
    A more efficient version of the above approach. CHheck the predecessor and successor of an element in the set
    instead of computing the subset.
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> prevNums = new TreeSet<Integer>();
        boolean nearbyPresent = false;

        for(int index = 0, len = nums.length ; index < len ; index++) {
            int currentNum = nums[index];

            int lowerBound = currentNum - valueDiff, upperBound = currentNum + valueDiff;

            Integer predecessorElement = prevNums.floor(currentNum);

            if(predecessorElement != null && predecessorElement >= lowerBound) {
                nearbyPresent = true;
                break;
            }

            Integer successorElement = prevNums.ceiling(currentNum);

            if(successorElement != null && successorElement <= upperBound) {
                nearbyPresent = true;
                break;
            }

            prevNums.add(currentNum);
            if(prevNums.size() > indexDiff) {
                prevNums.remove(nums[index - indexDiff]);
            }
        }

        return nearbyPresent;
    }
}
