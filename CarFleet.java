import java.util.Arrays;

public class CarFleet {
}

/*
Time : O(n * Log(n))
Space : O(n)

Sort the cars as per their driving positions.
Start processing the cars in the ascending order of distance from destination.
Keep track of the time which the last car took to reach destination, if the current car is reaching sooner than this
time set its arrival time as that of the last car, otherwise arrival time of this car is kept as it is and the
number of fleet is incremented.
 */
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int nCars = position.length;
        int[][] carInfo = new int[nCars][2];

        for(int index = 0 ; index < nCars ; index++) {
            carInfo[index][0] = position[index];
            carInfo[index][1] = speed[index];
        }

        Arrays.sort(carInfo, (int[] a, int[] b) -> {
            int diff = a[0] - b[0];

            if(diff == 0) {
                diff = b[1] - a[1];
            }

            return diff;
        });

        double currentArrivalTime = -1;
        int carFleetsCount = 0;

        for(int index = nCars - 1 ; index > -1 ; index--) {
            int currSpeed = carInfo[index][1], currPosition = carInfo[index][0];

            double timeTakenByCurrentCar = (target - currPosition) / ((double)currSpeed);

            if(timeTakenByCurrentCar > currentArrivalTime) {
                carFleetsCount++;
                currentArrivalTime = timeTakenByCurrentCar;
            }
        }

        return carFleetsCount;
    }
}
