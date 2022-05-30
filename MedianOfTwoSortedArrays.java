public class MedianOfTwoSortedArrays {
}

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int lengthOfA = nums1.length;
        int lengthOfB = nums2.length;

        double median = Integer.MIN_VALUE;
        if(lengthOfB < lengthOfA) {
            median = findMedianSortedArrays(nums2, nums1);
        }
        else {
            int lowerIndexForBinarySearchOnA = 0;
            int upperIndexForBinarySearchOnA = lengthOfA;

            int lengthOfMergedArray = lengthOfA + lengthOfB;
            int middleIndex = lengthOfMergedArray / 2;

            while(lowerIndexForBinarySearchOnA <= upperIndexForBinarySearchOnA) {

                int cutOfA = (lowerIndexForBinarySearchOnA + upperIndexForBinarySearchOnA) / 2;
                int cutOfB = middleIndex - cutOfA;

                int leftToCutOfA = (cutOfA == 0) ? Integer.MIN_VALUE : nums1[cutOfA - 1];
                int rightToCutOfA = (cutOfA == lengthOfA) ? Integer.MAX_VALUE : nums1[cutOfA];
                int leftToCutOfB = (cutOfB == 0) ? Integer.MIN_VALUE : nums2[cutOfB - 1];
                int rightToCutOfB = (cutOfB == lengthOfB) ? Integer.MAX_VALUE : nums2[cutOfB];

                if(leftToCutOfA > rightToCutOfB) {
                    upperIndexForBinarySearchOnA = cutOfA - 1;
                }
                else if(leftToCutOfB > rightToCutOfA) {
                    lowerIndexForBinarySearchOnA = cutOfA + 1;
                }
                else {
                    if(lengthOfMergedArray % 2 == 0) {
                        median = (Math.max(leftToCutOfA, leftToCutOfB) + Math.min(rightToCutOfA, rightToCutOfB)) / 2.0;
                    }
                    else {
                        median = Math.min(rightToCutOfA, rightToCutOfB);
                    }
                    break;
                }
            }
        }

        return median;
    }
}