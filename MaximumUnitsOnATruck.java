public class MaximumUnitsOnATruck {

    // Sorting based approach.
    // Time complexity : O(nLog(n)). Space : O(1)
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (int[] a, int[] b) -> {return a[1] - b[1];});

        int maxUnits = 0, remainingTruckSize = truckSize;
        for(int currentBoxIndex = boxTypes.length - 1 ; (currentBoxIndex > -1) && (remainingTruckSize > 0) ; currentBoxIndex--) {
            int unitsSelected = Math.min(boxTypes[currentBoxIndex][0], remainingTruckSize);

            remainingTruckSize -= unitsSelected;
            maxUnits += (unitsSelected * boxTypes[currentBoxIndex][1]);
        }

        return maxUnits;
    }

    // Priority queue based approach.
    // Time complexity : O(nLog(n)). Space : O(n).
    // Linear space required to store each box type in the priority queue.
    // Logarithmic time required for each heapify operation.
    public int maximumUnits(int[][] boxTypes, int truckSize) {

        PriorityQueue<int[]> boxMaxHeap = new PriorityQueue<int[]>((int[] a, int[] b) -> {return b[1] - a[1];});

        for(int[] currentBoxType : boxTypes) {
            boxMaxHeap.add(currentBoxType);
        }

        int maxUnits = 0, remainingTruckSize = truckSize;
        while(remainingTruckSize > 0 && !boxMaxHeap.isEmpty()) {
            int[] nextMaxUnitBox = boxMaxHeap.poll();
            int unitsAdded = Math.min(nextMaxUnitBox[0], remainingTruckSize);
            maxUnits += (unitsAdded * nextMaxUnitBox[1]);
            remainingTruckSize -= unitsAdded;
        }

        return maxUnits;
    }
}
