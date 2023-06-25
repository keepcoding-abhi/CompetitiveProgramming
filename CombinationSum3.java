public class CombinationSum3 {

    /*
    Time : O(nPk * k)
    nPk : because there are 9 choices for first letter, 8 for second and so on. In all there are nPk combinations
    and O(k) space is required when we convert the array to list.
    Space : O(k)), size of recursion stack is limited by the number of elements that can be added in a combination.
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new LinkedList<List<Integer>>();
        computeCombinations(k, n, combinations, new int[k], 1);

        return combinations;
    }

    private void computeCombinations(int numsToBeAdded, int target, List<List<Integer>> combinations, int[] currentCombination, int startValuesFrom) {

        if((target < startValuesFrom) || startValuesFrom > 9) {
            return;
        }
        else if(numsToBeAdded == 1) {
            if(target <= 9) {
                currentCombination[currentCombination.length - 1] = target;
                List<Integer> newComb = new ArrayList<Integer>(currentCombination.length);
                for(int num : currentCombination) {
                    newComb.add(num);
                }
                combinations.add(newComb);
            }
        }
        else {
            int addNewNumAt = currentCombination.length - numsToBeAdded;
            for(int currentNum = startValuesFrom ; currentNum <= 9 ; currentNum++) {
                currentCombination[addNewNumAt] = currentNum;
                computeCombinations(numsToBeAdded - 1, target - currentNum, combinations, currentCombination, currentNum + 1);
            }
        }
    }
}
