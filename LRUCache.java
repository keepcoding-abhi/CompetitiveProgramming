public class LRUCache {
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
