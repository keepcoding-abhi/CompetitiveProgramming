public class RichestCustomerWealth {

    // Time : O(m * n), Space : O(1)
    public int maximumWealth(int[][] accounts) {
        int maxWealth = 0;

        for(int rowIndex = 0, numRows = accounts.length ; rowIndex < numRows ; rowIndex++) {

            int currentCustomersWealth = 0;
            for(int colIndex = 0, numCols = accounts[rowIndex].length ; colIndex < numCols ; colIndex++) {
                currentCustomersWealth += accounts[rowIndex][colIndex];
            }

            if(currentCustomersWealth > maxWealth) {
                maxWealth = currentCustomersWealth;
            }
        }

        return maxWealth;
    }
}
