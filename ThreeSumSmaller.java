public class ThreeSumSmaller {

    // Time : O(n^2)
    // Space : O(Log(n)) to O(n) depending on the sorting algorithm
    // Sort the array and fix the first element in triplet.
    // Use two pointers for finding all combinations of the remaining two elements.
    // When the two pointers are located at positions such that the sum is lesser than the target,
    // find all possible combinations of element at left pointer with elements up to the right pointer.
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;

        for(int firstIndex = 0 ; firstIndex < nums.length - 2 ; firstIndex++) {
            int firstNum = nums[firstIndex];

            int leftPointer = firstIndex + 1, rightPointer = nums.length - 1;
            int currentUpperBound = target - firstNum;
            while(leftPointer < rightPointer) {
                int currentSum = nums[leftPointer] + nums[rightPointer];

                if(currentSum >= currentUpperBound) {
                    rightPointer--;
                }
                else {
                    count += ((rightPointer - leftPointer));
                    leftPointer++;
                }
            }

        }

        return count;
    }

    // Time : O(n^2 * Log(n))
    // Space : O(Log(n)) to O(n)
    // Fixing the first two elements of the triplet, and then using binary search to find the correct position of
    // the third element such that the sum becomes target.
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;

        for(int firstIndex = 0 ; firstIndex < nums.length - 2 ; firstIndex++) {
            for(int secondIndex = firstIndex + 1 ; secondIndex < nums.length - 1 ; secondIndex++) {

                int lastValidIndex = binarySearch(nums, secondIndex + 1, target - nums[firstIndex] - nums[secondIndex]);

                count += (lastValidIndex - secondIndex - 1);
            }
        }

        return count;
    }

    private int binarySearch(int[] nums, int startIndex, int target) {
        int lowerIndex = startIndex, upperIndex = nums.length - 1;

        while(lowerIndex <= upperIndex) {
            int mid = (lowerIndex + upperIndex) / 2;
            int midElement = nums[mid];

            if(midElement < target) {
                lowerIndex = mid + 1;
            }
            else if(midElement == target) {
                upperIndex = mid - 1;
            }
            else {
                upperIndex = mid - 1;
            }
        }

        return lowerIndex;
    }


    // Time : O(n^3)
    // Space : O(1)
    // Brute-force approach
    // Exceeds time limit
    public int threeSumSmaller(int[] nums, int target) {
        int count = 0;

        for(int firstIndex = 0 ; firstIndex < nums.length - 2 ; firstIndex++) {
            int firstNum = nums[firstIndex];
            for(int secondIndex = firstIndex + 1 ; secondIndex < nums.length - 1 ; secondIndex++) {
                int secondNum = nums[secondIndex];
                for(int thirdIndex = secondIndex + 1 ; thirdIndex < nums.length ; thirdIndex++) {
                    if(firstNum + secondNum + nums[thirdIndex] < target) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
