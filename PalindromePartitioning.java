import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartitioning {
    /*
    Time: O(n * 2^(n)), O(2^n) possible substring partition and each partition takes O(n) time for palindrome checking
    and substring generation.
    Space: O(n) depth of recursion stack

     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new LinkedList<List<String>>();
        palindromePartition(s, 0, new LinkedList<String>(), result);
        return result;
    }

    private void palindromePartition(String s, int startIndex, List<String> currentPartitions, List<List<String>> resultingPartitions) {

        int sLen = s.length();

        if(startIndex == sLen) {
            List<String> nextPartition = new ArrayList<String>(currentPartitions);
            resultingPartitions.add(nextPartition);
        }
        else {

            for(int endIndex = startIndex ; endIndex < sLen ; endIndex++) {
                if(checkPalin(s, startIndex, endIndex)) {
                    currentPartitions.add(s.substring(startIndex, endIndex + 1));
                    palindromePartition(s, endIndex + 1, currentPartitions, resultingPartitions);
                    currentPartitions.remove(currentPartitions.size() - 1);
                }
            }
        }
    }

    private boolean checkPalin(String s, int begin, int end) {
        int left = begin, right = end;

        boolean isPalin = true;

        while(left < right) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            }
            else {
                isPalin = false;
                break;
            }
        }

        return isPalin;
    }

    /*
    Time: O(n * 2^(n))
    Space: O(n^2) for storing the table for palindromic strings

    The check for palindromic strings is done using a table in constant time.
     */
    public List<List<String>> partition(String s) {

        List<List<String>> result = new LinkedList<List<String>>();
        List<String> currentPartition = new LinkedList<String>();

        int sLen = s.length();
        boolean[][] palinTable = new boolean[sLen][sLen];

        generatePartitions(s, 0, palinTable, currentPartition, result);

        return result;
    }

    private void generatePartitions(String s, int beginIndex, boolean[][] palindromeTable, List<String> currentPartition, List<List<String>> resultingPartitions) {

        int sLen = s.length();

        if(beginIndex == sLen) {
            List<String> newPartitions = new ArrayList<String>(currentPartition);
            resultingPartitions.add(newPartitions);
        }
        else {
            for(int endIndex = beginIndex ; endIndex < sLen ; endIndex++) {
                if(s.charAt(beginIndex) == s.charAt(endIndex) && (beginIndex == endIndex || (endIndex - beginIndex + 1) == 2 || palindromeTable[beginIndex + 1][endIndex - 1] == true)) {
                    palindromeTable[beginIndex][endIndex] = true;

                    currentPartition.add(s.substring(beginIndex, endIndex + 1));

                    generatePartitions(s, endIndex + 1, palindromeTable, currentPartition, resultingPartitions);

                    currentPartition.remove(currentPartition.size() - 1);
                }
            }
        }
    }
}
