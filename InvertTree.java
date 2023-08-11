import java.util.Deque;
import java.util.LinkedList;

public class InvertTree {
    /*
    Time : O(n)
    Space : O(n)
    Out of place inversion.
     */
    public TreeNode invertTree(TreeNode root) {
        TreeNode invertedNode;

        if(root != null) {
            invertedNode = new TreeNode(root.val);
            invertedNode.left = invertTree(root.right);
            invertedNode.right = invertTree(root.left);
        }
        else {
            invertedNode = null;
        }

        return invertedNode;
    }

    /*
    Time : O(n)
    Space : O(n)
    In-place inversion.
     */
    public TreeNode invertTree(TreeNode root) {

        if(root != null) {
            TreeNode originalLeftChild = root.left;
            root.left = invertTree(root.right);
            root.right = invertTree(originalLeftChild);
        }

        return root;
    }

    /*
        Time : O(n)
        Space : O(n)
        Iterative version of the above approaches.
     */
    public TreeNode invertTree(TreeNode root) {

        Deque<TreeNode> treeStack = new LinkedList<TreeNode>();

        if(root != null) {
            treeStack.push(root);

            while(!treeStack.isEmpty()) {
                TreeNode currentNode = treeStack.pop();

                TreeNode originalLeftTree = currentNode.left;
                currentNode.left = currentNode.right;
                currentNode.right = originalLeftTree;

                if(currentNode.left != null) {
                    treeStack.push(currentNode.left);
                }

                if(currentNode.right != null) {
                    treeStack.push(currentNode.right);
                }
            }
        }

        return root;
    }
}
