public class PalindromeLinkedList {
}

class Solution {

    // Time : O(n) and Space : O(1)
    // Reverse the pointers of the second linked list. and compare the two lists.
    public boolean isPalindrome(ListNode head) {
        int listLength = findLength(head);

        boolean isPalin = true;

        if(listLength > 1) {
            int secondHalfStartIndex = listLength/2;
            if(listLength % 2 == 1) {
                secondHalfStartIndex++;
            }

            ListNode secondHalfBeginning = head;

            for(int index = 1 ; index < secondHalfStartIndex ; index++) {
                secondHalfBeginning = secondHalfBeginning.next;
            }

            ListNode reversedList = reverseList(secondHalfBeginning.next);
            ListNode reverseListPointer = reversedList;
            ListNode forwardNode = head;
            ListNode lastNodeInReversedList = null;

            while(reverseListPointer != null) {
                if(reverseListPointer.val != forwardNode.val) {
                    isPalin = false;
                    break;
                }

                forwardNode = forwardNode.next;
                lastNodeInReversedList = reverseListPointer;
                reverseListPointer = reverseListPointer.next;
            }

            reverseList(reversedList);
        }

        return isPalin;
    }

    private int findLength(ListNode head) {
        int length = 0;
        while(head != null) {
            length++;
            head = head.next;
        }

        return length;
    }

    private ListNode reverseList(ListNode head) {
        ListNode currentNode = head, nextNode = head.next;

        while(nextNode != null) {
            ListNode nextToNextNode = nextNode.next;
            nextNode.next = currentNode;
            currentNode = nextNode;
            nextNode = nextToNextNode;
        }
        head.next = null;

        return currentNode;
    }
}

