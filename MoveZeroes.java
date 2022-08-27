public class MoveZeroes {
}

// 1) O(n) solution. Maintaining index of the end of sub-array containing non-zero elements.
class Solution {
    public void moveZeroes(int[] nums) {
        int nonZeroArrIndex = 0;

        for(int index = 0, totalElements = nums.length ; index < totalElements ; index++) {
            int currentElement = nums[index];

            if(currentElement != 0) {
                nums[nonZeroArrIndex] = currentElement;
                nonZeroArrIndex++;
            }
        }

        for(int index = nonZeroArrIndex, totalElements = nums.length ; index < totalElements ; index++) {
            nums[index] = 0;
        }
    }
}

// 2) O(n^2) solution. Shifting the remaining array to the left when zero is encountered.
class Solution {
    public void moveZeroes(int[] nums) {

        int totalElements = nums.length;

        for(int index = 0 ; index < totalElements ;) {
            int currentElement = nums[index];

            if(currentElement == 0) {
                shiftToLeft(nums, index + 1, totalElements);
                nums[totalElements - 1] = 0;
                totalElements--;
            }
            else {
                index++;
            }
        }
    }

    public void shiftToLeft(int[] nums, int startIndex, int endIndex) {

        for(int currentIndex = startIndex ; currentIndex < endIndex ; currentIndex++) {
            nums[currentIndex - 1] = nums[currentIndex];
        }
    }
}

// 3) Fastest solution. Adding zeros if the current non-zero element was placed at an earlier location. This eliminates
// the need for traversing the array again to fill-up zeroes. In solution 1 write operation is performed on all elements of the array.
// Therefore, the number of operations performed in sub-optimal. In this case, the write operation is performed only in case of the
// non-zero elements of the array.
// When number of zeroes are two large this difference could be huge.
// For an array with 1000 elements and 2 non-zero elements. The current algorithm will only require 2 write operations.
// WHereas the first algorithm would require 1000 write operations.


class Solution {
    public void moveZeroes(int[] nums) {

        int nonZeroIndex = 0;

        for(int index = 0, totalElements = nums.length ; index < totalElements ; index++) {
            int currentElement = nums[index];

            if(currentElement != 0) {
                nums[nonZeroIndex] = currentElement;
                if(nonZeroIndex != index) {
                    nums[index] = 0;
                }

                nonZeroIndex++;
            }
        }
    }
}