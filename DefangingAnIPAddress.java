public class DefangingAnIPAddress {
    // Time : O(n), space : O(n).
    public String defangIPaddr(String address) {
        StringBuilder defangedBuilder = new StringBuilder();

        for(int index = 0, len = address.length() ; index < len ; index++) {
            char currentChar = address.charAt(index);

            if(currentChar == '.') {
                defangedBuilder.append("[.]");
            }
            else {
                defangedBuilder.append(currentChar);
            }
        }

        return defangedBuilder.toString();
    }
}
