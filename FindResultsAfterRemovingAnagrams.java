public class FindResultsAfterRemovingAnagrams {

    // Time complexity ; O(n), Space complexity : O(n)
    // n : is the sum of all the words in the input list.
    public List<String> removeAnagrams(String[] words) {

        List<String> resultantArrayAfterRemovingAnagrams = new LinkedList<String>();

        String lastStr = words[0];
        int[] lastStrCharFrequencies = getCharFrequencies(lastStr);
        for(int index = 1, len = words.length ; index < len ; index++) {
            String currentStr = words[index];
            int[] currentStrCharFrequencies = getCharFrequencies(currentStr);

            if(lastStr.length() == currentStr.length()) {
                if(!sameArrays(lastStrCharFrequencies, currentStrCharFrequencies)) {
                    resultantArrayAfterRemovingAnagrams.add(lastStr);
                    lastStr = currentStr;
                    lastStrCharFrequencies = currentStrCharFrequencies;
                }
            }
            else {
                resultantArrayAfterRemovingAnagrams.add(lastStr);
                lastStr = currentStr;
                lastStrCharFrequencies = currentStrCharFrequencies;
            }
        }

        resultantArrayAfterRemovingAnagrams.add(lastStr);

        return resultantArrayAfterRemovingAnagrams;
    }

    private boolean sameArrays(int[] arr1, int[] arr2) {

        boolean sameArrays = true;
        for(int index = 0, len = arr1.length ; index < len ; index++) {
            if(arr1[index] != arr2[index]) {
                sameArrays = false;
                break;
            }
        }

        return sameArrays;
    }

    private int[] getCharFrequencies(String str) {
        int[] charFrequencies = new int[26];

        for(int index = 0, len = str.length() ; index < len ; index++) {
            charFrequencies[str.charAt(index) - 'a']++;
        }

        return charFrequencies;
    }

}
