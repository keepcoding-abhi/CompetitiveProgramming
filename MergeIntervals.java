public class MergeIntervals {

    //Using insertion sort.
    public int[][] merge(int[][] intervals) {

        int numberOfIntervals = intervals.length;

        int index = 1;
        while(index < numberOfIntervals) {

            int[] currentInterval = intervals[index];
            int sortedArrayIndex = index - 1;
            while(sortedArrayIndex > -1 && intervals[sortedArrayIndex][0] > currentInterval[0]) {
                intervals[sortedArrayIndex + 1] = intervals[sortedArrayIndex];
                sortedArrayIndex--;
            }
            intervals[sortedArrayIndex + 1] = currentInterval;
            index++;
        }

        List<int[]> mergedIntervals = new LinkedList<int[]>();
        int intervalIndex = 0;

        int currentIntervalStart = intervals[0][0];
        int currentIntervalEnd = intervals[0][1];
        intervalIndex++;
        while(intervalIndex < numberOfIntervals) {
            if(intervals[intervalIndex][0] <= currentIntervalEnd) {
                int nextIntervalEnd = intervals[intervalIndex][1];
                currentIntervalEnd = (currentIntervalEnd > nextIntervalEnd) ? currentIntervalEnd : nextIntervalEnd;
            }
            else {
                mergedIntervals.add(new int[]{currentIntervalStart, currentIntervalEnd});
                currentIntervalStart = intervals[intervalIndex][0];
                currentIntervalEnd = intervals[intervalIndex][1];
            }

            intervalIndex++;
        }
        mergedIntervals.add(new int[]{currentIntervalStart, currentIntervalEnd});

        int numberOfMergedIntervals = mergedIntervals.size();
        int[][] result = new int[numberOfMergedIntervals][2];
        for(index = 0 ; index < numberOfMergedIntervals ; index++) {
            result[index] = mergedIntervals.remove(0);
        }

        return result;
    }

    //2) Using built-in sort function.
    public int[][] merge(int[][] intervals) {

        int numberOfIntervals = intervals.length;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> mergedIntervals = new LinkedList<int[]>();
        int intervalIndex = 0;

        int currentIntervalStart = intervals[0][0];
        int currentIntervalEnd = intervals[0][1];
        intervalIndex++;
        while(intervalIndex < numberOfIntervals) {
            if(intervals[intervalIndex][0] <= currentIntervalEnd) {
                int nextIntervalEnd = intervals[intervalIndex][1];
                currentIntervalEnd = (currentIntervalEnd > nextIntervalEnd) ? currentIntervalEnd : nextIntervalEnd;
            }
            else {
                mergedIntervals.add(new int[]{currentIntervalStart, currentIntervalEnd});
                currentIntervalStart = intervals[intervalIndex][0];
                currentIntervalEnd = intervals[intervalIndex][1];
            }

            intervalIndex++;
        }
        mergedIntervals.add(new int[]{currentIntervalStart, currentIntervalEnd});

        int numberOfMergedIntervals = mergedIntervals.size();
        int[][] result = new int[numberOfMergedIntervals][2];
        for(int index = 0 ; index < numberOfMergedIntervals ; index++) {
            result[index] = mergedIntervals.remove(0);
        }

        return result;
    }
}
