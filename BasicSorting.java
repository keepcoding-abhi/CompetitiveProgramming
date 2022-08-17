import java.util.Random;

public class BasicSorting {

    public static void main(String args[]) {
        final int SIZE_OF_ARRAY = 1000;

        int arr[] = new int[SIZE_OF_ARRAY];

        Random rand = new Random();
        for(int index = 0 ; index < SIZE_OF_ARRAY ; index++) {
            arr[index] = rand.nextInt(SIZE_OF_ARRAY);
        }

        long start, end;

        int[] arrToPass = arr.clone();
        start = System.nanoTime();
        insertionSort(arrToPass);
        end = System.nanoTime();
        //printArray(arrToPass);

        long timeElapsed = end - start;
        System.out.println("Time required for insertion sort is " + timeElapsed);

        arrToPass = arr.clone();
        start = System.nanoTime();
        bubbleSort(arrToPass);
        end = System.nanoTime();
        //printArray(arrToPass);

        timeElapsed = end - start;
        System.out.println("Time required for bubble sort is " + timeElapsed);

        arrToPass = arr.clone();
        start = System.nanoTime();
        insertionSortOptimizedWithBinarySearch(arrToPass);
        end = System.nanoTime();
        //printArray(arrToPass);

        timeElapsed = end - start;
        System.out.println("Time required for optimized version of insert sort is " + timeElapsed);

    }

    // The optimized version is taking more time than the original version.
    // This could be because swapping is performed after binary search for finding the correct position for the next element.
    // In the original version, swapping was performed on the way as we were finding the right position for the next element.
    // However, this optimized version outperformed the original one when it was tested on some really large arrays.
    private static void insertionSortOptimizedWithBinarySearch(int[] arr) {
        for(int key = 1 ; key < arr.length ; key++) {

            int sortedArrayIndex = key - 1;
            int nextElement = arr[key];
            int lowerBound = 0, upperBound = key - 1;

            while(lowerBound < upperBound) {

                int currentIndex = (lowerBound + upperBound) / 2;
                int currentElement = arr[currentIndex];

                if(currentElement > nextElement) {
                    upperBound = currentIndex - 1;
                }
                else if(currentElement < nextElement) {
                    lowerBound = currentIndex + 1;
                }
                else {
                    lowerBound = upperBound = currentIndex;
                    break;
                }
            }

            int correctPosition = lowerBound + 1;
            if(arr[lowerBound] > nextElement) {
                correctPosition--;
            }

            for(int index = key ; index > correctPosition ; index--) {
                arr[index] = arr[index - 1];
            }

            arr[correctPosition] = nextElement;

        }
    }

    private static void insertionSort(int[] arr) {

        for(int key = 1 ; key < arr.length ; key++) {

            int sortedArrayIndex = key - 1;
            int currentKey = arr[key];
            while(sortedArrayIndex > -1) {
                if(arr[sortedArrayIndex] > currentKey) {
                    arr[sortedArrayIndex + 1] = arr[sortedArrayIndex];
                    sortedArrayIndex--;
                }
                else {
                    break;
                }
            }

            arr[sortedArrayIndex + 1] = currentKey;
        }
    }

    private static void printArray(int[] arr) {
        for(int index = 0 ; index < arr.length ; index++) {
            System.out.print(arr[index] + " ");
        }
        System.out.println();
    }

    private static void bubbleSort(int[] arr) {

        for(int index1 = 1 ; index1 < arr.length ; index1++) {
            for(int index2 = 0 ; index2 < arr.length - index1 ; index2++) {
                if(arr[index2] > arr[index2 + 1]) {
                    int temp = arr[index2];
                    arr[index2] = arr[index2 + 1];
                    arr[index2 + 1] = temp;
                }
            }
        }
    }
}
