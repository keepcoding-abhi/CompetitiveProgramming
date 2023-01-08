public class MaximumIceCreamBars {

    // Counting sort based approach.
    // Since range of costs is not very large using counting sort is feasible.
    // Time : O(n + m), Space : O(m), m is the ice cream with maximum cost.
    public int maxIceCream(int[] costs, int coins) {
        int maxCost = 0;

        int totalBars = costs.length;
        for(int index = 0 ; index < totalBars ; index++) {
            if(costs[index] > maxCost) {
                maxCost = costs[index];
            }
        }

        int[] costFreqs = new int[maxCost + 1];

        for(int index = 0 ; index < totalBars ; index++) {
            costFreqs[costs[index]]++;
        }

        int maxIceCreamBars = 0, coinsLeft = coins;
        for(int currentBar = 1 ; currentBar <= maxCost ; currentBar++) {
            if(coinsLeft >= currentBar) {
                int numberOfCurrentBarsPossible = coinsLeft/currentBar;
                int numberOfCurrentBarsChosen = Math.min(numberOfCurrentBarsPossible, costFreqs[currentBar]);

                maxIceCreamBars += numberOfCurrentBarsChosen;
                coinsLeft -= (currentBar * numberOfCurrentBarsChosen);
            }
            else {
                break;
            }
        }


        return maxIceCreamBars;
    }
    
    // Comparison based sorting approach.
    // Time : O(nLog(n)) and Space : O(log(n))
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);

        int numberOfBars = 0, currentSum = 0;

        for(int currentIndex = 0 ; (currentIndex < costs.length) ; currentIndex++) {
            currentSum += costs[currentIndex];

            if(currentSum <= coins) {
                numberOfBars++;
            }
            else {
                break;
            }
        }

        return numberOfBars;
    }
}
