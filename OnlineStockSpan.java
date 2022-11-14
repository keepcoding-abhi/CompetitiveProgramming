public class OnlineStockSpan {
}

// Efficient solution. Maintaining pointers to the days that ended current day's span.
class StockSpanner {

    ListNode stockHistory;

    public StockSpanner() {

    }

    public int next(int price) {
        int span = 1;

        ListNode todaysPrice = new ListNode(price);

        if(stockHistory == null) {
            stockHistory = todaysPrice;
            todaysPrice.span = span;
        }
        else {

            ListNode currentDay = stockHistory;

            while(currentDay != null && price >= currentDay.price) {

                span += currentDay.span;
                currentDay = currentDay.spanEndedBy;
            }

            todaysPrice.span = span;
            todaysPrice.spanEndedBy = currentDay;

            todaysPrice.next = stockHistory;
            stockHistory = todaysPrice;
        }

        return span;
    }
}

class ListNode {
    int price;
    int span;
    ListNode next, spanEndedBy;

    ListNode(int price) {
        this.price = price;
    }
}

// Using stack to keep track of only the relevant stock prices and their spans.
// We'll only store the prices that will stop the span of upcoming stock prices on the stack.

class StockSpanner {

    Deque<int[]> pastPrices;

    public StockSpanner() {
        pastPrices = new LinkedList<int[]>();
    }

    public int next(int price) {

        int currentStockSpan = 1;

        while(!pastPrices.isEmpty() && pastPrices.peek()[0] <= price) {
            currentStockSpan += pastPrices.pop()[1];
        }

        pastPrices.push(new int[]{price, currentStockSpan});

        return currentStockSpan;
    }
}



// Using a singly linked list to store earlier prices. Performing linear search from the most recent day backwards to
// find the span.

class StockSpanner {

    ListNode stockHistory;

    public StockSpanner() {

    }

    public int next(int price) {
        int span = 1;

        ListNode todaysPrice = new ListNode(price);

        if(stockHistory == null) {
            stockHistory = todaysPrice;
        }
        else {

            ListNode currentDay = stockHistory;

            while(currentDay != null && price >= currentDay.price) {
                currentDay = currentDay.next;
                span++;
            }

            todaysPrice.next = stockHistory;
            stockHistory = todaysPrice;
        }

        return span;
    }
}

class ListNode {
    int price;
    ListNode next;

    ListNode(int price) {
        this.price = price;
    }
}

