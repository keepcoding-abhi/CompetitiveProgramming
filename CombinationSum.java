public class CombinationSum {

    //Using ArrayList instead of LinkedList gave better performance.
    //1) Brute Force solution.
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        LinkedList<Integer> currentCombination = new LinkedList<Integer>();
        List<List<Integer>> result = new LinkedList<List<Integer>>();

        getCombinations(candidates, 0, target, currentCombination, result);

        return result;
    }

    private void getCombinations(int[] candidates, int currentIndex, int target, LinkedList<Integer> currentList, List<List<Integer>> allCombs) {

        if(target == 0) {
            allCombs.add(new LinkedList<Integer>(currentList));
        }
        else if(target > 0) {
            for(int index = currentIndex, totalCandidates = candidates.length ; index < totalCandidates ; index++) {

                int currentCandidate = candidates[index];
                currentList.add(currentCandidate);

                int nextTarget = target - currentCandidate;

                getCombinations(candidates, index, nextTarget, currentList, allCombs);

                currentList.removeLast();
            }
        }
    }

    //2) Optimized version of BruteForce, using sorting.
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        LinkedList<Integer> currentCombination = new LinkedList<Integer>();
        List<List<Integer>> result = new LinkedList<List<Integer>>();

        Arrays.sort(candidates);
        getCombinations(candidates, 0, target, currentCombination, result);

        return result;
    }

    private void getCombinations(int[] candidates, int currentIndex, int target, LinkedList<Integer> currentList, List<List<Integer>> allCombs) {

        if(target == 0) {
            allCombs.add(new LinkedList<Integer>(currentList));
        }
        else if(target > 0) {
            for(int index = currentIndex, totalCandidates = candidates.length ; index < totalCandidates ; index++) {

                int currentCandidate = candidates[index];
                currentList.add(currentCandidate);

                int nextTarget = target - currentCandidate;

                if(nextTarget < 0) {
                    currentList.removeLast();
                    break;
                }
                getCombinations(candidates, index, nextTarget, currentList, allCombs);

                currentList.removeLast();
            }
        }
    }

}
