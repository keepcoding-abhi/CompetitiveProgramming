import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {

    /*
    Time : O(n)
    Space : O(n)
    BFS traversal
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelByLevel = new LinkedList<List<Integer>>();

        if(root != null) {
            List<TreeNode> currentLevel = new LinkedList<TreeNode>();
            currentLevel.add(root);

            List<TreeNode> nextLevel = new LinkedList<TreeNode>();
            List<Integer> currentLevelNums = new LinkedList<Integer>();

            while(!currentLevel.isEmpty() || !nextLevel.isEmpty()) {
                if(currentLevel.isEmpty()) {
                    levelByLevel.add(currentLevelNums);
                    currentLevel = nextLevel;
                    nextLevel = new LinkedList<TreeNode>();
                    currentLevelNums = new LinkedList<Integer>();
                }

                TreeNode currentNode = currentLevel.remove(0);
                currentLevelNums.add(currentNode.val);

                if(currentNode.left != null) {
                    nextLevel.add(currentNode.left);
                }

                if(currentNode.right != null) {
                    nextLevel.add(currentNode.right);
                }
            }

            levelByLevel.add(currentLevelNums);
        }

        return levelByLevel;
    }

    /*
    Time : O(n)
    Space : O(n)
    BFS using only one queue. Recording the number of nodes in current level and fetching only those many nnodes
    from the current level.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelByLevel = new LinkedList<List<Integer>>();

        if(root != null) {
            List<TreeNode> currentLevelNodes = new LinkedList<TreeNode>();
            currentLevelNodes.add(root);

            while(!currentLevelNodes.isEmpty()) {

                List<Integer> currentLevelNums = new LinkedList<Integer>();
                levelByLevel.add(currentLevelNums);

                int numNodesInCurrentLevel = currentLevelNodes.size();
                for(int index = 0 ; index < numNodesInCurrentLevel ; index++) {
                    TreeNode currentNode = currentLevelNodes.remove(0);

                    currentLevelNums.add(currentNode.val);

                    if(currentNode.left != null) {
                        currentLevelNodes.add(currentNode.left);
                    }

                    if(currentNode.right != null) {
                        currentLevelNodes.add(currentNode.right);
                    }
                }

            }
        }

        return levelByLevel;
    }

    /*
    Time : O(n)
    Space : O(n)
    Using DFS traversal by tracking the level number at which we currently are.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelByLevel = new LinkedList<List<Integer>>();
        trackLevels(root, levelByLevel, 0);

        return levelByLevel;
    }

    private void trackLevels(TreeNode root, List<List<Integer>> levels, int currentLevel) {
        if(root != null) {
            if(currentLevel == levels.size()) {
                levels.add(new LinkedList<Integer>());
            }

            levels.get(currentLevel).add(root.val);
            trackLevels(root.left, levels, currentLevel + 1);
            trackLevels(root.right, levels, currentLevel + 1);
        }
    }
}
