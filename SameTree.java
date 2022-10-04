public class SameTree {
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
    public boolean isSameTree(TreeNode p, TreeNode q) {

        boolean sameTree = true;

        if(p != null && q != null) {
            if(p.val == q.val) {
                sameTree = isSameTree(p.left, q.left);

                if(sameTree) {
                    sameTree = isSameTree(p.right, q.right);
                }
            }
            else {
                sameTree = false;
            }
        }
        else if(p == null && q == null) {
            sameTree = true;
        }
        else {
            sameTree = false;
        }

        return sameTree;
    }
}
