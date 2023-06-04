import java.util.*;

public class ThreeSum {

    /*
    Time : O(n^2)
    Space : O(Log(n)) to O(n) depending on the algorithm used for sorting
    Sort the array.
    Fix the first number on the triplet and fid the remaining two using two pointers.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> overallThreeSums = new ArrayList<List<Integer>>();

        for(int index = 0, len = nums.length ; index < len - 2 ; index++) {
            if(index != 0 && nums[index] == nums[index  - 1]) {
                continue;
            }

            // If the first element of triplet is positive we need negative numbers to make the sum zero
            // But all elements to the right are positive since we have sorted the array.
            // Therefore, no loops required in this case.
            if(nums[index] > 0) {
                continue;
            }

            int requiredSum = -1 * nums[index];

            int leftPointer = index + 1, rightPointer = nums.length - 1;

            while(leftPointer < rightPointer) {
                int currentSum = nums[leftPointer] + nums[rightPointer];

                if(currentSum == requiredSum) {
                    List<Integer> current3Sum = new ArrayList<Integer>(3);
                    current3Sum.add(nums[index]);
                    current3Sum.add(nums[leftPointer]);
                    current3Sum.add(nums[rightPointer]);
                    overallThreeSums.add(current3Sum);

                    leftPointer++;
                    while(leftPointer < rightPointer && nums[leftPointer] == nums[leftPointer - 1]) {
                        leftPointer++;
                    }

                    rightPointer--;
                    while(leftPointer < rightPointer && nums[rightPointer] == nums[rightPointer + 1]) {
                        rightPointer--;
                    }
                }
                else if(currentSum > requiredSum) {
                    rightPointer--;
                }
                else {
                    leftPointer++;
                }
            }
        }

        return overallThreeSums;
    }

    /*
    Time : O(n^2)
    Space : O(n)
    Using map to record the indices of elements in the array.
    Iterating over the combination of first two elements of the triplet and finding the
    occurrence of third element in the hash-set.
     */
    public List<List<Integer>> threeSum(int[] nums) {

        Set<List<Integer>> threeSums = new HashSet<List<Integer>>();

        Map<Integer, Integer> elementIndices = new HashMap<Integer, Integer>();
        for(int index = 0 ; index < nums.length ; index++) {
            elementIndices.put(nums[index], index);
        }

        for(int firstIndex = 0 ; firstIndex < nums.length - 2 ; firstIndex++) {
            int firstNum = nums[firstIndex];

            for(int remIndex = firstIndex + 1 ; remIndex < nums.length ; remIndex++) {
                int thirdElRequired = -1 * (firstNum + nums[remIndex]);

                int elIndex = elementIndices.getOrDefault(thirdElRequired, -1);
                if(elIndex > remIndex) {
                    List<Integer> newTriplet = Arrays.asList(firstNum, nums[remIndex], thirdElRequired);
                    Collections.sort(newTriplet);
                    threeSums.add(newTriplet);
                }
            }
        }

        return new ArrayList<List<Integer>>(threeSums);
    }

    /*
    Brute force solution.
    Time : O(n^3).
    Space : worst case O(n^2)
            In worst case all elements are zero.
            nC3 triplets will be created, but only one of them will be added to the result.
            Moreover, the use of set creates a memory overhead as the output is in the form of a list.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> overallThreeSums = new ArrayList<List<Integer>>();
        Set<List<Integer>> threeSums = new HashSet<List<Integer>>();

        for(int firstIndex = 0 ; firstIndex < nums.length - 2 ; firstIndex++) {
            int firstNum = nums[firstIndex];
            for(int secondIndex = firstIndex + 1 ; secondIndex < nums.length - 1 ; secondIndex++) {
                int secondNum = nums[secondIndex];
                for(int thirdIndex = secondIndex + 1 ; thirdIndex < nums.length ; thirdIndex++) {
                    if(firstNum + secondNum + nums[thirdIndex] == 0) {
                        List<Integer> triplet = Arrays.asList(firstNum, secondNum, nums[thirdIndex]);

                        Collections.sort(triplet);
                        threeSums.add(triplet);
                    }
                }
            }
        }

        return new ArrayList<List<Integer>>(threeSums);
    }

    /*
    Time : O(n^3)
    Space : O(1)
    Brute force solution after sorting array.
    Both brute force solutions exceed time limit.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> threeSums = new LinkedList<List<Integer>>();

        for(int firstIndex = 0 ; firstIndex < nums.length - 2 ; firstIndex++) {
            int firstNum = nums[firstIndex];

            if(firstNum > 0) {
                continue;
            }

            if(firstIndex > 0 && firstNum == nums[firstIndex - 1]) {
                continue;
            }

            for(int secondIndex = firstIndex + 1 ; secondIndex < nums.length - 1 ; secondIndex++) {
                int secondNum = nums[secondIndex];

                if(secondIndex > firstIndex + 1 && nums[secondIndex] == nums[secondIndex - 1]) {
                    continue;
                }

                for(int thirdIndex = secondIndex + 1 ; thirdIndex < nums.length ; thirdIndex++) {
                    if(firstNum + secondNum + nums[thirdIndex] == 0) {
                        List<Integer> triplet = Arrays.asList(firstNum, secondNum, nums[thirdIndex]);

                        threeSums.add(triplet);
                        break;
                    }
                }
            }
        }

        return threeSums;
    }
}
