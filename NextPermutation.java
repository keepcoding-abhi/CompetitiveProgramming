public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int index = nums.length - 1;

        while(index > 0 && nums[index - 1] >= nums[index]) {
            index--;
        }

        if(index != 0) {
            int nextElement = nums[index - 1];

            int indexOfSmallestElementGreaterThanNextElementInSecondHalf = index;
            for(int index_1 = nums.length - 1 ; index_1 >= index ; index_1--) {
                if(nums[index_1] > nextElement) {
                    indexOfSmallestElementGreaterThanNextElementInSecondHalf = index_1;
                    break;
                }
            }

            swap(nums, index - 1, indexOfSmallestElementGreaterThanNextElementInSecondHalf);
        }

        reverse(nums, index, nums.length);
    }

    private void reverse(int arr[], int start, int end) {

        int leftIndex = start, rightIndex = end - 1;

        while(leftIndex < rightIndex) {
            swap(arr, leftIndex, rightIndex);
            leftIndex++;
            rightIndex--;
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

}
