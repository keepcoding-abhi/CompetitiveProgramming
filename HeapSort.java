import java.util.Random;

public class HeapSort {

    public static void main(String args[]) {
        final int INITIAL_SIZE_OF_HEAP = 10;
        int[] arr = new int[INITIAL_SIZE_OF_HEAP + 3];
        int CURRENT_SIZE_OF_HEAP = INITIAL_SIZE_OF_HEAP;

        Random random = new Random();

        for(int index = 0 ; index < INITIAL_SIZE_OF_HEAP ; index++) {
            arr[index] = random.nextInt(INITIAL_SIZE_OF_HEAP);
        }

        printArray(arr);
        long start, end;

        buildMaxHeap(arr, CURRENT_SIZE_OF_HEAP);
        System.out.println("Built heap :" + arr);

        int[] arrToSort = arr.clone();
        start = System.nanoTime();
        heapSort(arrToSort);
        end = System.nanoTime();

        System.out.println("Sorted array :");
        printArray(arrToSort);

        System.out.println("Time required for heap sort is " + (end - start) + " nanoseconds.");

        System.out.println("Heap before performing insertions : ");
        printArray(arr);

        insertInHeap(arr, CURRENT_SIZE_OF_HEAP++, INITIAL_SIZE_OF_HEAP + 1);
        System.out.println("Current heap :");
        printArray(arr);

        insertInHeap(arr, CURRENT_SIZE_OF_HEAP++, INITIAL_SIZE_OF_HEAP - INITIAL_SIZE_OF_HEAP);
        System.out.println("Current heap :");
        printArray(arr);

        insertInHeap(arr, CURRENT_SIZE_OF_HEAP++, INITIAL_SIZE_OF_HEAP / 2);
        System.out.println("Current heap :");
        printArray(arr);
    }

    private static void insertInHeap(int[] arr, int sizeOfHeap, int newVal) {

        arr[sizeOfHeap] = newVal;
        maxHeapifyBottomUp(arr, sizeOfHeap + 1, sizeOfHeap);
    }

    private static void maxHeapifyBottomUp(int[] arr, final int HEAP_SIZE, int startIndex) {

        int parentIndex = (startIndex - 1) / 2;

        while(parentIndex >= 0) {
            int parentKey = arr[parentIndex];
            int rightChildIndex = 2 * (parentIndex + 1);
            int leftChildIndex = rightChildIndex - 1;

            int greatestChild = arr[leftChildIndex];
            int greatestChildIndex = leftChildIndex;

            if(rightChildIndex < HEAP_SIZE) {
                int rightChild = arr[rightChildIndex];
                if(rightChild > greatestChild) {
                    greatestChild = rightChild;
                    greatestChildIndex = rightChildIndex;
                }
            }

            if(parentKey < greatestChild) {
                arr[parentIndex] = greatestChild;
                arr[greatestChildIndex] = parentKey;
                parentIndex = (parentIndex - 1) / 2;
            }
            else {
                break;
            }
        }
    }

    private static void printArray(int arr[]) {
        for(int index = 0 ; index < arr.length ; index++) {
            System.out.print(arr[index] + " ");
        }

        System.out.println();
    }

    private static void heapSort(int[] arr) {

        int lengthOfHeap = arr.length;
        while(lengthOfHeap > 0) {
            int greatestElement = arr[0];

            arr[0] = arr[lengthOfHeap - 1];
            arr[lengthOfHeap - 1] = greatestElement;
            lengthOfHeap--;
            maxHeapify(arr, 0, lengthOfHeap);

        }
    }

    private static void buildMaxHeap(int[] arr, final int SIZE_OF_HEAP) {

        final int lastParentIndex = (SIZE_OF_HEAP / 2) - 1;
        for(int index = lastParentIndex ; index > -1 ; index--) {
            maxHeapify(arr, index, SIZE_OF_HEAP);
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
