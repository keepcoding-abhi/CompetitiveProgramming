public class RemoveNthNodeFromEnd {
}

// 1) Optimum one-pass solution
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fwdPtr = head, bckPtr = head;

        for(int index = 0 ; index < n ; index++) {
            fwdPtr = fwdPtr.next;
        }

        // if fwdPtr == null then it means that nth node must be removed from a list of size n, i.e
        // first node is to be removed
        if(fwdPtr == null) {
            head = head.next;
        }
        else {
            while(fwdPtr.next != null) {
                fwdPtr = fwdPtr.next;
                bckPtr = bckPtr.next;
            }

            bckPtr.next = bckPtr.next.next;
        }

        return head;
    }
}

// 2) Modified approach 1, adding dummy node before head

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);

        ListNode fwdPtr = dummy, bckPtr = dummy;

        for(int index = 0 ; index < n ; index++) {
            fwdPtr = fwdPtr.next;
        }

        while(fwdPtr.next != null) {
            fwdPtr = fwdPtr.next;
            bckPtr = bckPtr.next;
        }

        bckPtr.next = bckPtr.next.next;

        return dummy.next;
    }
}

//3) Two pass solution.
// In the first pass determine the length of the linked list.
// Calculate the index which you want to remove, and in the second pass traverse upto that index.

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;

        ListNode iterator = head;
        while(iterator != null) {
            length++;
            iterator = iterator.next;
        }

        int indexToRemove = length - n;

        // To avoid this if-else add dummy node before head.
        if(indexToRemove == 0) {
            head = head.next;
        }
        else {
            iterator = head;
            for(int index = 1 ; index < indexToRemove ; index++) {
                iterator = iterator.next;
            }

            iterator.next = iterator.next.next;
        }

        return head;
    }
}

//4) Two Pass with dummy node
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;

        ListNode iterator = head;
        while(iterator != null) {
            length++;
            iterator = iterator.next;
        }

        int indexToRemove = length - n;
        ListNode dummy = new ListNode(0, head);

        iterator = dummy;
        for(int index = 0 ; index < indexToRemove ; index++) {
            iterator = iterator.next;
        }

        iterator.next = iterator.next.next;

        return dummy.next;
    }
}

