public class InsertDeleteGetRandom {

    // Time Complexity : O(1) average-case. O(n) time will be required when array is filled to its capacity.
    // Space complexity = O(n) to store the elements.
    class RandomizedSet {

        HashMap<Integer, Integer> itemsAndIndices;
        List<Integer> items;
        Random rng;

        public RandomizedSet() {
            itemsAndIndices = new HashMap<Integer, Integer>();
            items = new ArrayList<Integer>();
            rng = new Random();
        }

        public boolean insert(int val) {

            boolean insertStatus = false;

            if(!itemsAndIndices.containsKey(val)) {
                itemsAndIndices.put(val, items.size());
                items.add(val);
                insertStatus = true;
            }

            return insertStatus;
        }

        public boolean remove(int val) {
            boolean deleteStatus = false;

            if(itemsAndIndices.containsKey(val)) {
                int indexOfItem = itemsAndIndices.remove(val);
                int lastItem = items.remove(items.size() - 1);

                if(indexOfItem != items.size()) {
                    items.set(indexOfItem, lastItem);
                    itemsAndIndices.put(lastItem, indexOfItem);
                }

                deleteStatus = true;
            }

            return deleteStatus;
        }

        public int getRandom() {

            int randIndex = rng.nextInt(items.size());
            return items.get(randIndex);
        }
    }
}
