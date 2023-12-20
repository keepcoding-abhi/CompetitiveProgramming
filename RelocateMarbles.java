import java.util.*;

public class RelocateMarbles {

    /*
    Time: O(n + m)
    Space: O(n)

    n size of nums, m size of moveFrom

    Use a set to keep track of current elements
     */
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> currentEls = new HashSet<Integer>();

        for(int num : nums) {
            currentEls.add(num);
        }

        int nOps = moveFrom.length;

        for(int index = 0 ; index < nOps ; index++) {
            currentEls.remove(moveFrom[index]);
            currentEls.add(moveTo[index]);
        }

        List<Integer> result = new ArrayList<Integer>();

        for(int num : currentEls) {
            result.add(num);
        }

        Collections.sort(result);

        return result;
    }
}
