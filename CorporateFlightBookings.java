public class CorporateFlightBookings {
    // Time : O(n^2), space : O(n)
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int flightBookings[] = new int[n];

        for(int index = 0, len = bookings.length ; index < len ; index++) {

            int newBookings = bookings[index][2];
            for(int flightIndex = bookings[index][0], lastFlight = bookings[index][1] ; flightIndex <= lastFlight ; flightIndex++) {

                flightBookings[flightIndex - 1] = flightBookings[flightIndex - 1] + newBookings;
            }
        }

        return flightBookings;
    }
}
