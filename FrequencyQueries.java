import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FrequencyQueries {
    /*
    Tims : O(nQueries)
    Space : O(nQueries)
    Maintaining two hashmaps one stores details about the frequency of each number, and the other one stores
    how many entries in the first hashmap have a particular value.
     */
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> result = new LinkedList<Integer>();
        Map<Integer, Integer> freqs = new HashMap<Integer, Integer>();
        Map<Integer, Integer> vals = new HashMap<Integer, Integer>();

        for(int queryIndex = 0, nQueries = queries.size() ; queryIndex < nQueries ; queryIndex++) {
            List<Integer> currentQuery = queries.get(queryIndex);
            int operation = currentQuery.get(0);
            int val = currentQuery.get(1);

            if(operation == 1) {
                int prevFreq = freqs.getOrDefault(val, 0);

                if(prevFreq != 0) {
                    vals.put(prevFreq, vals.get(prevFreq) - 1);
                }

                freqs.put(val, prevFreq + 1);
                vals.put(prevFreq + 1, vals.getOrDefault(prevFreq + 1, 0) + 1);
            }
            else if(operation == 2) {

                int prevFreq = freqs.getOrDefault(val, 0);

                if(prevFreq != 0) {
                    vals.put(prevFreq, vals.get(prevFreq) - 1);
                    freqs.put(val, prevFreq - 1);
                    vals.put(prevFreq - 1, vals.getOrDefault(prevFreq - 1, 0) + 1);
                }
            }
            else if(operation == 3) {
                result.add((vals.getOrDefault(val, 0) == 0) ? 0 : 1);
            }
        }

        return result;
    }
}
