public class ValidPalindrome {
}

class Solution {
    public boolean isPalindrome(String s) {

        int strlen = s.length();
        int upper = strlen - 1, lower = 0;

        boolean isPalin = true;
        char lowerChar = s.charAt(lower);
        char upperChar = s.charAt(upper);

        while(lower <= upper) {

            if(lowerChar >= 'A' && lowerChar <= 'Z') {
                lowerChar = (char)(lowerChar - 'A' + 'a');
            }
            if((lowerChar >= 'a' && lowerChar <= 'z') || (lowerChar >= '0' && lowerChar <= '9')) {

                if(upperChar >= 'A' && upperChar <= 'Z') {
                    upperChar = (char)(upperChar - 'A' + 'a');
                }

                if((upperChar >= 'a' && upperChar <= 'z') || (upperChar >= '0' && upperChar <= '9')) {

                    if(upperChar != lowerChar) {
                        isPalin = false;
                        break;
                    }

                    lower++;
                    if(lower < strlen) {
                        lowerChar = s.charAt(lower);
                    }
                    else {
                        break;
                    }

                    upper--;
                    if(upper > -1) {
                        upperChar = s.charAt(upper);
                    }
                    else {
                        break;
                    }
                }
                else {
                    upper--;
                    if(upper > -1) {
                        upperChar = s.charAt(upper);
                    }
                    else {
                        break;
                    }
                }
            }
            else {
                lower++;
                if(lower < strlen) {
                    lowerChar = s.charAt(lower);
                }
                else {
                    break;
                }
            }
        }

        return isPalin;
    }
}


//2) Slightly different approach.
class Solution {
    public boolean isPalindrome(String s) {

        int strlen = s.length();
        int upper = strlen - 1, lower = 0;

        boolean isPalin = true;
        char lowerChar = s.charAt(lower);
        char upperChar = s.charAt(upper);

        while(lower < upper) {

            while(lower < upper && !isCharLetterOrDigit(lowerChar)) {
                lower++;
                lowerChar = s.charAt(lower);
            }

            if(lowerChar >= 'A' && lowerChar <= 'Z') {
                lowerChar = (char)(lowerChar - 'A' + 'a');
            }

            while(lower < upper && !isCharLetterOrDigit(upperChar)) {
                upper--;
                upperChar = s.charAt(upper);
            }

            if(upperChar >= 'A' && upperChar <= 'Z') {
                upperChar = (char)(upperChar - 'A' + 'a');
            }

            if(lowerChar != upperChar) {
                isPalin = false;
                break;
            }
            else {
                lower++;
                if(lower < upper) {
                    lowerChar = s.charAt(lower);
                }

                upper--;
                if(lower < upper) {
                    upperChar = s.charAt(upper);
                }
            }
        }

        return isPalin;
    }


    private boolean isCharLetterOrDigit(char c) {

        boolean isAlphaNum = false;
        if((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
            isAlphaNum = true;
        }

        return isAlphaNum;
    }
}
