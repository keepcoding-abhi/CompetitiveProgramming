public class ValidPalindrome2 {

    // Time : O(n*d), space : O(d)
    // n : length of string, d : number of deletions allowed.
    public boolean validPalindrome(String s) {
        return checkPalin(s, 0, s.length() - 1, 1);
    }

    private boolean checkPalin(String s, int lowerIndex, int upperIndex, int deletionsRemaining) {

        boolean isPalin = true;
        while(lowerIndex < upperIndex) {
            if(s.charAt(lowerIndex) != s.charAt(upperIndex)) {
                if(deletionsRemaining > 0) {
                    isPalin = checkPalin(s, lowerIndex + 1, upperIndex, deletionsRemaining - 1);
                    if(!isPalin) {
                        isPalin = checkPalin(s, lowerIndex, upperIndex - 1, deletionsRemaining - 1);
                    }
                }
                else {
                    isPalin = false;
                }
                break;
            }

            lowerIndex++;
            upperIndex--;
        }

        return isPalin;
    }
}
