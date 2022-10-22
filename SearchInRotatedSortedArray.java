public class SearchInRotatedSortedArray {

    //1) Using Binary Search with the fact that one half is properly sorted and the other half of the middle element is distorted
    // because of rotation.
    //Fastest and most efficient.
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int targetIndex = -1;

        while(low <= high) {
            int mid = (low + high) / 2;
            int currentElement = nums[mid];

            if(target == currentElement) {
                targetIndex = mid;
                break;
            }

            int leftExtreme = nums[low];
            if(currentElement >= leftExtreme) {
                if(target >= leftExtreme && target < currentElement) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            else {
                int rightExtreme = nums[high];

                if(target > currentElement && target <= rightExtreme) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }

        return targetIndex;

    }

    public int findRotateIndex(int[] nums, int low, int high) {

        int rotateIndex = 0;
        if(nums[low] > nums[high]) {
            while(low <= high) {
                int currentMid = (low + high) / 2;
                int currentElement = nums[currentMid];

                if(currentElement > nums[currentMid + 1]) {
                    rotateIndex = currentMid + 1;
                    break;
                }

                if(nums[low] > currentElement) {
                    high = currentMid - 1;
                }
                else {
                    //currentElement > nums[high]
                    low = currentMid + 1;
                }
            }
        }

        return rotateIndex;
    }

    //2) First using binary search to find the smallest element in the sorted array. Then performing the traditional binary search
    // on the appropriate half.
    private int binarySearch(int target, int[] nums, int low, int high) {
        int location = -1;

        while(low <= high) {
            int mid = (low + high) / 2;
            int currentElement = nums[mid];

            if(currentElement < target) {
                low = mid + 1;
            }
            else if(currentElement > target) {
                high = mid - 1;
            }
            else {
                location = mid;
                break;
            }
        }

        return location;
    }

    public int search(int[] nums, int target) {
        int rotationIndex = findRotateIndex(nums, 0, nums.length - 1);

        int pivotElement = nums[rotationIndex];

        int searchIndex = -1;

        if(pivotElement == target) {
            searchIndex = rotationIndex;
        }
        else if(target > pivotElement && target <= nums[nums.length - 1]) {
            searchIndex = binarySearch(target, nums, rotationIndex + 1, nums.length - 1);
        }
        else {
            searchIndex = binarySearch(target, nums, 0, rotationIndex - 1);
        }

        return searchIndex;
    }

}
