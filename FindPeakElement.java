public class FindPeakElement {
    class Solution {
        //O(Log(n)) solution. Using Divide and conquer strategy.
        public int findPeakElement(int[] nums) {
            int totalElements = nums.length;
            int lowerBound = 0, upperBound = totalElements - 1;
            int peakIndex = -1;

            while(lowerBound <= upperBound) {
                int currentIndex = (lowerBound + upperBound) / 2;
                int currentElement = nums[currentIndex];

                if(currentIndex - 1 > -1 && nums[currentIndex - 1] > currentElement) {
                    upperBound = currentIndex - 1;
                }
                else if(currentIndex + 1 < totalElements && nums[currentIndex + 1] > currentElement) {
                    lowerBound = currentIndex + 1;
                }
                else {
                    peakIndex = currentIndex;
                    break;
                }
            }

            return peakIndex;
        }
    }
}
