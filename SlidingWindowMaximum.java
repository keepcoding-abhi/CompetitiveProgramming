import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {
}

/*
Time: O(n)
Space: O(k)

Using a monotonic queue to keep track of the last k elements in descending order
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int numberOfWindows = nums.length - k + 1;
        int[] windowMaxs = new int[numberOfWindows];

        Deque<Integer> prevElements = new LinkedList<Integer>();

        for(int index = 0 ; index < nums.length ; index++) {
            int currentNum = nums[index];

            while(!prevElements.isEmpty() && nums[prevElements.peekLast()] <= currentNum) {
                prevElements.removeLast();
            }

            prevElements.addLast(index);

            if(index >= k - 1) {

                int leastAcceptableIndex = (index - k) + 1;

                while(prevElements.peekFirst() < leastAcceptableIndex) {
                    prevElements.removeFirst();
                }

                windowMaxs[(index - k) + 1] = nums[prevElements.peekFirst()];
            }
        }

        return windowMaxs;
    }
}

/*
Time: O(n * Log(k))
Space: O(k)

Maintining a max heap to keep track of the largest number in the current window.
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> window = new PriorityQueue<Integer>(k, (Integer a, Integer b) -> {
            return b - a;
        });

        int numberOfWindows = nums.length - k + 1;
        int[] result = new int[numberOfWindows];

        for(int index = 0 ; index < k ; index++) {
            window.add(nums[index]);
        }

        result[0] = window.peek();

        for(int index = k ; index < nums.length ; index++) {
            window.remove(nums[index - k]);
            window.add(nums[index]);

            result[index - k + 1] = window.peek();
        }

        return result;
    }
}

/*
Time: O(n^2)
Space: O(1)

Brute force solution. Assess each window and find the maximum element within it
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int lastWindowIndex = nums.length - k + 1;
        int[] result = new int[lastWindowIndex];

        for(int index = 0 ; index < lastWindowIndex ; index++) {
            result[index] = findMax(nums, index, index + k);
        }

        return result;
    }

    private int findMax(int[] nums, int start, int end) {
        int max = nums[start];

        for(int index = start + 1 ; index < end ; index++) {
            max = Math.max(nums[index], max);
        }

        return max;
    }
}
