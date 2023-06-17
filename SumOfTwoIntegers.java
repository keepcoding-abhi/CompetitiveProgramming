public class SumOfTwoIntegers {

    // Time : O(1) since the number of iterations remains the same regardless of the input
    // Space : O(1)
    // Perform bitwise addition on each of the 32 bits used to represent an integer.
    // Since the numbers are already stored in 2s-complement format simple bitwise addition is guaranteed
    // to give good results even in the presence of negative values.
    public int getSum(int a, int b) {
        int sum = 0;
        int carry = 0;

        for(int bitIndex = 0 ; bitIndex < 32 ; bitIndex++) {
            int aLSB = a & 1;
            int bLSB = b & 1;

            int currentSum = aLSB ^ bLSB ^ carry;
            if(aLSB == 1 && bLSB == 1) {
                carry = 1;
            }
            else if(aLSB == 1 && carry == 1) {
                carry = 1;
            }
            else if(bLSB == 1 && carry == 1) {
                carry = 1;
            }
            else {
                carry = 0;
            }

            // Performing unsigned bit shift. Otherwise ones will be added from Most Significant positions for
            // negative numbers.
            a >>>= 1;
            b >>>= 1;

            currentSum <<= bitIndex;

            sum |= currentSum;
        }

        // Ignore the carry-forwarded value since all 32-bits have been processed.

        return sum;
    }
}
