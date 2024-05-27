import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle2 {
}

/*
Time: O(n)
Space: O(1)

According to Floyd's Tortoise and Hare algorithm, when slow pointer and fast pointer meet in a cycle.
Set the fast pointer equal to head of the linked list and have the slow and fast pointer take a step at a time,
the point where they will meet necxt is the start of the cycle.
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slowPtr = head, fastPtr = head;
        boolean cyclePresent = false;

        while(fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;

            if(slowPtr == fastPtr) {
                cyclePresent = true;
                break;
            }
        }

        ListNode cycleStart = null;

        if(cyclePresent) {
            fastPtr = head;

            while(slowPtr != fastPtr) {
                slowPtr = slowPtr.next;
                fastPtr = fastPtr.next;
            }

            cycleStart = slowPtr;
        }

        return cycleStart;
    }
}

/*
Time: O(n)
Space: O(n)

Save the already visited nodes in a set.
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visitedNodes = new HashSet<ListNode>();

        ListNode currentPtr = head;
        int index = 0;
        int cycleIndex = -1;

        while(currentPtr != null) {
            if(visitedNodes.contains(currentPtr)) {
                break;
            }

            visitedNodes.add(currentPtr);
            currentPtr = currentPtr.next;
        }

        return currentPtr;
    }
}
