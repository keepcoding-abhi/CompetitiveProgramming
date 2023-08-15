import java.util.HashMap;
import java.util.Map;

public class RotateArray {

    /*
    Time : O(n)
    Space : O(1)
    1 2 3 4 5 6
    6 5 4 3 2 1
    4 5 6 1 2 3
     */
    public void rotate(int[] nums, int k) {
        k = (k % nums.length);

        reverseArray(nums, 0, nums.length - 1);

        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);
    }

    private void reverseArray(int[] nums, int startIndex, int endIndex) {
        while(startIndex < endIndex) {
            int temp = nums[startIndex];
            nums[startIndex] = nums[endIndex];
            nums[endIndex] = temp;

            startIndex++;
            endIndex--;
        }
    }

    /*
    Time : O(n)
    Space : O(1)
    Moving each element recursively. Shift an element to right place. Then shift the element to which this element
    got moved and so on. In cases like moving to right by 10 places in a 20 length array you'll reach the original index
    before moving all elements. In that case, increment the starting index by 1 and repeat.
     */
    public void rotate(int[] nums, int k) {
        k = (k % nums.length);

        int startIndex = 0;
        int elementsMoved = 0;

        while(elementsMoved < nums.length) {

            int sourceIndex = startIndex, destIndex = (startIndex + k) % nums.length;
            int movingEl = nums[sourceIndex];

            do {
                int removedEl = nums[destIndex];
                nums[destIndex] = movingEl;
                movingEl = removedEl;
                sourceIndex = destIndex;
                destIndex = (destIndex + k) % nums.length;
                elementsMoved++;
            } while(sourceIndex != startIndex);

            startIndex++;
        }
    }

    /*
    Time : O(n)
    Space : O(n)
    Saving the index and element information of original array and then using this information to map indices in the
    resulting array with those in original array.
     */
    public void rotate(int[] nums, int k) {
        Map<Integer, Integer> originalInfo = new HashMap<Integer, Integer>();

        int len = nums.length;
        for(int index = 0 ; index < len ; index++) {
            originalInfo.put(index, nums[index]);
        }

        k = k % len;
        for(int index = 0 ; index < len ; index++) {
            nums[index] = originalInfo.get((index - k + len) % len);
        }
    }

    /*
    Time : O(n^2)
    Space : O(1)
    Moving to right by 1 for k times.
     */
    public void rotate(int[] nums, int k) {
        k = (k % nums.length);

        for(int index = 0 ; index < k ; index++) {
            rightShift(nums);
        }
    }

    private void rightShift(int[] nums) {
        int lastNum = nums[nums.length - 1];

        for(int index = nums.length - 1 ; index > 0 ; index--) {
            nums[index] = nums[index - 1];
        }

        nums[0] = lastNum;
    }
}
