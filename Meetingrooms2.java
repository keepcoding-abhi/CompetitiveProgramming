import java.util.Arrays;
import java.util.PriorityQueue;

public class Meetingrooms2 {

    /*
    Time: O(nLog(n))
    Space: O(n)

    Sort the start time and end time separately. Each end time tells us the time when a room will be vacated.
    Use a pointer in the array of sorted end times, this pointer tells us the latest time when a room would get free.
    As long as we have meetings that start before this time assign new rooms. Otherwise, if a meeting starts after this time
    one of the existing rooms can be reused, therefore, increment the latest end time.
     */

    public int minMeetingRooms(int[][] intervals) {

        int[] startTimes = new int[intervals.length];
        int[] endTimes = new int[intervals.length];
        int index = 0;

        for(int[] interval : intervals) {
            startTimes[index] = interval[0];
            endTimes[index] = interval[1];
            index++;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int startIndex = 0;
        int latestEndTime = endTimes[0];
        int latestEndTimePtr = 0;
        int roomsAllocated = 1;

        for(startIndex = 1 ; startIndex < intervals.length ; startIndex++) {
            if(startTimes[startIndex] < latestEndTime) {
                roomsAllocated++;
            }
            else {
                latestEndTimePtr++;
                latestEndTime = endTimes[latestEndTimePtr];
            }
        }

        return roomsAllocated;
    }

    /*
    Time: O(nLog(n))
    Space: O(n)

    Sorting the meeting as per start times and then utilizing a Priority Queue (min heap) to keep track of the
    end times of the scheduled meetings.
     */
    public int minMeetingRooms(int[][] intervals) {

        int minRoomsRequired = 1;

        Arrays.sort(intervals, (int[] interval1, int[] interval2) -> {
            return interval1[0] - interval2[0];
        });

        PriorityQueue<Integer> rooms = new PriorityQueue<Integer>();

        rooms.add(intervals[0][1]);

        for(int index = 1 ; index < intervals.length ; index++) {

            if(rooms.peek() <= intervals[index][0]) {
                rooms.poll();
                rooms.add(intervals[index][1]);
            }
            else {
                rooms.add(intervals[index][1]);
            }

            minRoomsRequired = Math.max(minRoomsRequired, rooms.size());
        }

        return minRoomsRequired;
    }

    /*
    Time : O(nLog(n)) + O(n^2)
    Space : O(n)
    Put the intervals that do not overlap in a group in a greedy manner.
     */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (int[] interval1, int[] interval2) -> {
            return interval1[0] - interval2[0];
        });

        int intervalLen = intervals.length;
        int[] groups = new int[intervalLen];

        int groupCount = 0;

        for(int index = 0 ; index < intervalLen ; index++) {
            if(groups[index] == 0) {
                groupCount++;
                groups[index] = groupCount;
                int currentIntervalEndTime = intervals[index][1];

                for(int index1 = index + 1 ; index1 < intervalLen ; index1++) {
                    if(groups[index1] == 0) {
                        int[] nextInterval = intervals[index1];

                        if(currentIntervalEndTime <= nextInterval[0]) {
                            groups[index1] = groupCount;
                            currentIntervalEndTime = nextInterval[1];
                        }
                    }
                }
            }
        }

        return groupCount;
    }
}
