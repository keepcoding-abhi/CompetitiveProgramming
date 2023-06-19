public class ReverseBits {

    // Time : O(1)
    // Space : O(1)
    // Switching the positions of 16 bit blocks, then 8 bit and son on down to 1 bit.
    // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
    // 9 10 11 12 13 14 15 16 1 2 3 4 5 6 7 8
    // 13 14 15 16 9 10 11 12 5 6 7 8 1 2 3 4
    // 15 16 13 14 11 12 9 10 7 8 5 6 3 4 1 2
    // 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
    public int reverseBits(int n) {

        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);

        return n;
    }

    // Time : O(1)
    // Space : O(1)
    // Construct the reversed value in a bit-by-bit manner.
    // Extract the LSb in each iteration and add it in the end of reversed value.
    // Shift the reversed value and original number in opposite directions.
    public int reverseBits(int n) {
        int reversedNum = 0;

        for(int index = 0 ; index < 32 ; index++) {
            int currentBit = n & 1;
            reversedNum <<= 1;

            reversedNum = (reversedNum | currentBit);
            n >>>= 1;
        }

        return reversedNum;
    }

    // Time : O(1)
    // Space : O(1), in worst case the map will store 256 entries.
    // Reversing byte-by-byte
    // Using memoization to reuse the bytes which were reversed earlier.
    private Map<Integer, Integer> byteAndReverse = new HashMap<Integer, Integer>();

    public int reverseBits(int n) {
        int reversedNum = 0;
        int byteMask = 0xff;

        for(int index = 0 ; index < 4 ; index++) {
            int currentByte = ((n & byteMask) >> (8 * index));

            if(!byteAndReverse.containsKey(currentByte)) {
                byteAndReverse.put(currentByte, reverseByte(currentByte));
            }

            int reversedByte = byteAndReverse.get(currentByte);
            reversedNum <<= 8;
            reversedNum |= (reversedByte);

            byteMask <<= 8;
        }

        return reversedNum;
    }

    private int reverseByte(int b) {

        int reversedByte = 0;
        int originalMask = 1;

        for(int index = 0 ; index < 8 ; index++) {
            int currentBit = ((b & originalMask) >> index);

            reversedByte = (reversedByte | (currentBit << (7 - index)));
            originalMask <<= 1;
        }

        return reversedByte;
    }
}
