import java.util.LinkedList;
import java.util.List;

public class BinaryTreeRightSideView {

    /*
    Time: O(n)
    Space: O(n)

    Traverse the right side nodes and maintain a counter for the max height of last seen node.
    All nodes at this height are hidden by some node on the right.
    Include the first node greater than the last seen height in the results section.
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> viewableNodes = new LinkedList<Integer>();

        recordViewableFromRight(root, viewableNodes, 0, -1);

        return viewableNodes;
    }

    private int recordViewableFromRight(TreeNode root, List<Integer> viewable, int currentHeight, int lastSeenHeight) {

        if(root != null) {
            if(currentHeight > lastSeenHeight) {
                viewable.add(root.val);
                lastSeenHeight = currentHeight;
            }

            lastSeenHeight = recordViewableFromRight(root.right, viewable, currentHeight + 1, lastSeenHeight);

            lastSeenHeight = recordViewableFromRight(root.left, viewable, currentHeight + 1, lastSeenHeight);
        }

        return lastSeenHeight;
    }
}
