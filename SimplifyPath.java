public class SimplifyPath {
}

class Solution {
    public String simplifyPath(String path) {

        LinkedList<String> directoryNames = new LinkedList<String>();
        int lastNameIndex = 1, pathLength = path.length();

        for(int index = 1 ; index < pathLength ; index++) {

            char currentChar = path.charAt(index);
            if(currentChar == '/') {

                if(lastNameIndex != index) {
                    if(path.charAt(lastNameIndex) == '.') {

                        if(path.charAt(lastNameIndex + 1) == '.') {

                            if(lastNameIndex + 2 == index) {
                                if(!directoryNames.isEmpty()) {
                                    directoryNames.removeLast();
                                }
                            }
                            else {
                                String nextName = path.substring(lastNameIndex, index);
                                directoryNames.add(nextName);
                            }

                        }
                        else if(lastNameIndex + 1 != index) {
                            String nextName = path.substring(lastNameIndex, index);
                            directoryNames.add(nextName);
                        }
                    }
                    else {
                        String nextName = path.substring(lastNameIndex, index);
                        directoryNames.add(nextName);
                    }

                }

                lastNameIndex = index + 1;
            }
        }

        if(lastNameIndex < pathLength) {
            String nextName = path.substring(lastNameIndex);

            if(nextName.equals("..")) {
                if(!directoryNames.isEmpty()) {
                    directoryNames.removeLast();
                }
            }
            else if(!nextName.equals(".")) {
                directoryNames.add(nextName);
            }

        }

        StringBuilder canonicalPath = new StringBuilder();

        for(String pathComponent : directoryNames) {

            canonicalPath.append("/");
            canonicalPath.append(pathComponent);
        }

        if(canonicalPath.length() == 0) {
            canonicalPath.append("/");
        }

        return canonicalPath.toString();
    }
}
