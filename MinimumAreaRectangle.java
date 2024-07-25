import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumAreaRectangle {
}

/*
Time: O(n^2)
Space: O(n)

Store all points in a hashmap. Find the two ends of the diagonal of a rectangle. Now use the hashmap to
find whether the other two ends exist.
 */
class Solution {
    public int minAreaRect(int[][] points) {
        int minRect = 0;

        Map<Integer, Set<Integer>> pointStore = new HashMap<Integer, Set<Integer>>();

        for(int[] point : points) {
            Set<Integer> valsAtX;

            if(pointStore.containsKey(point[0])) {
                valsAtX = pointStore.get(point[0]);
            }
            else {
                valsAtX = new HashSet<Integer>();
                pointStore.put(point[0], valsAtX);
            }

            valsAtX.add(point[1]);
        }

        for(int point1Index = 0 ; point1Index < points.length ; point1Index++) {
            int[] point1 = points[point1Index];
            int x1 = point1[0], y1 = point1[1];

            for(int point2Index = point1Index + 1 ; point2Index < points.length ;
                point2Index++) {
                int[] point2 = points[point2Index];
                int x2 = point2[0], y2 = point2[1];

                if(point1[0] != point2[0] && point1[1] != point2[1]) {
                    if(pointStore.get(point1[0]).contains(point2[1]) &&
                            pointStore.get(point2[0]).contains(point1[1])) {
                        int areaOfCurrRect = Math.abs(x1 - x2) * Math.abs(y1 - y2);
                        if(minRect == 0) {
                            minRect = areaOfCurrRect;
                        }
                        else {
                            minRect = Math.min(minRect, areaOfCurrRect);
                        }
                    }
                }
            }
        }

        return minRect;
    }
}
