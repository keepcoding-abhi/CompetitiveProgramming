public class MinStacks {
    /*
    Time : O(1)
    Space : O(n)

    Storing the current element and the element which would be minimum after popping off the current element on stack.
     */
    class MinStack {

        Deque<int[]> elementAndNextMin;

        public MinStack() {
            elementAndNextMin = new LinkedList<int[]>();
        }

        public void push(int val) {
            if(elementAndNextMin.isEmpty()) {
                elementAndNextMin.push(new int[]{val, val});
            }
            else {
                int prevMin = elementAndNextMin.peek()[1];
                elementAndNextMin.push(new int[]{val, Math.min(prevMin, val)});
            }
        }

        public void pop() {
            elementAndNextMin.pop();
        }

        public int top() {
            return elementAndNextMin.peek()[0];
        }

        public int getMin() {
            return elementAndNextMin.peek()[1];
        }
    }

    /*
    Time : O(1) for each operation
    Space : O(n) overall.
    Using two stacks, one for storing all elements and the other for minimum elements only.
     */
    class MinStack {

        Deque<Integer> normalElementStack, minimumElementStack;

        public MinStack() {
            normalElementStack = new LinkedList<Integer>();
            minimumElementStack = new LinkedList<Integer>();
        }

        public void push(int val) {
            normalElementStack.push(val);

            if(minimumElementStack.isEmpty()) {
                minimumElementStack.push(val);
            }
            else if(minimumElementStack.peek() >= val) {
                minimumElementStack.push(val);
            }
        }

        public void pop() {
            int poppedVal = normalElementStack.pop();

            if(poppedVal == minimumElementStack.peek()) {
                minimumElementStack.pop();
            }
        }

        public int top() {
            return normalElementStack.peek();
        }

        public int getMin() {
            return minimumElementStack.peek();
        }
    }

    /*
    Time : O(1) for each operation.
    Space : O(n) overall.
       Instead of storing all occurrences of minimum element on stack, counts are stored to save space.
     */
    class MinStack {

        Deque<Integer> normalElementStack;
        Deque<int[]> minimumElementStack;

        public MinStack() {
            normalElementStack = new LinkedList<Integer>();
            minimumElementStack = new LinkedList<int[]>();
        }

        public void push(int val) {
            normalElementStack.push(val);

            if(minimumElementStack.isEmpty()) {
                minimumElementStack.push(new int[]{val, 1});
            }
            else {
                int[] currentMinimum = minimumElementStack.peek();

                if(currentMinimum[0] == val) {
                    currentMinimum[1]++;
                }
                else if(currentMinimum[0] > val) {
                    minimumElementStack.push(new int[]{val, 1});
                }
            }
        }

        public void pop() {
            int poppedVal = normalElementStack.pop();

            int[] currentMinimum = minimumElementStack.peek();
            if(poppedVal == currentMinimum[0]) {
                if(currentMinimum[1] == 1) {
                    minimumElementStack.pop();
                }
                else {
                    currentMinimum[1]--;
                }
            }
        }

        public int top() {
            return normalElementStack.peek();
        }

        public int getMin() {
            return minimumElementStack.peek()[0];
        }
    }
}
