public class CopyListWithRandomPointer {

    
    public Node copyRandomList(Node head) {

        Node newListHead = null;

        if(head != null) {
            newListHead = new Node(head.val);
            newListHead.next = head.next;

            HashMap<Node, Node> newAndOriginalListMappings = new HashMap<Node, Node>();
            newAndOriginalListMappings.put(head, newListHead);

            Node currentNodeInOriginal = head.next;
            Node currentNodeInNew = newListHead;

            while(currentNodeInOriginal != null) {
                Node newNode = new Node(currentNodeInOriginal.val);

                currentNodeInNew.next = newNode;
                currentNodeInNew = currentNodeInNew.next;

                newAndOriginalListMappings.put(currentNodeInOriginal, currentNodeInNew);

                currentNodeInOriginal = currentNodeInOriginal.next;
            }

            currentNodeInOriginal = head;
            currentNodeInNew = newListHead;

            while(currentNodeInOriginal != null) {
                if(currentNodeInOriginal.random != null) {
                    currentNodeInNew.random = newAndOriginalListMappings.get(currentNodeInOriginal.random);
                }

                currentNodeInOriginal = currentNodeInOriginal.next;
                currentNodeInNew = currentNodeInNew.next;
            }
        }

        return newListHead;
    }

}
