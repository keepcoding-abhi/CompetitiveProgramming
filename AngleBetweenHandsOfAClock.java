public class AngleBetweenHandsOfAClock {
    // Time and space complexity : O(1)
    public double angleClock(int hour, int minutes) {
        double hourDegree = (hour * 30) + ((minutes / 60.0) * 30.0);
        double minutesDegree = (minutes / 60.0) * 360;

        double difference = Math.abs(hourDegree - minutesDegree);

        if(difference > 180) {
            difference = 360 - difference;
        }

        return difference;
    }
}
