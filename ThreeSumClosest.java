public class ThreeSumClosest {

    // Time : O(n^2)
    // Space : O(Log(n)) to O(n) depending on the sorting algorithm.
    // Fix the first element of triplet and find other two elements using two sum.
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int closestSum = nums[0] + nums[1] + nums[2];
        int closestDiff = Math.abs(target - (closestSum));

        for(int firstIndex = 0, len = nums.length ; firstIndex < len - 2 ; firstIndex++) {
            int firstNum = nums[firstIndex];

            int requiredSum = target - firstNum;
            int leftIndex = firstIndex + 1, rightIndex = len - 1;

            while(leftIndex < rightIndex) {
                int currentSum = nums[leftIndex] + nums[rightIndex];
                int currentDiff = Math.abs(requiredSum - currentSum);

                if(currentDiff < closestDiff) {
                    closestDiff = currentDiff;
                    closestSum = currentSum + firstNum;
                }

                if(currentSum < requiredSum) {
                    leftIndex++;
                }
                else if(currentSum > requiredSum) {
                    rightIndex--;
                }
                else {
                    break;
                }
            }

            if(closestDiff == 0) {
                break;
            }
        }

        return closestSum;
    }

    // Time : O(n^2 * Log(n))
    // Space : O(Log(n) to O(n) depending on sorting algorithm
    // Binary search based solution.
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int closestSum = nums[0] + nums[1] + nums[2];
        int closestDiff = Math.abs(target - (closestSum));

        for(int firstIndex = 0, len = nums.length ; (firstIndex < len - 2) && (closestDiff != 0) ; firstIndex++) {
            int firstNum = nums[firstIndex];

            for(int secondIndex = firstIndex + 1 ; (secondIndex < len - 1) && (closestDiff != 0) ; secondIndex++) {
                int secondNum = nums[secondIndex];
                int requiredNum = target - (firstNum + secondNum);

                int lowerIndex = secondIndex + 1, upperIndex = len - 1;
                while(lowerIndex <= upperIndex) {
                    int mid = (lowerIndex + upperIndex) / 2;
                    int currentElement = nums[mid];

                    if(currentElement == requiredNum) {
                        closestSum = target;
                        closestDiff = 0;
                        break;
                    }
                    else if(currentElement < requiredNum) {
                        lowerIndex = mid + 1;
                    }
                    else {
                        upperIndex = mid - 1;
                    }
                }

                if(lowerIndex < len) {
                    int nextHigherEl = nums[lowerIndex];
                    int currentSum = firstNum + secondNum + nextHigherEl;
                    int currentDiff = Math.abs(target - currentSum);
                    if(currentDiff < closestDiff) {
                        closestDiff = currentDiff;
                        closestSum = currentSum;
                    }
                }

                if(lowerIndex > secondIndex + 1) {
                    int nextLowerEl = nums[lowerIndex - 1];
                    int currentSum = firstNum + secondNum + nextLowerEl;
                    int currentDiff = Math.abs(target - currentSum);
                    if(currentDiff < closestDiff) {
                        closestDiff = currentDiff;
                        closestSum = currentSum;
                    }
                }
            }
        }

        return closestSum;
    }

    // Time : O(n^3)
    // Space : O(1)
    // Brute force solution.
    public int threeSumClosest(int[] nums, int target) {
        int closestSum = nums[0] + nums[1] + nums[2];
        int closestDiff = Math.abs(target - closestSum);

        for(int firstIndex = 0, len = nums.length ; firstIndex < len - 2 && closestDiff != 0 ; firstIndex++) {
            int firstNum = nums[firstIndex];
            for(int secondIndex = firstIndex + 1 ; secondIndex < len - 1 &&closestDiff != 0 ; secondIndex++) {
                int secondNum = nums[secondIndex];
                for(int thirdIndex = secondIndex + 1 ; thirdIndex < len && closestDiff != 0 ; thirdIndex++) {
                    int currentSum = firstNum + secondNum + nums[thirdIndex];
                    int currentDiff = Math.abs(target - currentSum);

                    if(currentDiff < closestDiff) {
                        closestDiff = currentDiff;
                        closestSum = currentSum;
                    }
                }
            }
        }

        return closestSum;
    }
}
