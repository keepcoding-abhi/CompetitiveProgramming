public class SortColors {

    //1) Faster approach with swapping.
    public void sortColors(int[] nums) {

        int lastTwo = nums.length;
        while(lastTwo > 0 && nums[lastTwo - 1] == 2) {
            lastTwo--;
        }

        int lastZero = -1;
        while(lastZero < lastTwo - 1 && nums[lastZero + 1] == 0) {
            lastZero++;
        }

        int firstOne = -1, lastOne = -1;
        int index = lastZero + 1;
        while(index < lastTwo) {

            int currentColor = nums[index];

            if(currentColor == 2) {
                swap(nums, index, lastTwo - 1);
                lastTwo--;
            }
            else if(currentColor == 1) {
                index++;
            }
            else {
                swap(nums, lastZero + 1, index);
                lastZero++;
                index++;
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];

        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    //2) Slow approach using insertion sort.
    public void sortColors(int[] nums) {

        int totalElements = nums.length;
        for(int index = 1 ; index < totalElements ; index++) {

            int currentColor = nums[index];
            int sortedPortionIndex = index - 1;
            while(sortedPortionIndex > -1 && nums[sortedPortionIndex] > currentColor) {
                nums[sortedPortionIndex + 1] = nums[sortedPortionIndex];
                sortedPortionIndex--;
            }
            nums[sortedPortionIndex + 1] = currentColor;
        }
    }


}
