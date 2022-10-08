public class SymmetricTree {

    //1) Faster recursive approach.
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode leftNode, TreeNode rightNode) {
        boolean mirror;

        if(leftNode != null && rightNode != null) {

            if(leftNode.val == rightNode.val) {
                mirror = isMirror(leftNode.left, rightNode.right);

                if(mirror) {
                    mirror = isMirror(leftNode.right, rightNode.left);
                }
            }
            else {
                mirror = false;
            }

        }
        else if((leftNode == null) && (rightNode == null)) {
            mirror = true;
        }
        else {
            mirror = false;
        }

        return mirror;
    }

    //2) Iterative approach
    public boolean isSymmetric(TreeNode root) {

        List<TreeNode> scannedNodes = new LinkedList<TreeNode>();
        boolean symmetric = true;

        if(root != null) {
            scannedNodes.add(root.left);
            scannedNodes.add(root.right);

            while(!scannedNodes.isEmpty()) {
                TreeNode leftSubtreeNode = scannedNodes.remove(0);
                TreeNode rightSubtreeNode = scannedNodes.remove(0);

                if(leftSubtreeNode != null && rightSubtreeNode != null) {

                    if(leftSubtreeNode.val != rightSubtreeNode.val) {
                        symmetric = false;
                        break;
                    }

                    scannedNodes.add(leftSubtreeNode.left);
                    scannedNodes.add(rightSubtreeNode.right);

                    scannedNodes.add(leftSubtreeNode.right);
                    scannedNodes.add(rightSubtreeNode.left);
                }
                else if(leftSubtreeNode != null || rightSubtreeNode != null) {
                    symmetric = false;
                    break;
                }
            }
        }

        return symmetric;
    }
}
