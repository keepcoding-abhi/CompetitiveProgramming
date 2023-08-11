import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
}

/*
Time : addNum : O(log(n)) ; findMedian : O(1)
Space : O(n) to hold the data stream
Store the input array in two halves. The first is stored as a max heap while the second one is stored as min heap.
Maintain equal number of elements in two heaps.
 */
class MedianFinder {

    PriorityQueue<Integer> lowerHalfMaxHeap, upperHalfMinHeap;

    public MedianFinder() {
        lowerHalfMaxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        upperHalfMinHeap = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {

        if(lowerHalfMaxHeap.size() == upperHalfMinHeap.size()) {
            lowerHalfMaxHeap.add(num);
            if(!upperHalfMinHeap.isEmpty() && upperHalfMinHeap.peek() < lowerHalfMaxHeap.peek()) {
                int higherEl = lowerHalfMaxHeap.poll();
                lowerHalfMaxHeap.add(upperHalfMinHeap.poll());
                upperHalfMinHeap.add(higherEl);
            }
        }
        else {
            lowerHalfMaxHeap.add(num);
            upperHalfMinHeap.add(lowerHalfMaxHeap.poll());
        }
    }

    public double findMedian() {
        double result;

        if(lowerHalfMaxHeap.size() == upperHalfMinHeap.size()) {
            result = (lowerHalfMaxHeap.peek() + upperHalfMinHeap.peek()) / 2.0;
        }
        else {
            result = lowerHalfMaxHeap.peek();
        }

        return result;
    }
}

/*
A cleaner version of above code. New element are always added to the lower half and the highest element
from lower half is moved to upper half. If the size of upper half is more, move an element from upper half to lower half.
 */
class MedianFinder {

    PriorityQueue<Integer> lowerHalfMaxHeap, upperHalfMinHeap;

    public MedianFinder() {
        lowerHalfMaxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        upperHalfMinHeap = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {

        lowerHalfMaxHeap.add(num);

        upperHalfMinHeap.add(lowerHalfMaxHeap.poll());

        if(upperHalfMinHeap.size() > lowerHalfMaxHeap.size()) {
            lowerHalfMaxHeap.add(upperHalfMinHeap.poll());
        }
    }

    public double findMedian() {
        double result;

        if(lowerHalfMaxHeap.size() == upperHalfMinHeap.size()) {
            result = (lowerHalfMaxHeap.peek() + upperHalfMinHeap.peek()) / 2.0;
        }
        else {
            result = lowerHalfMaxHeap.peek();
        }

        return result;
    }
}

/*
Time : O(n) for both addNum and find median for each call
Space : O(n) all elements are stored in a linked list.
 */
class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
        next = null;
    }
}

class MedianFinder {

    Node head;
    int size;

    public MedianFinder() {
        head = null;
        size = 0;
    }

    public void addNum(int num) {
        Node newEl = new Node(num);
        if(size == 0) {
            head = newEl;
        }
        else {
            if(num < head.val) {
                newEl.next = head;
                head = newEl;
            }
            else {
                Node current = head;
                while(current.next != null && current.next.val < num) {
                    current = current.next;
                }

                newEl.next = current.next;
                current.next = newEl;
            }
        }

        size++;
    }

    public double findMedian() {
        double median;
        Node currentNode = head;

        if(size % 2 == 0) {
            int firstMiddleIndex = (size / 2) - 1;

            for(int index = 0 ; index < firstMiddleIndex ; index++) {
                currentNode = currentNode.next;
            }

            median = (currentNode.val + currentNode.next.val) / 2.0;
        }
        else {
            int middleIndex = size / 2;

            for(int index = 0 ; index < middleIndex ; index++) {
                currentNode = currentNode.next;
            }

            median = currentNode.val;
        }

        return median;
    }
}