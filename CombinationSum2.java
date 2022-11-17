public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);

        List<List<Integer>> allCombs = new LinkedList<List<Integer>>();
        List<Integer> currentComb = new LinkedList<Integer>();

        generateCombinations(allCombs, candidates, currentComb, target, 0);

        return allCombs;
    }

    private void generateCombinations(List<List<Integer>> allCombs, int[] candidates, List<Integer> currentComb, int target, int startIndex) {

        if(target == 0) {
            List<Integer> newComb = new ArrayList<Integer>(currentComb.size());
            newComb.addAll(currentComb);
            allCombs.add(newComb);
        }
        else if(target > 0) {

            for(int index = startIndex ; index < candidates.length ; index++) {

                if(index > startIndex && candidates[index] == candidates[index - 1]) {
                    continue;
                }

                currentComb.add(candidates[index]);

                generateCombinations(allCombs, candidates, currentComb, target - candidates[index], index + 1);

                currentComb.remove(currentComb.size() - 1);
            }
        }
    }

}
