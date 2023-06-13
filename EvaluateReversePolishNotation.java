public class EvaluateReversePolishNotation {
    /*
    Time : O(n)
    Space : O(n)
    Using stack to evaluate the expression.
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> computationsStack = new LinkedList<Integer>();

        for(String currentToken : tokens) {
            int rightOp, leftOp;
            switch(currentToken) {
                case "+":
                    rightOp = computationsStack.pop();
                    leftOp = computationsStack.pop();
                    computationsStack.push(leftOp + rightOp);
                    break;
                case "-":
                    rightOp = computationsStack.pop();
                    leftOp = computationsStack.pop();
                    computationsStack.push(leftOp - rightOp);
                    break;
                case "*":
                    rightOp = computationsStack.pop();
                    leftOp = computationsStack.pop();
                    computationsStack.push(leftOp * rightOp);
                    break;
                case "/":
                    rightOp = computationsStack.pop();
                    leftOp = computationsStack.pop();
                    computationsStack.push(leftOp / rightOp);
                    break;
                default:
                    computationsStack.push(Integer.parseInt(currentToken));
                    break;
            }
        }

        return computationsStack.pop();
    }
}
