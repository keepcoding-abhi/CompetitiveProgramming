public class RemoveElement {
}

// 1) Slower solution. Moving elements back by keeping track of the number of duplicates found.
class Solution {
    public int removeElement(int[] nums, int val) {
        int numberOfDuplicatesFound = 0;
        int totalElements = nums.length;

        for(int index = 0 ; index < totalElements ; index++) {
            int currentElement = nums[index];

            if(val == currentElement) {
                numberOfDuplicatesFound++;
            }
            else {
                nums[index - numberOfDuplicatesFound] = currentElement;
            }
        }

        return totalElements - numberOfDuplicatesFound;
    }
}

// 2) Keeping a pointer to mark the end of filtered array.
class Solution {
    public int removeElement(int[] nums, int val) {
        int filteredArrIndex = 0;

        for(int index = 0 ; index < nums.length ; index++) {
            int currentElement = nums[index];

            if(currentElement != val) {
                nums[filteredArrIndex] = currentElement;
                filteredArrIndex++;
            }
        }

        return filteredArrIndex;
    }
}