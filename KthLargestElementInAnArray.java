public class KthLargestElementInAnArray {
}

class Solution {

    // Find a pivot element in each iteration till the pivot element is present at last kth position.
    // Time :O(n^2), Space : O(1)
    public int findKthLargest(int[] nums, int k) {

        int leftIndex = 0, rightIndex = nums.length - 1;
        int lastKthIndex = nums.length - k;
        int kthLargest = -1;

        for(int index = 0 ; index < nums.length ; index++) {
            int currentPivot = partition(nums, leftIndex, rightIndex);

            if(currentPivot == lastKthIndex) {
                kthLargest = nums[lastKthIndex];
                break;
            }
            else if(currentPivot < lastKthIndex) {
                leftIndex = currentPivot + 1;
            }
            else {
                rightIndex = currentPivot - 1;
            }
        }

        return kthLargest;
    }

    private int partition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[endIndex];
        int leftIndex = startIndex;

        for(int index = startIndex ; index < endIndex ; index++) {
            int currentNum = arr[index];

            if(currentNum < pivot) {
                int temp = arr[leftIndex];
                arr[leftIndex] = currentNum;
                arr[index] = temp;
                leftIndex++;
            }
        }

        int temp = arr[leftIndex];
        arr[leftIndex] = pivot;
        arr[endIndex] = temp;

        return leftIndex;
    }
}