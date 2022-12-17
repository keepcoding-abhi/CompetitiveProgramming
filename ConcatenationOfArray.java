public class ConcatenationOfArray {

    public int[] getConcatenation(int[] nums) {
        int len = nums.length;
        int[] ans = new int[2 * len];

        for(int index = 0 ; index < len ; index++) {

            int currentEl = nums[index];
            ans[index] = currentEl;
            ans[index + len] = currentEl;
        }

        return ans;
    }

}
