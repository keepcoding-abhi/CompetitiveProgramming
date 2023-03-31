public class MaximumNestingDepthOfTheParentheses {

    // Using a counter to keep track of the current depth and returning the value of maximum depth recorded.
    // Time : O(n), Space : O(1).
    public int maxDepth(String s) {
        int currentDepth = 0, maxDepth = 0;

        for(int index = 0, sLen = s.length() ; index < sLen ; index++) {
            char currentChar = s.charAt(index);

            if(currentChar == '(') {
                currentDepth++;
            }
            else if(currentChar == ')') {
                currentDepth--;
            }

            maxDepth = Math.max(maxDepth, currentDepth);
        }

        return maxDepth;
    }

    // Using stack to store the depth of parentheses.
    // Time : O(n), Space : O(n)
    public int maxDepth(String s) {
        Deque<Integer> depthStack = new LinkedList<Integer>();

        for(int index = 0, sLen = s.length() ; index < sLen ; index++) {
            char currentChar = s.charAt(index);

            if(currentChar == '(') {
                depthStack.push(-1);
            }
            else if(currentChar == ')') {
                int currentDepth = 0;

                while(depthStack.peek() != -1) {
                    currentDepth = Math.max(currentDepth, depthStack.pop());
                }
                depthStack.pop();
                depthStack.push(currentDepth + 1);
            }
        }

        int currentDepth = 0;

        while(!depthStack.isEmpty()) {
            currentDepth = Math.max(currentDepth, depthStack.pop());
        }

        return currentDepth;
    }
}
