public class RotateList {

    // O(n) solution. Finding the node from which all elements must be moved to the head of the list.
    public ListNode rotateRight(ListNode head, int k) {

        ListNode currentNode = head;
        int listLength = 0;

        while(currentNode != null) {
            listLength++;
            currentNode = currentNode.next;
        }

        if(listLength > 0) {
            k = k % listLength;

            if(k != 0) {
                ListNode kPlacesBehindNode = head;
                currentNode = head.next;
                int currentDistance = 1;

                while(currentDistance < k) {
                    currentNode = currentNode.next;
                    currentDistance++;
                }

                while(currentNode.next != null) {
                    currentNode = currentNode.next;

                    kPlacesBehindNode = kPlacesBehindNode.next;
                }

                currentNode.next = head;
                head = kPlacesBehindNode.next;
                kPlacesBehindNode.next = null;
            }
        }

        return head;
    }

}
