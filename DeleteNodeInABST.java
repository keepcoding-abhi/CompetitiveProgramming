public class DeleteNodeInABST {
    // Time complexity, Space complexity : O(heightOfTree)
    public TreeNode deleteNode(TreeNode root, int key) {

        TreeNode parentOfNodeToDelete = null, nodeToDelete = null;
        if(root != null && root.val == key) {
            nodeToDelete = root;
        }
        else {
            parentOfNodeToDelete = findParentOfNode(root, key);

            if(parentOfNodeToDelete != null){
                if(parentOfNodeToDelete.left != null && parentOfNodeToDelete.left.val == key) {
                    nodeToDelete = parentOfNodeToDelete.left;
                }
                else {
                    nodeToDelete = parentOfNodeToDelete.right;
                }
            }
        }

        if(nodeToDelete != null) {

            if(nodeToDelete.left == null && nodeToDelete.right == null) {
                if(nodeToDelete == root) {
                    root = null;
                }
                else if(parentOfNodeToDelete.left == nodeToDelete) {
                    parentOfNodeToDelete.left = null;
                }
                else {
                    parentOfNodeToDelete.right = null;
                }
            }
            else if(nodeToDelete.left != null && nodeToDelete.right != null) {
                int predecessor = removePredecessor(nodeToDelete);
                nodeToDelete.val = predecessor;
            }
            else if(nodeToDelete.left != null) {
                if(nodeToDelete == root) {
                    root = nodeToDelete.left;
                }
                else {
                    if(parentOfNodeToDelete.left == nodeToDelete) {
                        parentOfNodeToDelete.left = nodeToDelete.left;
                    }
                    else {
                        parentOfNodeToDelete.right = nodeToDelete.left;
                    }
                }
            }
            else {
                // Only child of node to be deleted is at right.
                if(nodeToDelete == root) {
                    root = nodeToDelete.right;
                }
                else {
                    if(parentOfNodeToDelete.left == nodeToDelete) {
                        parentOfNodeToDelete.left = nodeToDelete.right;
                    }
                    else {
                        parentOfNodeToDelete.right = nodeToDelete.right;
                    }
                }
            }
        }

        return root;
    }

    private int removePredecessor(TreeNode root) {
        TreeNode parentOfCurrentNode = root;
        TreeNode currentNode = root.left;

        while(currentNode.right != null) {
            parentOfCurrentNode = currentNode;
            currentNode = currentNode.right;
        }

        int predecessorVal = currentNode.val;

        if(parentOfCurrentNode == root) {
            parentOfCurrentNode.left = currentNode.left;
        }
        else {
            parentOfCurrentNode.right = currentNode.left;
        }

        return predecessorVal;
    }

    private TreeNode findParentOfNode(TreeNode currentNode, int key) {
        TreeNode parentOfTarget = null;
        if(currentNode != null) {
            if(key < currentNode.val && currentNode.left != null) {
                if(currentNode.left.val == key) {
                    parentOfTarget = currentNode;
                }
                else {
                    parentOfTarget = findParentOfNode(currentNode.left, key);
                }
            }
            else if(key > currentNode.val && currentNode.right != null) {
                if(currentNode.right.val == key) {
                    parentOfTarget = currentNode;
                }
                else {
                    parentOfTarget = findParentOfNode(currentNode.right, key);
                }
            }
        }

        return parentOfTarget;
    }
}
