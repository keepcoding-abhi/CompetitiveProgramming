import java.util.Random;

public class MergeSort {

    public static void main(String args[]) {
        final int SIZE_OF_ARRAY = 10000;
        int[] arr = new int[SIZE_OF_ARRAY];

        Random random = new Random();
        for(int index = 0 ; index < SIZE_OF_ARRAY ; index++) {
            arr[index] = random.nextInt(SIZE_OF_ARRAY);
        }

        printArray(arr);

        long start, end;
        start = System.nanoTime();
        int[] mergedArr = mergeSort(arr, 0, arr.length - 1);
        end = System.nanoTime();

        printArray(mergedArr);
        System.out.println("Time consumed by merge sort routine = " + (end - start));
    }

    static private void printArray(int[] arr) {
        for(int index = 0 ; index < arr.length ; index++) {
            System.out.print(arr[index] + " ");
        }
        System.out.println();
    }

    private static int[] mergeSort(int[] arr, int lower, int upper) {

        if(upper > lower) {
            int mid = (lower + upper) / 2;
            int[] leftArr = mergeSort(arr, lower, mid);
            int rightArr[] = mergeSort(arr, mid + 1, upper);
            int[] mergedArray = mergeTwoArrays(leftArr, rightArr);

            // the memory created for leftArr and rightArr can now be reclaimed
            leftArr = null;
            rightArr = null;
            return mergedArray;
        }
        else if(upper == lower) {
            return new int[]{arr[lower]};
        }
        else {
            throw new IllegalArgumentException("Can't perform merge sort on " + arr + " for lower index " + lower + " and upper index " + upper);
        }
    }

    private static int[] mergeTwoArrays(int[] leftArr, int rightArr[]) {
        int leftLength = leftArr.length;
        int rightLength = rightArr.length;

        int currentIndex = 0, leftIndex = 0, rightIndex = 0;
        int[] mergedArr = new int[leftLength + rightLength];

        while(leftIndex < leftLength && rightIndex < rightLength) {
            if(leftArr[leftIndex] < rightArr[rightIndex]) {
                mergedArr[currentIndex] = leftArr[leftIndex];
                leftIndex++;
            }
            else {
                mergedArr[currentIndex] = rightArr[rightIndex];
                rightIndex++;
            }

            currentIndex++;
        }

        while(leftIndex < leftLength) {
            mergedArr[currentIndex++] = leftArr[leftIndex++];
        }
        while(rightIndex < rightLength) {
            mergedArr[currentIndex++] = rightArr[rightIndex++];
        }

        return mergedArr;
    }
}
