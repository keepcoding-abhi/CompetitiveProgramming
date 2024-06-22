import java.util.*;

public class GraphValidTree {
}

/*
Time: O(n^2) in worst case but further analysis can reveal that the complexity is O(n)
Space: O(n)

Implementing the Union Find data structure. Each node first belongs to its own set/connected component.
Keep a mapping of the root node in each connected component. To merge two connected components set the
root of one connected component as the other connected component. If two nodes belong to the same connected
component, cycle is detected.
 */
class Solution {
    public boolean validTree(int n, int[][] edges) {

        boolean treeValid = false;

        if(edges.length == n - 1) {
            int[] setAssociation = new int[n];

            for(int index = 0 ; index < n ; index++) {
                setAssociation[index] = index;
            }

            treeValid = true;

            for(int[] edge : edges) {
                if(!union(setAssociation, edge[0], edge[1])) {
                    treeValid = false;
                    break;
                }
            }
        }

        return treeValid;
    }

    private boolean union(int[] setAssociation, int node1, int node2) {
        int rootOfNode1 = find(setAssociation, node1);
        int rootOfNode2 = find(setAssociation, node2);

        boolean unionValid;
        if(rootOfNode1 == rootOfNode2) {
            unionValid = false;
        }
        else {
            setAssociation[rootOfNode1] = rootOfNode2;
            unionValid = true;
        }

        return unionValid;
    }

    private int find(int[] set, int node) {

        int currNode = node;

        while(set[currNode] != currNode) {
            currNode = set[currNode];
        }

        return currNode;
    }
}

/*
Time: O(4*n) or O(n * alpha(n)), alpha is the inverse ackermann function
Space: O(n)

Optimizing the above approach, by ensuring that the smaller tree is attached to the bigger one, and also
making sure to minimize the gap between a node and its root by setting the value of parent directly equal to
the root.
 */
class Solution {
    public boolean validTree(int n, int[][] edges) {

        boolean treeValid = false;

        if(edges.length == n - 1) {
            int[] setAssociation = new int[n];

            for(int index = 0 ; index < n ; index++) {
                setAssociation[index] = index;
            }

            treeValid = true;

            for(int[] edge : edges) {
                if(!union(setAssociation, edge[0], edge[1])) {
                    treeValid = false;
                    break;
                }
            }
        }

        return treeValid;
    }

    private boolean union(int[] setAssociation, int node1, int node2) {
        List<Integer> nodesInComponent1 = find(setAssociation, node1);
        List<Integer> nodesInComponent2 = find(setAssociation, node2);

        int rootOfNode1 = nodesInComponent1.get(nodesInComponent1.size() - 1);
        int rootOfNode2 = nodesInComponent2.get(nodesInComponent2.size() - 1);

        boolean unionValid;
        if(rootOfNode1 == rootOfNode2) {
            unionValid = false;
        }
        else {
            int comp1Size = nodesInComponent1.size();
            int comp2Size = nodesInComponent2.size();

            if(comp1Size > comp2Size) {
                int temp = rootOfNode1;
                rootOfNode1 = rootOfNode2;
                rootOfNode2 = temp;

                List<Integer> tempComps = nodesInComponent1;
                nodesInComponent1 = nodesInComponent2;
                nodesInComponent2 = tempComps;
            }

            setAssociation[rootOfNode1] = rootOfNode2;

            for(int node : nodesInComponent1) {
                setAssociation[node] = rootOfNode2;
            }

            unionValid = true;
        }

        return unionValid;
    }

    private List<Integer> find(int[] set, int node) {

        int currNode = node;

        List<Integer> nodesEncountered = new LinkedList<Integer>();
        nodesEncountered.addLast(currNode);

        while(set[currNode] != currNode) {
            currNode = set[currNode];
            nodesEncountered.addLast(currNode);
        }

        return nodesEncountered;
    }
}

/*
Time: O(n)
Space: O(n)

Optimization of the above optimization
 */
class Solution {
    public boolean validTree(int n, int[][] edges) {

        boolean treeValid = false;

        if(edges.length == n - 1) {
            int[] setAssociation = new int[n];
            int[] sizeOfSets = new int[n];
            Arrays.fill(sizeOfSets, 1);

            for(int index = 0 ; index < n ; index++) {
                setAssociation[index] = index;
            }

            treeValid = true;

            for(int[] edge : edges) {
                if(!union(setAssociation, edge[0], edge[1], sizeOfSets)) {
                    treeValid = false;
                    break;
                }
            }
        }

        return treeValid;
    }

    private boolean union(int[] setAssociation, int node1, int node2, int[] setSizes) {
        int rootOfNode1 = find(setAssociation, node1);
        int rootOfNode2 = find(setAssociation, node2);

        boolean unionValid;
        if(rootOfNode1 == rootOfNode2) {
            unionValid = false;
        }
        else {
            int comp1Size = setSizes[rootOfNode1];
            int comp2Size = setSizes[rootOfNode2];

            if(comp1Size > comp2Size) {
                setAssociation[rootOfNode2] = rootOfNode1;
                setSizes[rootOfNode1] += setSizes[rootOfNode2];
            }
            else {
                setAssociation[rootOfNode1] = rootOfNode2;
                setSizes[rootOfNode2] += setSizes[rootOfNode1];
            }

            unionValid = true;
        }

        return unionValid;
    }

    private int find(int[] set, int node) {

        int currNode = node;

        while(set[currNode] != currNode) {
            currNode = set[currNode];
        }

        int root = currNode;
        currNode = node;

        while(currNode != root) {
            int nextNode = set[currNode];
            set[currNode] = root;
            currNode = nextNode;
        }

        return root;
    }
}

/*
Time: O(n + m)
Space: O(n)

Traversing the tree in a BFS manner, saving the nodes that have been visited, and recording the parent which
was used while entering a node to detect the presence of cycles.
 */
class Solution {
    public boolean validTree(int n, int[][] edges) {

        boolean treeValid;

        if(n > 1) {
            if(edges.length == n - 1) {
                List<List<Integer>> tree = generateTree(n, edges);
                treeValid = true;

                List<Integer> nodesToVisit = new LinkedList<Integer>();
                nodesToVisit.add(0);

                Map<Integer, Integer> entryPointOfNodes = new HashMap<Integer, Integer>();
                int[] nodesVisited = new int[n];

                while(!nodesToVisit.isEmpty()) {
                    int currentNode = nodesToVisit.removeFirst();
                    if(nodesVisited[currentNode] == 1) {
                        treeValid = false;
                        break;
                    }

                    nodesVisited[currentNode] = 1;

                    List<Integer> currNodesNeighbors = tree.get(currentNode);
                    int entryPointOfCurrentNode = entryPointOfNodes.getOrDefault(currentNode, -1);

                    for(int currNeighbor : currNodesNeighbors) {
                        if(entryPointOfCurrentNode == currNeighbor) {
                            continue;
                        }

                        entryPointOfNodes.put(currNeighbor, currentNode);
                        nodesToVisit.addLast(currNeighbor);
                    }
                }

                if(treeValid) {
                    for(int currNodeVisitStatus : nodesVisited) {
                        if(currNodeVisitStatus == 0) {
                            treeValid = false;
                            break;
                        }
                    }
                }
            }
            else {
                treeValid = false;
            }
        }
        else {
            treeValid = true;
        }

        return treeValid;
    }

    private List<List<Integer>> generateTree(int n, int[][] edges) {
        List<List<Integer>> tree = new ArrayList<List<Integer>>(n);

        for(int index = 0 ; index < n ; index++) {
            tree.add(new LinkedList<Integer>());
        }

        for(int[] edge : edges) {
            int node1 = edge[0], node2 = edge[1];
            tree.get(node1).add(node2);
            tree.get(node2).add(node1);
        }

        return tree;
    }
}

/*
Time: O(n + m)
Space: O(n)

Iterative DFS version of above approach
 */
class Solution {
    public boolean validTree(int n, int[][] edges) {

        boolean treeValid;

        if(n > 1) {
            List<List<Integer>> tree = generateTree(n, edges);

            List<Integer> nodesToVisit = new LinkedList<Integer>();
            nodesToVisit.addLast(0);

            Map<Integer, Integer> directedEdgeVisited = new HashMap<Integer, Integer>();
            Set<Integer> visitedNodes = new HashSet<Integer>();

            treeValid = true;

            while(!nodesToVisit.isEmpty()) {
                int currentNode = nodesToVisit.removeFirst();

                if(visitedNodes.contains(currentNode)) {
                    treeValid = false;
                    break;
                }

                visitedNodes.add(currentNode);

                List<Integer> neighborsOfCurrentNode = tree.get(currentNode);
                int edgeTakenToVisitCurrentNode = directedEdgeVisited.getOrDefault(currentNode, -1);

                for(int currNeighbor : neighborsOfCurrentNode) {

                    if(edgeTakenToVisitCurrentNode != currNeighbor) {
                        nodesToVisit.add(currNeighbor);
                        directedEdgeVisited.put(currNeighbor, currentNode);
                    }
                }
            }

            if(treeValid) {
                treeValid = visitedNodes.size() == n;
            }
        }
        else {
            treeValid = true;
        }

        return treeValid;
    }

    private List<List<Integer>> generateTree(int n, int[][] edges) {
        List<List<Integer>> tree = new ArrayList<List<Integer>>(n);

        for(int index = 0 ; index < n ; index++) {
            tree.add(new LinkedList<Integer>());
        }

        for(int[] edge : edges) {
            int node1 = edge[0], node2 = edge[1];
            tree.get(node1).add(node2);
            tree.get(node2).add(node1);
        }

        return tree;
    }
}


/*
Time: O(n^2)
Space: O(n)

Maintain a record of the set to which each node belongs
If the two nodes of an edge belong to the same set, cycle is present. Moreover, if at the end all nodes are not in the
same set, the graph is not connected
 */
class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] membership = new int[n];

        for(int index = 0 ; index < n ; index++) {
            membership[index] = index;
        }

        boolean treeValid = true;

        for(int[] currEdge : edges) {
            if(membership[currEdge[0]] == membership[currEdge[1]]) {
                treeValid = false;
                break;
            }
            union(membership, membership[currEdge[0]], membership[currEdge[1]]);
        }

        if(treeValid) {
            int setId = membership[0];

            for(int index = 1 ; index < n ; index++) {
                if(membership[index] != setId) {
                    treeValid = false;
                    break;
                }
            }
        }

        return treeValid;
    }

    private void union(int[] memberships, int group1, int group2) {
        for(int index = 0 ; index < memberships.length ; index++) {
            if(memberships[index] == group2) {
                memberships[index] = group1;
            }
        }
    }
}
