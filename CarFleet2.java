import java.util.Deque;
import java.util.LinkedList;

public class CarFleet2 {
}

/*
Time : O(n)
Space : O(n)
Processing the cars in reverse order.
Using collision times to detect whether current car collides with the next car.
If the collision time is lesser than or equal to the next car's collision time otherwise the time by which this car hits
the next car the next car would have already collided, so consider the next car.
 */
class Solution {
    public double[] getCollisionTimes(int[][] cars) {

        int nCars = cars.length;
        double[] collisionTimes = new double[nCars];

        collisionTimes[nCars - 1] = -1;
        Deque<Integer> carsAhead = new LinkedList<Integer>();
        carsAhead.push(nCars - 1);

        for(int index = nCars - 2 ; index > -1 ; index--) {
            int currCarPosition = cars[index][0];
            int currCarSpeed = cars[index][1];

            collisionTimes[index] = -1;

            while(!carsAhead.isEmpty()) {
                int nextCarIndex = carsAhead.peek();
                int nextCarPosition = cars[nextCarIndex][0];
                int nextCarSpeed = cars[nextCarIndex][1];

                if(nextCarSpeed < currCarSpeed) {
                    double collisionTime = (nextCarPosition * 1.0 - currCarPosition) / (currCarSpeed * 1.0 - nextCarSpeed);

                    double nextCarsCollisionTime = collisionTimes[nextCarIndex];
                    if(nextCarsCollisionTime == -1) {
                        collisionTimes[index] = collisionTime;
                        break;
                    }
                    else if(collisionTime <= nextCarsCollisionTime) {
                        collisionTimes[index] = collisionTime;
                        break;
                    }
                    else {
                        carsAhead.pop();
                    }
                }
                else {
                    carsAhead.pop();
                }
            }

            carsAhead.push(index);
        }

        return collisionTimes;
    }
}
