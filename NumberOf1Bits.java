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
        int msbMask = 1;

        for(int index = 0 ; index < 32 ; index++) {
            if((n & msbMask) != 0) {
                numberOfOnes++;
            }

            msbMask = msbMask << 1;
        }

        return numberOfOnes;
    }
}
