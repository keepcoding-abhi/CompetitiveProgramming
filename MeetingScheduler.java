public class MeetingScheduler {

    // Using heap to sort the slots and schedule the meeting.
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {

        PriorityQueue<int[]> sortedSlots = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);

        addSlots(sortedSlots, slots1, duration);
        addSlots(sortedSlots, slots2, duration);

        int numSlots1 = slots1.length, numSlots2 = slots2.length;
        int slot1Index = 0, slot2Index = 0;
        List<Integer> selectedSlot = new ArrayList<Integer>(2);

        while(sortedSlots.size() > 1) {
            int[] firstSlot = sortedSlots.poll();
            int[] secondSlot = sortedSlots.peek();

            int potentialStart = Math.max(firstSlot[0], secondSlot[0]);
            int potentialEnd = Math.min(firstSlot[1], secondSlot[1]);

            if(potentialEnd - potentialStart >= duration) {
                selectedSlot.add(potentialStart);
                selectedSlot.add(potentialStart + duration);
                break;
            }
        }

        return selectedSlot;
    }

    private void addSlots(PriorityQueue<int[]>sortedSlots, int[][] slots, int duration) {
        for(int index = 0, len = slots.length ; index < len ; index++) {
            int[] currSlot = slots[index];

            if(currSlot[1] - currSlot[0] >= duration) {
                sortedSlots.add(currSlot);
            }
        }
    }

    // Using built-in function for sorting and usin two pointers to examine each slot.
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int slot1Index = 0, slot2Index = 0, numSlots1 = slots1.length, numSlots2 = slots2.length;

        List<Integer> selectedSlot = new ArrayList<Integer>(2);
        while(slot1Index < numSlots1 && slot2Index < numSlots2) {
            int[] slot1 = slots1[slot1Index];
            int[] slot2 = slots2[slot2Index];

            int potentialStart = Math.max(slot1[0], slot2[0]);
            int potentialEnd = Math.min(slot1[1], slot2[1]);

            if(potentialEnd - potentialStart >= duration) {
                selectedSlot.add(potentialStart);
                selectedSlot.add(potentialStart + duration);
                break;
            }

            if(slot1[1] > slot2[1]) {
                slot2Index++;
            }
            else {
                slot1Index++;
            }
        }

        return selectedSlot;
    }

    // Slow solution. Using insertion sort to sort the slots according to start time.
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {

        int slot1Index = 0, slot2Index = 0;
        int numSlots1 = slots1.length, numSlots2 = slots2.length;

        sort(slots1);
        sort(slots2);

        List<Integer> compatibleSlot = new LinkedList<Integer>();

        while(slot1Index < numSlots1 && slot2Index < numSlots2) {
            int[] slot1 = slots1[slot1Index];
            int[] slot2 = slots2[slot2Index];

            if((slot1[0] >= slot2[0] && slot1[0] <= slot2[1]) || (slot2[0] >= slot1[0] && slot2[0] <= slot1[1])) {

                int intersectionStart = Math.max(slot1[0], slot2[0]);
                int intersectionEnd = Math.min(slot1[1], slot2[1]);

                int intersectionDuration = intersectionEnd - intersectionStart;

                if(intersectionDuration >= duration) {
                    compatibleSlot.add(intersectionStart);
                    compatibleSlot.add(intersectionStart + duration);
                    break;
                }
            }

            if(slot1[1] > slot2[1]) {
                slot2Index++;
            }
            else {
                slot1Index++;
            }
        }

        return compatibleSlot;
    }

    // sort the rows of the matrix as per the value of the first column
    private void sort(int[][] matrix) {

        int numRows = matrix.length;
        int unsortedIndex = 1;

        while(unsortedIndex < numRows) {
            int[] current = matrix[unsortedIndex];
            int sortedPosition = unsortedIndex - 1;

            while(sortedPosition > -1 && matrix[sortedPosition][0] > current[0]) {
                matrix[sortedPosition + 1] = matrix[sortedPosition];
                sortedPosition--;
            }

            matrix[sortedPosition + 1] = current;
            unsortedIndex++;
        }

    }

}
