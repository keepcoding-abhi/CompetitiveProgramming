import java.util.PriorityQueue;

public class MedianStream {
    /*
    Time: O(n*Log(n))
    Space: O(n)

    Use two heaps to store the upper and lower halves of the two arrays
     */
    int[] findMedian(int[] arr) {
        int[] medians = new int[arr.length];

        if(arr.length == 1) {
            medians[0] = arr[0];
        }
        else if(arr.length == 2) {
            medians[0] = arr[0];
            medians[1] = (arr[0] + arr[1]) / 2;
        }
        else {
            PriorityQueue<Integer> lowerHalf = new PriorityQueue<Integer>((Integer a, Integer b) -> {
                return b - a;
            });

            PriorityQueue<Integer> upperHalf = new PriorityQueue<Integer>();

            medians[0] = arr[0];
            medians[1] = (arr[0] + arr[1]) / 2;

            lowerHalf.add(arr[0]);
            lowerHalf.add(arr[1]);

            upperHalf.add(lowerHalf.poll());

            for(int index = 2 ; index < arr.length ; index++) {
                int currNum = arr[index];

                lowerHalf.add(currNum);

                if(lowerHalf.peek() > upperHalf.peek()) {
                    upperHalf.add(lowerHalf.poll());
                    lowerHalf.add(upperHalf.poll());
                }

                if(lowerHalf.size() == upperHalf.size() + 2) {
                    upperHalf.add(lowerHalf.poll());
                }

                if(lowerHalf.size() == upperHalf.size() + 1) {
                    medians[index] = lowerHalf.peek();
                }
                else {
                    medians[index] = (lowerHalf.peek() + upperHalf.peek()) / 2;
                }
            }
        }

        return medians;
    }
}
