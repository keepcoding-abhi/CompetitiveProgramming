public class HitCounters {
}

// Using Linked List
class HitCounter {

    private Map<Integer, ListNode> timestamps;
    private ListNode head;

    public HitCounter() {
        timestamps = new HashMap<Integer, ListNode>();
    }

    public void hit(int timestamp) {
        if(timestamps.containsKey(timestamp)) {
            ListNode curntTimestamp = timestamps.get(timestamp);
            curntTimestamp.hits++;
        }
        else {
            ListNode newTimestamp = new ListNode(timestamp);
            newTimestamp.hits = 1;

            timestamps.put(timestamp, newTimestamp);

            if(head == null) {
                head = newTimestamp;
            }
            else {
                newTimestamp.next = head;
                head = newTimestamp;
            }
        }
    }

    public int getHits(int timestamp) {

        int totalHits = 0;

        int fromTime = Math.max(0, timestamp - 299);

        int currentTimestamp = timestamp;
        ListNode currentTimestampInfo = null;

        while(currentTimestamp >= fromTime && currentTimestampInfo == null) {
            currentTimestampInfo = timestamps.get(currentTimestamp);
            currentTimestamp--;
        }

        if(currentTimestampInfo != null) {
            currentTimestamp = currentTimestampInfo.timestamp;
            totalHits += currentTimestampInfo.hits;

            currentTimestampInfo = currentTimestampInfo.next;

            while(currentTimestampInfo != null) {
                currentTimestamp = currentTimestampInfo.timestamp;

                if(currentTimestamp >= fromTime) {
                    totalHits += currentTimestampInfo.hits;
                    currentTimestampInfo = currentTimestampInfo.next;
                }
                else {
                    break;
                }
            }
        }

        return totalHits;
    }
}

class ListNode {
    int timestamp;
    int hits;
    ListNode next;

    public ListNode(int timestamp) {
        this.timestamp = timestamp;
    }
}


// Slower solution. Using HashMap to store timestamps and their hits.
class HitCounter {

    private Map<Integer, Integer> timestampHits;


    public HitCounter() {
        timestampHits = new HashMap<Integer, Integer>();
    }

    public void hit(int timestamp) {
        timestampHits.put(timestamp, timestampHits.getOrDefault(timestamp, 0) + 1);
    }

    public int getHits(int timestamp) {

        int totalHits = 0;

        int startTime = Math.max(0, timestamp - 299);
        for(int currTime = startTime ; currTime <= timestamp ; currTime++) {
            totalHits += timestampHits.getOrDefault(currTime, 0);
        }

        return totalHits;
    }
}


