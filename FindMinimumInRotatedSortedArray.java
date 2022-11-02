public class FindMinimumInRotatedSortedArray {

    //Time complexity = O(Logn) Space complexity = O(1)
    public int findMin(int[] nums) {

        int minimumElement = nums[0];
        if(nums[0] > nums[nums.length - 1]) {
            int lower = 0;
            int upper = nums.length - 1;

            while(lower <= upper) {
                int currentMid = (lower + upper) / 2;
                int currentElement = nums[currentMid];

                if(currentElement > nums[currentMid + 1]) {
                    minimumElement = nums[currentMid + 1];
                    break;
                }
                else if(nums[lower] <= currentElement) {
                    lower = currentMid + 1;
                }
                else {
                    upper = currentMid - 1;
                }
            }
        }
        return minimumElement;
    }

    //2) Cleaner solution
    public int findMin(int[] nums) {

        int minimumElement = nums[0];
        if(nums[0] > nums[nums.length - 1]) {
            int lower = 0;
            int upper = nums.length - 1;

            while(lower <= upper) {
                int currentMid = (lower + upper) / 2;
                int currentElement = nums[currentMid];

                if(currentElement > nums[currentMid + 1]) {
                    minimumElement = nums[currentMid + 1];
                    break;
                }
                else if(nums[currentMid - 1] > currentElement) {
                    minimumElement = currentElement;
                    break;
                }
                else if(nums[lower] < currentElement) {
                    lower = currentMid + 1;
                }
                else {
                    upper = currentMid - 1;
                }
            }
        }

        return minimumElement;
    }

}
