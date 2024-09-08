import java.util.Arrays;
import java.util.PriorityQueue;

public class LargestTripletProducts {
    /*
    Time: O(n)
    Space: O(1)

    Maintain a heap to keep track of the three largest numbers seen up to a certain point in the array.
     */
    int[] findMaxProduct(int[] arr) {
        // Write your code here
        int[] result = new int[arr.length];

        if(arr != null && arr.length > 2) {
            result[0] = -1;
            result[1] = -1;
            int currProduct = arr[0] * arr[1] * arr[2];
            result[2] = currProduct;

            PriorityQueue<Integer> largestTriplet = new PriorityQueue<Integer>(3);
            largestTriplet.add(arr[0]);
            largestTriplet.add(arr[1]);
            largestTriplet.add(arr[2]);

            for(int index = 3 ; index < arr.length ; index++) {
                int currNum = arr[index];
                if(currNum > largestTriplet.peek()) {
                    int removedVal = largestTriplet.poll();
                    largestTriplet.add(currNum);
                    currProduct = currProduct / removedVal;

                    currProduct *= currNum;
                }

                result[index] = currProduct;
            }
        }
        else {
            Arrays.fill(result, -1);
        }

        return result;
    }
}
