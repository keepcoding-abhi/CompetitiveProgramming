import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AlienDictionary {
}

/*
Time: O(n * AvgSizeOfWord)
Space: O(n * AvgSizeOfWord, n - 1)

Build a graph based on the ordering implied from adjacent words.
Perform topological sort to determine the correct ordering.
 */

class Solution {

    class Node {
        int indegree;
        List<Integer> neighbors;

        Node() {
            indegree = -1;
            neighbors = new LinkedList<Integer>();
        }
    }

    public String alienOrder(String[] words) {

        boolean singletonCase = true;

        for(int index = 0 ; index < words.length ; index++) {
            if(words[index].length() != 1) {
                singletonCase = false;
                break;
            }
        }

        if(singletonCase) {
            for(int index = 1 ; index < words.length ; index++) {
                if(words[index].charAt(0) != words[index - 1].charAt(0)) {
                    singletonCase = false;
                    break;
                }
            }
        }

        String result;
        if(singletonCase) {
            result = words[0].charAt(0) + "";
        }
        else {
            List<Integer> nodesToVisit = new LinkedList<Integer>();

            List<Node> graph = generateGraph(words);

            if(graph == null) {
                result = "";
            }
            else {
                for(int index = 0 ; index < 26 ; index++) {
                    Node currNode = graph.get(index);

                    if(currNode.indegree == 0) {
                        nodesToVisit.add(index);
                    }
                }

                StringBuilder properOrder = new StringBuilder();

                while(!nodesToVisit.isEmpty()) {
                    int currNodeIndex = nodesToVisit.removeFirst();
                    properOrder.append((char)('a' + currNodeIndex));

                    Node currNode = graph.get(currNodeIndex);

                    for(int currNeighbor : currNode.neighbors) {
                        Node neighborNode = graph.get(currNeighbor);
                        neighborNode.indegree--;

                        if(neighborNode.indegree == 0) {
                            nodesToVisit.add(currNeighbor);
                        }
                    }
                }

                Boolean orderExists = true;
                int index = 0;

                for(Node node : graph) {

                    if(node.indegree > 0) {
                        orderExists = false;
                        break;
                    }
                    else if(node.indegree == -2) {
                        properOrder.append((char)('a' + index));
                    }

                    index++;
                }

                if(orderExists) {
                    result = properOrder.toString();
                }
                else {
                    result = "";
                }
            }
        }

        return result;
    }

    private List<Node> generateGraph(String[] words) {

        List<Node> graph = new ArrayList<Node>(26);

        for(int index = 0 ; index < 26 ; index++) {
            graph.add(new Node());
        }

        int nWords = words.length;

        boolean invalidOrdering = false;

        for(int index = 0 ; index < nWords - 1 ; index++) {
            String currWord = words[index], nextWord = words[index + 1];

            int lastIndex = Math.min(currWord.length(), nextWord.length()) - 1;
            int currIndex = 0;

            while(currIndex <= lastIndex) {

                if(currWord.charAt(currIndex) != nextWord.charAt(currIndex)) {

                    if(graph.get(currWord.charAt(currIndex) - 'a').indegree == -1) {
                        graph.get(currWord.charAt(currIndex) - 'a').indegree = 0;
                    }

                    if(graph.get(nextWord.charAt(currIndex) - 'a').indegree == -1) {
                        graph.get(nextWord.charAt(currIndex) - 'a').indegree = 0;
                    }

                    graph.get(currWord.charAt(currIndex) - 'a').neighbors.add(nextWord.charAt(currIndex) - 'a');
                    graph.get(nextWord.charAt(currIndex) - 'a').indegree++;
                    break;
                }

                currIndex++;
            }

            if(currIndex > lastIndex) {
                if(currWord.length() > nextWord.length()) {
                    invalidOrdering = true;
                    break;
                }
            }
        }

        if(invalidOrdering) {
            graph = null;
        }
        else {
            for(String word : words) {
                for(int index = 0, wordLen = word.length() ; index < wordLen ; index++) {
                    if(graph.get(word.charAt(index) - 'a').indegree == -1) {
                        graph.get(word.charAt(index) - 'a').indegree = -2;
                    }
                }
            }
        }

        return graph;
    }
}
