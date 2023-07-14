import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinations {
    /*
    Time : O(nCk)
    Space : O(k)
    Place evey possible number at each index in the combination.
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new LinkedList<List<Integer>>();
        generateCombinations(1, n, 0, combinations, new int[k]);

        return combinations;
    }

    private void generateCombinations(int startNum, int n, int numbersAdded, List<List<Integer>> combinations, int[] currentCombination) {
        if(numbersAdded == currentCombination.length) {
            List<Integer> newCombination = new ArrayList(currentCombination.length);
            for(int num : currentCombination) {
                newCombination.add(num);
            }
            combinations.add(newCombination);
        }
        else {
            int lastNumberToBeAdded = n - (currentCombination.length - numbersAdded) + 1;
            for(int currentNum = startNum ; currentNum <= lastNumberToBeAdded ; currentNum++) {
                currentCombination[numbersAdded] = currentNum;
                generateCombinations(currentNum + 1, n, numbersAdded + 1, combinations, currentCombination);
            }
        }
    }
}
