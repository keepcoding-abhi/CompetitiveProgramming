import java.util.PriorityQueue;

/*
Time: O(n Log(k))
Space: O(k)

Using a heap to keep track of k largest elements
 */
class KthLargest {

    PriorityQueue<Integer> kLargestEls;
    int k;

    public KthLargest(int k, int[] nums) {
        kLargestEls = new PriorityQueue<Integer>();

        for(int num : nums) {
            kLargestEls.add(num);

            if(kLargestEls.size() > k) {
                kLargestEls.remove();
            }
        }

        this.k = k;
    }

    public int add(int val) {
        kLargestEls.add(val);

        if(kLargestEls.size() > k) {
            kLargestEls.remove();
        }

        return kLargestEls.peek();
    }
}