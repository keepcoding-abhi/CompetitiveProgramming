public class TwoSum2 {
    /*
    The input array is already sorted. Using two pointers to greedily move towards the optimal pair.
    The left pointer will point to the smallest and right to the largest element considered so far.
    Increment left pointer to increase the sum and decrement right pointer to reduce the sum.
    This ensures that a new pair is considered in each iteration.

    Time : O(n), Space : O(1)
     */
    public int[] twoSum(int[] numbers, int target) {

        int leftPointer = 0, rightPointer = numbers.length - 1;
        int[] resTwoNums = new int[2];

        while(leftPointer < rightPointer) {
            int currentSum = numbers[leftPointer] + numbers[rightPointer];

            if(currentSum == target) {
                resTwoNums[0] = leftPointer + 1;
                resTwoNums[1] = rightPointer + 1;
                break;
            }
            else if(currentSum < target) {
                leftPointer++;
            }
            else {
                rightPointer--;
            }
        }

        return resTwoNums;
    }
}
