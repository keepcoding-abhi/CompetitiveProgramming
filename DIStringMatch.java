public class DIStringMatch {
    // Time : O(N), Space : O(1)
    // Fill I's in increasing order and D's in decreasing.
    public int[] diStringMatch(String s) {
        int len = s.length();
        int[] result = new int[len + 1];

        int lowerCounter = 0, upperCounter = len;
        for(int index = 0 ; index < len ; index++) {
            if(s.charAt(index) == 'I') {
                result[index] = lowerCounter++;
            }
            else {
                result[index] = upperCounter--;
            }
        }

        result[len] = lowerCounter;

        return result;
    }
}
