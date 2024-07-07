import java.util.ArrayList;
import java.util.List;

public class PlatesBetweenCandies {
}

/*
Time: O(sLen + qLen)
Space: O(sLen)

Record the last candle seen on left and right side of each index. Refer these values to compute the number of plates
between two candles.
 */
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {

        int sLen = s.length();
        int[] candlesOnLeft = new int[sLen];
        int[] candlesOnRight = new int[sLen];
        int[] candlesSeenSoFar = new int[sLen];
        int[] result = new int[queries.length];

        int lastCandleOnLeft = -1, candleCount = 0;
        for(int index = 0 ; index < sLen ; index++) {
            if(s.charAt(index) == '|') {
                lastCandleOnLeft = index;
                candleCount++;
            }

            candlesOnLeft[index] = lastCandleOnLeft;
            candlesSeenSoFar[index] = candleCount;
        }

        int lastCandleOnRight = -1;
        for(int index = sLen - 1 ; index > -1 ; index--) {
            if(s.charAt(index) == '|') {
                lastCandleOnRight = index;
            }

            candlesOnRight[index] = lastCandleOnRight;
        }

        for(int index = 0 ; index < queries.length ; index++) {
            int[] currQuery = queries[index];

            int leftCandle = candlesOnRight[currQuery[0]];
            int rightCandle = candlesOnLeft[currQuery[1]];

            if(leftCandle == -1 || rightCandle == -1 || leftCandle >= rightCandle) {
                continue;
            }

            result[index] = (rightCandle - leftCandle - 1) - (candlesSeenSoFar[rightCandle] - candlesSeenSoFar[leftCandle] - 1);
        }

        return result;
    }
}

/*
Time: O(nQueries * Log(sLen))
Space: O(1)

Record the indices where candles are present. In each query, use binary search to find the nearest candles to
what has been mentioned in the query.
 */
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int nQueries = queries.length;
        int[] results = new int[nQueries];

        int sLen = s.length();

        ArrayList<Integer> candleIndices = new ArrayList<Integer>(sLen);

        for(int sIndex = 0 ; sIndex < sLen ; sIndex++) {
            if(s.charAt(sIndex) == '|') {
                candleIndices.add(sIndex);
            }
        }

        int queryIndex = 0;
        for(int[] query : queries) {
            int leftCandleIdx = findElement(candleIndices, query[0]);
            int rightCandleIdx = findElement(candleIndices, query[1]);

            if(rightCandleIdx == candleIndices.size()) {
                rightCandleIdx--;
            }
            else if(candleIndices.get(rightCandleIdx) != query[1]) {
                rightCandleIdx--;
            }

            if(leftCandleIdx < rightCandleIdx) {
                results[queryIndex] = (candleIndices.get(rightCandleIdx) - candleIndices.get(leftCandleIdx) - 1) -
                        (rightCandleIdx - leftCandleIdx - 1);
            }
            else {
                results[queryIndex] = 0;
            }

            queryIndex++;
        }


        return results;
    }

    private int findElement(List<Integer> els, int target) {
        int left = 0, right = els.size() - 1;

        while(left <= right) {
            int currIndex = (left + right) / 2;

            int currEl = els.get(currIndex);

            if(currEl == target) {
                left = currIndex;
                break;
            }
            else if(currEl > target) {
                right = currIndex - 1;
            }
            else {
                left = currIndex + 1;
            }
        }

        return left;
    }
}

/*

 */
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int nQueries = queries.length;
        int[] results = new int[nQueries];
        int queryIndex = 0;

        for(int[] query : queries) {
            int candleFromLeft = query[0], candleFromRight = query[1];

            while(s.charAt(candleFromLeft) != '|') {
                candleFromLeft++;
            }

            while(s.charAt(candleFromRight) != '|') {
                candleFromRight--;
            }

            int nPlates = 0;

            for(int plateIndex = candleFromLeft ; plateIndex <= candleFromRight ;
                plateIndex++) {
                if(s.charAt(plateIndex) == '*') {
                    nPlates++;
                }
            }

            results[queryIndex] = nPlates;
            queryIndex++;
        }

        return results;
    }
}
