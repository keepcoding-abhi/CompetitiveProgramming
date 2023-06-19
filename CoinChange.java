public class CoinChange {

    // Time : O(amount * coins.length)
    // Space : O(amount)
    // Bottom-up DP-based solution.
    // Created a table to store the minimum coins required to form amounts ranging from 1 to amount.
    public int coinChange(int[] coins, int amount) {
        int[] minCoinsToMakeAmount = new int[amount + 1];
        Arrays.fill(minCoinsToMakeAmount, amount + 1);
        minCoinsToMakeAmount[0] = 0;

        for(int currentAmount = 1 ; currentAmount <= amount ; currentAmount++) {

            for(int coinIndex = 0 ; coinIndex < coins.length ; coinIndex++) {
                int currentCoin = coins[coinIndex], amountLeftAfterCurrentCoin = currentAmount - currentCoin;

                if(amountLeftAfterCurrentCoin >= 0) {
                    minCoinsToMakeAmount[currentAmount] = Math.min(minCoinsToMakeAmount[currentAmount], minCoinsToMakeAmount[amountLeftAfterCurrentCoin] + 1);
                }
            }
        }

        return (minCoinsToMakeAmount[amount] > amount) ? -1 : minCoinsToMakeAmount[amount];
    }

    // Time : O(amount * n), n is the number of coins. We're solving amount number of sub-problems
    // and iterating over coins in each sub-problem.
    // Space : O(amount) required for the table where results are saved.
    // Top-down memoization based solution.
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] savedResults = new int[amount + 1];

        for(int index = 0 ; index <= amount ; index++) {
            savedResults[index] = -2;
        }

        return findMinCoinsToMakeAmount(coins, amount, savedResults);
    }

    private int findMinCoinsToMakeAmount(int[] coins, int amount, int[] savedResults) {

        int minCoins = -1;

        if(amount == 0) {
            minCoins = 0;
        }
        else if(amount >= coins[0]) {

            if(savedResults[amount] != -2) {
                minCoins = savedResults[amount];
            }
            else {
                for(int index = 0, len = coins.length ; index < len ; index++) {
                    int currentCoin = coins[index];
                    int coinsReq = findMinCoinsToMakeAmount(coins, amount - currentCoin, savedResults);
                    if(coinsReq != -1) {
                        coinsReq++;
                        if(minCoins == -1 || (coinsReq < minCoins)) {
                            minCoins = coinsReq;
                        }
                    }
                }

                savedResults[amount] = minCoins;
            }
        }
        else {
            minCoins = -1;
        }

        return minCoins;
    }

    // Time : O(amount^(n)), n is the number of denominations :  exponential
    // Space :O(amount) :  exponential
    // Brute force approach. Generating every possible permutation with the given coins to make the amount.
    public int coinChange(int[] coins, int amount) {

        return findMinCoinsToMakeAmount(coins, amount);
    }

    private int findMinCoinsToMakeAmount(int[] coins, int amount) {

        int minCoins = -1;

        if(amount == 0) {
            minCoins = 0;
        }
        else if(amount >= coins[0]) {
            for(int index = 0, len = coins.length ; index < len ; index++) {
                int currentCoin = coins[index];
                int coinsReq = findMinCoinsToMakeAmount(coins, amount - currentCoin);
                if(coinsReq != -1) {
                    coinsReq++;
                    if(minCoins == -1 || (coinsReq < minCoins)) {
                        minCoins = coinsReq;
                    }
                }
            }
        }
        else {
            minCoins = -1;
        }

        return minCoins;
    }
}
