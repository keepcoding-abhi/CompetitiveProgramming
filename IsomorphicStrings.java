public class IsomorphicStrings {

    // Time : O(N), Space : O(1)
    public boolean isIsomorphic(String s, String t) {
        boolean isomorphic = true;
        int sLen = s.length(), tLen = t.length();

        if(sLen == tLen) {
            int[] mappings = new int[128];
            int[] mappedToChars = new int[128];

            for(int index = 0 ; index < 128 ; index++) {
                mappings[index] = -1;
            }

            for(int index = 0 ; index < sLen ; index++) {
                int sChar = s.charAt(index), tChar = t.charAt(index);

                if(mappings[sChar] != -1) {
                    if(mappings[sChar] != tChar) {
                        isomorphic = false;
                        break;
                    }
                }
                else {
                    if(mappedToChars[tChar] == 1) {
                        isomorphic = false;
                        break;
                    }
                    mappings[sChar] = tChar;
                    mappedToChars[tChar] = 1;
                }
            }
        }
        else {
            isomorphic = false;
        }

        return isomorphic;
    }

    public boolean isIsomorphic(String s, String t) {
        boolean isomorphic = true;
        int sLen = s.length(), tLen = t.length();

        if(sLen == tLen) {
            int[] mappingOfS = mapString(s);
            int[] mappingOfT = mapString(t);

            for(int index = 0 ; index < sLen ; index++) {
                if(mappingOfS[index] != mappingOfT[index]) {
                    isomorphic = false;
                    break;
                }
            }
        }
        else {
            isomorphic = false;
        }

        return isomorphic;
    }

    // Mapping string to numbers.
    // Time and Space : O(n)
    private int[] mapString(String s) {
        int sLen = s.length();
        int[] mappingOfS = new int[sLen];

        int mappingIndex = 1;
        int[] mappings = new int[128];
        for(int index = 0 ; index < sLen ; index++) {
            char currentChar = s.charAt(index);
            int currentCharMapping = mappings[currentChar];

            if(currentCharMapping == 0) {
                mappings[currentChar] = mappingIndex;
                currentCharMapping = mappingIndex;
                mappingIndex++;
            }

            mappingOfS[index] = currentCharMapping;
        }

        return mappingOfS;
    }
}
