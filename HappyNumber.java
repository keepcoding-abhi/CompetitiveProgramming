import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    /*
    Time: O(Log(n))
    Space: O(Log(n))

    Obtain the sum of squares of digits and save them in a set. Keep iterating until you come back to a previous element
    in the set or reach the solution.
     */
    public boolean isHappy(int n) {

        int currentNumber = n;
        boolean happyNumber = false;

        Set<Integer> seenNums = new HashSet<Integer>();

        while(currentNumber != 1 && !seenNums.contains(currentNumber)) {
            seenNums.add(currentNumber);
            currentNumber = sumOfSquaredNums(currentNumber);
        }

        if(currentNumber == 1) {
            happyNumber = true;
        }

        return happyNumber;
    }

    private int sumOfSquaredNums(int num) {
        int currentNumber = num;
        int sum = 0;

        while(currentNumber != 0) {
            int currentDigit = currentNumber % 10;

            sum += (currentDigit * currentDigit);

            currentNumber /= 10;
        }

        return sum;
    }

    /*
    Time: O(Log(n)
    Space: O(1)

    Treat the problem as similar to detecting cycle in a linked list.

    Maintain two pointers one goes ahead by 1 step while the other goes ahead by 2 steps.
     */
    public boolean isHappy(int n) {

        boolean happyNumber = false;

        int slowPointer = sumOfSquaredNums(n);
        int fastPointer = sumOfSquaredNums(slowPointer);

        while(fastPointer != 1 && slowPointer != fastPointer) {
            slowPointer = sumOfSquaredNums(slowPointer);
            fastPointer = sumOfSquaredNums(fastPointer);

            if(fastPointer == 1) {
                break;
            }

            fastPointer = sumOfSquaredNums(fastPointer);
        }

        if(fastPointer == 1) {
            happyNumber = true;
        }

        return happyNumber;
    }

    private int sumOfSquaredNums(int num) {
        int currentNumber = num;
        int sum = 0;

        while(currentNumber != 0) {
            int currentDigit = currentNumber % 10;

            sum += (currentDigit * currentDigit);

            currentNumber /= 10;
        }

        return sum;
    }

    /*
    Time: O(Log(n))
    Space: O(1)

    Mathematically it could be discovered that numbers that pass through 4 form a cycle. Others reach 1.
     */
    public boolean isHappy(int n) {

        boolean happyNumber = false;

        int currentNumber = n;

        while(currentNumber != 4 && currentNumber != 1) {
            currentNumber = sumOfSquaredNums(currentNumber);
        }

        if(currentNumber == 1) {
            happyNumber = true;
        }

        return happyNumber;
    }

    private int sumOfSquaredNums(int num) {
        int currentNumber = num;
        int sum = 0;

        while(currentNumber != 0) {
            int currentDigit = currentNumber % 10;

            sum += (currentDigit * currentDigit);

            currentNumber /= 10;
        }

        return sum;
    }
}
