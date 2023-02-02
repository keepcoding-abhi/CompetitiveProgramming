public class MyCalendar1 {
}

// Time complexity : O(Log(n), Space complexity : O(n)
class MyCalendar {

    List<int[]> scheduledEvents;

    public MyCalendar() {
        scheduledEvents = new ArrayList<int[]>();
    }

    public boolean book(int start, int end) {

        boolean bookingStatus = true;

        if(scheduledEvents.isEmpty()) {
            scheduledEvents.add(new int[]{start, end});
            bookingStatus = true;
        }
        else {
            int lowerIndex = 0, upperIndex = scheduledEvents.size() - 1;
            int lastSearchedIndex = 0;

            while(lowerIndex <= upperIndex) {
                lastSearchedIndex = (lowerIndex + upperIndex) / 2;
                int[] currentEvent = scheduledEvents.get(lastSearchedIndex);

                if(currentEvent[0] == start) {
                    break;
                }
                else if(currentEvent[0] < start) {
                    lowerIndex = lastSearchedIndex + 1;
                }
                else {
                    upperIndex = lastSearchedIndex - 1;
                }
            }

            int[] currentEvent = scheduledEvents.get(lastSearchedIndex);
            int numberOfEvents = scheduledEvents.size();

            if(start >= currentEvent[1]) {
                if(lastSearchedIndex < numberOfEvents - 1) {
                    int[] nextEvent = scheduledEvents.get(lastSearchedIndex + 1);
                    if(end <= nextEvent[0]) {
                        bookingStatus = true;
                        scheduledEvents.add(lastSearchedIndex + 1, new int[]{start, end});
                    }
                    else {
                        bookingStatus = false;
                    }
                }
                else {
                    bookingStatus = true;
                    scheduledEvents.add(new int[]{start, end});
                }
            }
            else {
                if(end <= currentEvent[0]) {
                    if(lastSearchedIndex > 0) {
                        int[] prevEvent = scheduledEvents.get(lastSearchedIndex - 1);
                        if(prevEvent[1] <= start) {
                            bookingStatus = true;
                            scheduledEvents.add(lastSearchedIndex, new int[]{start, end});
                        }
                        else {
                            bookingStatus = false;
                        }
                    }
                    else {
                        bookingStatus = true;
                        scheduledEvents.add(0, new int[]{start, end});
                    }
                }
                else {
                    bookingStatus = false;
                }
            }
        }

        return bookingStatus;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
