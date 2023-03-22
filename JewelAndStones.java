public class JewelAndStones {

    // Time : O(jewelLen + stonesLen)
    // Space : O(1).
    public int numJewelsInStones(String jewels, String stones) {

        int[] freqCounter = new int[52];

        for(int index = 0 ; index < 52 ; index++) {
            freqCounter[index] = -1;
        }

        for(int index = 0, jewelLen = jewels.length() ; index < jewelLen ; index++) {
            char currentChar = jewels.charAt(index);
            int charIndex = currentChar - 'a';

            if(charIndex >= 0 && charIndex <= 25) {
                freqCounter[charIndex] = 0;
            }
            else {
                charIndex = currentChar - 'A' + 26;

                freqCounter[charIndex] = 0;
            }
        }

        for(int index = 0, stoneLen = stones.length() ; index < stoneLen ; index++) {
            char currentChar = stones.charAt(index);

            int charIndex = currentChar - 'a';

            if(charIndex >= 0 && charIndex <= 25) {
                if(freqCounter[charIndex] > -1) {
                    freqCounter[charIndex]++;
                }
            }
            else {
                charIndex = currentChar - 'A' + 26;

                if(freqCounter[charIndex] > -1) {
                    freqCounter[charIndex]++;
                }
            }
        }

        int jewelCounts = 0;

        for(int index = 0 ; index < 52 ; index++) {

            if(freqCounter[index] > -1) {
                jewelCounts += freqCounter[index];
            }
        }

        return jewelCounts;
    }

    // Slow and inefficient solution using string replacement.
    // Time and space : O(jewelLength * stoneLength)
    public int numJewelsInStones(String jewels, String stones) {
        int originalLength = stones.length();

        for(int index = 0 ; index < jewels.length() ; index++) {
            char currentJewel = jewels.charAt(index);

            stones = stones.replaceAll(currentJewel + "", "");
        }

        return originalLength - stones.length();
    }
}
