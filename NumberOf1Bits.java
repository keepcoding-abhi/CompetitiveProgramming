public class NumberOf1Bits {

    // Time and space complexities of all solutions is constant.

    // Most generic solution. Performing AND operation on n and (n - 1), flips the
    // value of least significant 1-bit to 0.
    public int hammingWeight(int n) {
        int numberOfOnes = 0;

        while(n != 0) {
            n = n & (n - 1);
            numberOfOnes++;
        }

        return numberOfOnes;
    }

    public int hammingWeight(int n) {
        int numberOfOnes = 0;
        int msbMask = 0x80000000;

        while(n != 0) {
            if((n & msbMask) == 0x80000000) {
                numberOfOnes++;
            }

            n = n << 1;
        }

        return numberOfOnes;
    }

    public int hammingWeight(int n) {
        int numberOfOnes = 0;
        int bitMask = 1;

        for(int index = 0 ; index < 32 ; index++) {
            if((n & bitMask) != 0) {
                numberOfOnes++;
            }

            bitMask = bitMask << 1;
        }

        return numberOfOnes;
    }

    // Time : O(32)
    // Space : O(1) constant
    // Scanning each of the 32 bits
    public int hammingWeight(int n) {
        int ones = 0;

        for(int index = 0 ; index < 32 ; index++) {
            if((n & 1) == 1) {
                ones++;
            }

            n >>>= 1;
        }

        return ones;
    }
}
