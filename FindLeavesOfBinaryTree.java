public class FindLeavesOfBinaryTree {

    // Doing a single tree traversal and recording the height of each node.
    // Time : O(n), Space : O(n)
    public List<List<Integer>> findLeaves(TreeNode root) {

        List<List<Integer>> levelByLevelLeaves = new LinkedList<List<Integer>>();

        collectLeavesByHeight(root, levelByLevelLeaves);

        return levelByLevelLeaves;
    }

    private int collectLeavesByHeight(TreeNode currentNode, List<List<Integer>> leavesCollected) {

        int currentHeight;
        if(currentNode == null) {
            currentHeight = 0;
        }
        else {

            currentHeight = Math.max(collectLeavesByHeight(currentNode.left, leavesCollected), collectLeavesByHeight(currentNode.right, leavesCollected)) + 1;

            while(leavesCollected.size() < currentHeight) {
                leavesCollected.add(new LinkedList<Integer>());
            }

            leavesCollected.get(currentHeight - 1).add(currentNode.val);
        }

        return currentHeight;
    }

    // Traversing the tree and removing the leaves in each iteration.
    // Time : O(n^2), Space : O(n)
    public List<List<Integer>> findLeaves(TreeNode root) {

        List<List<Integer>> allLeavesRemoved = new LinkedList<List<Integer>>();
        while(!isLeaf(root)) {
            List<Integer> leavesRemovedInCurrentTraversal = new LinkedList<Integer>();
            removeLeaves(root, leavesRemovedInCurrentTraversal);
            allLeavesRemoved.add(leavesRemovedInCurrentTraversal);
        }
        List<Integer> rootLeaf = new ArrayList<Integer>(1);
        rootLeaf.add(root.val);

        allLeavesRemoved.add(rootLeaf);

        return allLeavesRemoved;
    }

    private void removeLeaves(TreeNode currentNode, List<Integer> leavesRemoved) {
        if(currentNode != null) {
            if(currentNode.left != null) {

                TreeNode leftChild = currentNode.left;
                if(isLeaf(leftChild)) {
                    leavesRemoved.add(leftChild.val);
                    currentNode.left = null;
                }
                else {
                    removeLeaves(leftChild, leavesRemoved);
                }
            }

            if(currentNode.right != null) {

                TreeNode rightChild = currentNode.right;
                if(isLeaf(rightChild)) {
                    leavesRemoved.add(rightChild.val);
                    currentNode.right = null;
                }
                else {
                    removeLeaves(rightChild, leavesRemoved);
                }
            }
        }
    }

    private boolean isLeaf(TreeNode currentNode) {

        boolean isLeaf = false;

        if (currentNode != null && currentNode.left == null && currentNode.right == null) {
            isLeaf = true;
        }

        return isLeaf;
    }
}
