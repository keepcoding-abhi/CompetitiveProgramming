import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures {
}

/*
Time : O(n)
Space : O(n)
Use a stack to keep track of next largest element.
 */
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> seenVals = new LinkedList<Integer>();
        int[] result = new int[temperatures.length];

        for(int index = 0 ; index < temperatures.length ; index++) {
            int currentTemp = temperatures[index];

            while(!seenVals.isEmpty() && currentTemp > temperatures[seenVals.peek()]) {
                int poppedIndex = seenVals.pop();
                result[poppedIndex] = (index - poppedIndex);
            }

            seenVals.push(index);
        }

        while(!seenVals.isEmpty()) {
            result[seenVals.pop()] = 0;
        }

        return result;
    }
}
