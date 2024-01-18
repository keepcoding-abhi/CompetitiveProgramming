import java.util.LinkedList;
import java.util.List;

public class PartitionLabels {

    /*
    Time: O(n)
    Space: O(1)

    Keep track of the first and last position of each character in the string.
    And keep track of the character in the current substring that has the farthest last occurrence.
     */
    public List<Integer> partitionLabels(String s) {
        int[][] firstAndLastOccrs = new int[26][2];

        for(int index = 0 ; index < 26 ; index++) {
            firstAndLastOccrs[index][0] = -1;
            firstAndLastOccrs[index][1] = -1;
        }

        int sLen = s.length();
        for(int index = 0 ; index < sLen ; index++) {
            int charIndex = s.charAt(index) - 'a';

            if(firstAndLastOccrs[charIndex][0] == -1) {
                firstAndLastOccrs[charIndex][0] = index;
            }

            firstAndLastOccrs[charIndex][1] = index;
        }

        int currentPartitionStartIndex = 0, currentPartitionEndIndex = firstAndLastOccrs[s.charAt(0) - 'a'][1];
        List<Integer> partitions = new LinkedList<Integer>();

        for(int index = 0 ; index < sLen ; index++) {
            int lastOccrsOfCurrentChar = firstAndLastOccrs[s.charAt(index) - 'a'][1];

            if(lastOccrsOfCurrentChar > currentPartitionEndIndex) {
                currentPartitionEndIndex = lastOccrsOfCurrentChar;
            }

            if(currentPartitionEndIndex == index) {
                partitions.add((currentPartitionEndIndex - currentPartitionStartIndex) + 1);

                currentPartitionStartIndex = index + 1;
                currentPartitionEndIndex = index + 1;
            }
        }

        if(currentPartitionStartIndex < sLen) {
            partitions.add(sLen - currentPartitionStartIndex);
        }

        return partitions;
    }
}
