public class ShuffleString {

    // Shuffling the string out-of-place
    // Time and space : O(n)
    public String restoreString(String s, int[] indices) {

        String shuffledInput = null;

        if(s != null) {
            int strLen = s.length();
            char[] shuffledString = new char[strLen];

            for(int index = 0 ; index < strLen ; index++) {
                shuffledString[indices[index]] = s.charAt(index);
            }

            shuffledInput = new String(shuffledString);
        }

        return shuffledInput;
    }

    // Using cyclic sort.
    // Time complexity : O(n). Space complexity could be constant if input is passed as array of strings.

    public String restoreString(String s, int[] indices) {
        char[] strToSort = s.toCharArray();

        for(int index = 0, strLen = s.length() ; index < strLen ; index++) {

            while(index != indices[index]) {
                swap(strToSort, index, indices[index]);
                swap(indices, index, indices[index]);
            }
        }

        return new String(strToSort);
    }

    private void swap(char[] arr, int index1, int index2) {
        char temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
