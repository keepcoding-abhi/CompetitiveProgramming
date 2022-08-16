public class BinarySearch {

    public static void main(String args[]) {
        final int SIZE_OF_ARRAY = 1000000;

        final int NUMBER_TO_SEARCH = -1;
        int[] numbers = new int[SIZE_OF_ARRAY];

        for (int index = 0; index < SIZE_OF_ARRAY; index++) {
            numbers[index] = index + 1;
        }

        long start, end;

        start = System.currentTimeMillis();
        binarySearchIterative(numbers, NUMBER_TO_SEARCH);
        end = System.currentTimeMillis();

        System.out.println("Time required for iterative binary search = " + (long)(end - start) + " milliseconds.");

        start = System.currentTimeMillis();
        binarySearchRecursive(numbers, NUMBER_TO_SEARCH);
        end = System.currentTimeMillis();

        System.out.println("Time required for recursive binary search = " + (long)(end - start) + " milliseconds.");
    }

    private static int binarySearchIterative(int[] arr, int target) {
        int lowerIndex = 0, upperIndex = arr.length;
        int location = -1;

        while (lowerIndex <= upperIndex) {
            int currentIndex = (lowerIndex + upperIndex) / 2;
            int currentElement = arr[currentIndex];

            if (currentElement < target) {
                lowerIndex = currentIndex + 1;
            } else if (currentElement > target) {
                upperIndex = currentIndex - 1;
            } else {
                location = currentIndex;
                break;
            }
        }

        if (location == -1) {
            throw new IllegalArgumentException("Cannot find the number " + target + " in " + arr + " either the number doesn't exist in the array" +
                    " or an unsorted array was given as input.");
        }

        return location;
    }

    private static int binarySearchRecursive(int[] arr, int target) {

        int location = binarySearch(arr, target, 0, arr.length);

        if (location == -1) {
            throw new IllegalArgumentException("Cannot find the number " + target + " in " + arr + " either the number doesn't exist in the array" +
                    " or an unsorted array was given as input.");
        }

        return location;
    }

    private static int binarySearch(int[] arr, int target, int lowerBound, int upperBound) {

        int result = -1;
        if(lowerBound <= upperBound) {
            int currentIndex = (upperBound + lowerBound) / 2;
            int currentElement = arr[currentIndex];

            if(currentElement > target) {
                result = binarySearch(arr, target, lowerBound, currentIndex - 1);
            }
            else if(currentElement < target) {
                result = binarySearch(arr, target, currentIndex + 1, upperBound);
            }
            else {
                result = currentIndex;
            }
        }

        return result;
    }
}
