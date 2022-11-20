public class ReverseLinkedList2 {

    // Fast solution using iterative link reversal. T : O(n), S : O(1)
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode currentNode = null;
        int index = 0;

        while(index < left - 1) {

            if(currentNode == null) {
                currentNode = head;
            }
            else {
                currentNode = currentNode.next;
            }

            index++;
        }

        ListNode lastNodeInNonReversedList = currentNode;

        index++;
        if(currentNode != null) {
            currentNode = currentNode.next;
        }
        else {
            currentNode = head;
        }

        ListNode firstNodeInReversedList = currentNode;

        ListNode previousNode = currentNode;
        ListNode nextNode = currentNode.next;

        index++;
        currentNode = currentNode.next;

        while(index <= right) {

            nextNode = currentNode.next;
            currentNode.next = previousNode;

            previousNode = currentNode;
            currentNode = nextNode;
            index++;
        }

        firstNodeInReversedList.next = nextNode;

        if(lastNodeInNonReversedList != null) {
            lastNodeInNonReversedList.next = previousNode;
        }
        else {
            head = previousNode;
        }

        return head;
    }

}
