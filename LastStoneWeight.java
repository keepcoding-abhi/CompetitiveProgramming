import java.util.PriorityQueue;

public class LastStoneWeight {
}

/*
Time: O(n + w)
Space: O(w)

Storing the frequency of each number in a bucket/array.
Traversing this array in reverse order to work with the largest stone weight.
 */
class Solution {
    public int lastStoneWeight(int[] stones) {
        int maxWeight = 0;

        for(int stone : stones) {
            maxWeight = Math.max(maxWeight, stone);
        }

        int[] buckets = new int[maxWeight + 1];

        for(int stone : stones) {
            buckets[stone]++;
        }

        int lastWeight = 0;

        for(int weight = maxWeight ; weight >= 1 ; weight--) {

            if(lastWeight != 0 && buckets[weight] > 0) {
                int weightDifference = lastWeight - weight;
                buckets[weightDifference]++;
                buckets[weight]--;
                buckets[lastWeight]--;

                lastWeight = 0;

                if(weightDifference > weight) {
                    lastWeight = weightDifference;
                    buckets[lastWeight]++;
                    weight++;
                    continue;
                }
            }

            if(buckets[weight] % 2 != 0) {
                lastWeight = weight;
                buckets[weight] = 1;
            }
            else {
                buckets[weight] = 0;
            }
        }

        return lastWeight;
    }
}

/*
Time: O(n * Log(n))
Space: O(n)

Use a heap to store the weights in descending order
 */
class Solution {
    public int lastStoneWeight(int[] stones) {
        int lastWeight = 0;

        PriorityQueue<Integer> weights = new PriorityQueue<Integer>(stones.length, (Integer int1, Integer int2) -> {
            return int2 - int1;
        });

        for(int stoneWeight : stones) {
            weights.add(stoneWeight);
        }

        while(weights.size() > 1) {
            int weight1 = weights.remove();
            int weight2 = weights.remove();

            weights.add(weight1 - weight2);
        }

        if(!weights.isEmpty()) {
            lastWeight = weights.peek();
        }

        return lastWeight;
    }
}
