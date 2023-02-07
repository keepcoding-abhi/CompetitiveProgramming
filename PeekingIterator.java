public class PeekingIterator {
}

// Time, Space : O(1)
class PeekingIterator implements Iterator<Integer> {

    Iterator<Integer> iterator;
    int nextElement;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;

        if(iterator.hasNext()) {
            nextElement = iterator.next();
        }
        else {
            nextElement = 0;
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return nextElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int result = nextElement;

        if(iterator.hasNext()) {
            nextElement = iterator.next();
        }
        else {
            nextElement = 0;
        }

        return result;
    }

    @Override
    public boolean hasNext() {
        return (nextElement == 0) ? false : true;
    }
}
Console

