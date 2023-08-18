import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsNearbyDuplicate {

    /*
    Time : O(n)
    Space : O(min(n, k))

    Save the last k elements in a hash table or set.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        boolean nearbyDup = false;

        if(k > 0) {
            int setSize = Math.min(nums.length, k);

            Set<Integer> lastKNums = new HashSet<Integer>(setSize);

            for(int index = 0 ; index < setSize ; index++) {
                int currentNum = nums[index];

                if(lastKNums.contains(currentNum)) {
                    nearbyDup = true;
                    break;
                }
                else {
                    lastKNums.add(currentNum);
                }
            }

            for(int index = setSize ; index < nums.length ; index++) {

                int currentNum = nums[index];

                if(lastKNums.contains(currentNum)) {
                    nearbyDup = true;
                    break;
                }
                else {
                    lastKNums.remove(nums[index - k]);
                    lastKNums.add(currentNum);
                }
            }
        }

        return nearbyDup;
    }

    /*
    Time : O(n)
    Space : O(min(n, k))

    A simpler code for the above approach.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        boolean nearbyDup = false;

        Set<Integer> lastKNums = new HashSet<Integer>();

        for(int index = 0 ; index < nums.length ; index++) {
            int nextNum = nums[index];

            if(lastKNums.contains(nextNum)) {
                nearbyDup = true;
                break;
            }

            lastKNums.add(nums[index]);

            if(lastKNums.size() > k) {
                lastKNums.remove(nums[index - k]);
            }
        }

        return nearbyDup;
    }

    /*
    Time : O(n)
    Space : O(n)

    Save previous index of each element in an hashmap and check if the difference between current index is within
    the threshold.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> indexInfo = new HashMap<Integer, Integer>();

        boolean nearbyDup = false;

        for(int index = 0, len = nums.length ; index < len ; index++) {
            int currentNum = nums[index];
            int prevIndex = indexInfo.getOrDefault(currentNum, -1);

            if(prevIndex != -1 && (Math.abs(index - prevIndex) <= k)) {
                nearbyDup = true;
                break;
            }

            indexInfo.put(currentNum, index);
        }

        return nearbyDup;
    }
}
