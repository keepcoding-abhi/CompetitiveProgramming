public class NextGreaterElement1 {

    // T : O(nums1Len + nums2Len), S : O(nums2Len) for hashmap
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int nums1Len = nums1.length;
        int[] answer = new int[nums1Len];

        Deque<Integer> prevElements = new LinkedList<Integer>();

        Map<Integer, Integer> nextGreaterNums2 = new HashMap<Integer, Integer>();

        for(int nums2Index = 0, nums2Len = nums2.length ; nums2Index < nums2Len ; nums2Index++) {

            int currentEl = nums2[nums2Index];

            while(!prevElements.isEmpty() && nums2[prevElements.peek()] < currentEl) {
                nextGreaterNums2.put(nums2[prevElements.pop()], currentEl);
            }

            prevElements.push(nums2Index);
        }

        while(!prevElements.isEmpty()) {
            nextGreaterNums2.put(nums2[prevElements.pop()], -1);
        }

        for(int nums1Index = 0 ; nums1Index < nums1Len ; nums1Index++) {

            answer[nums1Index] = nextGreaterNums2.get(nums1[nums1Index]);

        }

        return answer;
    }

    // Brute force approach. Time complexity : O(n1.length * n2.length), Space complexity : O(1)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int nums1Len = nums1.length;
        int[] answer = new int[nums1Len];

        for(int nums1Index = 0 ; nums1Index < nums1Len ; nums1Index++) {

            int nums1El = nums1[nums1Index];
            for(int nums2Index = 0, nums2Len = nums2.length ; nums2Index < nums2Len ; nums2Index++) {

                if(nums1El == nums2[nums2Index]) {
                    int nextGreaterFor = nums2[nums2Index];

                    int nums2Index_1 = nums2Index + 1;
                    for( ; nums2Index_1 < nums2Len ; nums2Index_1++) {
                        if(nums2[nums2Index_1] > nextGreaterFor) {
                            answer[nums1Index] = nums2[nums2Index_1];
                            break;
                        }
                    }

                    if(nums2Index_1 == nums2Len) {
                        answer[nums1Index] = -1;
                    }

                    break;
                }
            }
        }

        return answer;
    }

}
