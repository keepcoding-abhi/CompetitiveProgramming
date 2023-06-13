public class DeleteTheMiddleNodeOfALinkedList {

    // Time : O(n)
    // Space : O(1)
    // Scanning the list only once.
    // Determining the middle element of the list using fast pointer and slow pointer.
    public ListNode deleteMiddle(ListNode head) {
        ListNode prevPointer = null, middleNodePointer = head, endNodePointer = head;

        ListNode newHead = null;
        if(head != null && head.next != null) {
            newHead = head;

            while(endNodePointer != null && endNodePointer.next != null) {
                prevPointer = middleNodePointer;
                middleNodePointer = middleNodePointer.next;
                endNodePointer = endNodePointer.next.next;
            }

            prevPointer.next = middleNodePointer.next;
            middleNodePointer.next = null;
            middleNodePointer = null;
        }

        return newHead;
    }

    // Time : O(n)
    // Space : O(1)
    // Scanning the list twice. Recording the length of list in first iteration and then moving till the middle index
    // in the second.
    public ListNode deleteMiddle(ListNode head) {
        ListNode currentPointer = head;
        int listLength = 0;

        while(currentPointer != null) {
            currentPointer = currentPointer.next;
            listLength++;
        }

        ListNode prevPointer = null;
        ListNode newHead = null;
        int middleIndex = listLength / 2;

        if(listLength > 1) {
            newHead = head;
            prevPointer = head;
            currentPointer = head.next;
            for(int index = 1 ; index < middleIndex ; index++) {
                prevPointer = currentPointer;
                currentPointer = currentPointer.next;
            }

            prevPointer.next = currentPointer.next;
            currentPointer.next = null;
            currentPointer = null;
        }

        return newHead;
    }
}
