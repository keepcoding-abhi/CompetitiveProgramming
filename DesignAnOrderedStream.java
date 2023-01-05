public class DesignAnOrderedStream {

    // Time and space complexity : O(n).
    class OrderedStream {

        String[] stream;
        int currentPtr, len;

        public OrderedStream(int n) {
            stream = new String[n];
            currentPtr = 0;
            len = n;
        }

        public List<String> insert(int idKey, String value) {
            stream[idKey - 1] = value;

            List<String> resultingStream = new LinkedList<String>();

            while(currentPtr < len && stream[currentPtr] != null) {
                resultingStream.add(stream[currentPtr]);
                currentPtr++;
            }

            return resultingStream;
        }
    }
}
