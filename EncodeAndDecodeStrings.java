import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {
}

/*
Time : O(n)
Space : O(strs.length)
Saving the length of each string in the encoded format and traversing only that specific number of characters up-front.
 */
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();

        for(String str : strs) {
            encoded.append(str.length() + ":" + str);
        }

        return encoded.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {

        List<String> result = new ArrayList<String>();
        StringBuilder currentStr = new StringBuilder();

        for(int index = 0, len = s.length() ; index < len ; index++) {
            int lenOfNextStr = 0;

            char currentChar = s.charAt(index);

            while(currentChar != ':') {
                lenOfNextStr = (lenOfNextStr * 10) + (currentChar - '0');
                index++;
                currentChar = s.charAt(index);
            }

            index++;
            String nextString = s.substring(index, index + lenOfNextStr);

            index = index + lenOfNextStr - 1;
            result.add(nextString);
        }

        return result;
    }
}

/*
Time : O(n) n is the length of all strings combined
Space : O(strs.length * delimiter.length) since the chars of original strings are sent in output
we don't count them in space complexity.
 */
public class Codec {

    private final String delimiter = "$AsDelim$";

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();
        int len = strs.size();

        if(len > 0) {
            encoded.append(strs.get(0));
            for(int index = 1 ; index < len ; index++) {
                String currentStr = strs.get(index);
                encoded.append(delimiter + currentStr);
            }
        }

        return encoded.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        String[] parts = s.split("\\$AsDelim\\$", -1);
        List<String> result = new ArrayList<String>(parts.length);

        for(String currPart : parts) {
            result.add(currPart);
        }

        return result;
    }
}

/*
Time : O(n + strs.length) all strings in strs are scanned.
Space : O(strs.length)

Instead of solely relying on delimiter string using escape character to track the presence of necessary slash.
 */
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();

        for(String str : strs) {
            for(int charIndex = 0, len = str.length() ; charIndex < len ; charIndex++) {
                char currentChar = str.charAt(charIndex);
                if(currentChar == '/') {
                    encoded.append("//");
                }
                else {
                    encoded.append(currentChar);
                }
            }

            encoded.append("/:");
        }

        return encoded.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {

        List<String> result = new ArrayList<String>();
        StringBuilder currentStr = new StringBuilder();

        for(int index = 0, len = s.length() ; index < len ; index++) {
            char currentChar = s.charAt(index);

            if(currentChar == '/') {
                char nextChar = s.charAt(index + 1);
                if(nextChar == ':') {
                    result.add(currentStr.toString());
                    currentStr = new StringBuilder();
                    index++;
                }
                else if(nextChar == '/') {
                    currentStr.append('/');
                    index++;
                }
                else {
                    currentStr.append(currentChar);
                }
            }
            else {
                currentStr.append(currentChar);
            }
        }

        return result;
    }
}
