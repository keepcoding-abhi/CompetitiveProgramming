import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    class ListNode {
        int val;
        ListNode prev, next;
    }

    /*
    Time: O(1) for get and put operation
    Space: O(n)

    Use a map to store the key: value pair in cache along with the counter
    Maintain another map to store all the keys that occur with certain frequency in the form of an
    LRU cache (least recently used cache)
     */
    class DoublyLinkedList {
        private Map<Integer, ListNode> els;
        private ListNode head, tail;

        public DoublyLinkedList() {
            els = new HashMap<Integer, ListNode>();
            head = null;
            tail = null;
        }

        public int removeFirst() {
            int removedVal = head.val;

            removeKey(head.val);

            return removedVal;
        }

        public void addLast(int val) {
            ListNode newNode = new ListNode();
            newNode.val = val;

            if(head == null) {
                head = newNode;
                tail = head;
                els.put(val, tail);
            }
            else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
                els.put(val, tail);
            }
        }

        public void removeKey(int key) {
            if(els.containsKey(key)) {
                ListNode nodeToRemove = els.get(key);
                if(nodeToRemove == head) {
                    head = head.next;
                    nodeToRemove.next = null;

                    if(head != null) {
                        head.prev = null;
                    }
                }
                else if(nodeToRemove == tail) {
                    tail = tail.prev;
                    nodeToRemove.prev = null;

                    if(tail != null) {
                        tail.next = null;
                    }
                }
                else {
                    nodeToRemove.prev.next = nodeToRemove.next;
                    nodeToRemove.next.prev = nodeToRemove.prev;
                    nodeToRemove.prev = null;
                    nodeToRemove.next = null;
                }

                els.remove(key);
            }
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    class LFUCache {

        private Map<Integer, int[]> cache;
        private Map<Integer, DoublyLinkedList> cacheFreqs;
        private int minFreq, capacity;

        public LFUCache(int capacity) {
            cache = new HashMap<Integer, int[]>(capacity);
            cacheFreqs = new HashMap<Integer, DoublyLinkedList>();
            minFreq = 1;
            this.capacity = capacity;
        }

        public int get(int key) {
            int val;

            if(cache.containsKey(key)) {
                int[] keyInfo = cache.get(key);
                val = keyInfo[0];
                accessElement(key);
            }
            else {
                val = -1;
            }

            return val;
        }

        public void put(int key, int value) {
            if(cache.containsKey(key)) {
                int[] keyInfo = cache.get(key);
                keyInfo[0] = value;
            }
            else {

                if(cache.size() == capacity) {
                    removeLFU();
                }
                cache.put(key, new int[]{value, 0});
            }

            accessElement(key);
        }

        private void removeLFU() {
            DoublyLinkedList minFreqEls = cacheFreqs.get(minFreq);

            int removedKey = minFreqEls.removeFirst();
            cache.remove(removedKey);

            minFreq = 1;
        }

        private void accessElement(int key) {
            int[] keyInfo = cache.get(key);
            keyInfo[1]++;

            if(keyInfo[1] > 1) {
                DoublyLinkedList oldFreqs = cacheFreqs.get(keyInfo[1] - 1);
                oldFreqs.removeKey(key);

                if(oldFreqs.isEmpty()) {
                    if(minFreq == keyInfo[1] - 1) {
                        minFreq = keyInfo[1];
                    }
                    cacheFreqs.remove(keyInfo[1] - 1);
                }
            }
            else {
                minFreq = 1;
            }

            if(cacheFreqs.containsKey(keyInfo[1])) {
                cacheFreqs.get(keyInfo[1]).addLast(key);
            }
            else {
                DoublyLinkedList listOfKeys = new DoublyLinkedList();
                listOfKeys.addLast(key);
                cacheFreqs.put(keyInfo[1], listOfKeys);
            }
        }
    }
}
