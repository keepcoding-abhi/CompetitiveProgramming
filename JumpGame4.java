import java.util.*;

public class JumpGame4 {

    /*
    Time: O(n)
    Space: O(n)

    Perform BFS starting from the first position. At each level store the visitable positions in a list and
    the number of levels needed to reach the end position will be the number of steps required.
     */
    public int minJumps(int[] arr) {
        int stepsTaken = 0;

        if(arr.length > 0) {
            Map<Integer, List<Integer>> elAndPosition = getElAndPositions(arr);

            Queue<Integer> positionsInCurrentStep = new LinkedList<Integer>();

            boolean[] visited = new boolean[arr.length];

            positionsInCurrentStep.add(0);
            visited[0] = true;

            while(!visited[arr.length - 1]) {
                Queue<Integer> positionsInNextStep = new LinkedList<Integer>();

                for(int currentStep : positionsInCurrentStep) {

                    int nextStep = currentStep + 1;

                    if(nextStep == arr.length - 1) {
                        visited[nextStep] = true;
                        break;
                    }
                    else if(nextStep < arr.length && !visited[nextStep]) {
                        visited[nextStep] = true;
                        positionsInNextStep.add(nextStep);
                    }

                    nextStep = currentStep - 1;

                    if(nextStep > -1 && !visited[nextStep]) {
                        visited[nextStep] = true;
                        positionsInNextStep.add(nextStep);
                    }

                    List<Integer> sameValuedNeighbors = elAndPosition.get(arr[currentStep]);
                    for(int currentNeighbor : sameValuedNeighbors) {
                        if(!visited[currentNeighbor]) {
                            visited[currentNeighbor] = true;

                            positionsInNextStep.add(currentNeighbor);

                            if(currentNeighbor == arr.length - 1) {
                                break;
                            }
                        }
                    }

                    sameValuedNeighbors.clear();

                    if(visited[arr.length - 1] == true) {
                        break;
                    }
                }

                stepsTaken++;
                positionsInCurrentStep = positionsInNextStep;
            }
        }

        return stepsTaken;
    }

    private Map<Integer, List<Integer>> getElAndPositions(int[] arr) {

        Map<Integer, List<Integer>> elAndPosition = new HashMap<Integer, List<Integer>>();

        for(int index = 0 ; index < arr.length ; index++) {
            elAndPosition.computeIfAbsent(arr[index], v -> new LinkedList<Integer>()).add(index);
        }

        return elAndPosition;
    }

    /*
    Time: O(n)
    Space: O(n)

    Starting the BFS from last position takes less time
     */
    public int minJumps(int[] arr) {
        int stepsTaken = 0;

        if(arr.length > 0) {
            Map<Integer, List<Integer>> elAndPosition = getElAndPositions(arr);

            Queue<Integer> positionsInCurrentStep = new LinkedList<Integer>();

            boolean[] visited = new boolean[arr.length];

            positionsInCurrentStep.add(arr.length - 1);
            visited[arr.length - 1] = true;

            while(!visited[0]) {
                Queue<Integer> positionsInNextStep = new LinkedList<Integer>();

                for(int currentStep : positionsInCurrentStep) {

                    int nextStep = currentStep + 1;

                    if(nextStep < arr.length && !visited[nextStep]) {
                        visited[nextStep] = true;
                        positionsInNextStep.add(nextStep);
                    }

                    nextStep = currentStep - 1;

                    if(nextStep == 0) {
                        visited[nextStep] = true;
                        break;
                    }
                    else if(!visited[nextStep]) {
                        visited[nextStep] = true;
                        positionsInNextStep.add(nextStep);
                    }

                    List<Integer> sameValuedNeighbors = elAndPosition.get(arr[currentStep]);
                    for(int currentNeighbor : sameValuedNeighbors) {
                        if(!visited[currentNeighbor]) {
                            visited[currentNeighbor] = true;

                            positionsInNextStep.add(currentNeighbor);

                            if(currentNeighbor == 0) {
                                break;
                            }
                        }
                    }

                    sameValuedNeighbors.clear();

                    if(visited[0] == true) {
                        break;
                    }
                }

                stepsTaken++;
                positionsInCurrentStep = positionsInNextStep;
            }
        }

        return stepsTaken;
    }

    private Map<Integer, List<Integer>> getElAndPositions(int[] arr) {

        Map<Integer, List<Integer>> elAndPosition = new HashMap<Integer, List<Integer>>();

        for(int index = 0 ; index < arr.length ; index++) {
            elAndPosition.computeIfAbsent(arr[index], v -> new LinkedList<Integer>()).add(index);
        }

        return elAndPosition;
    }
}
