public class FindAnagramMappings {

    // Linear time and space solution.
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        Map<Integer, List<Integer>> elementPositions = new HashMap<Integer, List<Integer>>();

        int len = nums2.length;
        for(int index = 0 ; index < len ; index++) {

            List<Integer> occurrences;
            int currentEl = nums2[index];
            if(elementPositions.containsKey(nums2[index])) {
                occurrences = elementPositions.get(currentEl);
            }
            else {
                occurrences = new LinkedList<Integer>();
                elementPositions.put(currentEl, occurrences);
            }
            occurrences.add(index);
        }

        int[] mapping = new int[len];

        for(int index = 0 ; index < len ; index++) {
            int currentEl = nums1[index];

            List<Integer> occurrences = elementPositions.get(currentEl);

            mapping[index] = occurrences.remove(0);
        }

        return mapping;
    }

}
