public class CountAndSay {

    // 1) SLower approach using linked-list and stack.
    public String countAndSay(int n) {
        LinkedList<Character> currentStr = new LinkedList<Character>();

        currentStr.add('1');

        for(int index = 1 ; index < n ; index++) {

            char currentDigit = currentStr.removeFirst();
            int currentDigitOccurrences = 1;

            LinkedList<Character> newStr = new LinkedList<Character>();
            while(!currentStr.isEmpty()) {
                char nextDigit = currentStr.removeFirst();

                if(nextDigit != currentDigit) {

                    Stack<Character> charsInNumber = new Stack<Character>();
                    while(currentDigitOccurrences != 0) {
                        charsInNumber.push((char)((currentDigitOccurrences % 10) + '0'));
                        currentDigitOccurrences /= 10;
                    }

                    while(!charsInNumber.isEmpty()) {
                        newStr.addLast(charsInNumber.pop());
                    }

                    newStr.addLast(currentDigit);

                    currentDigit = nextDigit;
                    currentDigitOccurrences = 1;
                }
                else {
                    currentDigitOccurrences++;
                }
            }

            Stack<Character> charsInNumber = new Stack<Character>();
            while(currentDigitOccurrences != 0) {
                charsInNumber.push((char)((currentDigitOccurrences % 10) + '0'));
                currentDigitOccurrences /= 10;
            }

            while(!charsInNumber.isEmpty()) {
                newStr.addLast(charsInNumber.pop());
            }

            newStr.addLast(currentDigit);

            currentStr = newStr;
        }


        int resultLength = currentStr.size();
        char[] result = new char[resultLength];

        for(int index = 0 ; index < resultLength ; index++) {
            result[index] = currentStr.remove(0);
        }

        return new String(result);
    }

    //2) Faster approach with StringBUilder.
    class Solution {
        public String countAndSay(int n) {

            StringBuilder currentResult = new StringBuilder();
            currentResult.append('1');

            for(int index = 1 ; index < n ; index++) {

                char currentDigit = currentResult.charAt(0);
                int currentDigitOccurrences = 1;

                StringBuilder nextResult = new StringBuilder();

                for(int currentResultIndex = 1, currentResultLength = currentResult.length() ; currentResultIndex < currentResultLength ; currentResultIndex++) {
                    char nextDigit = currentResult.charAt(currentResultIndex);

                    if(nextDigit != currentDigit) {

                        nextResult.append(currentDigitOccurrences);
                        nextResult.append(currentDigit);

                        currentDigit = nextDigit;
                        currentDigitOccurrences = 1;
                    }
                    else {
                        currentDigitOccurrences++;
                    }
                }

                nextResult.append(currentDigitOccurrences);
                nextResult.append(currentDigit);

                currentResult = nextResult;
            }

            return currentResult.toString();
        }
    }
}


