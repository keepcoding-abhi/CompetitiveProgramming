import java.util.PriorityQueue;

public class MagicalCandyBags {
    /*
    Time: O(k*Log(n))
    Space: O(n)

    Using a max heap to keep track of the candies left and extract the most number of candies.
     */
    int maxCandies(int[] arr, int k) {
        // Write your code here
        PriorityQueue<Integer> candiesLeft = new PriorityQueue<Integer>((Integer a, Integer b) -> {
            return b - a;
        });
        int candiesEaten = 0;

        int minutesLeft = k;

        for(int num : arr) {
            candiesLeft.add(num);
        }

        while(minutesLeft > 0 && !candiesLeft.isEmpty()) {

            int currCandies = candiesLeft.poll();
            candiesEaten += currCandies;

            currCandies /= 2;

            if(currCandies > 0) {
                candiesLeft.add(currCandies);
            }

            minutesLeft--;
        }

        return candiesEaten;
    }
}
