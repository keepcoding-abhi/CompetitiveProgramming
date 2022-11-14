public class NextGreaterElement3 {

    // Using stack. T : O(n), S : O(n)
    public int nextGreaterElement(int n) {

        int currentNumber = n;
        Deque<Integer> lowerDigitStack = new LinkedList<Integer>();

        int digitToSwap = -1;
        while(currentNumber != 0) {

            int lastDigit = lowerDigitStack.isEmpty() ? -1 : lowerDigitStack.peek();

            int currentDigit = currentNumber % 10;
            currentNumber /= 10;

            if(currentDigit >= lastDigit) {
                lowerDigitStack.push(currentDigit);
            }
            else {
                digitToSwap = currentDigit;
                break;
            }

        }

        int nextGreaterNumber = -1;
        if(digitToSwap != -1) {

            long resultBuilder = 0, multiplier = 1;
            boolean swapDone = false;
            while(!lowerDigitStack.isEmpty()) {
                int nextDigit = lowerDigitStack.pop();

                if(!swapDone && (lowerDigitStack.isEmpty() || lowerDigitStack.peek() <= digitToSwap)) {
                    int temp = digitToSwap;
                    digitToSwap = nextDigit;
                    nextDigit = temp;
                    swapDone = true;
                }

                resultBuilder += nextDigit * multiplier;
                multiplier *= 10;
            }

            resultBuilder += digitToSwap * multiplier;
            multiplier *= 10;

            resultBuilder += currentNumber * multiplier;

            if(resultBuilder > Integer.MAX_VALUE || resultBuilder < 0) {
                nextGreaterNumber = -1;
            }
            else {
                nextGreaterNumber = (int)resultBuilder;
            }

        }

        return nextGreaterNumber;
    }

}
