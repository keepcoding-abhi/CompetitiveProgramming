public class BinarySearchTreeIterator {

    /*
    Time : O(n)
    Space : O(n)

    hasNext : O(1) time and space
    next : O(n) time and space. The amortized cost using aggregate method is O(1) for each call.
    Using a stack to keep track of the nodes which are yet to be visited in the in-order traversal.
     */
    class BSTIterator {

        Deque<TreeNode> savedNodes;

        public BSTIterator(TreeNode root) {
            savedNodes = new LinkedList<TreeNode>();
            saveNodes(root);
        }

        private void saveNodes(TreeNode currentNode) {

            while(currentNode != null) {
                savedNodes.push(currentNode);
                currentNode = currentNode.left;
            }
        }

        public int next() {
            TreeNode nextNode = savedNodes.pop();

            saveNodes(nextNode.right);
            return nextNode.val;
        }

        public boolean hasNext() {
            return !savedNodes.isEmpty();
        }
    }

    /*
    Time : O(n), for constructor
    Space : O(n), for constructor

    However, each cach call to next and hasNext takes constant time.
    Doing in-order traversal of the whole tree and saving it in a list.
     */
    class BSTIterator {

        List<Integer> savedNodes;
        int currentIndex;

        public BSTIterator(TreeNode root) {
            savedNodes = new ArrayList<Integer>();
            currentIndex = 0;
            inOrderTraversal(root);
        }

        private void inOrderTraversal(TreeNode currentNode) {

            if(currentNode != null) {
                inOrderTraversal(currentNode.left);

                savedNodes.add(currentNode.val);

                inOrderTraversal(currentNode.right);
            }
        }

        public int next() {
            return savedNodes.get(currentIndex++);
        }

        public boolean hasNext() {
            return currentIndex < savedNodes.size() ? true : false;
        }
    }

}
