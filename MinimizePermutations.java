import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MinimizePermutations {
}

/*
Time: O(n*n!)
Space: O(n!)

Perform a BFS starting from the given array. Generate all of the O(n^2) possible neighbors of the current element.
Stop when you reach the sorted permutation.
 */
class Solution {
    int minOperations(int[] arr) {
        // Write your code here
        Deque<Integer> permsToVisit = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();

        int opsPerformed = 0;

        int elsInCurrLevel = 1, elsInNextLevel = 0;
        int rep = serializeArr(arr);
        permsToVisit.add(rep);
        visited.add(rep);

        while(!permsToVisit.isEmpty()) {
            int[] currPerm = deserializeArr(permsToVisit.removeFirst(), arr.length);

            if(isSorted(currPerm)) {
                break;
            }

            for(int startIdx = 0 ; startIdx < arr.length ; startIdx++) {
                for(int endIdx = startIdx + 1 ; endIdx < arr.length ; endIdx++) {
                    swapEls(arr, startIdx, endIdx);

                    rep = serializeArr(arr);

                    if(!visited.contains(rep)) {
                        permsToVisit.add(rep);
                        visited.add(rep);
                        elsInNextLevel++;
                    }

                    swapEls(arr, startIdx, endIdx);
                }
            }

            elsInCurrLevel--;
            if(elsInCurrLevel == 0) {
                elsInCurrLevel = elsInNextLevel;
                elsInNextLevel = 0;
                opsPerformed++;
            }
        }

        return opsPerformed;
    }

    private void swapEls(int[] arr, int startIdx, int endIdx) {
        int left = startIdx, right = endIdx;

        while(left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    private boolean isSorted(int[] arr) {
        boolean sorted = true;

        for(int index = 1 ; index < arr.length ; index++) {
            if(arr[index - 1] > arr[index]) {
                sorted = false;
                break;
            }
        }

        return sorted;
    }

    private int[] deserializeArr(int rep, int len) {
        int[] nums = new int[len];

        for(int index = len - 1 ; index > -1 ; index--) {
            nums[index] = (rep % 10);
            rep /= 10;
        }

        return nums;
    }

    private int serializeArr(int[] arr) {
        int rep = 0;

        for(int num : arr) {
            rep = (rep * 10) + num;
        }

        return rep;
    }
}
