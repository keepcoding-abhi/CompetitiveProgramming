public class MiddleOfTheLinkedList {
    /*
    Time: O(n)
    Space: O(1)
     */
    public ListNode middleNode(ListNode head) {
        ListNode fastPointer = head, slowPointer = head;

        while(fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        return slowPointer;
    }
}
