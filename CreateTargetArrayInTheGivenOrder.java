public class CreateTargetArrayInTheGivenOrder {

    // Time : O(n), space : O(1).
    public int[] createTargetArray(int[] nums, int[] index) {
        int len = index.length;
        int[] target = new int[len];
        int currentSize = 0;

        for(int arrIndex = 0 ; arrIndex < len ; arrIndex++) {
            int indexToInsert = index[arrIndex];

            if(indexToInsert < currentSize) {
                for(int shiftIndex = currentSize - 1 ; shiftIndex >= indexToInsert ; shiftIndex--) {
                    target[shiftIndex + 1] = target[shiftIndex];
                }
            }

            target[indexToInsert] = nums[arrIndex];
            currentSize++;
        }

        return target;
    }

    // Using arraylist to store intermediate result.
    // Time and space : O(n)
    public int[] createTargetArray(int[] nums, int[] index) {
        int len = index.length;
        List<Integer> target = new ArrayList<Integer>();

        for(int arrIndex = 0 ; arrIndex < len ; arrIndex++) {
            int indexToInsert = index[arrIndex];
            int currentSize = target.size();

            target.add(indexToInsert, nums[arrIndex]);
        }

        len = target.size();
        int[] result = new int[len];

        for(int index1 = 0 ; index1 < len ; index1++) {
            result[index1] = target.get(index1);
        }

        return result;
    }
}
