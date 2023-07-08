import java.util.Arrays;

public class BestTimeToBuyAndSellStock2 {

    /*
    Time : O(n)
    Space : O(1)
    Whenever the price on current day is greater than previous day's price add the difference to the profit.
     */
    public int maxProfit(int[] prices) {
        int[] savedResults = new int[prices.length];

        int totalProfit = 0, currentDay = 1;
        int purchasePrice = Integer.MAX_VALUE;

        for(int index = 1 ; index < prices.length ; index++) {
            if(prices[index] > prices[index - 1]) {
                totalProfit += (prices[index] - prices[index - 1]);
            }
        }

        return totalProfit;
    }

    /*
    Time : O(n)
    Space : O(1)
    The next two solutions scan the prices array from left to right just once.
    Select the purchase price at a point where price of stock begins to rise and keep moving forward till the point where
    price begins to drop with respect to the price on previous day. Sell the stock on previous day's price.
    And keep moving forward till the price increases with respect to the previous day. The previous day would be the
    purchase price.
     */
    public int maxProfit(int[] prices) {
        int[] savedResults = new int[prices.length];

        int totalProfit = 0, currentDay = 1;
        int purchasePrice = Integer.MAX_VALUE;

        while(currentDay < prices.length) {

            while(currentDay < prices.length && prices[currentDay - 1] >= prices[currentDay]) {
                currentDay++;
            }

            purchasePrice = prices[currentDay - 1];

            while(currentDay < prices.length && prices[currentDay - 1] <= prices[currentDay]) {
                currentDay++;
            }

            totalProfit += (prices[currentDay - 1] - purchasePrice);
        }

        return totalProfit;
    }

    /*
    Time : O(n)
    Space : O(1)
     */
    public int maxProfit(int[] prices) {
        int[] savedResults = new int[prices.length];

        int totalProfit = 0;

        int currentDay = 1;
        int prevPrice = prices[0], purchasePrice = prices[0], currentPrice = -1;

        while(currentDay < prices.length) {
            currentPrice = prices[currentDay];
            if(currentPrice >= prevPrice) {
                prevPrice = currentPrice;
                currentDay++;
            }
            else {
                if(prevPrice > purchasePrice) {
                    totalProfit += (prevPrice - purchasePrice);
                    purchasePrice = currentPrice;
                }

                while(currentDay < prices.length && prices[currentDay] <= prevPrice) {
                    prevPrice = prices[currentDay];
                    currentDay++;
                }

                if(currentDay < prices.length) {
                    purchasePrice = prevPrice;
                }
            }
        }

        if(prevPrice > purchasePrice) {
            totalProfit += (prevPrice - purchasePrice);
        }

        return totalProfit;
    }

    /*
    Time : O(n^2)
    Space : O(n)
    Bottom-up version of next solution.
     */
    public int maxProfit(int[] prices) {
        int[] savedResults = new int[prices.length];

        int greatestProfitSeenTillNow = 0;

        for(int index = prices.length - 2 ; index > -1 ; index--) {

            int maxProfitFromSelling = 0;
            int currentDatePrice = prices[index];

            for(int sellDateIndex = index + 1 ; sellDateIndex < prices.length ; sellDateIndex++) {
                int sellDatePrice = prices[sellDateIndex];
                if(sellDatePrice > currentDatePrice) {
                    maxProfitFromSelling = Math.max(maxProfitFromSelling, (sellDatePrice - currentDatePrice) + savedResults[sellDateIndex]);
                }
            }

            greatestProfitSeenTillNow = Math.max(greatestProfitSeenTillNow, maxProfitFromSelling);
            savedResults[index] = greatestProfitSeenTillNow;
        }

        return greatestProfitSeenTillNow;
    }

    /*
    Time : O(n^2)
    Space : O(n)
    Top-down memoized version of next solution.
     */
    public int maxProfit(int[] prices) {
        int[] savedResults = new int[prices.length];
        Arrays.fill(savedResults, -1);

        return maximizeProfit(prices, -1, 0, savedResults);
    }

    private int maximizeProfit(int[] prices, int purchasePrice, int startIndex, int[] savedResults) {
        int maxProfit = 0;

        if(startIndex < prices.length) {

            if(purchasePrice != -1) {
                for(int index = startIndex ; index < prices.length ; index++) {
                    int currentPrice = prices[index];
                    if(currentPrice > purchasePrice) {
                        int profitAfterSelling = (currentPrice - purchasePrice) + maximizeProfit(prices, -1, index, savedResults);

                        maxProfit = Math.max(maxProfit, profitAfterSelling);
                    }
                }
            }
            else {
                if(savedResults[startIndex] != -1) {
                    maxProfit = savedResults[startIndex];
                }
                else {
                    int profitAfterBuying = maximizeProfit(prices, prices[startIndex], startIndex + 1, savedResults);
                    int profitAfterNotBuying = maximizeProfit(prices, -1, startIndex + 1, savedResults);

                    maxProfit = Math.max(profitAfterBuying, profitAfterNotBuying);

                    savedResults[startIndex] = maxProfit;
                }
            }
        }

        return maxProfit;
    }

    /*
    Time : O(n^n)
    Space : O(n), the depth of recursion stack will not exceed O(n), since at a time we won't consider more than
    n stocks.
    On each day find out the profit which will be obtained after buying the stock and selling the stock and take
    the maximum between the two.
     */
    public int maxProfit(int[] prices) {
        return maximizeProfit(prices, -1, 0);
    }

    private int maximizeProfit(int[] prices, int purchasePrice, int startIndex) {
        int maxProfit = 0;

        if(startIndex < prices.length) {
            if(purchasePrice != -1) {
                for(int index = startIndex ; index < prices.length ; index++) {
                    int currentPrice = prices[index];
                    if(currentPrice > purchasePrice) {
                        int profitAfterSelling = (currentPrice - purchasePrice) + maximizeProfit(prices, -1, index);

                        maxProfit = Math.max(maxProfit, profitAfterSelling);
                    }
                }
            }
            else {
                int profitAfterBuying = maximizeProfit(prices, prices[startIndex], startIndex + 1);
                int profitAfterNotBuying = maximizeProfit(prices, -1, startIndex + 1);

                maxProfit = Math.max(profitAfterBuying, profitAfterNotBuying);
            }
        }

        return maxProfit;
    }
}
