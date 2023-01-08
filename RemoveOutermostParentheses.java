public class RemoveOutermostParentheses {

    // Time : O(n), Space : O(n)
    public String removeOuterParentheses(String s) {
        StringBuilder nonOuterParenths = new StringBuilder();

        int topOfStackPtr = 0;

        for(int index = 0, sLen = s.length() ; index < sLen ; index++) {
            char currentChar = s.charAt(index);

            if(currentChar == '(') {

                if(topOfStackPtr > 0) {
                    nonOuterParenths.append('(');
                }

                topOfStackPtr++;
            }
            else {
                topOfStackPtr--;
                if(topOfStackPtr > 0) {
                    nonOuterParenths.append(')');
                }
            }
        }

        return nonOuterParenths.toString();
    }
}
