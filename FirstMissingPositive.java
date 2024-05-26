public class FirstMissingPositive {
}

/*
    Time: O(n)
    Space: O(1)

    Cyclic sort can be used to sort an array of length n, which has elements from 1 to n

    Use cyclic sort to place each element at its appropriate index. The first element in the array which is not at its
    correct index is the first missing positive element.
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        int index = 0;

        while(index < nums.length) {
            int currentNum = nums[index];

            if(currentNum >= 1 && currentNum <= nums.length && nums[currentNum - 1] != currentNum) {
                nums[index] = nums[currentNum - 1];
                nums[currentNum - 1] = currentNum;
            }
            else {
                index++;
            }
        }

        int missingNum = nums.length + 1;
        for(index = 0 ; index < nums.length ; index++) {
            if(nums[index] != index + 1) {
                missingNum = index + 1;
                break;
            }
        }

        return missingNum;
    }
}

/*
Time: O(n)
Space: O(1)

Use positions in nums to mark the occurrence of numbers from 1 to n
 */
class Solution {
    public int firstMissingPositive(int[] nums) {

        for(int index = 0 ; index < nums.length ; index++) {
            if(nums[index] <= 0) {
                nums[index] = 100001;
            }
        }

        int firstMissingPositive = nums.length + 1;

        for(int num : nums) {
            num = Math.abs(num);
            if(num > 0 && num <= nums.length) {

                if(nums[num - 1] > 0) {
                    nums[num - 1] *= -1;
                }
            }
        }

        for(int index = 0 ; index < nums.length ; index++) {
            if(nums[index] > 0) {
                firstMissingPositive = index + 1;
                break;
            }
        }

        return firstMissingPositive;
    }
}
