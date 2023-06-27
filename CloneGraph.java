import java.util.*;

public class CloneGraph {
}
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

/*
Time : O(n + m)
Space : O(n)
Performing BFS.
 */
class Solution {
    public Node cloneGraph(Node node) {

        Node clone = null;
        if(node != null) {

            Deque<Node> nodesToCreate = new LinkedList<Node>();
            Map<Integer, Node> copiedNodes = new HashMap<Integer, Node>();

            Set<Integer> enqueuedNodes = new HashSet<Integer>();

            nodesToCreate.addLast(node);

            while(!nodesToCreate.isEmpty()) {
                Node currentNode = nodesToCreate.removeFirst();

                List<Node> originalNeighbors = currentNode.neighbors;
                List<Node> copiedNeighbors = null;

                if(copiedNodes.containsKey(currentNode.val)) {
                    copiedNeighbors = copiedNodes.get(currentNode.val).neighbors;
                }
                else {
                    Node copy = new Node(currentNode.val);
                    copiedNeighbors = copy.neighbors;
                    copiedNodes.put(currentNode.val, copy);
                }

                for(Node currentNeighbor : originalNeighbors) {
                    if(copiedNodes.containsKey(currentNeighbor.val)) {
                        copiedNeighbors.add(copiedNodes.get(currentNeighbor.val));
                    }
                    else {
                        Node copyOfCurrentNeighbor = new Node(currentNeighbor.val);
                        copiedNodes.put(currentNeighbor.val, copyOfCurrentNeighbor);

                        copiedNeighbors.add(copyOfCurrentNeighbor);
                        nodesToCreate.addLast(currentNeighbor);
                    }
                }
            }

            clone = copiedNodes.get(node.val);
        }

        return clone;
    }
}

/*
Time : O(n + m), n nodes : m edges.
Space : O(n)
Performing DFS
 */
class Solution {
    //
    public Node cloneGraph(Node node) {

        Node clone = null;
        if (node != null) {
            Map<Integer, Node> keyAndNode = new HashMap<Integer, Node>();

            clone = copyGraph(node, keyAndNode);
        }

        return clone;
    }

    private Node copyGraph(Node node, Map<Integer, Node> savedNodes) {

        int val = node.val;

        Node copiedNode;

        if (savedNodes.containsKey(val)) {
            copiedNode = savedNodes.get(val);
        } else {
            List<Node> originalNeighbors = node.neighbors;
            ArrayList<Node> copiedNeighbors = new ArrayList<Node>(originalNeighbors.size());
            copiedNode = new Node(node.val, copiedNeighbors);
            savedNodes.put(val, copiedNode);

            for (Node currentNeighbor : originalNeighbors) {

                copiedNeighbors.add(copyGraph(currentNeighbor, savedNodes));
            }
        }

        return copiedNode;
    }
}
