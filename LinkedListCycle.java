public class LinkedListCycle {
}

//1) Using a table to store the previously visited nodes. Space complexity= O(n)

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = (head != null) ? head.next : null;

        boolean hasCycle = false;
        while(fastPointer != null) {

            if(slowPointer == fastPointer) {
                hasCycle = true;
                break;
            }

            slowPointer = slowPointer.next;

            fastPointer = fastPointer.next;
            if(fastPointer != null) {
                fastPointer = fastPointer.next;
            }
        }

        return hasCycle;
    }
}

//2) Floyd's algorithm. Space complexity = O(1). Time complexity = O(n) in both cases.

public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> prevNodes = new HashSet<ListNode>();

        ListNode current = head;

        boolean hasCycle = false;
        while(current != null) {
            if(prevNodes.contains(current)) {
                hasCycle = true;
                break;
            }
            else {
                prevNodes.add(current);
            }
            current = current.next;
        }

        return hasCycle;
    }
}
