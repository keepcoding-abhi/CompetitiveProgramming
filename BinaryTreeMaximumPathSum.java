public class BinaryTreeMaximumPathSum {
    /*
    Time : O(n)
    Space : O(n)
    At each node, we could either choose a path that includes both left and right subtrees and passes through root.
    Or just left subtree and root, or just right subtree and root, or only root.
    But the first path, if selected will not allow any path from parent nodes to be included.
    Therefore, we use a separate variable which records the maximum path seen so far.
     */
    public int maxPathSum(TreeNode root) {
        return getMathSum(root)[1];
    }

    private int[] getMathSum(TreeNode root) {
        int[] result = new int[2];
        result[1] = Integer.MIN_VALUE;
        result[0] = Integer.MIN_VALUE;

        if(root != null) {
            int[] resultFromLeft = getMathSum(root.left);
            int[] resultFromRight = getMathSum(root.right);

            int sumFromLeft = resultFromLeft[0], sumFromRight = resultFromRight[0];

            int sumIncludingRoot = Math.max(sumFromLeft, 0) + Math.max(sumFromRight, 0) + root.val;

            result[0] = root.val;
            int maxSubtree = Math.max(sumFromLeft, sumFromRight);

            if(maxSubtree > 0) {
                result[0] += maxSubtree;
            }

            result[1] = resultFromLeft[1];
            result[1] = Math.max(result[1], resultFromRight[1]);
            result[1] = Math.max(result[1], sumIncludingRoot);
        }

        return result;
    }
}
