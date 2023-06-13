import java.util.Deque;
import java.util.LinkedList;

public class ReorderList {
    // Time : O(n)
    // Space : O(n)
    // Using stack to keep track of the nodes to be inserted in the reverse order.
    public void reorderList(ListNode head) {
        Deque<ListNode> stack = new LinkedList<ListNode>();

        ListNode currentPointer = head;
        int listSize = 0;

        while(currentPointer != null) {
            stack.push(currentPointer);
            currentPointer = currentPointer.next;
            listSize++;
        }

        int numberOfPopsToBeMade = listSize / 2;

        currentPointer = head;

        for(int index = 0 ; index < numberOfPopsToBeMade ; index++) {
            ListNode poppedEl = stack.pop();
            ListNode nextNode = currentPointer.next;

            currentPointer.next = poppedEl;
            poppedEl.next = nextNode;

            currentPointer = nextNode;
        }
        currentPointer.next = null;
    }
}
