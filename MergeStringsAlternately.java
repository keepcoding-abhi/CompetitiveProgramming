public class MergeStringsAlternately {
    /*
    Time : O(n + m)
    Space : O(n + m) : StringBuilder or char array is used to construct the final string.
    Use character array for smaller runtime.
     */
    public String mergeAlternately(String word1, String word2) {
        int word1Len = word1.length(), word2Len = word2.length();
        StringBuilder finalStr = new StringBuilder(word1Len + word2Len);

        int lengthOfShorterString = Math.min(word1Len, word2Len);

        for(int index = 0 ; index < lengthOfShorterString ; index++) {
            finalStr.append(word1.charAt(index));
            finalStr.append(word2.charAt(index));
        }

        String largerString = null;
        if(word1Len > lengthOfShorterString) {
            largerString = word1;
        }
        else {
            largerString = word2;
        }

        int finalStrIndex = 2*lengthOfShorterString;
        for(int index = lengthOfShorterString ; index < largerString.length() ; index++) {
            finalStr.append(largerString.charAt(index));
        }

        return finalStr.toString();
    }
}
