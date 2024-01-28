public class DiameterOfBinaryTree {
    /*
    Time: O(n)
    Space: O(n)

    At each level compute the length of the longest possible branch based on the height of left and right sub trees
    and also compute the height of the node for analysis by upper nodes.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        return computeLongestBranchAndLeaf(root)[1];
    }

    private int[] computeLongestBranchAndLeaf(TreeNode root) {
        int[] result = new int[2];

        if(root != null) {
            int[] longestBranchOnLeft = computeLongestBranchAndLeaf(root.left);
            int[] longestBranchOnRight = computeLongestBranchAndLeaf(root.right);

            int leftHeight = longestBranchOnLeft[0];
            int rightHeight = longestBranchOnRight[0];

            result[0] = Math.max(leftHeight, rightHeight) + 1;
            result[1] = Math.max(Math.max(leftHeight + rightHeight, longestBranchOnLeft[1]), longestBranchOnRight[1]);
        }

        return result;
    }
}
