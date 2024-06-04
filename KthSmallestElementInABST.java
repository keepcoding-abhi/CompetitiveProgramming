import java.util.Deque;
import java.util.LinkedList;

public class KthSmallestElementInABST {
}

/*
Time: O(n)
Space: O(n)

Performing an iterative pre-order traversal of the nodes in BST and stopping when k nodes have been processed.
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        TreeNode dummyNode = new TreeNode();
        dummyNode.right = root;

        Deque<TreeNode> nodesToVisit = new LinkedList<TreeNode>();
        int kthSmallest = -1;
        int currentIndex = 0;
        nodesToVisit.push(dummyNode);

        while(!nodesToVisit.isEmpty()) {
            TreeNode currentNode = nodesToVisit.pop();

            if(currentIndex == k) {
                kthSmallest = currentNode.val;
                break;
            }
            else {
                currentNode = currentNode.right;

                while(currentNode != null) {
                    nodesToVisit.push(currentNode);
                    currentNode = currentNode.left;
                }
            }

            currentIndex++;
        }

        return kthSmallest;
    }
}

/*
Time: O(n)
Space: O(n)

Doing a pre-order traversal of the BST and stopping after k nodes have been visited.
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        return getKthSmallest(root, 0, k)[1];
    }

    private int[] getKthSmallest(TreeNode root, int prevElIndex, int k) {
        int[] currVal = null;

        if(root != null) {

            if(root.left != null) {
                int[] prevElVal = getKthSmallest(root.left, prevElIndex, k);

                if(prevElVal[0] == k) {
                    currVal = prevElVal;
                }
                else {
                    currVal = new int[]{prevElVal[0] + 1, root.val};
                }
            }
            else {
                currVal = new int[]{prevElIndex + 1, root.val};
            }

            if(currVal[0] != k && root.right != null) {
                currVal = getKthSmallest(root.right, currVal[0], k);
            }
        }

        return currVal;
    }
}
