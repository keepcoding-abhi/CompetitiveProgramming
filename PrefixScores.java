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

public class PrefixScores {
}

/*
Time : O(n)
 */
class Result {

    public static List<Integer> getPrefixScores(List<Integer> arr) {
        // Write your code here
        int len = arr.size();

        List<Integer> scores = new ArrayList<Integer>(len);
        int base = (int)(1e9 + 7);

        int maxYet = -1;
        long originalElemSum = 0, subsetSum = 0;

        for(int index = 0 ; index < len ; index++) {
            originalElemSum = (originalElemSum + arr.get(index));

            subsetSum += (originalElemSum % base);

            maxYet = Math.max(maxYet, arr.get(index));

            long temp = subsetSum + (maxYet * (index + 1l));
            scores.add((int)(temp % base));
        }

        return scores;
    }

}

/*
Time : O(n^2)
 */
class Result {

    /*
     * Complete the 'getPrefixScores' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> getPrefixScores(List<Integer> arr) {
        // Write your code here
        int len = arr.size();

        List<Integer> scores = new ArrayList<Integer>(len);

        int maxYet = -1;
        for(int index = 0 ; index < len ; index++) {
            int currentNum = arr.get(index);

            maxYet = Math.max(maxYet, currentNum);

            long prevVal = arr.get(0) + maxYet;
            long score = prevVal;

            for(int index1 = 1 ; index1 <= index ; index1++) {
                long nextVal = prevVal + arr.get(index1);
                score += nextVal;
                prevVal = nextVal;
            }

            scores.add((int)(score % 1000000007));
        }

        return scores;
    }

}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.getPrefixScores(arr);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

