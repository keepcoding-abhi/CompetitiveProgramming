import java.util.Deque;
import java.util.LinkedList;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }


public class MaximumDepthOfBinaryTree {
    /*
    Time : O(n)
    Space : O(n)
    Traversing the tree recursively in a post-order manner.
     */
    public int maxDepth(TreeNode root) {
        int highestDepth = 0;
        if(root != null) {
            highestDepth = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }

        return highestDepth;
    }

    class StackEntry {
        TreeNode node;
        int leftSubTreeDepth, rightSubTreeDepth;

        StackEntry(TreeNode node) {
            this.node = node;
            leftSubTreeDepth = -1;
            rightSubTreeDepth = -1;
        }
    }

    /*
    Time : O(n)
    Space : O(n)
    Iterative version of the previous approach.
     */
    public int maxDepth(TreeNode root) {
        Deque<StackEntry> treeStack = new LinkedList<StackEntry>();

        TreeNode currentNode = root;

        while(currentNode != null) {
            treeStack.push(new StackEntry(currentNode));
            currentNode = currentNode.left;
        }

        int prevDepth = 0;
        StackEntry topofStack = null;
        while(!treeStack.isEmpty()) {

            topofStack = treeStack.pop();
            currentNode = topofStack.node;

            if(topofStack.leftSubTreeDepth == -1) {

                topofStack.leftSubTreeDepth = prevDepth;
                treeStack.push(topofStack);
                currentNode = currentNode.right;

                while(currentNode != null) {
                    treeStack.push(new StackEntry(currentNode));
                    currentNode = currentNode.left;
                }
                prevDepth = 0;
            }
            else {
                topofStack.rightSubTreeDepth = prevDepth;
                prevDepth = Math.max(topofStack.leftSubTreeDepth, topofStack.rightSubTreeDepth) + 1;
            }
        }

        return prevDepth;
    }

    /*
    Time : O(n)
    Space : O(n)
    A more straightforward iterative version. Making use of the fact that we don't need to come back to a node
    that has already been traversed if we know its depth which can be saved when that node gets pushed on stack.
     */
    class StackEntry {
        TreeNode node;
        int depth;

        StackEntry(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int maxDepth(TreeNode root) {
        Deque<StackEntry> treeStack = new LinkedList<StackEntry>();

        int maxDepth = 0;

        if(root != null) {
            maxDepth = 1;
            treeStack.push(new StackEntry(root, 1));

            while(!treeStack.isEmpty()) {
                StackEntry topOfStack = treeStack.pop();
                if(topOfStack.node.left == null && topOfStack.node.right == null) {
                    maxDepth = Math.max(maxDepth, topOfStack.depth);
                }
                else {
                    if(topOfStack.node.left != null) {
                        treeStack.push(new StackEntry(topOfStack.node.left, topOfStack.depth + 1));
                    }

                    if(topOfStack.node.right != null) {
                        treeStack.push(new StackEntry(topOfStack.node.right, topOfStack.depth + 1));
                    }
                }
            }
        }

        return maxDepth;
    }
}
