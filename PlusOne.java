public class PlusOne {
}

// Slower solution

class Solution {
    public int[] plusOne(int[] digits) {
        int digitLength = digits.length;

        int currentIndex = digitLength - 1;
        int carry = 1;

        while(currentIndex > -1 && carry != 0) {

            int currentDigit = digits[currentIndex];
            int currentSum = currentDigit + carry;

            int quotient = currentSum / 10;

            digits[currentIndex] = currentSum - (quotient * 10);

            currentIndex--;
            carry = quotient;
        }

        if(currentIndex == -1 && carry != 0) {
            int[] newDigits = new int[digitLength + 1];
            newDigits[0] = carry;

            for(int index = 0 ; index < digitLength ; index++) {
                newDigits[index + 1] = digits[index];
            }

            digits = newDigits;
        }

        return digits;
    }
}

// Faster solution

class Solution {
    public int[] plusOne(int[] digits) {

        int origDigitLength = digits.length;
        int index = origDigitLength - 1;

        for( ; index > -1 ; index--) {
            int currentDigit = digits[index];

            if(currentDigit == 9) {
                digits[index] = 0;
            }
            else {
                digits[index] = currentDigit + 1;
                break;
            }
        }

        if(index == -1) {
            int newDigits[] = new int[origDigitLength + 1];
            newDigits[0] = 1;
            digits = newDigits;
        }

        return digits;
    }
}