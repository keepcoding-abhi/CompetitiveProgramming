import java.util.LinkedList;
import java.util.Queue;

public class JumpGame3 {

    /*
    Time Complexity: O(n)
    Space Complexity: O(n)

    Begin at the start position and go in either direction. Record the indices to visit in a queue.
    Carry on until you reach the index with 0 value.
     */
    public boolean canReach(int[] arr, int start) {
        boolean[] visitedIndices = new boolean[arr.length];

        Queue<Integer> currentIndices = new LinkedList<Integer>();
        boolean reachable = false;

        currentIndices.add(start);
        visitedIndices[start] = true;

        while(!currentIndices.isEmpty()) {
            int currentIndex = currentIndices.poll();
            int jumpSize = arr[currentIndex];

            int forwardJump = currentIndex + jumpSize;

            if(forwardJump < arr.length) {
                if(arr[forwardJump] == 0) {
                    reachable = true;
                    break;
                }
                else if(visitedIndices[forwardJump] == false) {
                    visitedIndices[forwardJump] = true;
                    currentIndices.add(forwardJump);
                }
            }

            int backwardJump = currentIndex - jumpSize;

            if(backwardJump > -1) {

                if(arr[backwardJump] == 0) {
                    reachable = true;
                    break;
                }
                else if(visitedIndices[backwardJump] == false) {
                    visitedIndices[backwardJump] = true;
                    currentIndices.add(backwardJump);
                }
            }
        }

        return reachable;
    }

    /*
    Recursive version of the above approach consumes much lesser running time
    Time: O(n)
    Space: O(n)
     */

    public boolean canReach(int[] arr, int start) {
        return jump(start, arr, new boolean[arr.length]);
    }

    private boolean jump(int currentIndex, int[] arr, boolean[] visited) {

        boolean found = false;
        if(currentIndex > -1 && currentIndex < arr.length) {

            if(!visited[currentIndex]) {

                visited[currentIndex] = true;

                if(arr[currentIndex] == 0) {
                    found = true;
                }
                else {
                    int jumpSize = arr[currentIndex];

                    found = jump(currentIndex + jumpSize, arr, visited);

                    if(!found) {
                        found = jump(currentIndex - jumpSize, arr, visited);
                    }
                }
            }
        }

        return found;
    }

    /*
    Time Complexity: O(n^2)
    Space: O(n)

    Start at positions having 0 value and see if you can go to the start position.
     */
    class Solution {
        public boolean canReach(int[] arr, int start) {
            boolean[] visitedIndices = new boolean[arr.length];

            Queue<Integer> currentIndices = new LinkedList<Integer>();
            boolean reachable = false;

            for(int index = 0 ; index < arr.length ; index++) {
                if(arr[index] == 0) {
                    currentIndices.add(index);
                    visitedIndices[index] = true;
                }
            }

            while(!currentIndices.isEmpty() && visitedIndices[start] == false) {
                int currentIndex = currentIndices.poll();

                visitReachableNodes(currentIndex, arr, currentIndices, visitedIndices);
            }

            reachable = visitedIndices[start];

            return reachable;
        }

        private void visitReachableNodes(int position, int[] arr, Queue<Integer> currIndices, boolean[] visited) {

            for(int index = 0 ; index < arr.length ; index++) {
                if((index + arr[index] == position) || (index - arr[index] == position)) {
                    if(visited[index] == false) {
                        visited[index] = true;
                        currIndices.add(index);
                    }
                }
            }
        }

    }
}
