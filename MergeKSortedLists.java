public class MergeKSortedLists {
}
//1) Store all the elements in an array and sort it.
// Then create a new list and store the values as per the order in sorted array

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        LinkedList<Integer> allValues = new LinkedList();

        for(int index = 0, numberOfLists = lists.length ; index < numberOfLists ; index++) {
            ListNode currentList = lists[index];

            while(currentList != null) {
                allValues.add(currentList.val);
                currentList = currentList.next;
            }
        }

        int totalNums = allValues.size();
        Integer[] nums = new Integer[totalNums];
        allValues.toArray(nums);

        Arrays.sort(nums);

        ListNode resultHead = new ListNode();
        ListNode resultTail = resultHead;
        for(int index = 0 ; index < totalNums ; index++) {
            ListNode nextNode = new ListNode(nums[index]);
            resultTail.next = nextNode;

            resultTail = resultTail.next;
        }

        return resultHead.next;
    }
}




// 2) Compare One-by-One

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {

        ListNode resultHead = new ListNode();
        ListNode resultTail = resultHead;

        int numberOfLists = lists.length;

        while(true) {

            int valueToBeAdded = -1;
            int listIndexOfCurrentVal = -1;

            for(int index = 0 ; index < numberOfLists ; index++) {
                ListNode currentList = lists[index];

                if(currentList == null)
                    continue;

                int currentVal = currentList.val;

                if(listIndexOfCurrentVal == -1) {
                    valueToBeAdded = currentVal;
                    listIndexOfCurrentVal = index;
                } else if(currentVal < valueToBeAdded) {
                    valueToBeAdded = currentVal;
                    listIndexOfCurrentVal = index;
                }
            }

            if(listIndexOfCurrentVal == -1) {
                break;
            }
            else {
                resultTail.next = lists[listIndexOfCurrentVal];
                lists[listIndexOfCurrentVal] = lists[listIndexOfCurrentVal].next;

                resultTail = resultTail.next;
            }
        }

        return resultHead.next;
    }
}

// 3) Compare with priority queue
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode resultHead = new ListNode();
        ListNode resultTail = resultHead;

        int numberOfLists = lists.length;

        if(numberOfLists > 0) {
            PriorityQueue<Entry> nextElementsInLists = new PriorityQueue<Entry>(numberOfLists);
            for(int index = 0 ; index < numberOfLists ; index++) {
                ListNode currentList = lists[index];
                if(currentList != null) {
                    int currentValue = currentList.val;

                    Entry entry = new Entry(currentList);
                    nextElementsInLists.add(entry);
                }
            }

            while(!nextElementsInLists.isEmpty()) {
                Entry nextElement = nextElementsInLists.remove();
                ListNode listOfInterest = nextElement.getList();

                resultTail.next = listOfInterest;
                resultTail = resultTail.next;

                listOfInterest = listOfInterest.next;
                if(listOfInterest != null) {
                    Entry nextEntry = new Entry(listOfInterest);
                    nextElementsInLists.add(nextEntry);
                }
            }
        }

        return resultHead.next;
    }
}

class Entry implements Comparable<Entry> {
    private ListNode list;

    public Entry(ListNode list) {
        this.list = list;
    }

    public ListNode getList() {
        return this.list;
    }

    @Override
    public int compareTo (Entry other) {
        return this.list.val - other.list.val;
    }
}

// 4) Merge two lists at a time

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        int numberOfLists = lists.length;
        ListNode resultHead = null;

        if(numberOfLists == 1) {
            resultHead = lists[0];
        }
        else if(numberOfLists > 1) {
            resultHead = mergeTwoLists(lists[0], lists[1]);

            for(int index = 2 ; index < numberOfLists ; index++) {
                resultHead = mergeTwoLists(resultHead, lists[index]);
            }
        }

        return resultHead;
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode resultHead = new ListNode();
        ListNode resultTail = resultHead;

        while(list1 != null && list2 != null) {
            if(list1.val > list2.val) {
                resultTail.next = list2;
                list2 = list2.next;
            }
            else {
                resultTail.next = list1;
                list1 = list1.next;
            }

            resultTail = resultTail.next;
        }

        if(list1 != null) {
            resultTail.next = list1;
        }
        else if(list2 != null) {
            resultTail.next = list2;
        }

        return resultHead.next;
    }
}

// 5) Divide and conquer
//    Merge two lists but perform merge operation in pairs. Each list is first paired with its adjacent list.
//    Later merge the pairs which were formed after the earlier merge operation.

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode resultHead = null;
        if(lists.length > 0) {

            for(int interval = 1, numberOfLists = lists.length ; interval < numberOfLists ; interval *= 2) {
                for(int index = 0, upperBound = numberOfLists - interval ; index < upperBound ; index += (interval * 2)) {
                    lists[index] = mergeTwoLists(lists[index], lists[index + interval]);
                }
            }
            resultHead = lists[0];
        }

        return resultHead;
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode resultHead = new ListNode();
        ListNode resultTail = resultHead;

        while(list1 != null && list2 != null) {
            if(list1.val > list2.val) {
                resultTail.next = list2;
                list2 = list2.next;
            }
            else {
                resultTail.next = list1;
                list1 = list1.next;
            }

            resultTail = resultTail.next;
        }

        if(list1 != null) {
            resultTail.next = list1;
        }
        else if(list2 != null) {
            resultTail.next = list2;
        }

        return resultHead.next;
    }
}



