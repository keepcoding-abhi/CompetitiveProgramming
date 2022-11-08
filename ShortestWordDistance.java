public class ShortestWordDistance {

    // Time Complexity = O(n * m) n is the number of words in wordsDict, m is the total length of the words among word1, word2
    public int shortestDistance(String[] wordsDict, String word1, String word2) {

        int shortestDistance = Integer.MAX_VALUE;
        int word1LastOccurrence = -1, word2LastOccurrence = -1;
        int currentIndex = 1;
        for(String currentWord : wordsDict) {

            if(currentWord.equals(word1)) {
                word1LastOccurrence = currentIndex;
                if(word2LastOccurrence != -1) {
                    shortestDistance = Math.min(shortestDistance, word1LastOccurrence - word2LastOccurrence);
                }
            }
            else if(currentWord.equals(word2)) {
                word2LastOccurrence = currentIndex;
                if(word1LastOccurrence != -1) {
                    shortestDistance = Math.min(shortestDistance, word2LastOccurrence - word1LastOccurrence);
                }
            }

            currentIndex++;
        }

        return shortestDistance;
    }
}
