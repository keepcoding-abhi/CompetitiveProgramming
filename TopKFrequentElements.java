import java.util.*;

public class TopKFrequentElements {
}

/*
Time : O(n) best case O(n^2) average case
Space : O(n)

Saving each number and its count in a map. Using the counts to compare unique values/keys of this hashmap.
Partition algorithm used in quick sort is applied until the pivot element separates the top k highest values elements
from the rest.
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> freqs = new HashMap<Integer, Integer>();

        for(int num : nums) {
            freqs.put(num, freqs.getOrDefault(num, 0) + 1);
        }

        int[] uniqueVals = new int[freqs.size()];
        int index = 0;
        for(Map.Entry<Integer, Integer> entry : freqs.entrySet()) {
            int key = entry.getKey();

            uniqueVals[index++] = key;
        }

        getKElements(uniqueVals, uniqueVals.length - k, freqs);

        int[] result = new int[k];

        int resIndex = 0;
        for(index = uniqueVals.length - k ; index < uniqueVals.length ; index++) {
            result[resIndex++] = uniqueVals[index];
        }

        return result;
    }

    private void getKElements(int[] uniqueVals, int k, Map<Integer, Integer> freqs) {
        int leftIndex = 0, rightIndex = uniqueVals.length - 1;
        int pivotIndex = partition(uniqueVals, freqs, leftIndex, rightIndex);

        while(pivotIndex != k) {
            if(pivotIndex < k) {
                leftIndex = pivotIndex + 1;
            }
            else {
                rightIndex = pivotIndex - 1;
            }

            pivotIndex = partition(uniqueVals, freqs, leftIndex, rightIndex);
        }
    }

    private int partition(int[] arr, Map<Integer, Integer> comparisonVals, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int pivotVal = comparisonVals.get(pivot);

        int leftIndex = startIndex + 1, smallervalsIndex = leftIndex;

        while(leftIndex <= endIndex) {
            if(comparisonVals.get(arr[leftIndex]) < pivotVal) {
                int temp = arr[smallervalsIndex];
                arr[smallervalsIndex++] = arr[leftIndex];
                arr[leftIndex] = temp;
            }

            leftIndex++;
        }

        int temp = arr[smallervalsIndex - 1];
        arr[smallervalsIndex - 1] = pivot;
        arr[startIndex] = temp;

        return smallervalsIndex - 1;
    }
}

/*
Time : O(nLog(n))
Space : O(n) to O(Log(n)) depending on sorting algorithm.
Save the frequency of each number and sort numbers based on frequencies.
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqs = new HashMap<Integer, Integer>();

        for(int num : nums) {
            freqs.put(num, freqs.getOrDefault(num, 0) + 1);
        }

        Set<Integer> keys = freqs.keySet();
        int nKeys = keys.size();
        Integer[] uniqueNums = new Integer[nKeys];
        Iterator<Integer> keyIterator = keys.iterator();

        for(int index = 0 ; index < nKeys ; index++) {
            uniqueNums[index] = keyIterator.next();
        }

        Arrays.sort(uniqueNums, (Integer a, Integer b) -> {
            return freqs.get(a) - freqs.get(b);
        });

        int[] result = new int[k];
        int resultIndex = 0;
        for(int index = uniqueNums.length - k ; index < uniqueNums.length ; index++) {
            result[resultIndex] = uniqueNums[index];
            resultIndex++;
        }

        return result;
    }
}

/*
Time : O(n * Log(n))
Space : O(n)
Saving the counts in a hashmap and getting the 'k' highest values using priority queue.
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqs = new HashMap<Integer, Integer>();

        for(int num : nums) {
            freqs.put(num, freqs.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> orderedVals = new PriorityQueue<int[]>((int[] arr1, int[] arr2) -> {
            return arr2[1] - arr1[1];
        });

        for(Map.Entry<Integer, Integer> entry : freqs.entrySet()) {
            orderedVals.add(new int[]{entry.getKey(), entry.getValue()});
        }

        int[] result = new int[k];

        for(int index = 0 ; index < k ; index++) {
            result[index] = orderedVals.poll()[0];
        }

        return result;
    }
}

/*
Time : O(n^2)
Space : O(n)
Brute force solution.
Save the occurrences of each element in a hash-map.
Sort the values of the map using insertion sort.
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqs = new HashMap<Integer, Integer>();

        for(int num : nums) {
            freqs.put(num, freqs.getOrDefault(num, 0) + 1);
        }

        int[] kMostFreq = new int[k];
        int[] freqVals = new int[k];

        for(Map.Entry<Integer, Integer> entry : freqs.entrySet()) {
            int element = entry.getKey();
            int freq = entry.getValue();

            int indexInFreq = getIndexInFreq(freqVals, freq);
            if(indexInFreq != -1) {
                for(int index = k - 1 ; index > indexInFreq ; index--) {
                    kMostFreq[index] = kMostFreq[index - 1];
                }
                kMostFreq[indexInFreq] = element;
            }
        }

        return kMostFreq;
    }

    private int getIndexInFreq(int[] arr, int num) {

        int correctPos = -1, index = arr.length - 1;

        while(index > -1 && arr[index] < num) {
            if(index > 0) {
                arr[index] = arr[index - 1];
            }

            index--;
        }

        if(index == arr.length - 1) {
            correctPos = -1;
        }
        else {
            correctPos = index + 1;
            arr[correctPos] = num;
        }

        return correctPos;
    }
}
