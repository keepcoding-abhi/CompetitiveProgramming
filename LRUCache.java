import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
}

/*
Time: O(1)
Space: O(n) for the hashmap and doubly linked list

Utilize a doubly linked list for storing the elements in cache. And use a hashmap to access the elements of linked list
in constant time.
 */
class ListNode {
    int key, val;
    ListNode prev, next;
}

class DoublyLinkedList {
    ListNode head, tail;

    DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public ListNode insertNode(int key, int val) {
        ListNode newNode = new ListNode();
        newNode.key = key;
        newNode.val = val;

        if(tail == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        return newNode;
    }

    public void insertNode(ListNode node) {
        if(head == null) {
            head = node;
            tail = node;
        }
        else {
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
        }
    }

    public int removeFirst() {
        int key = -1;

        if(head != null) {
            key = head.key;
            head = head.next;

            if(head == null) {
                tail = null;
            }
            else {
                head.prev.next = null;
                head.prev = null;
            }
        }

        return key;
    }

    public int removeLast() {
        int key = -1;

        if(tail != null) {
            key = tail.val;
            tail = tail.prev;

            if(tail == null) {
                head = null;
            }
            else {
                tail.next.prev = null;
                tail.next = null;
            }
        }

        return key;
    }

    public void removeNode(ListNode node) {
        if(node == head) {
            removeFirst();
        }
        else if(node == tail) {
            removeLast();
        }
        else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }
    }
}

class LRUCache {

    Map<Integer, ListNode> cacheAccess;
    DoublyLinkedList cache;
    int size, capacity;

    public LRUCache(int capacity) {
        cache = new DoublyLinkedList();
        cacheAccess = new HashMap<Integer, ListNode>(capacity);
        this.capacity = capacity;
        size = 0;
    }

    public int get(int key) {

        int value = -1;
        ListNode currKeyEntry;

        if(cacheAccess.containsKey(key)) {
            currKeyEntry = cacheAccess.get(key);
            value = currKeyEntry.val;

            cache.removeNode(currKeyEntry);
            cache.insertNode(currKeyEntry);
        }

        return value;
    }

    public void put(int key, int value) {

        if(cacheAccess.containsKey(key)) {
            ListNode currNode = cacheAccess.get(key);
            cache.removeNode(currNode);
            currNode.val = value;
            cache.insertNode(currNode);
        }
        else {
            ListNode currNode = cache.insertNode(key, value);
            cacheAccess.put(key, currNode);
            size++;
        }

        if(size > capacity) {
            int removedKey = cache.removeFirst();
            cacheAccess.remove(removedKey);
            size--;
        }
    }
}

/*

Time: O(1)
Space: O(capacity) pseudo polynomial
Utilizing the linked hash map data structure for achieving the solution above.
 */
class LRUCache {

    Map<Integer, Integer> cache;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}


//1) Use hashmap for retrieving value and doubly linked list to maintain the sequence of elements in cache.
//  Cleaner code with operations separated in different classes.

class LRUCache {

    private DoublyLinkedList dll;
    private int currentSize;
    private final int MAX_CAPACITY;
    private Map<Integer, DoublyLinkedNode> cache;

    public LRUCache(int capacity) {
        dll = new DoublyLinkedList();
        MAX_CAPACITY = capacity;
        currentSize = 0;
        cache = new HashMap<Integer, DoublyLinkedNode>(MAX_CAPACITY);
    }

    public int get(int key) {
        DoublyLinkedNode node = cache.get(key);
        int result;

        if(node == null) {
            result = -1;
        }
        else {
            result = node.getValue();
            dll.moveToHead(node);
        }

        return result;
    }

    public void put(int key, int value) {
        DoublyLinkedNode node = cache.get(key);

        if(node == null) {

            if(currentSize == MAX_CAPACITY) {
                int keyRemoved = dll.removeFromTail();
                cache.remove(keyRemoved);
                currentSize--;
            }

            node = new DoublyLinkedNode(key, value);
            cache.put(key, node);
            dll.addToHead(node);
            currentSize++;

        } else {
            node.setValue(value);
            dll.moveToHead(node);
        }
    }
}

class DoublyLinkedList {
    private DoublyLinkedNode head, tail;

    public DoublyLinkedList() {
        head = tail = null;
    }

    public void addToHead(DoublyLinkedNode node) {
        if(head == null) {
            tail = node;
            tail.next = null;
        }
        else {
            head.prev = node;
            node.next = head;
        }

        head = node;
        head.prev = null;
    }

    // In this example moveToHead is responsible for removing the node and adding it to the head
    // It would be easier if we segregate these two functionalities in different methods.
    public void moveToHead(DoublyLinkedNode node) {
        if(head != node) {

            if(tail == node) {
                tail = tail.prev;
            } else {
                node.next.prev = node.prev;
            }

            node.prev.next = node.next;
            node.next = head;
            node.prev = null;

            head.prev = node;
            head = node;
        }
    }

    public int removeFromTail() {
        if(tail != null) {

            int keyRemoved = tail.getKey();
            if(tail == head) {
                tail = head = null;
            }
            else {
                tail = tail.prev;
                tail.next = null;
            }

            return keyRemoved;
        }

        throw new IllegalArgumentException("Cannot remove node from an empty list.");
    }
}

class DoublyLinkedNode {
    private int key, value;

    DoublyLinkedNode prev, next;

    public DoublyLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
        prev = next = null;
    }

    public int getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

//2) Instead of creating separate classes encapsulating the required DoublyLinkedList class inside the main class.

class LRUCache {

    private DoublyLinkedNode head, tail;
    private int currentSize;
    private final int MAX_CAPACITY;
    private Map<Integer, DoublyLinkedNode> cache;

    class DoublyLinkedNode {
        int key, value;
        DoublyLinkedNode prev, next;

        DoublyLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        head = tail = null;
        MAX_CAPACITY = capacity;
        currentSize = 0;
        cache = new HashMap<Integer, DoublyLinkedNode>(MAX_CAPACITY);
    }

    public int get(int key) {
        DoublyLinkedNode node = cache.get(key);
        int result;

        if(node == null) {
            result = -1;
        }
        else {
            result = node.value;
            removeNode(node);
            addToHead(node);
        }

        return result;
    }

    public void put(int key, int value) {
        DoublyLinkedNode node = cache.get(key);

        if(node == null) {

            if(currentSize == MAX_CAPACITY) {
                int keyRemoved = removeFromTail();
                cache.remove(keyRemoved);
                currentSize--;
            }

            node = new DoublyLinkedNode(key, value);
            cache.put(key, node);
            addToHead(node);
            currentSize++;

        } else {
            node.value = value;
            removeNode(node);
            addToHead(node);
        }
    }

    private void removeNode(DoublyLinkedNode node) {
        if(head == tail) {
            head = tail = null;
        }
        else if(tail == node) {
            tail = tail.prev;
            tail.next = null;
        }
        else if(head == node) {
            head = head.next;
            head.prev = null;
        }
        else {
            node.next.prev = node.prev;
            node.prev.next = node.next;

            node.prev = node.next = null;
        }
    }

    private void addToHead(DoublyLinkedNode node) {
        if(head == null) {
            tail = node;
        }
        else {
            head.prev = node;
            node.next = head;
        }

        head = node;
    }

    private int removeFromTail() {
        if(tail != null) {

            int keyRemoved = tail.key;

            if(head == tail) {
                head = tail = null;
            }
            else {
                tail = tail.prev;
                tail.next = null;
            }

            return keyRemoved;
        }

        throw new IllegalStateException("Cannot remove node from an empty linked list.");
    }
}
