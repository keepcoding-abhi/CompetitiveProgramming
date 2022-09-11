public class FindTheIndexOfTheFirstOccurrenceInAString {
}

class Solution {
    public int strStr(String haystack, String needle) {

        int firstIndexOfNeedle = -1;

        int haystackLength = haystack.length(), needleLength = needle.length();

        if(needleLength <= haystackLength) {

            int lastPossibleStartIndex = haystackLength - needleLength;
            for(int haystackIndex = 0 ; haystackIndex <= lastPossibleStartIndex ; haystackIndex++) {

                char currentHaystackChar = haystack.charAt(haystackIndex);

                if(currentHaystackChar == needle.charAt(0)) {

                    int needleIndex = 1;
                    for( ; needleIndex < needleLength ; needleIndex++) {
                        char needleChar = needle.charAt(needleIndex);
                        currentHaystackChar = haystack.charAt(haystackIndex + needleIndex);

                        if(needleChar != currentHaystackChar) {
                            break;
                        }
                    }

                    if(needleIndex == needleLength) {
                        firstIndexOfNeedle = haystackIndex;
                        break;
                    }

                }
            }
        }

        return firstIndexOfNeedle;
    }
}