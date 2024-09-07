import java.util.Arrays;

public class SeatingArrangement {
}

/*
Time: O(n*Log(n))
Space: O(n to Log(n)) depending on sorting algorithm

Sort the array and distribute the data in a normal distribution. So that people with similar heights
are closer to each other

For example, if sorted array is:
a[0] a[1] a[2] ........ a[n-2] a[n-1]

Normal distribution will be
a[0] a[2] ... a[n - 3] a[n - 1] a[n-2] .... a[3] a[1]
 */
class Main1 {

    // Add any helper functions you may need here


    int minOverallAwkwardness(int[] arr) {
        // Write your code here
        int[] intermediateArr = new int[arr.length];
        Arrays.sort(arr);

        int left = 0, right = arr.length - 1;
        int idx = 0;
        boolean fillLeft = true;

        while (idx < arr.length) {
            if (fillLeft) {
                intermediateArr[left] = arr[idx];
                left++;
            } else {
                intermediateArr[right] = arr[idx];
                right--;
            }

            fillLeft = !fillLeft;
            idx++;
        }

        return computeAwkwardness(intermediateArr);
    }

    private int computeAwkwardness(int[] arr) {

        int awkwardness = Math.abs(arr[0] - arr[1]);
        for (int index = 1; index < arr.length - 1; index++) {
            awkwardness = Math.max(awkwardness, Math.abs(arr[index] - arr[index + 1]));
        }

        awkwardness = Math.max(awkwardness, Math.abs(arr[0] - arr[arr.length - 1]));

        return awkwardness;
    }
}

/*
Time: O(n * n!)
Space: O(n)

Generate all possible permutations of the seating arrangements and for each of them find the awkwardness
 */
class Main {

    // Add any helper functions you may need here


    int minOverallAwkwardness(int[] arr) {
        // Write your code here
        return minOverallAwkwardness(arr, 0);
    }

    private int minOverallAwkwardness(int[] arr, int startIdx) {

        int minAwkwardness;

        if (startIdx == arr.length) {
            minAwkwardness = computeAwkwardness(arr);
        } else {
            minAwkwardness = Integer.MAX_VALUE;

            for (int otherIdx = startIdx; otherIdx < arr.length; otherIdx++) {

                swap(arr, startIdx, otherIdx);
                minAwkwardness = Math.min(minAwkwardness, minOverallAwkwardness(arr, startIdx + 1));
                swap(arr, startIdx, otherIdx);
            }
        }

        return minAwkwardness;
    }

    private int computeAwkwardness(int[] arr) {

        int awkwardness = Math.abs(arr[0] - arr[1]);
        for (int index = 1; index < arr.length - 1; index++) {
            awkwardness = Math.max(awkwardness, Math.abs(arr[index] - arr[index + 1]));
        }

        awkwardness = Math.max(awkwardness, Math.abs(arr[0] - arr[arr.length - 1]));

        return awkwardness;
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}