public class SubtractProductAndSum {
    // Time : O(N) where N is the number of digits in the number n, Space O(1)
    public int subtractProductAndSum(int n) {
        int productSumSubtraction = 0;

        if(n > 0) {
            int product = 1, sum = 0;

            while(n > 0) {
                int currentDigit = n % 10;

                product *= currentDigit;
                sum += currentDigit;

                n /= 10;
            }

            productSumSubtraction = product - sum;
        }

        return productSumSubtraction;
    }
}
