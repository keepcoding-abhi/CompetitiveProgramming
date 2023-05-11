import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    class Solution {
        /*
        Scan each element of nums. And for every element add the number required to add to the target as key and
        the index of that element as its value in the map. On scanning a new element check whether an element with that
        key already exists in the array. If yes, the value of that element and the current index constitute the result.
        Time : O(n), Space : O(n)
         */
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> numbersRequired = new HashMap<Integer, Integer>();
            int[] result = new int[2];

            for(int index = 0 ; index < nums.length ; index++) {
                int currentNum = nums[index];

                if(numbersRequired.containsKey(currentNum)) {
                    result[0] = numbersRequired.get(currentNum);
                    result[1] = index;
                    break;
                }

                numbersRequired.put(target - currentNum, index);
            }

            return result;
        }
    }
}