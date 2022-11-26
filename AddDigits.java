public class AddDigits {

    // Brute Force solution
    // T : O(n) S : O(1)
    public int addDigits(int num) {

        int sum = getSum(num);

        while((sum / 10) > 0) {
            sum = getSum(sum);
        }

        return sum;
    }

    private int getSum(int num) {
        int sum = 0;

        while(num > 0) {
            sum += (num % 10);
            num /= 10;
        }

        return sum;
    }

    // Constant time solution. SUm of digits is the remainder after dividing the number with 9 for all non-multiples of 9.
    // And 9 for 9's multiples.
    public int addDigits(int num) {

        int digitSum = num % 9;

        if(digitSum == 0 && num != 0) {
            digitSum = 9;
        }
        return digitSum;
    }

}
