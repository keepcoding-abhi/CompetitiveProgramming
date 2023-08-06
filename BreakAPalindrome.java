public class BreakAPalindrome {

    /*
    Time : O(n)
    Space : O(n)
    Using a character array for better performance.
     */
    public String breakPalindrome(String palindrome) {

        String result = "";
        int len = palindrome.length();

        char[] original = palindrome.toCharArray();

        if(len > 1) {
            int midPoint = len / 2;

            for(int index = 0 ; index < midPoint ; index++) {
                if(original[index] != 'a') {
                    original[index] = 'a';
                    result = String.valueOf(original);
                    break;
                }
            }

            if(result.isEmpty()) {
                result = palindrome.substring(0, len - 1) + "b";
            }
        }

        return result;
    }

    /*
    Time : O(n)
    Space : O(1)
    Use the fact that the other half will be a replica. And only consider on half.
     */
    public String breakPalindrome(String palindrome) {

        String result = "";
        int len = palindrome.length();

        if(len > 1) {
            int midPoint = len / 2;

            for(int index = 0 ; index < midPoint ; index++) {
                if(palindrome.charAt(index) != 'a') {
                    result = palindrome.substring(0, index) + "a" + palindrome.substring(index + 1, len);
                    break;
                }
            }

            if(result.isEmpty()) {
                result = palindrome.substring(0, len - 1) + "b";
            }
        }

        return result;


    /*
    Time : O(n^2)
    Space : O(1)
    Convert the first non 'a' to 'a'. If doing so, results in a palindrome, ignore the
    current index and go on next.
    If the string is full of a's change the last character to b.
     */
    public String breakPalindrome(String palindrome) {

        String result = "";
        int len = palindrome.length();
        for(int index = 0 ; index < len ; index++) {
            char currentChar = palindrome.charAt(index);

            if(currentChar != 'a') {
                result = palindrome.substring(0, index) + 'a' + palindrome.substring(index + 1, len);

                if(!checkPalin(result)) {
                    break;
                }
                else {
                    result = "";
                }
            }
        }

        if(len > 1 && result.isEmpty()) {
            result = palindrome.substring(0, len - 1) + "b";
        }

        return result;
    }

    private boolean checkPalin(String s) {
        int leftIndex = 0, rightIndex = s.length() - 1;
        boolean isPalin = true;

        while(leftIndex < rightIndex) {
            if(s.charAt(leftIndex) != s.charAt(rightIndex)) {
                isPalin = false;
                break;
            }

            leftIndex++;
            rightIndex--;
        }

        return isPalin;
    }
}
