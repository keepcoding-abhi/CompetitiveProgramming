public class FourSum {

    /*
    Time : O(n^3)
    Space : O(1)
    Generic recursive version of N-Sum using two pointers.
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        return getNSums(nums, 4, (long)target, 0);
    }

    private List<List<Integer>> getNSums(int[] nums, int n, long target, int startIndex) {
        List<List<Integer>> consolidatedNSums = new LinkedList<List<Integer>>();

        long averageValueOfRemElements = (int)target / n;

        if(nums[startIndex] > averageValueOfRemElements || nums[nums.length - 1] < averageValueOfRemElements) {
            return consolidatedNSums;
        }

        if(n == 2) {
            consolidatedNSums = getTwoSums(nums, startIndex, target);
        }
        else {
            for(int index = startIndex ; index <= nums.length - n ; index++) {
                int currentNum = nums[index];

                if(index > startIndex && nums[index] == nums[index - 1]) {
                    continue;
                }

                List<List<Integer>> currentNSums = getNSums(nums, n - 1, target - currentNum, index + 1);

                for(List<Integer> partialNSum : currentNSums) {
                    partialNSum.add(currentNum);
                    consolidatedNSums.add(partialNSum);
                }
            }
        }

        return consolidatedNSums;
    }

    private List<List<Integer>> getTwoSums(int[] nums, int startIndex, long target) {
        int lowerIndex = startIndex, upperIndex = nums.length - 1;

        List<List<Integer>> twoSums = new LinkedList<List<Integer>>();
        while(lowerIndex < upperIndex) {
            int currentSum = nums[lowerIndex] + nums[upperIndex];

            if(currentSum == target) {
                List<Integer> nextTwoSum = new ArrayList<Integer>(4);
                nextTwoSum.add(nums[lowerIndex]);
                nextTwoSum.add(nums[upperIndex]);
                twoSums.add(nextTwoSum);

                lowerIndex++;
                while(lowerIndex < upperIndex && nums[lowerIndex] == nums[lowerIndex - 1]) {
                    lowerIndex++;
                }

                upperIndex--;
                while(upperIndex > lowerIndex && nums[upperIndex] == nums[upperIndex + 1]) {
                    upperIndex--;
                }
            }
            else if(currentSum > target) {
                upperIndex--;
            }
            else {
                lowerIndex++;
            }
        }

        return twoSums;
    }

    /*
    Time : O(n^3)
    Space : O(n)
    Generic N-Sum using HashMap for searching last element of the quadruple in constant time
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        Map<Long, Integer> elementIndices = new HashMap<Long, Integer>();
        for(int index = 0 ; index < nums.length ; index++) {
            elementIndices.put((long)nums[index], index);
        }

        return getNSums(nums, 4, (long)target, 0, elementIndices);
    }

    private List<List<Integer>> getNSums(int[] nums, int n, long target, int startIndex, Map<Long, Integer> elementIndices) {
        List<List<Integer>> consolidatedNSums = null;
        if(n == 1) {
            consolidatedNSums = new LinkedList<List<Integer>>();
            int lastIndexOfTarget = elementIndices.getOrDefault(target, -1);
            if(lastIndexOfTarget >= startIndex) {
                List<Integer> newTuple = new LinkedList<Integer>();
                newTuple.add((int)target);
                consolidatedNSums.add(newTuple);
            }
        }
        else {
            consolidatedNSums = new LinkedList<List<Integer>>();
            for(int index = startIndex ; index <= nums.length - n ; index++) {
                int currentNum = nums[index];

                if(index > startIndex && nums[index] == nums[index - 1]) {
                    continue;
                }

                List<List<Integer>> currentNSums = getNSums(nums, n - 1, target - currentNum, index + 1, elementIndices);

                for(List<Integer> partialNSum : currentNSums) {
                    partialNSum.add(currentNum);
                    consolidatedNSums.add(partialNSum);
                }
            }
        }

        return consolidatedNSums;
    }

    /*
    Time : O(n^3 * Log(n))
    Space : O(n) to O(Log(n)) depending on sorting algorithm.
    Generic N-sum using binary search to find the last element.
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        return getNSums(nums, 4, (long)target, 0);
    }

    private List<List<Integer>> getNSums(int[] nums, int n, long target, int startIndex) {
        List<List<Integer>> consolidatedNSums = null;
        if(n == 1) {
            consolidatedNSums = new LinkedList<List<Integer>>();
            boolean targetPresent = binarySearch(nums, startIndex, target);
            if(targetPresent) {
                List<Integer> newTuple = new LinkedList<Integer>();
                newTuple.add((int)target);
                consolidatedNSums.add(newTuple);
            }
        }
        else {
            consolidatedNSums = new LinkedList<List<Integer>>();
            for(int index = startIndex ; index <= nums.length - n ; index++) {
                int currentNum = nums[index];

                if(index > startIndex && nums[index] == nums[index - 1]) {
                    continue;
                }

                List<List<Integer>> currentNSums = getNSums(nums, n - 1, target - currentNum, index + 1);

                for(List<Integer> partialNSum : currentNSums) {
                    partialNSum.add(currentNum);
                    consolidatedNSums.add(partialNSum);
                }
            }
        }

        return consolidatedNSums;
    }

    private boolean binarySearch(int[] nums, int index, long target) {

        int lowerIndex = index, upperIndex = nums.length - 1;
        boolean found = false;

        while(lowerIndex <= upperIndex) {
            int midIndex = (lowerIndex + upperIndex) / 2;
            int midEl = nums[midIndex];

            if(target == midEl) {
                found = true;
                break;
            }
            else if(target > midEl) {
                lowerIndex = midIndex + 1;
            }
            else {
                upperIndex = midIndex - 1;
            }
        }

        return found;
    }

    /*
    Time : O(n^3)
    Space : O(n) to O(Log(n)) depending on sorting algorithm

    Extending two pointer version of two sum.
    Fixing first two elements of the quadruplet.
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> fourSums = new LinkedList<List<Integer>>();
        long extendedTarget = target;
        // -2 -1 0 0 1 2
        for(int firstIndex = 0 ; firstIndex < nums.length - 3 ; firstIndex++) {
            int firstNum = nums[firstIndex];

            if((firstIndex > 0) && (firstNum == nums[firstIndex - 1])) {
                continue;
            }

            for(int secondIndex = firstIndex + 1 ; secondIndex < nums.length - 2 ; secondIndex++) {
                int secondNum = nums[secondIndex];

                if((secondIndex > firstIndex + 1) && (secondNum == nums[secondIndex - 1])) {
                    continue;
                }

                List<List<Integer>> twoSums = getTwoSums(nums, secondIndex + 1, extendedTarget - firstNum - secondNum);

                for(List<Integer> nextQuad : twoSums) {
                    nextQuad.add(firstNum);
                    nextQuad.add(secondNum);

                    fourSums.add(nextQuad);
                }
            }
        }

        return fourSums;
    }

    private List<List<Integer>> getTwoSums(int[] nums, int startIndex, long target) {
        int lowerIndex = startIndex, upperIndex = nums.length - 1;

        List<List<Integer>> twoSums = new LinkedList<List<Integer>>();
        while(lowerIndex < upperIndex) {
            int currentSum = nums[lowerIndex] + nums[upperIndex];

            if(currentSum == target) {
                List<Integer> nextTwoSum = new ArrayList<Integer>(4);
                nextTwoSum.add(nums[lowerIndex]);
                nextTwoSum.add(nums[upperIndex]);
                twoSums.add(nextTwoSum);

                lowerIndex++;
                while(lowerIndex < upperIndex && nums[lowerIndex] == nums[lowerIndex - 1]) {
                    lowerIndex++;
                }

                upperIndex--;
                while(upperIndex > lowerIndex && nums[upperIndex] == nums[upperIndex + 1]) {
                    upperIndex--;
                }
            }
            else if(currentSum > target) {
                upperIndex--;
            }
            else {
                lowerIndex++;
            }
        }

        return twoSums;
    }

    /*
    Time : O(n^4)
    Space : O(1)

    Optimized the brute-force approach by sorting.
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> fourSums = new LinkedList<List<Integer>>();

        getQuadruplets(nums, 0, target, new Integer[4], 0, fourSums);

        return (fourSums);
    }

    private void getQuadruplets(int[] nums, int startIndex, long target, Integer[] currentQuadruplet, int quadSize, List<List<Integer>> allQuadruplets) {

        for(int index = startIndex, lastValidIndex = nums.length - currentQuadruplet.length + quadSize ; index <= lastValidIndex ; index++) {
            int currentNum = nums[index];

            if(index != startIndex && currentNum == nums[index - 1]) {
                continue;
            }

            if(quadSize < 3) {
                currentQuadruplet[quadSize] = currentNum;
                getQuadruplets(nums, index + 1, target - currentNum, currentQuadruplet, quadSize + 1, allQuadruplets);
            }
            else {
                if(currentNum == target) {
                    currentQuadruplet[quadSize] = currentNum;
                    List<Integer> newQuad = new ArrayList<Integer>(Arrays.asList(currentQuadruplet));
                    Collections.sort(newQuad);

                    allQuadruplets.add(newQuad);
                }
            }
        }
    }

    /*
    Time : O(n^4)
    Space : O(n^4) space required to maintain the set. nC4 quadruplets are possible.
    Recursion based brute force approach. Exceeds time limit.
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> fourSums = new HashSet<List<Integer>>();

        getQuadruplets(nums, 0, target, new Integer[4], 0, fourSums);

        return new ArrayList<List<Integer>>(fourSums);
    }

    private void getQuadruplets(int[] nums, int startIndex, int target, Integer[] currentQuadruplet, int quadSize, Set<List<Integer>> allQuadruplets) {

        for(int index = startIndex, lastValidIndex = nums.length - currentQuadruplet.length + quadSize ; index <= lastValidIndex ; index++) {
            int currentNum = nums[index];

            if(quadSize < 3) {
                currentQuadruplet[quadSize] = currentNum;
                getQuadruplets(nums, index + 1, target - currentNum, currentQuadruplet, quadSize + 1, allQuadruplets);
            }
            else {
                if(currentNum == target) {
                    currentQuadruplet[quadSize] = currentNum;
                    List<Integer> newQuad = new ArrayList<Integer>(Arrays.asList(currentQuadruplet));
                    Collections.sort(newQuad);

                    allQuadruplets.add(newQuad);
                }
            }
        }
    }

    /*
    Time : O(n^4)
    Space : O(n^4)

    Iterative brute force solution. Exceeds time limit.
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> fourSums = new HashSet<List<Integer>>();

        for(int firstIndex = 0, len = nums.length ; firstIndex < len - 3 ; firstIndex++) {
            int firstNum = nums[firstIndex];
            for(int secondIndex = firstIndex + 1 ; secondIndex < len - 2 ; secondIndex++) {
                int secondNum = nums[secondIndex];
                for(int thirdIndex = secondIndex + 1 ; thirdIndex < len - 1 ; thirdIndex++) {
                    int thirdNum = nums[thirdIndex];
                    for(int fourthIndex = thirdIndex + 1 ; fourthIndex < len ; fourthIndex++) {
                        if(firstNum + secondNum + thirdNum + nums[fourthIndex] == target) {
                            List<Integer> new4Sum = new ArrayList<Integer>(4);
                            new4Sum.add(firstNum);
                            new4Sum.add(secondNum);
                            new4Sum.add(thirdNum);
                            new4Sum.add(nums[fourthIndex]);

                            Collections.sort(new4Sum);
                            fourSums.add(new4Sum);
                        }
                    }
                }
            }
        }

        return new ArrayList<List<Integer>>(fourSums);
    }
}
