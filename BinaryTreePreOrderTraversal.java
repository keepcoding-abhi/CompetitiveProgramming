public class BinaryTreePreOrderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new LinkedList<Integer>();

        preorderTraversal(preorder, root);

        return preorder;
    }

    //1) Recursive solution.
    private void preorderTraversal(List<Integer> order, TreeNode currentNode) {
        if(currentNode != null) {
            order.add(currentNode.val);
            preorderTraversal(order, currentNode.left);
            preorderTraversal(order, currentNode.right);
        }
    }

    //2) Iterative solution.
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new LinkedList<Integer>();

        Stack<TreeNode> treeTraversalStack = new Stack<TreeNode>();

        TreeNode currentNode = root;

        while(currentNode != null) {
            treeTraversalStack.push(currentNode);
            preorder.add(currentNode.val);
            currentNode = currentNode.left;
        }

        while(!treeTraversalStack.isEmpty()) {
            currentNode = treeTraversalStack.pop();
            currentNode = currentNode.right;

            while(currentNode != null) {
                treeTraversalStack.push(currentNode);
                preorder.add(currentNode.val);
                currentNode = currentNode.left;
            }

        }


        return preorder;
    }
}
