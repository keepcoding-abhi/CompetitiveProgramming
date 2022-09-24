public class GetMaxAdditionalDinersCount {
}

//1) Getting time limit exceeded for 2 test cases.

class Solution {

    public long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        // Write your code here
        Arrays.sort(S);

        int nextDinerIndex = 0;
        long newComers = 0;
        long currentCustomerIndex = 1;
        for( ; currentCustomerIndex <= N ; currentCustomerIndex++) {

            long nextDinerPosition = -1;
            if(nextDinerIndex < M) {
                nextDinerPosition = S[nextDinerIndex];

                long nextUnoccupiableSeatStart = nextDinerPosition - K;
                long nextUnoccupiableSeatEnd = nextDinerPosition + K;

                if(currentCustomerIndex < nextUnoccupiableSeatStart) {
                    newComers++;
                    currentCustomerIndex += K;
                }


                if(currentCustomerIndex >= nextUnoccupiableSeatStart && currentCustomerIndex <= nextUnoccupiableSeatEnd) {
                    currentCustomerIndex = nextUnoccupiableSeatEnd;
                    nextDinerIndex++;
                }
            }
            else {
                break;
            }
        }

        long numberOfSeatsRemaining = N - currentCustomerIndex + 1;

        if(numberOfSeatsRemaining > 0) {

            long occupiableSeatsLeft = (long)Math.ceil(numberOfSeatsRemaining / (double)(K + 1));

            newComers += occupiableSeatsLeft;
        }

        return newComers;
    }

}

//2) Solved the time limit issue. Instead of adding 1 position of new diner per iteration of the for loop,
//      directly calculating the available positions in a single function call and visiting the next available
//      position on the dining table.

class Solution {

    public long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        // Write your code here
        Arrays.sort(S);

        int nextDinerIndex = 0;
        long newComers = 0;
        long currentCustomerIndex = 1;

        long nextUnoccupiableSeatEnd = S[0] - K;

        for(int existingDinerIndex = 0 ; existingDinerIndex < M ; existingDinerIndex++) {

            long nextDinerPosition = S[existingDinerIndex];
            long nextOccupiableSeat = nextDinerPosition - K - 1;
            newComers += getNumberOfAvailableSeats(currentCustomerIndex, nextOccupiableSeat, K);
            currentCustomerIndex = nextDinerPosition + K + 1;

        }

        newComers += getNumberOfAvailableSeats(currentCustomerIndex, N, K);

        return newComers;
    }

    private long getNumberOfAvailableSeats(long start, long end, long intervalLength) {
        long numberOfSeatsRemaining = end - start + 1;

        long avlblSeats = 0;

        if(numberOfSeatsRemaining > 0) {

            long occupiableSeatsLeft = (long)Math.ceil(numberOfSeatsRemaining / (double)(intervalLength + 1));

            avlblSeats = occupiableSeatsLeft;
        }

        return avlblSeats;
    }
}


