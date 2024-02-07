public class SingleNumber {

    /*
    Time: O(n)
    Space: O(1)

    Perform the XOR operation to cancel out a number and its pair.
     */
    public int singleNumber(int[] nums) {
        int resultOfXOR = 0;

        for(int num : nums) {
            resultOfXOR = resultOfXOR ^ num;
        }

        return resultOfXOR;
    }
}
