public class SquaresOfASortedArray {

    // Merge two sorted arrays.
    // Time complexity : O(n), Space : O(1)
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int nonNegPtr = len;

        for(int index = 0 ; index < len ; index++) {
            if(nums[index] >= 0) {
                nonNegPtr = index;
                break;
            }
        }

        int negPtr = nonNegPtr - 1;

        int[] sqSortedArray = new int[len];
        int sortedPtr = 0;
        while(negPtr > -1 && nonNegPtr < len) {
            int negValSq = nums[negPtr] * nums[negPtr];
            int nonNegValSq = nums[nonNegPtr] * nums[nonNegPtr];

            if(negValSq < nonNegValSq) {
                sqSortedArray[sortedPtr] = negValSq;
                negPtr--;
            }
            else {
                sqSortedArray[sortedPtr] = nonNegValSq;
                nonNegPtr++;
            }

            sortedPtr++;
        }

        while(negPtr > -1) {
            sqSortedArray[sortedPtr] = nums[negPtr] * nums[negPtr];
            negPtr--;
            sortedPtr++;
        }

        while(nonNegPtr < len) {
            sqSortedArray[sortedPtr] = nums[nonNegPtr] * nums[nonNegPtr];
            nonNegPtr++;
            sortedPtr++;
        }

        return sqSortedArray;
    }

    // Filling the resulting array in reverse order leads to cleaner code.
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] sqSorted = new int[len];

        int leftPtr = 0, rightPtr = len - 1;
        int leftVal = nums[leftPtr] * nums[leftPtr];
        int rightVal = nums[rightPtr] * nums[rightPtr];
        int sortedArrIndex = len - 1;

        while(leftPtr < rightPtr) {
            if(leftVal < rightVal) {
                sqSorted[sortedArrIndex] = rightVal;
                rightPtr--;
                rightVal = nums[rightPtr] * nums[rightPtr];
            }
            else {
                sqSorted[sortedArrIndex] = leftVal;
                leftPtr++;
                leftVal = nums[leftPtr] * nums[leftPtr];
            }

            sortedArrIndex--;
        }

        sqSorted[sortedArrIndex] = leftVal;

        return sqSorted;
    }
}
