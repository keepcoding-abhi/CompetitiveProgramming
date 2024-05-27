public class FindTheDuplicateNumber {
}

/*
Time: O(n)
Space: O(1)

Utilizing the Floyd's Tortoise and Hare algorithm to find the beginning of cycle in a linked list.

The linked list is composed of indices of the array which specify the previous node and the array element, which
specifies the current node.
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int slowPtr = nums[0];
        int fastPtr = nums[0];

        slowPtr = nums[slowPtr];
        fastPtr = nums[nums[fastPtr]];

        while(slowPtr != fastPtr) {
            slowPtr = nums[slowPtr];
            fastPtr = nums[nums[fastPtr]];
        }

        fastPtr = nums[0];

        while(slowPtr != fastPtr) {
            slowPtr = nums[slowPtr];
            fastPtr = nums[fastPtr];
        }

        return slowPtr;
    }
}

/*
Time: O(n*Log(n))
Space: O(1)

In the array [1 2 3 4]
the number of elements less than or equal to a number n, are equal to n

When duplicates are present, the number of elements less than or equal to a number is more than that number.

Since the array consists of n + 1 elements and only the numbers from 1 to n are present in it. We can expect the previous
property to hold in this case.

Starting from 1 the above check would return false until, we hit the duplicate number. Apply binary search to reach
this number.
 */
class Solution {

    public int findDuplicate(int[] nums) {
        int lowestNum = 1, highestNum = nums.length - 1;

        while(lowestNum <= highestNum) {
            int currentBound = (lowestNum + highestNum) / 2;

            if(checkDuplicates(nums, currentBound)) {
                highestNum = currentBound - 1;
            }
            else {
                lowestNum = currentBound + 1;
            }
        }

        return lowestNum;
    }

    private boolean checkDuplicates(int[] nums, int upperBound) {
        int numsLessThanUB = 0;

        for(int num : nums) {
            if(num <= upperBound) {
                numsLessThanUB++;
            }
        }

        boolean duplicatesPresent = false;

        if(numsLessThanUB > upperBound) {
            duplicatesPresent = true;
        }

        return duplicatesPresent;
    }
}

/*
Time: O(n)
Space: O(n)

Using Cycle sort. Since the array of length n, only consists of numbers from 1 to n + 1, each number n + 1 in the array
can be placed at index n. Swap the numbers that are not at their correct positions. During swapping the presence of
duplicate numbers can be detected.
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int index = 0, duplicateVal = 0;

        while(index < nums.length) {
            int currentNum = nums[index];

            if(index == currentNum - 1) {
                index++;
            }
            else if(nums[currentNum - 1] == currentNum) {
                duplicateVal = currentNum;
                break;
            }
            else {
                nums[index] = nums[currentNum - 1];
                nums[currentNum - 1] = currentNum;
            }
        }

        return duplicateVal;
    }
}

/*
Time: O(n*Log(n))
Space: O(Log(n)), O(1) solution possible by computing the frequency of one bit at a time instead of storing them in
an array

Calculate the frequency with which each bit position is 1 for each element in nums, and the frequencies of bit positions
in numbers from 1 to n.

Compute the difference of two frequencies to get the duplicate value
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int largestNum = nums.length - 1;
        int numberOfBits = calculateBitCount(largestNum);

        int[] numsBitCount = new int[numberOfBits];
        for(int num : nums) {
            computeBitCount(numsBitCount, num);
        }

        int[] baseBitCount = new int[numberOfBits];
        for(int num = 1 ; num <= nums.length - 1 ; num++) {
            computeBitCount(baseBitCount, num);
        }

        subtractArrays(numsBitCount, baseBitCount);

        int duplicateVal = computeNumFromBitCount(numsBitCount);

        return duplicateVal;
    }

    private int computeNumFromBitCount(int[] bitCounts) {

        int num = 0;
        int power = 1;

        for(int index = 0 ; index < bitCounts.length ; index++) {
            if(bitCounts[index] > 0) {
                num += power;
            }

            power *= 2;
        }

        return num;
    }

    private void subtractArrays(int[] arr1, int[] arr2) {
        for(int index = 0, arrLen = arr1.length ; index < arrLen ; index++) {
            arr1[index] = arr1[index] - arr2[index];
        }
    }

    private void computeBitCount(int[] bitCounts, int num) {
        int index = 0;

        while(num != 0) {
            int bitVal = num & 1;

            if(bitVal == 1) {
                bitCounts[index]++;
            }

            index++;
            num >>= 1;
        }
    }

    private int calculateBitCount(int num) {
        int bitCount = 0;

        while(num != 0) {
            num /= 2;
            bitCount++;
        }

        return bitCount;
    }
}


/*
Time: O(n)
Space: O(n)

Use negative sign, to mark the occurrence of each number.
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int duplicateVal = -1;

        for(int num : nums) {

            num = Math.abs(num);

            if(nums[num - 1] < 0) {
                duplicateVal = num;
            }
            else {
                nums[num - 1] *= -1;
            }
        }

        return duplicateVal;
    }
}

/*
Time: O(n^2)
Space: O(1)

Brute force approach. Check every pair of numbers.
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int duplicateNum = -1;

        for(int index = 0 ; index < nums.length && duplicateNum == -1 ; index++) {
            int prevNum = nums[index];
            for(int index1 = index + 1 ; index1 < nums.length ; index1++) {
                if(nums[index1] == prevNum) {
                    duplicateNum = prevNum;
                    break;
                }
            }
        }

        return duplicateNum;
    }
}