public class DeleteDuplicatesFromSortedList {
}


// 1) Using two-pointer approach.
class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        ListNode resultHead = head;
        if(head != null) {
            int prevNodeVal = head.val;
            ListNode prevNode = head;
            head = head.next;

            while(head != null) {
                if(prevNodeVal != head.val) {
                    prevNodeVal = head.val;
                    prevNode.next = head;
                    prevNode = prevNode.next;
                }

                head = head.next;
            }

            prevNode.next = null;
        }

        return resultHead;
    }
}

// 2) Faster approach. At each iteration current value is compared with the value in subsequent node.
// If they're different, then the next node is added to the list, otherwise, the next node's successor is used for comparison.
class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        ListNode currentNode = head;

        while(currentNode != null && currentNode.next != null) {
            if(currentNode.val == currentNode.next.val) {
                currentNode.next = currentNode.next.next;
            }
            else {
                currentNode = currentNode.next;
            }
        }

        return head;
    }
}

