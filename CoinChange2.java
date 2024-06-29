public class CoinChange2 {
}

/*
Time: O(nCoins * amount)
Space: O(amount)

Consider each coin one by one and figure out the sums that can be made possible by building on top of the
sums that were already formed by the previous coins. Start by assuming there is one way to generate the sum of 0.
 */
class Solution {
    public int change(int amount, int[] coins) {
        int[] waysToMakeAmount = new int[amount + 1];
        waysToMakeAmount[0] = 1;

        for(int coin : coins) {
            int lastValidAmount = amount - coin;
            for(int currAmount = 0 ; currAmount <= lastValidAmount ; currAmount++) {
                waysToMakeAmount[currAmount + coin] += waysToMakeAmount[currAmount];
            }
        }

        return waysToMakeAmount[amount];
    }
}

/*
Time : O(n*amount)
Space : O(amount)

Space optimized version of next approach. The number of ways to make a sum with certain coin is equal to :
Ways of making that sum by ignoring the current coin + ways of making sum - coin's value
 */
class Solution {
    public int change(int amount, int[] coins) {
        int[] savedResults = new int[amount + 1];

        savedResults[0] = 1;

        for(int coinIndex = 0 ; coinIndex < coins.length ; coinIndex++) {
            int currentCoin = coins[coinIndex];

            for(int currentAmount = currentCoin ; currentAmount <= amount ; currentAmount++) {
                savedResults[currentAmount] += (savedResults[currentAmount - currentCoin]);
            }
        }

        return savedResults[amount];
    }
}

/*
Time : O(n*amount)
Space : O(n*amount)

Bottom up version of next approach.
 */
class Solution {
    public int change(int amount, int[] coins) {
        int[][] savedResults = new int[coins.length][amount + 1];

        int currVal = 0, firstNum = coins[0];
        while(currVal <= amount) {
            savedResults[0][currVal] = 1;
            currVal += firstNum;
        }

        for(int coinIndex = 1 ; coinIndex < coins.length ; coinIndex++) {
            int currentCoin = coins[coinIndex];

            for(int currentAmount = 0 ; currentAmount <= amount ; currentAmount++) {
                if(currentAmount < currentCoin) {
                    savedResults[coinIndex][currentAmount] = savedResults[coinIndex - 1][currentAmount];
                }
                else {
                    savedResults[coinIndex][currentAmount] = savedResults[coinIndex][currentAmount - currentCoin] + savedResults[coinIndex - 1][currentAmount];
                }
            }
        }

        int numberOfWays = savedResults[coins.length - 1][amount];

        return numberOfWays;
    }
}

/*
Time : O(n * amount)
Space : O(n * amount)
Top down version of next approach.
 */
class Solution {
    public int change(int amount, int[] coins) {
        int[][] savedResults = new int[coins.length][amount + 1];

        for(int rowIndex = 0 ; rowIndex < coins.length ; rowIndex++) {
            for(int colIndex = 0 ; colIndex <= amount ; colIndex++) {
                savedResults[rowIndex][colIndex] = -1;
            }
        }

        return getNumberOfCombinations(amount, coins, 0, savedResults);
    }

    private int getNumberOfCombinations(int amount, int[] coins, int startIndex, int[][] savedResults) {

        int numberOfCombinations = 0;
        if(amount == 0) {
            numberOfCombinations = 1;
        }
        else if(startIndex < coins.length) {

            int prevCount = savedResults[startIndex][amount];
            if(prevCount == -1) {
                int currentVal = coins[startIndex];
                int maxCountsPossible = (amount / currentVal);

                for(int currentCount = 0 ; currentCount <= maxCountsPossible ; currentCount++) {
                    numberOfCombinations += getNumberOfCombinations((amount - (currentCount * currentVal)), coins, startIndex + 1, savedResults);
                }

                savedResults[startIndex][amount] = numberOfCombinations;
            }
            else {
                numberOfCombinations = savedResults[startIndex][amount];
            }
        }

        return numberOfCombinations;
    }
}

/*
Time : O(m^n)
Space : O(n)
m is the quotient from the smallest coin when it divides amount. In the worst case m = amount.
 */
class Solution {
    public int change(int amount, int[] coins) {
        return getNumberOfCombinations(amount, coins, 0);
    }

    private int getNumberOfCombinations(int amount, int[] coins, int startIndex) {

        int numberOfCombinations = 0;
        if(amount == 0) {
            numberOfCombinations = 1;
        }
        else if(startIndex < coins.length) {
            int currentVal = coins[startIndex];
            int maxCountsPossible = (amount / currentVal);

            for(int currentCount = 0 ; currentCount <= maxCountsPossible ; currentCount++) {
                numberOfCombinations += getNumberOfCombinations((amount - (currentCount * currentVal)), coins, startIndex + 1);
            }
        }

        return numberOfCombinations;
    }
}
