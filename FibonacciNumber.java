public class FibonacciNumber {

    // Bottom-up DP solution.
    public int fib(int n) {

        int result;
        if(n > 1) {

            int secondLastFib = 0, lastFib = 1, currentFib = 1;

            for(int index = 2 ; index <= n ; index++) {
                currentFib = lastFib + secondLastFib;

                secondLastFib = lastFib;
                lastFib = currentFib;
            }

            result = currentFib;
        }
        else {
            result = n;
        }

        return result;
    }

    // Top down recursive solution.
    public int fib(int n) {

        int result;
        if(n > 1) {
            int[] fibonacciVals = new int[n + 1];
            fibonacciVals[1] = 1;

            for(int index = 2 ; index < n ; index++) {
                fibonacciVals[index] = -1;
            }
            fibonacciVals[n] = -1;

            computeFib(n, fibonacciVals);
            result = fibonacciVals[n];
        }
        else {
            result = n;
        }



        return result;
    }

    private void computeFib(int n, int[] fibonacciVals) {

        int fib1 = fibonacciVals[n - 1], fib2 = fibonacciVals[n - 2];

        if(fib1 == -1) {
            computeFib(n - 1, fibonacciVals);
            fib1 = fibonacciVals[n - 1];
        }

        if(fib2 == -1) {
            computeFib(n - 2, fibonacciVals);
            fib2 = fibonacciVals[n - 2];
        }

        fibonacciVals[n] = fib1 + fib2;
    }

    // Brute Force recursive solution. Time Complexity : O(2^n) Space : O(n).
    public int fib(int n) {

        int result = n;
        if(n > 1) {
            result = fib(n - 1) + fib(n - 2);
        }

        return result;
    }

}
