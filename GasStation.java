public class GasStation {

    /*
    Time: O(n)
    Space: O(1)

    Since we are sure that there is at most one valid solution, start from the first position and proceed incrementally
    and check whether the conditions are getting violated. If there is no fuel to continue the journey abandon the
    chosen path, and start a new path from next position.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int currentStartingStation = 0;

        int currentIndex = 0, totalGain = 0, gainSinceLastStation = 0;

        while(currentIndex < gas.length) {
            int currentGain = (gas[currentIndex] - cost[currentIndex]);

            gainSinceLastStation += (currentGain);
            totalGain += currentGain;

            if(gainSinceLastStation < 0) {
                gainSinceLastStation = 0;
                currentStartingStation = currentIndex + 1;
            }

            currentIndex++;
        }

        if(totalGain < 0 || currentStartingStation == gas.length) {
            currentStartingStation = -1;
        }

        return currentStartingStation;
    }

    /*
    Time: O(n^2)
    Space: O(1)

    Check with each position as a starting location. Traverse the whole array from this location and check whether the
    condition is satisfied.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int stationToStart = -1;

        for(int startingPosition = 0 ; startingPosition < gas.length ; startingPosition++) {
            int tankCapacity = 0;

            int stationsCovered = 0;
            int currentStation = startingPosition;

            while(stationsCovered < gas.length) {

                tankCapacity += gas[currentStation];

                if(tankCapacity >= cost[currentStation]) {
                    tankCapacity -= cost[currentStation];
                }
                else {
                    tankCapacity = -1;
                    break;
                }

                stationsCovered++;
                currentStation = (currentStation + 1) % gas.length;
            }

            if(stationsCovered == gas.length && tankCapacity >= 0) {
                stationToStart = startingPosition;
            }
        }

        return stationToStart;
    }
}
