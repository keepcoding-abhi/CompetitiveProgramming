import java.util.*;

public class DijkstrasAlgorithm {
}
 /*
 Time: O((n + e) * Log(n + e))
 Space: O(n + e)

Using a priority queue to extract the nearest node in the graph using Dijkstra's algorithm
  */
class Solution {
    public Map<Integer, Integer> shortestPath(int n,
                                              List<List<Integer>> edges, int src) {
        List<List<int[]>> graph = generateGraph(n, edges);

        Map<Integer, Integer> shortestPathCosts =
                performDijkstra(graph, src);

        for(int index = 0 ; index < n ; index++) {
            if(!shortestPathCosts.containsKey(index)) {
                shortestPathCosts.put(index, -1);
            }
        }

        return shortestPathCosts;
    }

    private Map<Integer, Integer> performDijkstra(
            List<List<int[]>> graph, int source) {
        int nNodes = graph.size();
        boolean[] visited = new boolean[nNodes];

        PriorityQueue<int[]> nodesToVisit =
                new PriorityQueue<int[]>((int[] a, int[] b) -> {
                    return a[1] - b[1];
                });

        nodesToVisit.add(new int[]{source, 0});
        Map<Integer, Integer> shortestPaths =
                new HashMap<Integer, Integer>();

        while(!nodesToVisit.isEmpty()) {
            int[] currNode = nodesToVisit.remove();

            if(!shortestPaths.containsKey(currNode[0])) {
                shortestPaths.put(currNode[0], currNode[1]);
                List<int[]> neighborsOfSrc = graph.get(currNode[0]);

                for(int[] neighbor : neighborsOfSrc) {
                    nodesToVisit.add(new int[]{neighbor[0],
                            currNode[1] + neighbor[1]});
                }
            }
        }

        return shortestPaths;
    }

    private List<List<int[]>> generateGraph(int nNodes,
                                            List<List<Integer>> edges) {

        List<List<int[]>> graph = new ArrayList<List<int[]>>(nNodes);

        for(int index = 0 ; index < nNodes ; index++) {
            graph.add(new LinkedList<int[]>());
        }

        for(List<Integer> edge : edges) {
            int[] edgeRep = new int[2];
            edgeRep[0] = edge.get(1);
            edgeRep[1] = edge.get(2);
            graph.get(edge.get(0)).add(edgeRep);
        }

        return graph;
    }
}


