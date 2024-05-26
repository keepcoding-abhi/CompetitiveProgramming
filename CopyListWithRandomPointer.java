import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

    /*
    Time: O(n)
    Space: O(1)

    Each node in the original list is followed by its copy. Later, random pointers are copied.
    Then the copied nodes are detached from the original list.
     */
    class Solution {

        public Node copyRandomList(Node head) {
            Node copiedListHead = null;

            if(head != null) {
                Node originalNodePtr = head;

                while(originalNodePtr != null) {
                    Node originalListNextNode = originalNodePtr.next;
                    Node copiedNode = new Node(originalNodePtr.val);

                    originalNodePtr.next = copiedNode;
                    copiedNode.next = originalListNextNode;
                    originalNodePtr = originalListNextNode;
                }

                originalNodePtr = head;

                while(originalNodePtr != null) {
                    originalNodePtr.next.random = (originalNodePtr.random == null) ? null : originalNodePtr.random.next;
                    originalNodePtr = originalNodePtr.next.next;
                }

                copiedListHead = head.next;

                Node copiedListPtr = head.next;
                originalNodePtr = head;

                while(copiedListPtr.next != null) {
                    Node nextNodeInCopiedList = copiedListPtr.next.next;
                    originalNodePtr.next = originalNodePtr.next.next;
                    copiedListPtr.next = nextNodeInCopiedList;
                    copiedListPtr = nextNodeInCopiedList;
                    originalNodePtr = originalNodePtr.next;
                }

                originalNodePtr.next = null;
            }

            return copiedListHead;
        }
    }

    /*
    Time: O(n)
    Space: O(n)

    First copy the original linked list without copying the random pointer.
    Maintain a map to store the mapping between nodes in original list and those in copied list
    Use the map to fetch the node in copied list when copying the random pointers.
     */
    class Solution {
        public Node copyRandomList(Node head) {
            Node copiedListHead = null;

            if(head != null) {
                Node originalListPtr = head;
                copiedListHead = new Node(0);
                Node copiedListPtr = copiedListHead;

                Map<Node, Node> originalAndCopiedNodesMapping = new HashMap<Node, Node>();

                while(originalListPtr != null) {
                    Node copiedNode = new Node(originalListPtr.val);

                    copiedListPtr.next = copiedNode;
                    copiedListPtr = copiedListPtr.next;
                    originalAndCopiedNodesMapping.put(originalListPtr, copiedNode);

                    originalListPtr = originalListPtr.next;
                }

                originalListPtr = head;

                copiedListHead = copiedListHead.next;
                copiedListPtr = copiedListHead;

                while(originalListPtr != null) {
                    Node randomPtr = originalListPtr.random;

                    if(randomPtr != null) {
                        copiedListPtr.random = originalAndCopiedNodesMapping.get(randomPtr);
                    }

                    originalListPtr = originalListPtr.next;
                    copiedListPtr = copiedListPtr.next;
                }
            }

            return copiedListHead;
        }
    }

    /*
    Time: O(n)
    Space: O(n)

    Cloning the graph. Using a map to keep track of the nodes which have already been created.
     */
    class Solution {

        Map<Node, Node> originalAndCreatedNodes = new HashMap<Node, Node>();

        public Node copyRandomList(Node head) {

            Node copiedNode = null;

            if(head != null) {

                if(originalAndCreatedNodes.containsKey(head)) {
                    copiedNode = originalAndCreatedNodes.get(head);
                }
                else {
                    copiedNode = new Node(head.val);

                    originalAndCreatedNodes.put(head, copiedNode);
                    copiedNode.next = copyRandomList(head.next);
                    copiedNode.random = copyRandomList(head.random);
                }
            }

            return copiedNode;
        }
    }

    
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
