import java.util.Arrays;

public class NonOverlappingIntervals {
    /*
    Time : O(n * Log(n))
    Space : O(n) to O(Log(n)), depending on sorting algorithm.
    Finding the maximum number of non-overlapping intervals by the earliest end time first greedy algorithm.
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (int[] interval1, int[] interval2) -> {
            return interval1[1] - interval2[1];
        });

        int lastIntervalsEndTime = intervals[0][1];
        int removedIntervals = 0;
        for(int index = 1, numIntervals = intervals.length ; index < numIntervals ; index++) {
            int[] currentInterval = intervals[index];

            if(currentInterval[0] >= lastIntervalsEndTime) {
                lastIntervalsEndTime = currentInterval[1];
            }
            else {
                removedIntervals++;
            }
        }

        return removedIntervals;
    }
}
