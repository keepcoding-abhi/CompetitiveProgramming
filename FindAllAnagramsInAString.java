public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> startIndicesOfAnagrams = new LinkedList<Integer>();

        int anagramSize = p.length();
        int sLen = s.length();

        if(sLen >= anagramSize) {
            int leftIndex = 0, rightIndex = 0;
            int[] charFreqsInP = getFreqs(p);

            int[] currentSubstrFreqs = new int[26];

            while(rightIndex < anagramSize - 1) {
                currentSubstrFreqs[s.charAt(rightIndex) - 'a']++;
                rightIndex++;
            }

            while(rightIndex < sLen) {
                currentSubstrFreqs[s.charAt(rightIndex) - 'a']++;

                if(sameArrays(charFreqsInP, currentSubstrFreqs)) {
                    startIndicesOfAnagrams.add(leftIndex);
                }

                currentSubstrFreqs[s.charAt(leftIndex) - 'a']--;
                leftIndex++;
                rightIndex++;
            }
        }

        return startIndicesOfAnagrams;
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

    private int[] getFreqs(String word) {
        int[] charFreqs = new int[26];

        for(int index = 0, len = word.length() ; index < len ; index++) {
            charFreqs[word.charAt(index) - 'a']++;
        }

        return charFreqs;
    }
}
