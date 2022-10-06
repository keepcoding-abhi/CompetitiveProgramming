import java.util.LinkedList;

public class Kaitenzushi {

    //Passed all test cases.
    public int getMaximumEatenDishCount(int N, int[] D, int K) {
        // Write your code here

        Map<Integer, Integer> lastOccurrenceOfDishes = new HashMap<Integer, Integer>();
        int dishesEaten = 0;

        for(int index = 0 ; index < N ; index++) {
            int currentDish = D[index];
            int lastOccurrence = lastOccurrenceOfDishes.getOrDefault(currentDish, -1);

            if(lastOccurrence == -1 || (dishesEaten - lastOccurrence >= K)) {
                dishesEaten++;
                lastOccurrenceOfDishes.put(currentDish, dishesEaten);
            }
        }

        return dishesEaten;
    }

    //1) LinkedList based approach : Time limit exceeded on two cases.
    public static int getMaximumEatenDishCount(int N, int[] D, int K) {
        // Write your code here

        LinkedList<Integer> previousKDishes = new LinkedList<Integer>();
        int dishesEaten = 0;

        for(int index = 0 ; index < N ; index++) {
            int currentDish = D[index];

            if(!previousKDishes.contains(currentDish)) {

                dishesEaten++;

                previousKDishes.addLast(currentDish);

                if(previousKDishes.size() > K) {
                    previousKDishes.removeFirst();
                }
            }
        }

        return dishesEaten;
    }

    //2) Circular queue based approach. Time limit exceeded on two cases.
    public int getMaximumEatenDishCount(int N, int[] D, int K) {
        // Write your code here

        int[] previousKDishes = new int[K];
        int dishesEaten = 1;
        int beginIndex = 0, endIndex = 0;
        previousKDishes[beginIndex] = D[0];

        for(int index = 1 ; index < N ; index++) {
            int currentDish = D[index];

            int previousDishIndex = beginIndex;
            int previousDish = previousKDishes[previousDishIndex];

            while(previousDishIndex != endIndex && previousDish != currentDish) {
                previousDish = previousKDishes[previousDishIndex];
                previousDishIndex = (previousDishIndex + 1) % K;
            }
            if(previousDish != currentDish) {
                previousDish = previousKDishes[previousDishIndex];
            }


            if(previousDish != currentDish) {
                int nextDishIndex = (endIndex + 1) % K;
                previousKDishes[nextDishIndex] = currentDish;

                if(nextDishIndex == beginIndex) {
                    beginIndex = (beginIndex + 1) % K;
                }

                endIndex = nextDishIndex;
                dishesEaten++;
            }

        }

        return dishesEaten;
    }

}
