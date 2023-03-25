public class UniqueMorseCodeWords {

    // Time : O(S), Space : O(S). S is the length of all words in words.
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> transformations = new HashSet<String>();
        String[] morseCodeMappings = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        for(String word : words) {
            String morseCode = generateMorse(word, morseCodeMappings);
            transformations.add(morseCode);
        }

        return transformations.size();
    }

    private String generateMorse(String word, String[] morseCodeMappings) {
        StringBuilder morseCodeBuilder = new StringBuilder();

        for(int index = 0, wordLen = word.length() ; index < wordLen ; index++) {
            char currentChar = word.charAt(index);

            morseCodeBuilder.append(morseCodeMappings[currentChar - 'a']);
        }

        return morseCodeBuilder.toString();
    }
}
