public class SortingTheSequence {

    // Time and space complexities : O(n), n is the number of characters in the string.
    public String sortSentence(String s) {

        String[] words = s.split(" ");
        int numWords = words.length;

        String[] sortedWords = new String[numWords];

        for(String currentWord : words) {
            int seqNumIndex = currentWord.length() - 1;
            int currentWordIndex = currentWord.charAt(seqNumIndex) - '1';
            sortedWords[currentWordIndex] = currentWord.substring(0, seqNumIndex);
        }

        StringBuilder sortedString = new StringBuilder();

        for(String currentWord : sortedWords) {
            sortedString.append(currentWord);
            sortedString.append(' ');
        }

        if(!sortedString.isEmpty()) {
            sortedString.deleteCharAt(sortedString.length() - 1);
        }

        return sortedString.toString();
    }
}
