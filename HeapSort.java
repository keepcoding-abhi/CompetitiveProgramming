import java.util.Random;

public class HeapSort {

    public static void main(String args[]) {
        final int SIZE_OF_ARRAY = 100;
        int[] arr = new int[SIZE_OF_ARRAY];

        Random random = new Random();

        for(int index = 0 ; index < SIZE_OF_ARRAY ; index++) {
            arr[index] = random.nextInt(SIZE_OF_ARRAY);
        }

        printArray(arr);
        long start, end;
        start = System.nanoTime();
        heapSort(arr);
        end = System.nanoTime();

        printArray(arr);
        
        System.out.println("Time required for heap sort is " + (end - start) + " nanoseconds.");
    }

    private static void printArray(int arr[]) {
        for(int index = 0 ; index < arr.length ; index++) {
            System.out.print(arr[index] + " ");
        }

        System.out.println();
    }

    private static void heapSort(int[] arr) {
        buildMaxHeap(arr);

        int lengthOfHeap = arr.length;
        while(lengthOfHeap > 0) {
            int greatestElement = arr[0];

            arr[0] = arr[lengthOfHeap - 1];
            arr[lengthOfHeap - 1] = greatestElement;
            lengthOfHeap--;
            maxHeapify(arr, 0, lengthOfHeap);

        }
    }

    private static void buildMaxHeap(int[] arr) {

        final int lastParentIndex = (arr.length / 2) - 1;
        for(int index = lastParentIndex ; index > -1 ; index--) {
            maxHeapify(arr, index, arr.length);
        }
    }

    private static void maxHeapify(int[] arr, int parentToBeginHeapify, final int lengthOfHeap) {

        int currentIndex = parentToBeginHeapify;
        while(true) {

            int parentKey = arr[currentIndex];

            final int rightChildIndex = 2 * (currentIndex + 1);
            final int leftChildIndex = rightChildIndex - 1;

            if(leftChildIndex < lengthOfHeap) {
                int greatestChild = arr[leftChildIndex];

                int greatestChildIndex = leftChildIndex;
                if(rightChildIndex < lengthOfHeap) {
                    final int rightChild = arr[rightChildIndex];
                    if(rightChild > greatestChild) {
                        greatestChild = rightChild;
                        greatestChildIndex = rightChildIndex;
                    }
                }

                if(greatestChild > parentKey) {
                    arr[currentIndex] = greatestChild;
                    arr[greatestChildIndex] = parentKey;
                    currentIndex = greatestChildIndex;
                }
                else {
                    break;
                }
            }
            else {
                break;
            }
        }
    }
}
