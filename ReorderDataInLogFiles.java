import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReorderDataInLogFiles {
}

/*
Time: O(m*nLog(n))
Space: O(Log(n))

Define a comparator to implement the rules in problem statement
 */
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        List<String> letterLogs = new ArrayList<String>();
        List<String> digitLogs = new ArrayList<String>();

        for(String log : logs) {
            if(isDigitLog(log)) {
                digitLogs.addLast(log);
            }
            else {
                letterLogs.addLast(log);
            }
        }

        Collections.sort(letterLogs, (String log1, String log2) -> {
            int comparison = 0;

            int log1Index = 0, log1Len = log1.length(), log2Index = 0, log2Len = log2.length();

            while(log1Index < log1Len && log1.charAt(log1Index) != ' ') {
                log1Index++;
            }

            while(log2Index < log2Len && log2.charAt(log2Index) != ' ') {
                log2Index++;
            }

            int log1IdentifierIndex = log1Index, log2IdentifierIndex = log2Index;

            log1Index++;
            log2Index++;

            while(log1Index < log1Len && log2Index < log2Len
                    && log1.charAt(log1Index) == log2.charAt(log2Index)) {
                log1Index++;
                log2Index++;
            }

            if(log1Index < log1Len && log2Index < log2Len) {
                comparison = log1.charAt(log1Index) - log2.charAt(log2Index);
            }
            else if(log1Index < log1Len) {
                comparison = 1;
            }
            else if(log2Index < log2Len) {
                comparison = -1;
            }
            else {
                String log1Identifier = log1.substring(0, log1IdentifierIndex);
                String log2Identifier = log2.substring(0, log2IdentifierIndex);

                comparison = log1Identifier.compareTo(log2Identifier);
            }

            return comparison;
        });

        String[] result = new String[logs.length];

        int resIndex = 0;

        for(String letterLog : letterLogs) {
            result[resIndex++] = letterLog;
        }

        for(String digitLog : digitLogs) {
            result[resIndex++] = digitLog;
        }

        return result;
    }

    private boolean isDigitLog(String log) {
        char currChar = 0;
        int logIndex = 0;

        while(log.charAt(logIndex) != ' ') {
            logIndex++;
        }

        logIndex++;
        char firstCharInLog = log.charAt(logIndex);

        boolean digitLog;

        if(firstCharInLog >= 'a' && firstCharInLog <= 'z') {
            digitLog = false;
        }
        else {
            digitLog = true;
        }

        return digitLog;
    }
}
