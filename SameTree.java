import java.util.Deque;
import java.util.LinkedList;

public class SameTree {
}

class Solution {
    /*
    Time : O(n)
    Space : O(n)
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        boolean sameTree = true;

        if(p != null && q != null) {
            if(p.val == q.val) {
                sameTree = isSameTree(p.left, q.left);

                if(sameTree) {
                    sameTree = isSameTree(p.right, q.right);
                }
            }
            else {
                sameTree = false;
            }
        }
        else if(p == null && q == null) {
            sameTree = true;
        }
        else {
            sameTree = false;
        }

        return sameTree;
    }

    private boolean checkSameNodes(TreeNode p, TreeNode q) {
        boolean same;

        if(p == null && q == null) {
            same = true;
        }
        else if(p != null && q != null) {
            if(p.val == q.val /*&& ((p.left == null && q.left == null) || (p.left != null && q.left != null)) && ((p.right == null && q.right == null) || (p.right != null && q.right != null))*/) {
                same = true;
            }
            else {
                same = false;
            }
        }
        else {
            same = false;
        }

        return same;
    }

    /*
    Iterative version of the above approach.
    Time : O(n)
    Space : O(n)
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Deque<TreeNode> pNodesToProcess = new LinkedList<TreeNode>(), qNodesToProcess = new LinkedList<TreeNode>();

        boolean same = true;

        pNodesToProcess.push(p);
        qNodesToProcess.push(q);

        while(!pNodesToProcess.isEmpty() && !qNodesToProcess.isEmpty()) {
            TreeNode pNode = pNodesToProcess.pop();
            TreeNode qNode = qNodesToProcess.pop();
            if(checkSameNodes(pNode, qNode)) {
                if(pNode != null) {
                    pNodesToProcess.push(pNode.left);
                    pNodesToProcess.push(pNode.right);
                    qNodesToProcess.push(qNode.left);
                    qNodesToProcess.push(qNode.right);
                }
            }
            else {
                same = false;
                break;
            }
        }

        return same;
    }
}
