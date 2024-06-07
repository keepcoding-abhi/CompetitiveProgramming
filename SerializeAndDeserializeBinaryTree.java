import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializeAndDeserializeBinaryTree {
}

/*
Time: O(n)
Space: O(n)

Generate unique representation of a tree by following pre-order traversal and storing information about null neighbors
as well. The null values help in storing information about leaf nodes.
 */
public class Codec {

    private int currentIndex;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder rep = new StringBuilder();
        generatePreOrderRep(root, rep);
        return rep.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataParts = data.split("\\$");
        currentIndex = 0;

        return generateTree(dataParts);
    }

    private TreeNode generateTree(String[] nodeVals) {

        TreeNode currentNode = null;

        if(currentIndex < nodeVals.length) {
            if(!nodeVals[currentIndex].equals("None")) {
                currentNode = new TreeNode(Integer.parseInt(nodeVals[currentIndex]));
                currentIndex++;
                currentNode.left = generateTree(nodeVals);
                currentNode.right = generateTree(nodeVals);
            }
            else {
                currentIndex++;
            }
        }

        return currentNode;
    }

    private void generatePreOrderRep(TreeNode root, StringBuilder rep) {

        if(root == null) {
            rep.append("None$");
        }
        else {
            rep.append(root.val + "$");
            generatePreOrderRep(root.left, rep);
            generatePreOrderRep(root.right, rep);
        }
    }
}

/*
Time: O(n)
Space: O(n)

Generating an in-order and pre-order representation of the tree. But this approach fails when the tree has duplicate
nodes.
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        String result;

        if(root == null) {
            result = "";
        }
        else {
            StringBuilder inorderRep = new StringBuilder();
            generateInorderTraversal(root, inorderRep);

            StringBuilder preorderRep = new StringBuilder();
            generatePreorderTraversal(root, preorderRep);
            String preorderRepStr = preorderRep.toString();
            result = preorderRepStr.length() + preorderRepStr + inorderRep.toString();
        }

        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        TreeNode root = null;

        if(!data.isEmpty()) {
            int lengthOfPreorder = 0;

            char currentChar = data.charAt(0);
            int index = 0;

            while(currentChar != ',') {
                lengthOfPreorder *= 10;
                lengthOfPreorder += (data.charAt(index) - '0');
                index++;
                currentChar = data.charAt(index);
            }

            List<Integer> preOrder = getListFromStr(data, index + 1, index + lengthOfPreorder - 1);
            List<Integer> inOrder = getListFromStr(data, index + lengthOfPreorder + 1, data.length() - 1);

            Map<Integer, Integer> indicesInInOrder = new HashMap<Integer, Integer>();

            int nNodes = inOrder.size();

            for(index = 0 ; index < nNodes ; index++) {
                indicesInInOrder.put(inOrder.get(index), index);
            }

            preOrderIndex = 0;

            root = generateTree(preOrder, indicesInInOrder, 0, nNodes - 1);
        }

        return root;
    }

    private TreeNode generateTree(List<Integer> preOrder, Map<Integer, Integer> indicesInInorder, int left, int right) {

        TreeNode currentNode = null;

        if(preOrderIndex < preOrder.size()) {
            int currentNodeVal = preOrder.get(preOrderIndex);

            int indexInInorder = indicesInInorder.get(currentNodeVal);

            if(indexInInorder >= left && indexInInorder <= right) {
                currentNode = new TreeNode();
                currentNode.val = currentNodeVal;
                preOrderIndex++;

                currentNode.left = generateTree(preOrder, indicesInInorder, left, indexInInorder - 1);
                currentNode.right = generateTree(preOrder, indicesInInorder, indexInInorder + 1, right);
            }
        }

        return currentNode;
    }

    private List<Integer> getListFromStr(String str, int start, int end) {

        List<Integer> elsList = new ArrayList<Integer>();

        int currentNum = 0;

        for(int index = start ; index <= end ; index++) {
            char currentChar = str.charAt(index);

            if(currentChar == ',') {
                elsList.add(currentNum);
                currentNum = 0;
            }
            else {
                currentNum *= 10;
                currentNum += (str.charAt(index) - '0');
            }
        }

        elsList.add(currentNum);

        return elsList;
    }

    private void generateInorderTraversal(TreeNode root, StringBuilder str) {

        if(root != null) {
            generateInorderTraversal(root.left, str);
            str.append("," + root.val);
            generateInorderTraversal(root.right, str);
        }
    }

    private void generatePreorderTraversal(TreeNode root, StringBuilder str) {

        if(root != null) {
            str.append("," + root.val);
            generatePreorderTraversal(root.left, str);
            generatePreorderTraversal(root.right, str);
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
