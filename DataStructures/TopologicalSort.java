package DataStructures;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TopologicalSort {
    /*
        Time: O(n + e)
        Space: O(n + e)

        Recording the in-degrees of each vertex and then processing the nodes which have an in-degree of zero
     */
    class GraphNode {
        int indegree;
        List<Integer> neighbors;

        public GraphNode() {
            indegree = 0;
            neighbors = new LinkedList<Integer>();
        }
    }

    class Solution {
        public List<Integer> topologicalSort(int n, int[][] edges) {
            GraphNode[] graph = generateGraph(n, edges);

            Deque<Integer> nodesToVisit = new LinkedList<Integer>();

            for(int index = 0 ; index < graph.length ; index++) {
                if(graph[index].indegree == 0) {
                    nodesToVisit.addLast(index);
                }
            }

            List<Integer> sortedOrder = new LinkedList<Integer>();

            while(!nodesToVisit.isEmpty()) {
                int currNode = nodesToVisit.removeFirst();
                sortedOrder.add(currNode);

                List<Integer> neighborsOfCurrNode =
                        graph[currNode].neighbors;

                for(int currNeighbor : neighborsOfCurrNode) {
                    graph[currNeighbor].indegree--;

                    if(graph[currNeighbor].indegree == 0) {
                        nodesToVisit.addLast(currNeighbor);
                    }
                }
            }

            if(sortedOrder.size() != n) {
                sortedOrder = new ArrayList<Integer>(0);
            }

            return sortedOrder;
        }

        private GraphNode[] generateGraph(int n, int[][] edges) {
            GraphNode[] graph = new GraphNode[n];

            for(int index = 0 ; index < n ; index++) {
                graph[index] = new GraphNode();
            }

            for(int[] edge : edges) {
                graph[edge[0]].neighbors.add(edge[1]);
                graph[edge[1]].indegree++;
            }

            return graph;
        }
    }
}
