public class ContainerWithMostWater {
}

//1) Brute Force : Time-limit exceeded

class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;

        for(int index1 = 0, length = height.length ; index1 < length ; index1++) {
            int height1 = height[index1];
            for(int index2 = index1 + 1 ; index2 < length ; index2++) {
                int height2 = height[index2];

                int currentContainerHeight = (height1 > height2) ? height2 : height1;
                int currentContainerWidth = index2 - index1;
                int currentContainerArea = currentContainerHeight * currentContainerWidth;

                if(currentContainerArea > maxArea) {
                    maxArea = currentContainerArea;
                }
            }
        }

        return maxArea;
    }
}

//2) Two-pointer approach . Time complexity : O(n). Space Complexity : O(1)

class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;

        int lower = 0, upper = height.length - 1;
        while(lower < upper) {
            int lowerHeight = height[lower];
            int upperHeight = height[upper];

            int currentHeight = (lowerHeight > upperHeight) ? upperHeight : lowerHeight;
            int currentWidth = upper - lower;
            int currentArea = currentHeight * currentWidth;

            if(currentArea > maxArea) {
                maxArea = currentArea;
            }

            if(lowerHeight > upperHeight) {
                upper--;
            }
            else {
                lower++;
            }
        }

        return maxArea;
    }
}