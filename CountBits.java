public class CountBits {

    /*
    Time : O(n)
    Space : O(1)

    Flipping the least significant 1 bit of a number by performing x & (x - 1). Since the resulting number
    would be a smaller value, the number of ones it has would already be stored in the array. Accessing teh previously
    stored value and adding one to it.
     */
    public int[] countBits(int n) {

        int[] countArray = new int[n + 1];

        for(int currentNumber = 1 ; currentNumber <= n ; currentNumber++) {
            countArray[currentNumber] = countArray[currentNumber & (currentNumber - 1)] + 1;
        }

        return countArray;
    }

    // Time : O(n)
    // Space : O(1)
    // The number of ones in N is equal to number of ones in N / 2 + N % 2
    // When we're at N we have already computed the number on ones in N / 2.
    // N % 2 is determined by finding the least significant bit.
    public int[] countBits(int n) {

        int[] countArray = new int[n + 1];

        for(int currentNumber = 1 ; currentNumber <= n ; currentNumber++) {
            countArray[currentNumber] = countArray[currentNumber >> 1] + (currentNumber & 1);
        }

        return countArray;
    }

    // Time : O(n), for each number only constant time is required.
    // Space : O(1).
    // The number of 1s in numbers from [8, 15] is one more than that in numbers from [0, 7].
    public int[] countBits(int n) {

        int[] countArray = new int[n + 1];

        int currentBase = 1, currentOffset = 0;

        while(currentBase <= n) {
            while(currentBase + currentOffset <= n && currentOffset < currentBase) {
                countArray[currentBase + currentOffset] = countArray[currentOffset] + 1;
                currentOffset++;
            }

            currentBase <<= 1;
            currentOffset = 0;
        }

        return countArray;
    }

    // Time : O(n Log(n)))
    // Space : O(1)
    // Brute force solution. Counting the number of bits in each number from 0 to n.
    public int[] countBits(int n) {

        int[] countArray = new int[n + 1];

        for(int currentNumber = 1 ; currentNumber <= n ; currentNumber++) {
            countArray[currentNumber] = countOnes(currentNumber);
        }

        return countArray;
    }

    private int countOnes(int number) {
        int count = 0;

        while(number > 0) {
            number = number & (number - 1);
            count++;
        }

        return count;
    }
}
