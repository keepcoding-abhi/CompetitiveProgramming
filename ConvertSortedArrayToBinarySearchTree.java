public class ConvertSortedArrayToBinarySearchTree {
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
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = buildBalancedTree(nums, 0, nums.length - 1);

        return root;
    }

    private TreeNode buildBalancedTree(int nums[], int low, int high) {

        TreeNode currentSubTreeRoot = null;
        if(low <= high) {
            int mid = (low + high) / 2;

            currentSubTreeRoot = new TreeNode(nums[mid]);
            currentSubTreeRoot.left = buildBalancedTree(nums, low, mid - 1);
            currentSubTreeRoot.right = buildBalancedTree(nums, mid + 1, high);
        }

        return currentSubTreeRoot;
    }
}
