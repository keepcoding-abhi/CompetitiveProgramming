public class NextGreaterElement2 {

    // Stack-based approach. Time complexity = O(n). Space : O(n)
    public int[] nextGreaterElements(int[] nums) {

        int numsLen = nums.length;
        int[] nextGreater = new int[numsLen];
        Deque<Integer> pendingEls = new LinkedList<Integer>();

        for(int index = 0 ; index < numsLen ; index++) {
            int currentEl = nums[index];

            while(!pendingEls.isEmpty() && nums[pendingEls.peek()] < currentEl) {
                nextGreater[pendingEls.pop()] = currentEl;
            }

            pendingEls.push(index);
        }

        for(int index = 0 ; index < numsLen ; index++) {
            int currentEl = nums[index];

            while(!pendingEls.isEmpty() && currentEl > nums[pendingEls.peek()]) {
                nextGreater[pendingEls.pop()] = currentEl;
            }
        }

        while(!pendingEls.isEmpty()) {
            nextGreater[pendingEls.pop()] = -1;
        }

        return nextGreater;
    }

    // Brute Force solution. T : O(n*n)
    public int[] nextGreaterElements(int[] nums) {

        int numsLen = nums.length;
        int[] nextGreater = new int[numsLen];

        for(int index = 0 ; index < numsLen ; index++) {
            int currentEl = nums[index];

            int index1 = (index + 1) % numsLen;
            while(index1 != index) {
                if(nums[index1] > currentEl) {
                    nextGreater[index] = nums[index1];
                    break;
                }
                index1 = (index1 + 1) % numsLen;
            }

            if(index1 == index) {
                nextGreater[index1] = -1;
            }
        }

        return nextGreater;
    }

}
