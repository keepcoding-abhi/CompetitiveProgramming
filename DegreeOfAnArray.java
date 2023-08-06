import java.util.HashMap;
import java.util.Map;

public class DegreeOfAnArray {
}

/*
Time : O(n)
Space : O(n)

For each unique number in nums maintain its frequency, first index of occurrence and last index of occurrence in a map.
Find the entry in map which has largest frequency and out of all the entries that have this frequency, get the one
with minimum length.
 */
class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> degreeInfos = new HashMap<Integer, int[]>();

        for(int index = 0, len = nums.length ; index < len ; index++) {
            int num = nums[index];

            int[] currentNumsDegreeInfo;

            if(degreeInfos.containsKey(num)) {
                currentNumsDegreeInfo = degreeInfos.get(num);
            }
            else {
                currentNumsDegreeInfo = new int[3];
                currentNumsDegreeInfo[1] = index;
                degreeInfos.put(num, currentNumsDegreeInfo);
            }

            currentNumsDegreeInfo[2] = index;
            currentNumsDegreeInfo[0]++;
        }

        int maxDegreeYet = 0, maxDegreeMinSubstrLen = Integer.MAX_VALUE;
        for(int[] currentValue : degreeInfos.values()) {
            if(currentValue[0] > maxDegreeYet) {
                int currentLen = currentValue[2] - currentValue[1] + 1;
                maxDegreeMinSubstrLen = currentLen;
                maxDegreeYet = currentValue[0];
            }
            else if(currentValue[0] == maxDegreeYet) {
                int currentLen = currentValue[2] - currentValue[1] + 1;
                if(maxDegreeMinSubstrLen > currentLen) {
                    maxDegreeMinSubstrLen = currentLen;
                }
            }
        }

        return maxDegreeMinSubstrLen;
    }
}

/*
Time : O(n)
Space : O(n)
Find the degree of original array. Then use sliding window to find the minimum length sub array with same degree.
If current sub array has required degree then move the left index to right till the degree stays the same.
 */
class Solution {
    public int findShortestSubArray(int[] nums) {
        int degreeOfOriginal = getDegree(nums);
        int minLen = nums.length;

        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        int leftIndex = 0, rightIndex = 0;

        while(rightIndex < nums.length) {
            int currentNum = nums[rightIndex], currentNumFreq = counts.getOrDefault(currentNum, 0) + 1;

            if(currentNumFreq == degreeOfOriginal) {
                while(nums[leftIndex] != currentNum) {
                    counts.put(nums[leftIndex], counts.get(nums[leftIndex]) - 1);
                    leftIndex++;
                }

                int currentLen = rightIndex - leftIndex + 1;
                minLen = Math.min(minLen, currentLen);
                leftIndex++;
            }
            else {
                counts.put(currentNum, currentNumFreq);
            }

            rightIndex++;
        }

        return minLen;
    }

    private int getDegree(int[] nums) {

        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();

        for(int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        int maxDegree = 1;
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int currentFreq = entry.getValue();
            if(currentFreq > maxDegree) {
                maxDegree = currentFreq;
            }
        }

        return maxDegree;
    }
}

/*
Time : O(n^2)
Space : O(n)
Instead of creating a new map to get frequencies in each sub array. We're reusing the one that was created in the
previous iteration for sub-array starting at same index. Since only the current number was modified we must check only
its frequency.
Exceeds time limit
 */
class Solution {
    public int findShortestSubArray(int[] nums) {
        int degreeOfOriginal = getDegree(nums);
        int minLen = nums.length;

        for(int indexL = 0 ; indexL < nums.length ; indexL++) {
            Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
            int degree = 0;
            for(int indexR = indexL ; indexR < nums.length ; indexR++) {
                int currentNum = nums[indexR];
                int currentNumFreq = counts.getOrDefault(currentNum, 0) + 1;

                counts.put(currentNum, currentNumFreq);

                if(currentNumFreq == degreeOfOriginal) {
                    int currentLen = indexR - indexL + 1;
                    if(currentLen < minLen) {
                        minLen = currentLen;
                    }
                }
            }
        }

        return minLen;
    }

    private int getDegree(int[] nums) {

        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();

        for(int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        int maxDegree = 1;
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int currentFreq = entry.getValue();
            if(currentFreq > maxDegree) {
                maxDegree = currentFreq;
            }
        }

        return maxDegree;
    }
}

/*
Time : O(n^3)
Space : O(n)

Brute force. Exceeds time limit.
Consider each sub array, calculate its length.
 */
class Solution {
    public int findShortestSubArray(int[] nums) {
        int degreeOfOriginal = getDegree(nums, 0, nums.length - 1);
        int minLen = nums.length;

        for(int indexL = 0 ; indexL < nums.length ; indexL++) {
            for(int indexR = indexL ; indexR < nums.length ; indexR++) {
                int currentDegree = getDegree(nums, indexL, indexR);
                if(currentDegree == degreeOfOriginal) {
                    int currentLen = indexR - indexL + 1;
                    if(currentLen < minLen) {
                        minLen = currentLen;
                    }
                }
            }
        }

        return minLen;
    }

    private int getDegree(int[] nums, int startIndex, int endIndex) {

        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();

        for(int index = startIndex ; index <= endIndex ; index++) {
            int currentNum = nums[index];

            counts.put(currentNum, counts.getOrDefault(currentNum, 0) + 1);
        }

        int maxDegree = 1;
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int currentFreq = entry.getValue();
            if(currentFreq > maxDegree) {
                maxDegree = currentFreq;
            }
        }

        return maxDegree;
    }
}
