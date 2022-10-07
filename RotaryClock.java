public class RotaryClock {

    public long getMinCodeEntryTime(int N, int M, int[] C) {
        // Write your code here

        int currentPosition = 1;
        long movesMade = 0;

        for(int index = 0 ; index < M ; index++) {
            int currentDestination = C[index];

            if(currentDestination != currentPosition) {
                int clockWiseSteps = currentDestination - currentPosition;
                if(clockWiseSteps < 0) {
                    clockWiseSteps += N;
                }

                int antiClockWiseSteps = currentPosition - currentDestination;
                if(antiClockWiseSteps < 0) {
                    antiClockWiseSteps += N;
                }

                movesMade += (clockWiseSteps > antiClockWiseSteps ? antiClockWiseSteps : clockWiseSteps);

                currentPosition = currentDestination;
            }
        }

        return movesMade;
    }

}

