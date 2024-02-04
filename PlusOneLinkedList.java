import java.util.Deque;
import java.util.LinkedList;

public class PlusOneLinkedList {

    /*
    Time: O(n)
    Space: O(1)

    Saving the right most digit which is not a 9, incrementing this digit by one. Since all subsequent digits are 9s
    they become zeros.
     */
    public ListNode plusOne(ListNode head) {
        ListNode rightMostNonNine = null;

        ListNode currentNode = head;

        while(currentNode != null) {
            if(currentNode.val != 9) {
                rightMostNonNine = currentNode;
            }

            currentNode = currentNode.next;
        }

        if(rightMostNonNine == null) {
            ListNode newNode = new ListNode(1);

            currentNode = head;
            newNode.next = head;
            head = newNode;
        }
        else {
            rightMostNonNine.val = rightMostNonNine.val + 1;
            currentNode = rightMostNonNine.next;
        }

        while(currentNode != null) {
            currentNode.val = 0;
            currentNode = currentNode.next;
        }

        return head;
    }

    /*
    Time: O(n)
    Space: O(n)

    Saving nodes of the linked list in a stack and then adding one starting from the rightmost digit.
     */
    public ListNode plusOne(ListNode head) {

        Deque<ListNode> stackedNodes = new LinkedList<ListNode>();

        ListNode currentNode = head;

        while(currentNode != null) {
            stackedNodes.push(currentNode);
            currentNode = currentNode.next;
        }

        boolean carry = false;
        while(!stackedNodes.isEmpty()) {
            ListNode poppedNode = stackedNodes.pop();

            if(poppedNode.val == 9) {
                poppedNode.val = 0;
                carry = true;
            }
            else {
                poppedNode.val++;
                carry = false;
                break;
            }
        }

        if(carry) {
            ListNode newNode = new ListNode(1);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }
}
