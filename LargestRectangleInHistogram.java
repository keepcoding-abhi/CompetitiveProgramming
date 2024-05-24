import java.util.Deque;
import java.util.LinkedList;

public class LargestRectangleInHistogram {
}

/*
Time: O(n)
Space: O(n)

Use a stack to store the previous elements which will act as limiting bars, if the next bar
will act as limiting bar for any of the elements on stack, pop all those elements and determine the
area of rectangle created by each of them. The width of rectangle starts at the bar which is limiting the height
of the bar popped on stack (i.e. the previous bar on stack) and the current bar (bar limiting the height from right side)
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> prevElsDecreasingOrder = new LinkedList<Integer>();
        int largestArea = 0;

        for(int index = 0, nHeights = heights.length ; index < nHeights ; index++) {
            int currentHeight = heights[index];

            while(!prevElsDecreasingOrder.isEmpty() && heights[prevElsDecreasingOrder.peek()] > currentHeight) {
                int areaAfterPop = calculateAreaAfterBarIsPopped(prevElsDecreasingOrder, index, heights);
                largestArea = Math.max(largestArea, areaAfterPop);
            }

            prevElsDecreasingOrder.push(index);
        }

        while(!prevElsDecreasingOrder.isEmpty()) {
            int areaAfterPop = calculateAreaAfterBarIsPopped(prevElsDecreasingOrder, heights.length, heights);
            largestArea = Math.max(largestArea, areaAfterPop);
        }

        return largestArea;
    }

    private int calculateAreaAfterBarIsPopped(Deque<Integer> prevShorterEls, int currentIndex, int[] heights) {
        int poppedIndex = prevShorterEls.pop();
        int rectArea = 0;

        if(prevShorterEls.isEmpty()) {
            rectArea = heights[poppedIndex] * currentIndex;
        }
        else {
            rectArea = heights[poppedIndex] * (currentIndex - prevShorterEls.peek() - 1);
        }

        return rectArea;
    }


}

/*
Time: O(n^2)
Space: O(n)

Divide the array based on minimum element. Since minimum element restricts the answer, the
rectangle either includes the minimum index, or consists of rectangles from either side of it.
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        return findLargestRect(heights, 0, heights.length - 1);
    }

    private int findLargestRect(int[] heights, int left, int right) {
        int maxRectArea = 0;

        if(left < right) {
            int minHeightIndex = getMinHeightIndex(heights, left, right);

            int areaWithMinIndex = heights[minHeightIndex] * (right - left + 1);
            int maxAreaFromLeft = findLargestRect(heights, left, minHeightIndex - 1);
            int maxAreaFromRight = findLargestRect(heights, minHeightIndex + 1, right);

            maxRectArea = Math.max(Math.max(areaWithMinIndex, maxAreaFromLeft), maxAreaFromRight);
        }
        else if(left==right && left < heights.length && left > -1) {
            maxRectArea = heights[left];
        }

        return maxRectArea;
    }

    private int getMinHeightIndex(int[] heights, int left, int right) {
        int minIndex = left, minEl = heights[left];

        for(int index = left + 1 ; index <= right ; index++) {
            int currentEl = heights[index];

            if(currentEl < minEl) {
                minEl = currentEl;
                minIndex = index;
            }
        }

        return minIndex;
    }
}

/*
Time: O(n^2)
Space: O(1)

Fix one end of the rectangle and try different values for the other end
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        int nBars = heights.length;
        int largestRectangleArea = 0;

        for(int leftMostBar = 0 ; leftMostBar < nBars ; leftMostBar++) {
            int minHeight = heights[leftMostBar];
            for(int rightMostBar = leftMostBar ; rightMostBar < nBars ; rightMostBar++) {
                minHeight = Math.min(minHeight, heights[rightMostBar]);

                int currentArea = minHeight * (rightMostBar - leftMostBar + 1);
                largestRectangleArea = Math.max(largestRectangleArea, currentArea);
            }
        }

        return largestRectangleArea;
    }
}

/*
Time: O(n^2)
Space: O(n)

Try each possible width one by one and use a sliding window along with a monotonic queue to find the largest area
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        int nBars = heights.length;
        int largestRectangleArea = 0;

        for(int rectWidth = 1 ; rectWidth <= nBars ; rectWidth++) {
            int largestRectangleWithCurrentWidth = getLargestRectWithWidth(heights, rectWidth);
            largestRectangleArea = Math.max(largestRectangleArea, largestRectangleWithCurrentWidth);
        }

        return largestRectangleArea;
    }

    private int getLargestRectWithWidth(int[] heights, int width) {
        int leftPtr = 0, rightPtr = width, nHeights = heights.length, largestArea = 0;
        Deque<Integer> prevSmallerIndices = new LinkedList<Integer>();

        for(int index = 0 ; index < width ; index++) {
            int currentHeight = heights[index];

            while(!prevSmallerIndices.isEmpty() && heights[prevSmallerIndices.peekLast()] >= currentHeight) {
                prevSmallerIndices.removeLast();
            }

            prevSmallerIndices.addLast(index);
        }

        largestArea = width * heights[prevSmallerIndices.peekFirst()];

        while(rightPtr < nHeights) {
            leftPtr++;
            int currentHeight = heights[rightPtr];

            while(!prevSmallerIndices.isEmpty() && heights[prevSmallerIndices.peekLast()] >= currentHeight) {
                prevSmallerIndices.removeLast();
            }

            prevSmallerIndices.addLast(rightPtr);

            while(prevSmallerIndices.peekFirst() < leftPtr) {
                prevSmallerIndices.removeFirst();
            }

            int currentArea = heights[prevSmallerIndices.peekFirst()] * width;
            largestArea = Math.max(largestArea, currentArea);

            rightPtr++;
        }

        return largestArea;
    }
}
