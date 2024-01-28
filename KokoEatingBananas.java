public class KokoEatingBananas {

    /*
    Time: O(n * Log(m))
    Space: O(1)

    On the axis of eating speeds from 1 to max bananas in a pile, use binary search to find the first speed
    which is valid but its previous speed is not.
     */
    public int minEatingSpeed(int[] piles, int h) {

        int maxEatingSpeed = piles[0];

        for(int bananas : piles) {
            maxEatingSpeed = Math.max(bananas, maxEatingSpeed);
        }

        int lowerBound = 1, upperBound = maxEatingSpeed;
        int currentEatingSpeed = (lowerBound + upperBound) / 2;

        while(true) {

            currentEatingSpeed = (lowerBound + upperBound) / 2;

            boolean currentEatingSpeedGood = computeTimeToEat(piles, currentEatingSpeed) <= h;

            boolean currentEatingSpeedMinusOneGood = computeTimeToEat(piles, currentEatingSpeed - 1) <= h;

            if(currentEatingSpeedGood) {
                if(!currentEatingSpeedMinusOneGood) {
                    break;
                }
                else {
                    upperBound = currentEatingSpeed - 1;
                }
            }
            else {
                lowerBound = currentEatingSpeed + 1;
            }
        }

        return currentEatingSpeed;
    }

    private int computeTimeToEat(int[] piles, int speed) {

        int timeRequired = 0;

        if(speed == 0) {
            timeRequired = Integer.MAX_VALUE;
        }
        else {
            for(int bananas : piles) {
                timeRequired += (bananas / speed);

                if((bananas % speed) != 0) {
                    timeRequired++;
                }
            }
        }

        return timeRequired;
    }
}
