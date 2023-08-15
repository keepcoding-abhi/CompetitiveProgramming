import java.util.Arrays;
import java.util.List;

public class ArrayManipulation {

    /*
        Time : O(nqueries * Log(nqueries))
        Space : O(nqueries)
        Merge the start and end indices and consider each index in the increasing order.
        Maintain a variable representing the count.
        If the current index represents the start time add its value, if it is an end time subtract its value.
     */
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        long maxVal = 0, currentVal = 0;
        int nQueries = queries.size();
        int[][] startInfo = new int[nQueries][2], endInfo = new int[nQueries][2];

        for(int queryIndex = 0 ; queryIndex < nQueries ; queryIndex++) {
            List<Integer> currentQuery = queries.get(queryIndex);

            int startIndex = currentQuery.get(0);
            int endIndex = currentQuery.get(1);
            int val = currentQuery.get(2);

            startInfo[queryIndex][0] = startIndex;
            startInfo[queryIndex][1] = val;

            endInfo[queryIndex][0] = endIndex;
            endInfo[queryIndex][1] = val;
        }

        Arrays.sort(startInfo, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });

        Arrays.sort(endInfo, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });

        int startIndex = 0, endIndex = 0;

        while(startIndex < nQueries && endIndex < nQueries) {
            if(startInfo[startIndex][0] <= endInfo[endIndex][0]) {
                currentVal += startInfo[startIndex][1];
                maxVal = Math.max(maxVal, currentVal);
                startIndex++;
            }
            else {
                currentVal -= endInfo[endIndex][1];
                endIndex++;
            }
        }

        return maxVal;
    }

    /*
    Time : O(queries * n)
    Space : O(n)
    Brute force exceeds time limit.
     */
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        long[] result = new long[n];

        for(List<Integer> currentQuery : queries) {
            int startIndex = currentQuery.get(0);
            int endIndex = currentQuery.get(1);
            int increment = currentQuery.get(2);

            for(int index = startIndex ; index <= endIndex ; index++) {
                result[index - 1] += increment;
            }
        }

        long maxVal = Integer.MIN_VALUE;
        for(long currVal : result) {
            maxVal = Math.max(maxVal, currVal);
        }

        return maxVal;
    }
}
