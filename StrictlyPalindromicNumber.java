public class StrictlyPalindromicNumber {

    // Time complexity : Log_{2} (n) + Log_{3} (n) + .. + Log_{n - 2} (n)
    // Space complexity is same as time complexity, since in each iteration we are adding one unit of memory.
    public boolean isStrictlyPalindromic(int n) {

        boolean isStrictlyPalin = true;

        for(int currentBase = 2 ; currentBase <= n - 2 ; currentBase++) {

            Deque<Integer> numberInCurrentBase = new LinkedList<Integer>();

            int currentNumber = n;
            while(currentNumber > 0) {
                numberInCurrentBase.addFirst(currentNumber % currentBase);
                currentNumber /= currentBase;
            }

            if(!isPalindrome(numberInCurrentBase)) {
                isStrictlyPalin = false;
                break;
            }
        }

        return isStrictlyPalin;
    }

    private boolean isPalindrome(Deque<Integer> digits) {

        boolean isPalin = true;

        while(digits.size() > 1) {

            if(digits.removeFirst() != digits.removeLast()) {
                isPalin = false;
                break;
            }
        }

        return isPalin;
    }
}
