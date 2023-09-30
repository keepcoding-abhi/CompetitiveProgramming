import java.util.Deque;
import java.util.LinkedList;

public class BaseballGame {
}

/*
Using a stack to record the information given in problem statement.
Time : O(n)
Space : (n)
 */
class Solution {
    public int calPoints(String[] operations) {
        Deque<Integer> recordStack = new LinkedList<Integer>();

        for(String op : operations) {
            if(op.equals("+")) {
                int top = recordStack.pop();
                int afterTop = recordStack.pop();

                recordStack.push(afterTop);
                recordStack.push(top);
                recordStack.push(top + afterTop);
            }
            else if(op.equals("D")) {
                int top = recordStack.pop();

                recordStack.push(top);
                recordStack.push(top * 2);
            }
            else if(op.equals("C")) {
                recordStack.pop();
            }
            else {
                recordStack.push(Integer.parseInt(op));
            }
        }

        int result = 0;
        while(!recordStack.isEmpty()) {
            result += recordStack.pop();
        }

        return result;
    }
    // 5, -2, -4, 9, 5, 14
}
