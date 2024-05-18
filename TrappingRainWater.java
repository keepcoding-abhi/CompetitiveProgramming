import java.util.Deque;
import java.util.LinkedList;

public class TrappingRainWater {

    // Time : O(n^2)
    // Space : O(1)
    // The amount of water trapped by each elevation map will be the area right above it, that is width will be
    // 1 unit. The height is restricted by buildings on either side of it. The maximum possible height would be
    // the minimum height of the buildings on either side.
    public int trap(int[] height) {
        int trappedWater = 0;

        for(int index = 0, len = height.length ; index < len ; index++) {
            int currentHeight = height[index];

            int maxLeftBuildingHeight = 0;
            for(int leftBuildingIndex = index ; leftBuildingIndex > -1 ; leftBuildingIndex--) {
                maxLeftBuildingHeight = Math.max(maxLeftBuildingHeight, height[leftBuildingIndex]);
            }

            int maxRightBuildingHeight = 0;
            for(int rightBuildingIndex = index ; rightBuildingIndex < len ; rightBuildingIndex++) {
                maxRightBuildingHeight = Math.max(maxRightBuildingHeight, height[rightBuildingIndex]);
            }

            trappedWater += (Math.min(maxLeftBuildingHeight, maxRightBuildingHeight) - currentHeight);
        }

        return trappedWater;
    }

    /*
    Time : O(n)
    Space : O(n)
    Saving the maximum height of all building towards the left and right of current building in an array and
    following the previous approach.
     */
    public int trap(int[] height) {

        int waterAccumulated = 0;

        if(height.length > 2) {
            int[] prefixHeights = new int[height.length];
            int[] suffixHeights = new int[height.length];

            prefixHeights[1] = height[0];
            suffixHeights[height.length - 2] = height[height.length - 1];

            for(int index = height.length - 3 ; index > -1 ; index--) {
                suffixHeights[index] = Math.max(height[index + 1], suffixHeights[index + 1]);
            }

            for(int index = 2 ; index < height.length ; index++) {
                prefixHeights[index] = Math.max(height[index - 1], prefixHeights[index - 1]);
            }

            for(int index = 1 ; index < height.length - 1 ; index++) {
                waterAccumulated += (Math.max(0, Math.min(prefixHeights[index], suffixHeights[index]) - height[index]));
            }
        }

        return waterAccumulated;
    }

    /*
    Time : O(n)
    Space : O(n)
    Using a stack to keep track of the tall bars on left side, right side bars are the ones we visit in each iteration.
    At each iteration we add the amount of water between left side bar and right side bar.
     */
    public int trap(int[] height) {
        Deque<Integer> prevBars = new LinkedList<Integer>();
        int waterAccumulated = 0;

        for(int currIndex = 0 ; currIndex < height.length ; currIndex++) {

            int newBarHeight = height[currIndex];
            while((!prevBars.isEmpty()) && (height[prevBars.peekFirst()] < newBarHeight)) {
                int removedBar = prevBars.removeFirst();

                if(prevBars.isEmpty()) {
                    break;
                }

                int heightOfWater = Math.min(height[prevBars.peekFirst()], newBarHeight) - height[removedBar];
                int width = currIndex - prevBars.peekFirst() - 1;

                waterAccumulated += (heightOfWater * width);
            }

            prevBars.addFirst(currIndex);
        }

        return waterAccumulated;
    }

    /*
    Time : O(n)
    Space : O(1)
    When a tall bar is encountered on the left side, we can safely add bars from the right side which are
    shorter than this bar and vice versa.

    While rightMax is greater than leftMax, leftMax acts as a threshold and determines the level of water.
     */
    public int trap(int[] height) {
        int leftIndex = 0, rightIndex = height.length - 1;
        int waterAccumulated = 0;
        int leftMax = height[0], rightMax = height[height.length - 1];

        while(leftIndex <= rightIndex) {
            if(leftMax <= rightMax) {
                while((leftIndex <= rightIndex) && (height[leftIndex] <= leftMax)) {
                    waterAccumulated = waterAccumulated + (leftMax - height[leftIndex]);
                    leftIndex++;
                }

                if(leftIndex <= rightIndex) {
                    leftMax = height[leftIndex];
                }
            }
            else {
                while(leftIndex <= rightIndex && height[rightIndex] <= rightMax) {
                    waterAccumulated = waterAccumulated + (rightMax - height[rightIndex]);
                    rightIndex--;
                }

                if(leftIndex <= rightIndex) {
                    rightMax = height[rightIndex];
                }
            }
        }

        return waterAccumulated;
    }

    /*
    Time : O(n)
    Space : O(1)
    Processing the smaller height between left and right ends first.
     */
    public int trap(int[] height) {
        int leftPtr = 0, rightPtr = height.length - 1;
        int trappedWater = 0;
        int leftMax = height[leftPtr], rightMax = height[rightPtr];

        while(leftPtr < rightPtr) {
            int leftHeight = height[leftPtr], rightHeight = height[rightPtr];
            leftMax = Math.max(leftMax, leftHeight);
            rightMax = Math.max(rightMax, rightHeight);

            if(leftHeight <= rightHeight) {
                trappedWater += (Math.min(leftMax, rightMax) - leftHeight);
                leftPtr++;
            }
            else {
                trappedWater += (Math.min(leftMax, rightMax) - rightHeight);
                rightPtr--;
            }
        }

        return trappedWater;
    }
}
