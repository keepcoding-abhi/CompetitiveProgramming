public class LongestConsecutiveSequence {

    // Storing the length of consecutive sequence beginning from an element in a hashmap.
    public int longestConsecutive(int[] nums) {

        Map<Integer, Integer> elsInArray = new HashMap<Integer, Integer>();

        for(int num : nums) {
            elsInArray.put(num, 0);
        }

        int longestSeq = 0;
        for(int index = 0, len = nums.length ; index < len ; index++) {
            int currentSeq = 1;

            currentSeq = maxSeqLen(elsInArray, nums[index]);

            longestSeq = Math.max(longestSeq, currentSeq);
        }

        return longestSeq;
    }

    private int maxSeqLen(Map<Integer, Integer> prevSeqs, int currentEl) {

        int knownLength = prevSeqs.getOrDefault(currentEl, -1);

        if(knownLength == 0) {
            knownLength = 1 + maxSeqLen(prevSeqs, currentEl + 1);
            prevSeqs.put(currentEl, knownLength);
        }
        else if(knownLength == -1) {
            knownLength = 0;
        }

        return knownLength;
    }

    // Counting each sequence only from the first element in the sequence. This is done by ensuring that the previous element is not
    // present in the array.
    public int longestConsecutive(int[] nums) {

        int longestSeq = 0;
        Set<Integer> prevSeen = new HashSet<Integer>();
        for(int num : nums) {
            prevSeen.add(num);
        }

        for(int index = 0, len = nums.length ; index < len ; index++) {

            int currentEl = nums[index];

            if(!prevSeen.contains(currentEl - 1)) {
                int nextInSeq = currentEl + 1;

                while(prevSeen.contains(nextInSeq)) {
                    nextInSeq++;
                }

                longestSeq = Math.max(longestSeq, nextInSeq - currentEl);
            }
        }

        return longestSeq;
    }

    // Sorting the array first.
    public int longestConsecutive(int[] nums) {

        int longestSeq = 0;
        if(nums.length > 0) {
            Arrays.sort(nums);

            longestSeq = 1;
            int currentSeq = 1;
            for(int index = 1, len = nums.length ; index < len ; index++) {

                if(nums[index] == nums[index - 1] + 1) {
                    currentSeq++;
                }
                else if(nums[index] != nums[index - 1]) {
                    longestSeq = Math.max(longestSeq, currentSeq);
                    currentSeq = 1;
                }
            }
            longestSeq = Math.max(longestSeq, currentSeq);
        }

        return longestSeq;
    }

    // Brute force solution. checking whether consecutive elements of the current element exist in the array
    // using linear search. T : O(n^3)
    public int longestConsecutive(int[] nums) {

        int longestSeq = 0;
        for(int index = 0, len = nums.length ; index < len ; index++) {
            int currentSeq = 1;
            int currentEl = nums[index] + 1;

            while(find(nums, currentEl)) {
                currentEl++;
                currentSeq++;
            }

            longestSeq = Math.max(longestSeq, currentSeq);
        }

        return longestSeq;
    }

    private boolean find(int[] arr, int target) {

        boolean found = false;
        for(int index = 0, len = arr.length ; index < len ; index++) {
            if(arr[index] == target) {
                found = true;
                break;
            }
        }

        return found;
    }

}
