public class MaxDepthOfBinaryTree {
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {

        int currentNodeHeight = 0;
        if(root == null) {
            currentNodeHeight = 0;
        }
        else if(root.left == null && root.right == null) {
            currentNodeHeight = 1;
        }
        else {

            int maxHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);

            if(maxHeight < rightHeight) {
                maxHeight = rightHeight;
            }

            currentNodeHeight = maxHeight + 1;
        }

        return currentNodeHeight;
    }
}

