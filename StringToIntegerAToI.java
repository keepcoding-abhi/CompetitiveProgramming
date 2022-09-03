public class StringToIntegerAToI {
}

class Solution {
    public int myAtoi(String s) {
        s = s.trim();

        int signFlip = -1;
        int number = 0;

        for(int index = 0, lengthOfString = s.length() ; index < lengthOfString ; index++) {
            char currentChar = s.charAt(index);

            if(currentChar == '-') {
                if(signFlip != -1) {
                    break;
                }
                else {
                    signFlip = 1;
                }
            }
            else if(currentChar == '+') {
                if(signFlip != -1) {
                    break;
                }
                else {
                    signFlip = 0;
                }
            }
            else {
                int currentCharValue = currentChar - '0';
                if(number == 0 && signFlip == -1) {
                    signFlip = 0;
                }

                if(currentCharValue >= 0 && currentCharValue <= 9) {

                    if((signFlip == 0) && (((number == Integer.MAX_VALUE / 10) && currentCharValue > (Integer.MAX_VALUE % 10)) || (number > (Integer.MAX_VALUE / 10)))) {
                        number = Integer.MAX_VALUE;
                        break;
                    }
                    else if((signFlip == 1) && ((number == Integer.MAX_VALUE / 10 && currentCharValue > 8) || (number > (Integer.MAX_VALUE / 10)))) {
                        signFlip = 0;
                        number = Integer.MIN_VALUE;
                        break;
                    }

                    number = (number * 10) + currentCharValue;
                }
                else {
                    break;
                }
            }
        }

        if(signFlip == 1) {
            number *= -1;
        }

        return number;
    }
}
