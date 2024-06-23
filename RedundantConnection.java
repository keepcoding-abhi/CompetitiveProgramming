public class RedundantConnection {
}

/*
Time: O(n) in practice, O(n * alpha(n)) in theory
Space: O(n)

Use union find data structure to find out edge which connects nodes that are in the same connected component
 */
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int nEdges = edges.length;
        int[] setAssociation = new int[nEdges];
        int[] setSizes = new int[nEdges];

        for(int index = 0 ; index < nEdges ; index++) {
            setAssociation[index] = index;
            setSizes[index] = 1;
        }

        int[] redundantEdge = null;

        for(int[] edge : edges) {
            if(!union(setAssociation, setSizes, edge[0] - 1, edge[1] - 1)) {
                redundantEdge = edge;
            }
        }

        return redundantEdge;
    }

    private boolean union(int[] setAssociation, int[] setSizes, int node1, int node2) {
        int rootOfNode1 = find(setAssociation, node1);
        int rootOfNode2 = find(setAssociation, node2);

        boolean unionValid = true;

        if(rootOfNode1 == rootOfNode2) {
            unionValid = false;
        }
        else {
            if(setSizes[node1] < setSizes[node2]) {
                setAssociation[rootOfNode1] = rootOfNode2;
                setSizes[rootOfNode2] += setSizes[rootOfNode1];
            }
            else {
                setAssociation[rootOfNode2] = rootOfNode1;
                setSizes[node1] += setSizes[node2];
            }
        }

        return unionValid;
    }

    private int find(int[] setAssociations, int node) {
        int currNode = node;

        while(setAssociations[currNode] != currNode) {
            currNode = setAssociations[currNode];
        }

        int root = currNode;

        currNode = node;

        while(currNode != root) {
            int nextNode = setAssociations[currNode];
            setAssociations[currNode] = root;
            currNode = nextNode;
        }

        return root;
    }
}

/*
Time: O(n^2)
Space: O(n)

Maintain a table storing information about the component to which a node belongs
 */
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int numberOfNodes = edges.length;

        int[] connectedComponentID = new int[numberOfNodes];

        for(int index = 0 ; index < numberOfNodes ; index++) {
            connectedComponentID[index] = index;
        }

        int[] lastRedundantEdge = null;

        for(int[] edge : edges) {

            if(connectedComponentID[edge[0] - 1] == connectedComponentID[edge[1] - 1]) {
                lastRedundantEdge = edge;
            }
            else {
                connectComponents(connectedComponentID, connectedComponentID[edge[0] - 1], connectedComponentID[edge[1] - 1]);
            }
        }

        return lastRedundantEdge;
    }

    private void connectComponents(int[] components, int comp1, int comp2) {
        for(int index = 0, len = components.length ; index < len ; index++) {
            if(components[index] == comp1) {
                components[index] = comp2;
            }
        }
    }
}