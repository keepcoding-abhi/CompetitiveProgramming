public class ReverseInteger {
}

//1. Using % operator as a modulus operator

class Solution {
    public int reverse(int x) {
        int reversedNumber = 0;

        int originalNumber = x;
        boolean flipSign = false;

        if(x < 0) {
            originalNumber *= -1;
            flipSign = true;
        }

        while(originalNumber > 0) {
            long newRev = (reversedNumber * 10l) + (originalNumber % 10);

            if(newRev == (int)newRev) {
                reversedNumber = (int)newRev;
            }
            else {
                reversedNumber = 0;
                break;
            }

            originalNumber = originalNumber/10;
        }

        if(flipSign) {
            reversedNumber *= -1;
        }
        return reversedNumber;
    }
}

//2. Using % as a remainder

class Solution {
    public int reverse(int x) {
        int reversedNumber = 0;

        while(x != 0) {
            long newRev = (reversedNumber * 10l) + (x % 10);

            if(newRev == (int)newRev) {
                reversedNumber = (int)newRev;
            }
            else {
                reversedNumber = 0;
                break;
            }

            x = x/10;
        }

        return reversedNumber;
    }
}

//3) Without using long datatype
class Solution {
    public int reverse(int x) {
        int reversedNumber = 0;

        int upperBound = Integer.MAX_VALUE / 10;
        int lowerBound = Integer.MIN_VALUE / 10;
        while(x != 0) {

            int currentRemainder = (x % 10);

            if(((upperBound == reversedNumber) && currentRemainder > 7) || upperBound < reversedNumber) {
                reversedNumber = 0;
                break;
            }
            else if((lowerBound == reversedNumber && currentRemainder < -8) || (lowerBound > reversedNumber)) {
                reversedNumber = 0;
                break;
            }

            reversedNumber = reversedNumber * 10 + currentRemainder;

            x = x/10;
        }

        return reversedNumber;
    }
}

