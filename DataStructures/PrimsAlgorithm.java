package DataStructures;

import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgorithm {
    /*
    Time: O(e * Log(e))
    Space: O(n^2)

    Using a matrix to store the nodes and edges and using a priority queue to fetch the least cost node.
     */
    public int minimumSpanningTree(List<List<Integer>> edges, int n) {
        int[][] graph = generateGraph(edges, n);

        int[] visitedNodes = new int[n];
        PriorityQueue<int[]> nodesToVisit =
                new PriorityQueue<int[]>((int[] a, int[] b) -> {
                    return a[1] - b[1];
                });

        int mstCost = 0, nodesVisited = 0;
        nodesToVisit.add(new int[]{0, 0});

        while(!nodesToVisit.isEmpty() && nodesVisited < n) {
            int[] currNode = nodesToVisit.remove();

            if(visitedNodes[currNode[0]] == 0) {
                visitedNodes[currNode[0]] = 1;
                mstCost += currNode[1];
                nodesVisited++;

                int[] neighborsOfCurrNode = graph[currNode[0]];
                for(int index = 0 ; index < n ; index++) {
                    if(neighborsOfCurrNode[index] != 0 &&
                            visitedNodes[index] == 0) {
                        nodesToVisit.add(new int[]{index, neighborsOfCurrNode[index]});
                    }
                }
            }
        }

        if(nodesVisited != n) {
            mstCost = -1;
        }

        return mstCost;
    }

    private int[][] generateGraph(List<List<Integer>> edges, int n) {
        int[][] graph = new int[n][n];

        for(List<Integer> edge : edges) {
            graph[edge.get(0)][edge.get(1)] = edge.get(2);
            graph[edge.get(1)][edge.get(0)] = edge.get(2);
        }

        return graph;
    }
}
