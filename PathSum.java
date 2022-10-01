public class PathSum {
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
    public boolean hasPathSum(TreeNode root, int targetSum) {

        boolean pathSumFound = false;

        if(root != null) {
            int reducedSum = targetSum - root.val;

            if(root.left == null && root.right == null) {
                if(reducedSum == 0) {
                    pathSumFound = true;
                }
                else {
                    pathSumFound = false;
                }
            }
            else if(!pathSumFound) {
                if(root.left != null) {
                    pathSumFound = hasPathSum(root.left, reducedSum);
                }

                if(!pathSumFound && root.right != null) {
                    pathSumFound = hasPathSum(root.right, reducedSum);
                }
            }
        }

        return pathSumFound;
    }
}


//2) Slower, iterative approach

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
    public boolean hasPathSum(TreeNode root, int targetSum) {

        boolean pathSumFound = false;

        if(root != null) {

            Stack<Integer> sumStack = new Stack<Integer>();
            Stack<TreeNode> nodeStack = new Stack<TreeNode>();

            int currentSum = targetSum;
            TreeNode currentNode = root;
            nodeStack.push(root);
            sumStack.push(currentSum);

            while(!nodeStack.isEmpty()) {
                currentNode = nodeStack.pop();
                currentSum = sumStack.pop() - currentNode.val;

                if(currentNode.left == null && currentNode.right == null) {
                    if(currentSum == 0) {
                        pathSumFound = true;
                        break;
                    }
                }

                if(currentNode.left != null) {
                    nodeStack.push(currentNode.left);
                    sumStack.push(currentSum);
                }

                if(currentNode.right != null) {
                    nodeStack.push(currentNode.right);
                    sumStack.push(currentSum);
                }
            }
        }

        return pathSumFound;
    }
}