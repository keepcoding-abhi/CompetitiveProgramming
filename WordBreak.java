import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    /*
    Time : O(n * m * M), m length of dictionary, M length of the largest word in the dictionary.
    Space : O(n) for recording results.
    Bottom-up solution.
    Start from the right end and check whether each character in the dictionary can contribute towards a successful
    word break.
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        int strLen = s.length();
        int[] savedResults = new int[strLen];

        for(int startIndex = strLen - 1 ; startIndex > -1 ; startIndex--) {
            for(String currentWord : wordDict) {
                int substringLength = strLen - startIndex;
                int currentWordLen = currentWord.length();

                int endIndex = startIndex + currentWordLen;

                if(endIndex < strLen && savedResults[endIndex] == 1 && checkSubstringEquality(s, startIndex, currentWord)) {
                    savedResults[startIndex] = 1;
                }
                else if(endIndex == strLen && checkSubstringEquality(s, startIndex, currentWord)) {
                    savedResults[startIndex] = 1;
                }
            }
        }

        return (savedResults[0] == 1) ? true : false;
    }

    private boolean checkSubstringEquality(String s, int startIndex, String currentWord) {

        boolean wordPresentInString = true;

        for(int index = 0, len = currentWord.length() ; index < len ; index++) {
            if(s.charAt(index + startIndex) != currentWord.charAt(index)) {
                wordPresentInString = false;
                break;
            }
        }

        return wordPresentInString;
    }

    /*
        Top-down memoized version of next approach.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxWordLen = wordDict.get(0).length();

        Set<String> dictionary = new HashSet<String>();

        for(String word : wordDict) {
            dictionary.add(word);
            maxWordLen = Math.max(maxWordLen, word.length());
        }

        int[] savedResults = new int[s.length()];

        return breakTheString(s, dictionary, 0, maxWordLen, savedResults);
    }

    private boolean breakTheString(String str, Set<String> dictionary, int startIndex, int maxLen, int[] savedResults) {

        boolean stringBroken = false;
        int strLen = str.length();
        if(startIndex == strLen) {
            stringBroken = true;
        }
        else if(savedResults[startIndex] == 0) {
            for(int index = startIndex, endIndex = Math.min(startIndex + maxLen, strLen) ; index < endIndex && !stringBroken ; index++) {
                String currentWord = str.substring(startIndex, index + 1);

                if(dictionary.contains(currentWord)) {
                    stringBroken = breakTheString(str, dictionary, index + 1, maxLen, savedResults);
                }
            }

            savedResults[startIndex] = !stringBroken ? -1 : 1;
        }

        return stringBroken;
    }

    /*
    Time : O(n * (m * M)), n s's length, m number of words in dictionary, M largest word in dictionary
    Space :
    Checking each possible substring of s and whether there's a word for it in the dictionary. If yes, perform the same
    operation in the remainder of the string.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxWordLen = wordDict.get(0).length();

        Set<String> dictionary = new HashSet<String>();

        for(String word : wordDict) {
            dictionary.add(word);
            maxWordLen = Math.max(maxWordLen, word.length());
        }

        return breakTheString(s, dictionary, 0, maxWordLen);
    }

    private boolean breakTheString(String str, Set<String> dictionary, int startIndex, int maxLen) {

        boolean stringBroken = false;
        int strLen = str.length();
        if(startIndex == strLen) {
            stringBroken = true;
        }
        else {
            for(int index = startIndex, endIndex = Math.min(startIndex + maxLen, strLen) ; index < endIndex && !stringBroken ; index++) {
                String currentWord = str.substring(startIndex, index + 1);

                if(dictionary.contains(currentWord)) {
                    stringBroken = breakTheString(str, dictionary, index + 1, maxLen);
                }
            }
        }

        return stringBroken;
    }

    /*
    Time : O(n^3 + m*k)
    O(n) time required to iterate over all start indices, O(n) time for setting end index, O(n) for creating substring.
    O(1) to search the word in dictionary.

    Space : O(m*k + n)
    O(m*k) : to create the set of words, O(n) for queue and also for substrings.

    Breadth First Search solution.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int sLen = s.length();

        Set<String> dictionary = new HashSet<String>();
        for(String currentStr : wordDict) {
            dictionary.add(currentStr);
        }

        Deque<Integer> queuedStartIndices = new LinkedList<Integer>();
        queuedStartIndices.add(0);
        boolean reachedEnd = false;

        int[] nodesAddedInQueue = new int[sLen];

        while(!queuedStartIndices.isEmpty() && !reachedEnd) {
            int currentStart = queuedStartIndices.removeFirst();

            for(int currentEnd = currentStart ; currentEnd < sLen ; currentEnd++) {
                if(dictionary.contains(s.substring(currentStart, currentEnd + 1))) {
                    int startIndexOfNextWord = currentEnd + 1;
                    if(startIndexOfNextWord == sLen) {
                        reachedEnd = true;
                        break;
                    }
                    else if(nodesAddedInQueue[startIndexOfNextWord] == 0) {
                        queuedStartIndices.addLast(startIndexOfNextWord);
                        nodesAddedInQueue[startIndexOfNextWord] = 1;
                    }
                }
            }
        }

        return reachedEnd;
    }

    /*
    Time : O(n*m*l)
    Space : O(n)
    Memoized version of next solution.
    Converting the dictionary from list to set for faster performance in terms of time
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int sLen = s.length();
        int[] wordBreakPossible = new int[sLen];

        Set<String> dictionary = new HashSet<String>();

        for(String currentWord : wordDict) {
            dictionary.add(currentWord);
        }

        Arrays.fill(wordBreakPossible, -1);

        return detectWordBreak(s, dictionary, 0, wordBreakPossible);
    }

    private boolean detectWordBreak(String s, Set<String> dictionary, int startIndex, int[] savedResults) {
        boolean wordBreakPossible = false;
        int sLen = s.length();
        if(startIndex == sLen) {
            wordBreakPossible = true;
        }
        else if(savedResults[startIndex] != -1) {
            wordBreakPossible = (savedResults[startIndex] == 1) ? true : false;
        }
        else {
            StringBuilder currentWord = new StringBuilder();

            for(int index = startIndex ; index < sLen ; index++) {
                char currentChar = s.charAt(index);
                currentWord.append(currentChar);

                if(dictionary.contains(currentWord.toString())) {
                    wordBreakPossible = detectWordBreak(s, dictionary, index + 1, savedResults);
                }

                if(wordBreakPossible) {
                    break;
                }
            }

            savedResults[startIndex] = wordBreakPossible ? 1 : 0;
        }

        return wordBreakPossible;
    }


    /*
    Time  : O(2^(n*m*l))
    Space : O(2^(n*m))
    Brute force approach.
    Checking each possible word from the substring.
    Exceeds time limit.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return detectWordBreak(s, wordDict, 0);
    }

    private boolean detectWordBreak(String s, List<String> dictionary, int startIndex) {
        boolean wordBreakPossible = false;
        int sLen = s.length();
        if(startIndex == sLen) {
            wordBreakPossible = true;
        }
        else {
            StringBuilder currentWord = new StringBuilder();

            for(int index = startIndex ; index < sLen ; index++) {
                char currentChar = s.charAt(index);
                currentWord.append(currentChar);

                if(dictionary.contains(currentWord.toString())) {
                    wordBreakPossible = detectWordBreak(s, dictionary, index + 1);
                }

                if(wordBreakPossible) {
                    break;
                }
            }
        }

        return wordBreakPossible;
    }
}
