public class BestTimeToBuyAndSellStock {
}

class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        int currentMin = prices[0];

        for(int index = 1, totalPrices = prices.length ; index < totalPrices ; index++) {
            int currentPrice = prices[index];

            int currentProfit = currentPrice - currentMin;

            if(currentProfit < 0) {
                currentMin = currentPrice;
            }
            else if(currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }

        return maxProfit;
    }
}