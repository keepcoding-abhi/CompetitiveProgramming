public class Permutations2 {

    public List<List<Integer>> permuteUnique(int[] nums) {

        Map<Integer, Integer> occursRemaining = new HashMap<Integer, Integer>();

        for(int num : nums) {
            occursRemaining.put(num, occursRemaining.getOrDefault(num, 0) + 1);
        }

        List<List<Integer>> allPerms = new LinkedList<List<Integer>>();
        List<Integer> currentPerm = new LinkedList<Integer>();

        generateUniquePerms(allPerms, occursRemaining, currentPerm, nums.length);

        return allPerms;
    }

    private void generateUniquePerms(List<List<Integer>> allPerms, Map<Integer, Integer> occursLeft, List<Integer> currentPerm, int size) {

        if(currentPerm.size() == size) {
            List<Integer> newPerm = new ArrayList<Integer>(size);
            newPerm.addAll(currentPerm);
            allPerms.add(newPerm);
        }
        else {
            for(Map.Entry<Integer, Integer> entry : occursLeft.entrySet()) {
                int num = entry.getKey();
                int countLeft = entry.getValue();

                if(countLeft > 0) {
                    currentPerm.add(num);

                    occursLeft.put(num, countLeft - 1);

                    generateUniquePerms(allPerms, occursLeft, currentPerm, size);

                    occursLeft.put(num, countLeft);

                    currentPerm.remove(currentPerm.size() - 1);
                }
            }
        }
    }
}
