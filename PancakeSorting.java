import java.util.LinkedList;
import java.util.List;

public class PancakeSorting {
}

/*
Time: O(n^2)
Space: O(1) if we are not taking into account the space required to hold the result of the function, otherwise
        O(n)

Traverse from right to left, and in each iteration, put one element at the correct position.
Find the position of the expected element in the left portion of the array, perform a pancake flip and
bring that element to the fronty of the array. Then move that element to the required position by doing another
pancake flip.
 */
class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> kVals = new LinkedList<Integer>();

        for(int index = arr.length - 1 ; index > 0 ; index--) {
            if(arr[index] != index + 1) {
                fixIndex(arr, index, kVals);
            }
        }

        return kVals;
    }

    private void fixIndex(int[] arr, int idx, List<Integer> kVals) {

        int currIndex = -1;

        for(int index = 0 ; index < arr.length ; index++) {
            if(arr[index] == idx + 1) {
                currIndex = index;
                break;
            }
        }

        kVals.add(currIndex + 1);
        pancakeFlip(arr, currIndex);

        kVals.add(idx + 1);
        pancakeFlip(arr, idx);
    }

    private void pancakeFlip(int[] arr, int idx) {
        int left = 0, right = idx;

        while(left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}

/*
Time: O(n^10)
Space: O(n)

Perform pancake flip at each possible index.
 */
class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> kVals = new LinkedList<Integer>();

        determineKValues(arr, kVals);

        return kVals;
    }

    private boolean determineKValues(int[] arr, List<Integer> kValues) {

        boolean kValuesFound = false;

        if(kValues.size() <= 10 * arr.length) {
            if(sortedSeq(arr)) {
                if(kValues.size() <= 10 * arr.length) {
                    kValuesFound = true;
                }
            }
            else {
                for(int index = 1 ; index < arr.length ; index++) {
                    pancakeFlip(arr, index);
                    kValues.addLast(index + 1);
                    kValuesFound = determineKValues(arr, kValues);

                    if(kValuesFound) {
                        break;
                    }

                    pancakeFlip(arr, index);
                    kValues.removeLast();
                }
            }
        }

        return kValuesFound;
    }

    private boolean sortedSeq(int[] arr) {
        boolean sorted = true;

        for(int index = 1 ; index < arr.length ; index++) {
            if(arr[index - 1] > arr[index]) {
                sorted = false;
                break;
            }
        }

        return sorted;
    }

    private void pancakeFlip(int[] arr, int idx) {
        int left = 0, right = idx;

        while(left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}
