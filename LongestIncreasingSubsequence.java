import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

    // Time : O(nLog(n))
    // Space : O(n)
    // Using binary search to find the correct position of a new element in the increasing subsequence.
    public int lengthOfLIS(int[] nums) {
        List<Integer> increasingSubSeq = new ArrayList<Integer>(nums.length);

        increasingSubSeq.add(nums[0]);

        for(int index = 1, len = nums.length ; index < len ; index++) {
            int currentNum = nums[index];

            if(currentNum > increasingSubSeq.get(increasingSubSeq.size() - 1)) {
                increasingSubSeq.add(currentNum);
            }
            else {
                for(int seqIndex = 0, seqSize = increasingSubSeq.size() ; seqIndex < seqSize ; seqIndex++) {
                    int correctIndex = findCorrectIndex(increasingSubSeq, currentNum);
                    increasingSubSeq.set(correctIndex, currentNum);
                }
            }
        }

        return increasingSubSeq.size();
    }

    private int findCorrectIndex(List<Integer> increasingSubSeq, int target) {
        int lowerIndex = 0, upperIndex = increasingSubSeq.size() - 1;

        while(lowerIndex <= upperIndex) {
            int mid = lowerIndex + (upperIndex - lowerIndex) / 2;
            int midEl = increasingSubSeq.get(mid);

            if(midEl == target) {
                lowerIndex = mid;
                break;
            }
            else if(midEl < target) {
                lowerIndex = mid + 1;
            }
            else {
                upperIndex = mid - 1;
            }
        }

        return lowerIndex;
    }

    // Time : O(n^2)
    // Space : O(n)
    // Using an array to keep track of the longest subsequence encountered so far as we scan the input.
    // The array is maintained in ascending order and in the end the length of array corresponds to the length of
    // the longest increasing subsequence although the contents of the array might not be same as the
    // longest increasing subsequence.
    public int lengthOfLIS(int[] nums) {
        List<Integer> increasingSubSeq = new ArrayList<Integer>(nums.length);

        increasingSubSeq.add(nums[0]);

        for(int index = 1, len = nums.length ; index < len ; index++) {
            int currentNum = nums[index];

            if(currentNum > increasingSubSeq.get(increasingSubSeq.size() - 1)) {
                increasingSubSeq.add(currentNum);
            }
            else {
                for(int seqIndex = 0, seqSize = increasingSubSeq.size() ; seqIndex < seqSize ; seqIndex++) {
                    if(increasingSubSeq.get(seqIndex) >= currentNum) {
                        increasingSubSeq.set(seqIndex, currentNum);
                        break;
                    }
                }
            }
        }

        return increasingSubSeq.size();
    }

    // Time : O(n^2)
    // Space O(n)
    // Bottom -up DP-based solution
    // savedSeqLens[i] is the length of the longest subsequence whose first element is nums[i]
    public int lengthOfLIS(int[] nums) {
        int[] savedSeqLens = new int[nums.length];
        savedSeqLens[nums.length - 1] = 1;

        for(int index = nums.length - 2 ; index > -1 ; index--) {
            int currentEl = nums[index];
            int longestSeqFromCurrent = 0;

            for(int index1 = index + 1 ; index1 < nums.length ; index1++) {
                if(nums[index1] > currentEl) {
                    longestSeqFromCurrent = Math.max(longestSeqFromCurrent, savedSeqLens[index1]);
                }
            }

            savedSeqLens[index] = longestSeqFromCurrent + 1;
        }

        int longestLen = 0;
        for(int len : savedSeqLens) {
            longestLen = Math.max(longestLen, len);
        }

        return longestLen;
    }

    // Time : O(n^2)
    // Space : O(n)
    // Top-down DP memoization based solution.
    public int lengthOfLIS(int[] nums) {
        int[] savedSeqLens = new int[nums.length];
        return longestSubseqLen(nums, -10001, 0, savedSeqLens);
    }

    private int longestSubseqLen(int[] nums, int prevEl, int startIndex, int[] savedSeqLens) {
        int longestSubseqLen = 0;
        if(startIndex >= nums.length) {
            longestSubseqLen = 0;
        }
        else if(savedSeqLens[startIndex] != 0) {
            longestSubseqLen = savedSeqLens[startIndex];
        }
        else {
            for(int index = startIndex, len = nums.length ; index < len ; index++) {
                int currentEl = nums[index];

                if(currentEl > prevEl) {
                    int currentSubSeqLen = 1 + longestSubseqLen(nums, currentEl, index + 1, savedSeqLens);
                    if(currentSubSeqLen > longestSubseqLen) {
                        longestSubseqLen = currentSubSeqLen;
                    }
                }
            }

            savedSeqLens[startIndex] = longestSubseqLen;
        }

        return longestSubseqLen;
    }

    // Time : O(2^{n})
    // Space : O(n), in the worst case length of recursion stack would be n.
    // For each subsequent element that's greater than current element decide whether to include it in
    // the sequence or not.
    public int lengthOfLIS(int[] nums) {
        return longestSubseqLen(nums, -10001, 0);
    }

    private int longestSubseqLen(int[] nums, int prevEl, int startIndex) {
        int longestSubseqLen = 0;

        for(int index = startIndex, len = nums.length ; index < len ; index++) {
            int currentEl = nums[index];

            if(currentEl > prevEl) {
                int currentSubSeqLen = 1 + longestSubseqLen(nums, currentEl, index + 1);
                if(currentSubSeqLen > longestSubseqLen) {
                    longestSubseqLen = currentSubSeqLen;
                }
            }
        }

        return longestSubseqLen;
    }
}
