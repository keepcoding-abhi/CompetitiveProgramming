public class SearchInsertPosition {
}

// 1) Finding proper position of target after completing binary search.

class Solution {
    public int searchInsert(int[] nums, int target) {

        int lower = 0, upper = nums.length - 1;
        int result = -1;

        while(lower < upper) {
            int mid = (lower + upper) / 2;
            int currentNumber = nums[mid];

            if(target < currentNumber) {
                upper = mid - 1;
            }
            else if(target > currentNumber) {
                lower = mid + 1;
            }
            else {
                result = mid;
                break;
            }
        }

        if(result == -1) {
            int currentElement = nums[lower];

            if(currentElement == target) {
                result = lower;
            }
            else if(currentElement > target) {
                result = lower;
            }
            else {
                result = lower + 1;
            }
        }

        return result;
    }
}

// 2) Better approach. The lower pointer always points to the proper location.

class Solution {
    public int searchInsert(int[] nums, int target) {

        int lower = 0, upper = nums.length - 1;
        int result = 0;

        if(target <= nums[0]) {
            result = 0;
        }
        else {
            while(lower <= upper) {
                int mid = lower + (upper - lower) / 2;  // To avoid integer overflow when upper and lower are large.
                int currentNumber = nums[mid];

                if(target < currentNumber) {
                    upper = mid - 1;
                }
                else if(target > currentNumber) {
                    result = lower = mid + 1;
                }
                else {
                    result = mid;
                    break;
                }
            }
        }

        return result;
    }
}

