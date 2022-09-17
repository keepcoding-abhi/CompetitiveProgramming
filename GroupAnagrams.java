public class GroupAnagrams {
}

// 1) Using static hash-table to keep track of the frequencies of different charactars in the string.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        int totalStrings = strs.length;
        List<List<String>> groupedAnagrams = new ArrayList<List<String>>(totalStrings);
        List<int[]> charFreqs = new ArrayList<int[]>(totalStrings);

        for(int index = 0 ; index < totalStrings ; index++) {
            String currentStr = strs[index];

            int[] currentStrFreqs = countCharFreqs(currentStr);
            List<String> anagramGroup = null;

            for(int freqIndex = 0, diffFreqs = charFreqs.size() ; freqIndex < diffFreqs ; freqIndex++) {
                if(compareArrs(charFreqs.get(freqIndex), currentStrFreqs)) {
                    anagramGroup = groupedAnagrams.get(freqIndex);
                    break;
                }
            }
            if(anagramGroup == null) {
                anagramGroup = new ArrayList<String>();
                charFreqs.add(currentStrFreqs);
                groupedAnagrams.add(anagramGroup);
            }

            anagramGroup.add(currentStr);
        }

        return groupedAnagrams;
    }

    private boolean compareArrs(int[] arr1, int[] arr2) {

        boolean equal = true;
        for(int index = 0, len = arr1.length ; index < len ; index++) {
            if(arr1[index] != arr2[index]) {
                equal = false;
                break;
            }
        }

        return equal;
    }

    private int[] countCharFreqs(String currentStr) {

        int[] charFreqs = new int[26];
        for(int index = 0, strlen = currentStr.length() ; index < strlen ; index++) {
            char currentChar = currentStr.charAt(index);

            charFreqs[currentChar - 'a']++;
        }

        return charFreqs;
    }
}

// 2) Sorting the string and string the sorted string as key in a map.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> anagramMap = new HashMap<String, List<String>>();

        for(int index = 0 ; index < strs.length ; index++) {
            String currentStr = strs[index];

            char strChars[] = currentStr.toCharArray();
            Arrays.sort(strChars);

            String keyOfCurrentChar = new String(strChars);

            List<String> anagramsWithSameKey = anagramMap.get(keyOfCurrentChar);

            if(anagramsWithSameKey == null) {
                anagramsWithSameKey = new LinkedList<String>();
                anagramMap.put(keyOfCurrentChar, anagramsWithSameKey);
            }
            anagramsWithSameKey.add(currentStr);
        }

        Set<String> allKeys = anagramMap.keySet();
        List<List<String>> groupedAnagrams = new ArrayList<List<String>>(allKeys.size());

        for(String currentKey : allKeys) {
            groupedAnagrams.add(anagramMap.get(currentKey));
        }

        return groupedAnagrams;
    }
}

// 3) Generating static hash table but storing it in a string.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> anagramMap = new HashMap<String, List<String>>();

        for(int index = 0 ; index < strs.length ; index++) {
            String currentStr = strs[index];

            int[] charFreqs = getCharFreqs(currentStr);

            StringBuilder keyGen = new StringBuilder();

            for(int index1 = 0, len = charFreqs.length ; index1 < len ; index1++) {
                keyGen.append('#');
                keyGen.append(charFreqs[index1]);
            }

            String keyOfCurrentChar = keyGen.toString();
            List<String> anagramsWithSameKey = anagramMap.get(keyOfCurrentChar);

            if(anagramsWithSameKey == null) {
                anagramsWithSameKey = new LinkedList<String>();
                anagramMap.put(keyOfCurrentChar, anagramsWithSameKey);
            }
            anagramsWithSameKey.add(currentStr);
        }

        Set<String> allKeys = anagramMap.keySet();
        List<List<String>> groupedAnagrams = new ArrayList<List<String>>(allKeys.size());

        for(String currentKey : allKeys) {
            groupedAnagrams.add(anagramMap.get(currentKey));
        }

        return groupedAnagrams;
    }

    private int[] getCharFreqs(String str) {
        int[] charFreqs = new int[26];

        for(int index = 0, strlen = str.length() ; index < strlen ; index++) {
            char currentChar = str.charAt(index);

            if(currentChar >= 'a' && currentChar <= 'z') {
                charFreqs[currentChar - 'a']++;
            }
            else if(currentChar >= 'A' && currentChar <= 'Z') {
                charFreqs[currentChar - 'A']++;
            }
        }

        return charFreqs;
    }
}