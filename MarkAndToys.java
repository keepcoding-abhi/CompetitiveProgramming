import java.util.Collections;
import java.util.List;

public class MarkAndToys {
    /*
    Time : O(n * Log(n))
    Space : O(n) to O(Log(n)) depending on sorting algorithm
    Considering the elements in ascending order of price to fit the most amount of items.
     */
    public static int maximumToys(List<Integer> prices, int k) {
        // Write your code here
        Collections.sort(prices);

        int currentSum = 0, items = 0;

        for(int currPrice : prices) {
            currentSum += currPrice;

            if(currentSum <= k) {
                items++;
            }
            else {
                break;
            }
        }

        return items;
    }
}
