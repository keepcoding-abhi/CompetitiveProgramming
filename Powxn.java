public class Powxn {

    //1) Iterative version of fast exponentiation algorithm.
    public double myPow(double x, int n) {
        double result = 1;
        boolean takeReciprocal = false;

        if(x == 0 || x == 1) {
            result = x;
        }
        else if(x == -1) {

            if(n == Integer.MIN_VALUE) {
                result = 1;
            }
            else {
                if(n < 0) {
                    n *= -1;
                }

                if(n % 2 == 0) {
                    result = 1;
                }
                else {
                    result = -1;
                }
            }
        }
        else if(n == 0) {
            result = 1;
        }
        else if(n == Integer.MIN_VALUE) {
            result = 0;
        }
        else {
            if(n < 0) {
                takeReciprocal = true;
                n *= -1;
            }

            double multiplicationFactor = x;

            for(int i = n ; i > 0 ; i /= 2) {

                if((i % 2) == 1) {
                    result = result * multiplicationFactor;
                }

                multiplicationFactor = multiplicationFactor * multiplicationFactor;
            }

            if(takeReciprocal) {
                result = 1 / result;
            }
        }

        return result;
    }

    //2) Recursive version of fast exponentiation algorithm.
    private double computePower(double x, int n) {

        if(n == 0) {
            return 1;
        }

        double half = computePower(x, n / 2);

        if(n % 2 == 1) {
            return half * half * x;
        }
        else {
            return half * half;
        }
    }

}
