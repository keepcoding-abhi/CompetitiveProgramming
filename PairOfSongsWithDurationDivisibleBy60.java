public class PairOfSongsWithDurationDivisibleBy60 {

    // Time complexity : O(n), Space complexity : O(1)
    public int numPairsDivisibleBy60(int[] time) {

        int numberOfPairs = 0;
        int numSongs = time.length;

        int[] durationFreqs = new int[60];

        for(int elIndex = 0 ; elIndex < numSongs ; elIndex++) {
            int remainder = time[elIndex] % 60;
            durationFreqs[remainder]++;
        }

        for(int currentRem = 0 ; currentRem < 60 ; currentRem++) {
            int numberOfOccurrences = durationFreqs[currentRem];

            if(numberOfOccurrences > 0) {
                if(currentRem == 0 || currentRem == 30) {
                    numberOfPairs += ((long)numberOfOccurrences * (long)(numberOfOccurrences - 1) / 2l);
                }
                else {
                    int elRequired = 60 - currentRem;

                    if(elRequired > currentRem) {
                        int occurencesOfPartner = durationFreqs[elRequired];
                        numberOfPairs += (numberOfOccurrences * occurencesOfPartner);
                    }
                }
            }
        }

        return numberOfPairs;
    }

    // Cleaner code. Incrementing the pairs counter as the input array gets scanned.
    // This ensures that we need not account for double counting of pairs.
    public int numPairsDivisibleBy60(int[] time) {

        int numberOfPairs = 0;
        int numSongs = time.length;

        int[] durationFreqs = new int[60];

        for(int elIndex = 0 ; elIndex < numSongs ; elIndex++) {
            int remainder = time[elIndex] % 60;

            if(remainder == 0) {
                numberOfPairs += durationFreqs[remainder];
            }
            else {
                numberOfPairs += durationFreqs[60 - remainder];
            }

            durationFreqs[remainder]++;
        }

        return numberOfPairs;
    }

    // Time and space complexity : O(n)
    public int numPairsDivisibleBy60(int[] time) {

        long numberOfPairs = 0;
        int numSongs = time.length;

        Map<Integer, Long> durationFreqs = new HashMap<Integer, Long>();

        for(int elIndex = 0 ; elIndex < numSongs ; elIndex++) {
            int remainder = time[elIndex] % 60;
            durationFreqs.put(remainder, durationFreqs.getOrDefault(remainder, 0l) + 1);
        }

        for(int index = 0 ; index < numSongs ; index++) {
            int remainder = time[index] % 60;

            if(remainder == 0) {
                numberOfPairs += (durationFreqs.get(remainder) - 1);
            }
            else {
                int sumRequired = 60 - remainder;

                if(durationFreqs.containsKey(sumRequired)) {
                    if(sumRequired == remainder) {
                        numberOfPairs += (durationFreqs.get(sumRequired) - 1);
                    }
                    else {
                        numberOfPairs += durationFreqs.get(sumRequired);
                    }
                }
            }
        }

        return (int)(numberOfPairs / 2);
    }

}
