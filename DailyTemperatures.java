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

/*
Time: O(n)
Space: O(1)

Traverse the array of temperatures in reverse order. Check if the temperature on next day is more, if not,
check if we have found some answer to next day.
 */
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int nDays = temperatures.length;

        int[] results = new int[nDays];

        for(int index = nDays - 2 ; index > -1 ; index--) {
            int currentTemp = temperatures[index];

            int futureDayIndex = index + 1;

            while(temperatures[futureDayIndex] <= currentTemp) {
                if(results[futureDayIndex] == 0) {
                    break;
                }
                else {
                    futureDayIndex += results[futureDayIndex];
                }
            }

            if(futureDayIndex != 0 && temperatures[futureDayIndex] > currentTemp) {
                results[index] = futureDayIndex - index;
            }
        }

        return results;
    }
}
