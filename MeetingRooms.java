import java.util.Arrays;

public class MeetingRooms {

    /*
    Time : O(n * Log(n))
    Space : O(n) to O(Log(n))
    Sort based on start times and check if adjacent intervals overlap by comparing current interval's
    start time with previous interval's end time.
     */
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (int[] interval1, int[] interval2) -> {
            return (interval1[0] - interval2[0]);
        });

        boolean canAttend = true;
        for(int index = 1 ; index < intervals.length ; index++) {
            if(intervals[index][0] < intervals[index - 1][1]) {
                canAttend = false;
                break;
            }
        }

        return canAttend;
    }

    /*
    Time : O(n^2)
    Space : O(1)
    Take each pair of intervals and check whether they overlap or not.
     */
    public boolean canAttendMeetings(int[][] intervals) {

        boolean canAttend = true;
        for(int index = 0, len = intervals.length ; index < len ; index++) {
            int[] prevInterval = intervals[index];
            for(int index1 = index + 1 ; index1 < len ; index1++) {
                int[] currInterval = intervals[index1];
                if(checkOverlap(prevInterval, currInterval)) {
                    canAttend = false;
                    break;
                }
            }
        }
        return canAttend;
    }

    private boolean checkOverlap(int[] interval1, int[] interval2) {
        boolean overlap = false;

        if(Math.max(interval1[0], interval2[0]) < Math.min(interval1[1], interval2[1])) {
            overlap = true;
        }

        return overlap;
    }
}
