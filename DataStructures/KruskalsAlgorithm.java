package DataStructures;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KruskalsAlgorithm {
}

/*
Time: O(e * Log (e) + e * alpha(n))
Space: O(e) to O(Log(e)) for sorting + O(n) for union find

Sort the edges and use union find data structure to form an MST by excluding edges that join nodes that are in the
same subtree.
 */
class UnionFind {
    private int[] setAssociations;
    private int[] setSizes;

    public UnionFind(int n) {
        setAssociations = new int[n];
        setSizes = new int[n];

        Arrays.fill(setSizes, 1);

        for(int index = 0 ; index < n ; index++) {
            setAssociations[index] = index;
        }
    }

    public int find(int setId) {
        int currNode = setId;

        while(currNode != setAssociations[currNode]) {
            currNode = setAssociations[currNode];
        }

        int parentOfCurrNode = currNode;

        while(setAssociations[currNode] != currNode) {
            int nextNode = setAssociations[currNode];
            setAssociations[currNode] = parentOfCurrNode;
            currNode = nextNode;
        }

        return parentOfCurrNode;
    }

    public boolean union(int node1, int node2) {
        int parentOfNode1 = find(node1);
        int parentOfNode2 = find(node2);

        boolean unionSuccessful;

        if(parentOfNode1 == parentOfNode2) {
            unionSuccessful = false;
        }
        else {
            unionSuccessful = true;

            if(setSizes[parentOfNode1] < setSizes[parentOfNode2]) {
                setAssociations[parentOfNode1] = parentOfNode2;
                setSizes[parentOfNode2] += setSizes[parentOfNode1];
            }
            else {
                setAssociations[parentOfNode2] = parentOfNode1;
                setSizes[parentOfNode1] += setSizes[parentOfNode2];
            }
        }

        return unionSuccessful;
    }
}

class Solution {
    public int minimumSpanningTree(List<List<Integer>> edges, int n) {
        Collections.sort(edges, (List<Integer> a,
                                 List<Integer> b) -> {
            return a.get(2) - b.get(2);
        });

        int mstCost = 0, edgesAdded = 0;
        UnionFind nodeAssociations = new UnionFind(n);

        for(List<Integer> edge : edges) {
            if(nodeAssociations.union(edge.get(0), edge.get(1))) {
                mstCost += edge.get(2);
                edgesAdded++;
            }
        }

        if(edgesAdded != n - 1) {
            mstCost = -1;
        }

        return mstCost;
    }
}

