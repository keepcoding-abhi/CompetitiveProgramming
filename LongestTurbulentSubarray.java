public class LongestTurbulentSubarray {

    // Time : O(n)
    // Space : O(1)
    // Use the difference between adjacent elements to check whether comparison flips.
    public int maxTurbulenceSize(int[] arr) {
        int leftPointer = 0, rightPointer = 1;

        int maxSubArraySize = 1, len = arr.length;

        while(rightPointer < len) {
            int nextNum = arr[rightPointer];

            int currentSize = rightPointer - leftPointer + 1;

            if(currentSize > 2) {
                int firstAndSecondDiff = nextNum - arr[rightPointer - 1];

                int secondAndThirdDiff = arr[rightPointer - 1] - arr[rightPointer - 2];

                if((firstAndSecondDiff >= 0 && secondAndThirdDiff >= 0) || (firstAndSecondDiff <= 0 && secondAndThirdDiff <= 0)) {
                    leftPointer = rightPointer - 1;

                    currentSize = 2;
                }
            }
            else if(currentSize == 2) {
                if(nextNum == arr[rightPointer - 1]) {
                    leftPointer = rightPointer;
                    currentSize = 1;
                }
            }

            if(currentSize > maxSubArraySize) {
                maxSubArraySize = currentSize;
            }

            rightPointer++;
        }

        return maxSubArraySize;
    }
}
