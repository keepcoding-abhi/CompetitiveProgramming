public class ValidAnagram {

    // Linear Time constant space solution.
    public boolean isAnagram(String s, String t) {
        int sLen = s.length(), tLen = t.length();

        boolean isAnagram;
        if(sLen == tLen) {
            int[] charFrequencies = new int[26];

            for(int index = 0 ; index < sLen ; index++) {
                charFrequencies[s.charAt(index) - 'a']++;
            }

            isAnagram = true;
            for(int index = 0 ; index < tLen ; index++) {
                charFrequencies[t.charAt(index) - 'a']--;
                if(charFrequencies[t.charAt(index) - 'a'] < 0) {
                    isAnagram = false;
                    break;
                }
            }
        }
        else {
            isAnagram = false;
        }

        return isAnagram;
    }

    // Linear Time constant space solution. The following approach uses more space than the previous one.
    public boolean isAnagram(String s, String t) {
        int[] charFrequencyInS = new int[26];

        for(int index = 0, sLen = s.length() ; index < sLen ; index++) {
            charFrequencyInS[s.charAt(index) - 'a']++;
        }

        int[] charFrequencyInT = new int[26];
        for(int index = 0, tLen = t.length() ; index < tLen ; index++) {
            charFrequencyInT[t.charAt(index) - 'a']++;
        }

        boolean isAnagram = true;
        for(int index = 0 ; index < 26 ; index++) {
            if(charFrequencyInS[index] != charFrequencyInT[index]) {
                isAnagram = false;
                break;
            }
        }

        return isAnagram;
    }
}
