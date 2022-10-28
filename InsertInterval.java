public class InsertInterval {

    //1) Faster version
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> mergedIntervals = new LinkedList<int[]>();

        int totalIntervals = intervals.length;
        int currentIntervalIndex = 0;
        int[] currentInterval = null;

        int newIntervalStart = newInterval[0];
        int newIntervalEnd = newInterval[1];

        while(currentIntervalIndex < totalIntervals) {

            currentInterval = intervals[currentIntervalIndex];

            if(intervals[currentIntervalIndex][1] < newIntervalStart) {
                mergedIntervals.add(intervals[currentIntervalIndex]);
                currentIntervalIndex++;
            }
            else {
                break;
            }

        }

        while(currentIntervalIndex < totalIntervals) {
            currentInterval = intervals[currentIntervalIndex];

            if(newIntervalEnd >= currentInterval[0]) {
                newIntervalStart = Math.min(currentInterval[0], newIntervalStart);
                newIntervalEnd = Math.max(currentInterval[1], newIntervalEnd);
                currentIntervalIndex++;
            }
            else {
                break;
            }

        }

        mergedIntervals.add(new int[]{newIntervalStart, newIntervalEnd});
        while(currentIntervalIndex < totalIntervals) {
            mergedIntervals.add(intervals[currentIntervalIndex]);
            currentIntervalIndex++;
        }

        int mergedIntervalsLength = mergedIntervals.size();

        int[][] newIntervals = new int[mergedIntervalsLength][2];

        for(int index = 0 ; index < mergedIntervalsLength ; index++) {
            newIntervals[index] = mergedIntervals.remove(0);
        }

        return newIntervals;
    }

    //2) Elementary solution.

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> mergedIntervals = new LinkedList<int[]>();

        int totalIntervals = intervals.length;
        int currentIntervalIndex = 0;

        while(currentIntervalIndex < totalIntervals) {
            int[] currentInterval = intervals[currentIntervalIndex];

            if(currentInterval[1] < newInterval[0]) {
                mergedIntervals.add(currentInterval);
                currentIntervalIndex++;
            }
            else {
                break;
            }
        }

        if(currentIntervalIndex < totalIntervals) {
            int[] currentInterval = intervals[currentIntervalIndex];

            if(newInterval[1] >= currentInterval[0]) {

                newInterval[0] = Math.min(currentInterval[0], newInterval[0]);
                newInterval[1] = Math.max(currentInterval[1], newInterval[1]);

                currentIntervalIndex++;

                while(currentIntervalIndex < totalIntervals) {
                    currentInterval = intervals[currentIntervalIndex];

                    if(newInterval[1] < currentInterval[0]) {
                        break;
                    }

                    newInterval[1] = Math.max(newInterval[1], currentInterval[1]);
                    currentIntervalIndex++;
                }
            }



            mergedIntervals.add(newInterval);
            while(currentIntervalIndex < totalIntervals) {
                mergedIntervals.add(intervals[currentIntervalIndex]);
                currentIntervalIndex++;
            }

        }
        else {
            mergedIntervals.add(newInterval);
        }

        int mergedIntervalsLength = mergedIntervals.size();

        int[][] newIntervals = new int[mergedIntervalsLength][2];

        for(int index = 0 ; index < mergedIntervalsLength ; index++) {
            newIntervals[index] = mergedIntervals.remove(0);
        }

        return newIntervals;
    }

}
