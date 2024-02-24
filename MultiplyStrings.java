public class MultiplyStrings {

    /*
    Time: O(m*n)
    Space: O(m + n)

    Multiply num1 with each digit from num2 and move the result to left by appropriate places.
    Store the results in a String.
     */
    public String multiply(String num1, String num2) {

        String result;

        if((num1.length() == 1 && num1.charAt(0) == '0') || (num2.length() == 1 && num2.charAt(0) == '0')) {
            result = "0";
        }
        else {
            StringBuilder intermediateResult = new StringBuilder();
            int offset = 0;

            for(int index = num2.length() - 1 ; index > -1 ; index--) {

                int num2Digit = (num2.charAt(index) - '0');
                mul(intermediateResult, num1, num2Digit, offset);
                offset++;
            }

            // int resLen = intermediateResult.size();
            // char[] resultBuilder = new char[resLen];

            // for(int resIndex = 0 ; resIndex < resLen ; resIndex++) {
            //     resultBuilder[resIndex] = (char)(intermediateResult.get(resLen - resIndex - 1) + '0');
            // }

            intermediateResult = intermediateResult.reverse();
            result = intermediateResult.toString();
        }

        return result;
    }

    private void mul(StringBuilder result, String num1, int num2Digit, int offset) {

        int carry = 0, num1Len = num1.length();

        int currentIndex = offset;

        for(int num1Index = num1Len - 1 ; num1Index > -1 ; num1Index--) {
            int num1Digit = num1.charAt(num1Index) - '0';

            int currentSum = (num1Digit * num2Digit);
            int prevVal;

            if(currentIndex < result.length()) {
                prevVal = result.charAt(currentIndex) - '0';
            }
            else {
                prevVal = 0;
                result.append('0');
            }

            currentSum += (prevVal + carry);
            result.setCharAt(currentIndex, (char)((currentSum % 10) + '0'));

            carry = currentSum / 10;
            currentIndex++;
        }

        while(carry != 0) {
            result.append((char)((carry % 10) + '0'));
            carry /= 10;
            currentIndex++;
        }
    }
}
