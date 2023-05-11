import java.util.Arrays;

public class TwoSumLessThanK {

    /*
    Sorting the array and using two pointers which start from the beginning and end of the array respectively.
    Increment the left pointer if a larger number is required and decrement the right pointer if a smaller number is required.
    This ensures that all possible combinations which add up to k are tried.
    Time : O(nLog(n)) The two pointer approach for finding two elements of interest takes O(n) time.
    Space : O(n) or O(Log(n)) depending on the implementation of the sorting algorithm.
     */
    public int twoSumLessThanK(int[] nums, int k) {
        int result = -1;

        Arrays.sort(nums);

        int leftPointer = 0, rightPointer = nums.length - 1;
        while(leftPointer < rightPointer) {
            int currentSum = nums[leftPointer] + nums[rightPointer];

            if(currentSum < k) {
                result = Math.max(result, currentSum);
                if(result == k - 1) {
                    break;
                }
                leftPointer++;
            }
            else {
                rightPointer--;
            }
        }

        return result;
    }

    /*
    Keeping track of the number of occurrences of each number. 1000 is the maximum possible number.
    Thus, using an array of length 1000 will be sufficient to record each number.
    Start with the one of the two sum numbers as 1 and the other as 1000.
    Check whether there is at least one occurrence of each of the two numbers.
    Decrement higher number until a number occurring more than once is found. Do the same with lower number.
    Check whether each combination of the two numbers produces a result that's less than k.
    Since same number can occur twice, run the loop for the case where lowerNumber == higherNumber.
    Time : O(n + m), m is the largest number and n is the number of elements in array.
    Space : O(m).
     */
    public int twoSumLessThanK(int[] nums, int k) {
        int result = -1;

        int[] counts = new int[1001];

        for(int index = 0 ; index < nums.length ; index++) {
            counts[nums[index]]++;
        }

        int lowerNum = 1, higherNum = 1000;
        while(lowerNum <= higherNum) {
            if(counts[higherNum] == 0 || lowerNum + higherNum >= k) {
                higherNum--;
            }
            else {
                if(lowerNum < higherNum && counts[lowerNum] > 0) {
                    result = Math.max(result, lowerNum + higherNum);
                }
                else if(lowerNum == higherNum && counts[lowerNum] > 1) {
                    result = Math.max(result, lowerNum + lowerNum);
                }
                lowerNum++;
            }
        }

        return result;
    }

    /*
    Brute force solution, computes the sum of each possible pair and checks whether it's less than k.
    The largest such sum is selected as the result.
    Time : O(n^2), Space : O(1).
     */
    public int twoSumLessThanK(int[] nums, int k) {
        int result = -1;

        for(int index = 0 ; index < nums.length ; index++) {
            int firstNum = nums[index];
            for(int index1 = index + 1 ; index1 < nums.length ; index1++) {
                int currentSum = firstNum + nums[index1];

                if(currentSum < k) {
                    result = Math.max(result, currentSum);
                }
            }
        }

        return result;
    }
}
