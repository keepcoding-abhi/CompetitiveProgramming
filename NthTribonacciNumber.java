public class NthTribonacciNumber {

    // Bottom-up solution DP without array
    public int tribonacci(int n) {
        int result = 0;

        if(n == 1 || n == 2) {
            result = 1;
        }
        else if(n > 2) {

            int last = 1;
            int secondLast = 1;
            int thirdLast = 0;

            for(int currentNum = 3 ; currentNum <= n ; currentNum++) {
                result = last + secondLast + thirdLast;
                thirdLast = secondLast;
                secondLast = last;
                last = result;
            }
        }

        return result;
    }

    // Bottom-up DP solution
    public int tribonacci(int n) {
        int result = 0;

        if(n == 1 || n == 2) {
            result = 1;
        }
        else if(n > 2) {
            int[] prevTribonaccis = new int[n + 1];

            prevTribonaccis[0] = 0;
            prevTribonaccis[1] = 1;
            prevTribonaccis[2] = 1;

            for(int currentNum = 3 ; currentNum <= n ; currentNum++) {
                prevTribonaccis[currentNum] = prevTribonaccis[currentNum - 1] + prevTribonaccis[currentNum - 2] + prevTribonaccis[currentNum - 3];
            }

            result = prevTribonaccis[n];
        }

        return result;
    }

    // Top-down DP solution.
    public int tribonacci(int n) {
        int result = 0;

        if(n == 1 || n == 2) {
            result = 1;
        }
        else if(n > 2) {
            int[] prevTribonaccis = new int[n + 1];

            prevTribonaccis[0] = 0;
            prevTribonaccis[1] = 1;
            prevTribonaccis[2] = 1;

            result = computeTribonaccis(n, prevTribonaccis);
        }

        return result;
    }

    private int computeTribonaccis(int n, int[] prevTribonaccis) {
        if(n > 2 && prevTribonaccis[n] == 0) {
            prevTribonaccis[n] = computeTribonaccis(n - 1, prevTribonaccis) + computeTribonaccis(n - 2, prevTribonaccis) + computeTribonaccis(n - 3, prevTribonaccis);
        }

        return prevTribonaccis[n];
    }
}
