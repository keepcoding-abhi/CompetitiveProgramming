import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class UnexpectedDemand {
}

/*
Time : O(n * Log(n))

Sorting the widgets and filling the smallest ones first.
 */
class Result {

    /*
     * Complete the 'filledOrders' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY order
     *  2. INTEGER k
     */

    public static int filledOrders(List<Integer> order, int k) {
        // Write your code here
        Collections.sort(order);

        int ordersSatisfied = 0;

        int widgetRem = k;

        for(int index = 0 ; index < order.size() ; index++) {
            int current = order.get(index);

            if(current <= widgetRem) {
                widgetRem = widgetRem - current;
                ordersSatisfied++;
            }
            else {
                break;
            }
        }

        return ordersSatisfied;
    }

    private static int getMaxFulFilled(List<Integer> order, int k, int currentIndex) {
        int nOrders = order.size();

        int maxFulfilled = 0;

        if(k > 0 && currentIndex < nOrders) {


            int currentOrder = order.get(currentIndex);

            if(currentOrder <= k) {
                maxFulfilled = 1 + getMaxFulFilled(order, k - currentOrder, currentIndex + 1);
            }

            maxFulfilled = Math.max(maxFulfilled, getMaxFulFilled(order, k, currentIndex + 1));

        }

        return maxFulfilled;
    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int orderCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> order = IntStream.range(0, orderCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.filledOrders(order, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

