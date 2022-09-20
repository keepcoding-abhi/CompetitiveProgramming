public class BinaryTreeInorderTraversal {
}

//1) Simple and slower recursive solution.
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderTraversal = new LinkedList<Integer>();
        doInorderTraversal(inorderTraversal, root);
        return inorderTraversal;
    }

    public void doInorderTraversal(List<Integer> inorderTraversal, TreeNode currentNode) {
        if(currentNode != null) {
            doInorderTraversal(inorderTraversal, currentNode.left);
            inorderTraversal.add(currentNode.val);
            doInorderTraversal(inorderTraversal, currentNode.right);
        }
    }
}

//2) Iteratively performing pre-order traversal.

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderTraversal = new LinkedList<Integer>();

        Stack<TreeNode> preOrderStack = new Stack<TreeNode>();

        TreeNode currentNode = root;
        while(currentNode != null) {
            preOrderStack.push(currentNode);
            currentNode = currentNode.left;
        }

        while(!preOrderStack.isEmpty()) {
            currentNode = preOrderStack.pop();

            inorderTraversal.add(currentNode.val);

            currentNode = currentNode.right;

            while(currentNode != null) {
                preOrderStack.push(currentNode);
                currentNode = currentNode.left;
            }
        }

        return inorderTraversal;
    }
}

//3 Better implementation of iterative approach.

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderTraversal = new LinkedList<Integer>();

        Stack<TreeNode> preOrderStack = new Stack<TreeNode>();

        TreeNode currentNode = root;

        while(currentNode != null || !preOrderStack.isEmpty()) {
            while(currentNode != null) {
                preOrderStack.push(currentNode);
                currentNode = currentNode.left;
            }

            currentNode = preOrderStack.pop();

            inorderTraversal.add(currentNode.val);

            currentNode = currentNode.right;
        }

        return inorderTraversal;
    }
}
