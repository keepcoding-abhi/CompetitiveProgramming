public class BuildArrayFromPermutation {

    // Time : O(n), space : O(1)
    // After first loop each number in nums[index] will contain original number plus new number * len
    // In the second loop we remove the original number since it's < len, therefore, original number / len will be zero.
    // % len is done to get the original number from nums[nums[index]], since any newly added value is multiplied by len
    // it'll get removed in % len operation.
    public int[] buildArray(int[] nums) {
        int len = nums.length;

        for(int index = 0 ; index < len ; index++) {
            nums[index] = nums[index] + len * (nums[nums[index]] % len);
        }

        for(int index = 0 ; index < len ; index++) {
            nums[index] = nums[index] / len;
        }

        return nums;
    }

    // Time and space : O(n).
    public int[] buildArray(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        for(int index = 0 ; index < len ; index++) {
            result[index] = nums[nums[index]];
        }

        return result;
    }
}
