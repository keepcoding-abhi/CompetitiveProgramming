public class ReverseToMakeEqual {
    /*

    Problem statement :
    Given two arrays A and B of length N, determine if there is a way to make A equal to B by reversing any subarrays from array B any number of times.

    Time : O(n)
    Space : O(1)
    If there exists a sub-array which when reversed equals the two arrays then the remaining portion of those two arrays are same.
    Find the sub-array which is not equal and see if the reverse of that subarray makes the two arrays equal.
     */
    boolean areTheyEqual(int[] array_a, int[] array_b) {

        int arrayALen = array_a.length, arrayBLen = array_b.length;
        boolean equal = false;

        if(arrayALen == arrayBLen) {
            int leftIndex = 0, rightIndex = arrayALen - 1;

            while(leftIndex < arrayALen) {

                if(array_a[leftIndex] == array_b[leftIndex]) {
                    leftIndex++;
                }
                else {
                    break;
                }
            }

            while(rightIndex > -1) {

                if(array_a[rightIndex] == array_b[rightIndex]) {
                    rightIndex--;
                }
                else {
                    break;
                }
            }

            if(leftIndex < rightIndex) {
                int subArrayLen = rightIndex - leftIndex + 1;

                int count = 0;
                while(count < subArrayLen) {
                    if(array_a[leftIndex] == array_b[rightIndex]) {
                        leftIndex++;
                        rightIndex--;
                    }
                    else {
                        break;
                    }

                    count++;
                }

                if(count == subArrayLen) {
                    equal = true;
                }
            }
        }

        return equal;
    }
}
