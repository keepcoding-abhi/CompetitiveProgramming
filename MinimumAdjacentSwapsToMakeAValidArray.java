public class MinimumAdjacentSwapsToMakeAValidArray {
}

/*
Time: O(n)
Space: O(1)

Find the indices of the minimum and maximum elements and mathematically compute the number of swaps required
 */
class Solution {
    public int minimumSwaps(int[] nums) {

        int swapsReqd = 0;
        if(nums.length > 1) {
            int minEl = 100001, maxEl = 0;

            for(int num : nums) {
                minEl = Math.min(num, minEl);
                maxEl = Math.max(num, maxEl);
            }

            int firstOccrsOfMin = -1;
            for(int index = 0 ; index < nums.length ; index++) {
                if(nums[index] == minEl) {
                    firstOccrsOfMin = index;
                    break;
                }
            }

            int lastOccrsOfMax = nums.length;
            for(int index = nums.length - 1 ; index > -1 ; index--) {
                if(nums[index] == maxEl) {
                    lastOccrsOfMax = index;
                    break;
                }
            }

            if(firstOccrsOfMin != 0 && lastOccrsOfMax != nums.length - 1) {
                if(firstOccrsOfMin < lastOccrsOfMax) {
                    swapsReqd = firstOccrsOfMin + (nums.length - lastOccrsOfMax - 1);
                }
                else {
                    swapsReqd = firstOccrsOfMin + (nums.length - lastOccrsOfMax - 2);
                }
            }
            else if(firstOccrsOfMin != 0) {
                swapsReqd = firstOccrsOfMin;
            }
            else {
                swapsReqd = (nums.length - lastOccrsOfMax - 1);
            }
        }

        return swapsReqd;
    }
}
