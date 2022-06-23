public class ValidParentheses {
}


// 1) Customized Stack solution
class Solution {
    public boolean isValid(String s) {
        ParenthesesStack parenthesesStack = new ParenthesesStack();
        boolean valid = true;

        for(int index = 0, length = s.length() ; index < length ; index++) {
            char currentChar = s.charAt(index);

            if(currentChar == '(' || currentChar == '{' || currentChar == '[')
                parenthesesStack.push(currentChar);
            else {
                if(parenthesesStack.isEmpty()) {
                    valid = false;
                    break;
                }
                else {
                    char poppedChar = parenthesesStack.pop();

                    if(currentChar == '}' && poppedChar != '{') {
                        valid = false;
                        break;
                    }
                    else if(currentChar == ']' && poppedChar != '[') {
                        valid = false;
                        break;
                    }
                    else if(currentChar == ')' && poppedChar != '(') {
                        valid = false;
                        break;
                    }
                }
            }
        }

        if(valid && !parenthesesStack.isEmpty())
            valid = false;

        return valid;
    }
}

class ParenthesesStack {
    StackElement head;

    public ParenthesesStack() {
        head = null;
    }

    public void push(char nextChar) {
        StackElement newHead = new StackElement(nextChar, head);
        head = newHead;
    }

    public char pop() {
        StackElement poppedElement = head;
        head = head.next;

        return poppedElement.value;
    }

    public boolean isEmpty() {
        boolean empty = true;

        if(head != null) {
            empty = false;
        }

        return empty;
    }
}

class StackElement {
    char value;
    StackElement next;

    public StackElement(char value, StackElement next) {
        this.value = value;
        this.next = next;
    }
}

// 2) Built-in stack solution
class Solution {
    public boolean isValid(String s) {

        Stack<Character> parenthesesStack = new Stack<Character>();
        boolean valid = true;

        for(int index = 0, length = s.length() ; index < length ; index++) {
            char currentChar = s.charAt(index);

            if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                parenthesesStack.push(currentChar);
            }
            else if (parenthesesStack.isEmpty()) {
                valid = false;
                break;
            }
            else {
                Character poppedChar = parenthesesStack.pop();

                if((currentChar == ')' && poppedChar != '(') || (currentChar == '}' && poppedChar != '{') || (currentChar == ']' && poppedChar != '[')) {
                    valid = false;
                    break;
                }
            }
        }

        if(valid && !parenthesesStack.isEmpty()) {
            valid = false;
        }

        return valid;
    }
}

//3) Using HashMap to store mappings

class Solution {

    private HashMap<Character, Character> mappings = new HashMap<Character, Character>();

    public Solution() {
        mappings = new HashMap<Character, Character>();
        mappings.put(')', '(');
        mappings.put(']', '[');
        mappings.put('}', '{');
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<Character>();
        boolean valid = true;

        for(int index = 0, length = s.length() ; index < length ; index++) {

            char currentChar = s.charAt(index);

            if(mappings.containsKey(currentChar)) {
                if(stack.isEmpty()) {
                    valid = false;
                    break;
                }
                else {
                    char openingPair = mappings.get(currentChar);

                    if(stack.pop() != openingPair) {
                        valid = false;
                        break;
                    }
                }
            }
            else {
                stack.push(currentChar);
            }
        }

        if(!stack.isEmpty()) {
            valid = false;
        }

        return valid;
    }
}


//4) No need to store the mappings for each parenthesis type

class Solution {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<Character>();
        boolean valid = true;

        for(int index = 0, length = s.length() ; index < length ; index++) {

            char currentChar = s.charAt(index);

            if(currentChar == '(') {
                stack.push(')');
            }
            else if(currentChar == '[') {
                stack.push(']');
            }
            else if(currentChar == '{') {
                stack.push('}');
            }
            else if(stack.isEmpty() || stack.pop() != currentChar) {
                valid = false;
                break;
            }
        }

        if(!stack.isEmpty()) {
            valid = false;
        }

        return valid;
    }
}


