import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CountTriplets1 {

    /*
    Time : O(n)
    Space : O(n)
    Maintaining two separate tables to ensure that a triplet gets counted exactly once.
     */
    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> numbersYetToBeSeen = new HashMap<Long, Long>();
        int arrLen = arr.size();

        for(int index = 0 ; index < arrLen ; index++) {
            long currentNum = arr.get(index);
            numbersYetToBeSeen.put(currentNum, numbersYetToBeSeen.getOrDefault(currentNum, 0l) + 1);
        }

        long counts = 0;
        Map<Long, Long> numbersAlreadySeen = new HashMap<Long, Long>();

        for(int index = 0 ; index < arrLen ; index++) {

            long secondNum = arr.get(index);
            System.out.println("Second" + secondNum);
            if(((secondNum / r) * r) == secondNum) {
                long firstNum = secondNum / r;
                long thirdNum = secondNum * r;

                numbersYetToBeSeen.put(secondNum, numbersYetToBeSeen.get(secondNum) - 1l);

                if(numbersAlreadySeen.containsKey(firstNum) && numbersYetToBeSeen.containsKey(thirdNum)) {
                    counts += (numbersAlreadySeen.get(firstNum) * numbersYetToBeSeen.get(thirdNum));
                }

                numbersAlreadySeen.put(secondNum, numbersAlreadySeen.getOrDefault(secondNum, 0l) + 1l);
            }
            else {
                numbersYetToBeSeen.put(secondNum, numbersYetToBeSeen.get(secondNum) - 1l);

                numbersAlreadySeen.put(secondNum, numbersAlreadySeen.getOrDefault(secondNum, 0l) + 1l);
            }

        }

        return counts;
    }

    /*
    Time : O(n^3)
    Space : O(n)
    Assume every number is the first one in triplet and determine the second and third number using the ratio.
    Save the indices of numbers and check whether each index in the triplet is larger than previous ones.
     */
    static long countTriplets(List<Long> arr, long r) {
        Map<Long, List<Integer>> positions = new HashMap<Long, List<Integer>>();
        int arrLen = arr.size();

        for(int index = 0 ; index < arrLen ; index++) {
            long currentNum = arr.get(index);
            List<Integer> positionsOfCurrent;

            if(positions.containsKey(currentNum)) {
                positionsOfCurrent = positions.get(currentNum);
            }
            else {
                positionsOfCurrent = new LinkedList<Integer>();
                positions.put(currentNum, positionsOfCurrent);
            }

            positionsOfCurrent.add(index);
        }

        long counts = 0;

        for(int index = 0 ; index < arrLen ; index++) {
            long firstNum = arr.get(index);
            long secondNum = firstNum * r;
            long thirdNum = secondNum * r;

            if(positions.containsKey(secondNum) && positions.containsKey(thirdNum)) {
                List<Integer> secondNumIndices = positions.get(secondNum);
                List<Integer> thirdNumIndices = positions.get(thirdNum);

                for(int currentSecondIndex : secondNumIndices) {

                    if(currentSecondIndex > index) {
                        for(int currentThirdIndex : thirdNumIndices) {
                            if(currentThirdIndex > currentSecondIndex) {
                                counts++;
                            }
                        }
                    }
                }
            }
        }

        return counts;
    }
}
