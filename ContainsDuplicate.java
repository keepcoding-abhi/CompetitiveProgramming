import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    /*
    Time : O(n)
    Space : O(n)

    Save the previously seen elements in a hash map.
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seenVals = new HashSet<Integer>();
        boolean duplicate = false;

        for(int num : nums) {
            if(seenVals.contains(num)) {
                duplicate = true;
                break;
            }
            else {
                seenVals.add(num);
            }
        }

        return duplicate;
    }

}
