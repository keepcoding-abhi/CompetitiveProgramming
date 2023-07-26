import java.util.PriorityQueue;

public class MergeKSortedLists {
}
//1) Store all the elements in an array and sort it.
// Then create a new list and store the values as per the order in sorted array
/*
Time : O((k * n) * Log(k * n)), here k is the number of lists and n is the average length in the lists.
Space : O(k * n) for storing numbers in an array.
 */

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
/*
Time : O(n*k)
Space ; O(1)
 */

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
/*
Time : O(N * Log(k))
Space : O(k)
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode result = null;

        if (lists != null && lists.length > 0) {

            PriorityQueue<ListNode> nodesToAdd = new PriorityQueue<ListNode>((ListNode a, ListNode b) -> {
                return a.val - b.val;
            });

            for (int index = 0; index < lists.length; index++) {
                ListNode currentNode = lists[index];

                if (currentNode != null) {
                    nodesToAdd.add(currentNode);
                }
            }

            if (!nodesToAdd.isEmpty()) {
                result = nodesToAdd.poll();
                ListNode currentNode = result;

                if (currentNode.next != null) {
                    nodesToAdd.add(currentNode.next);
                }

                while (!nodesToAdd.isEmpty()) {
                    currentNode.next = nodesToAdd.poll();
                    currentNode = currentNode.next;

                    if (currentNode.next != null) {
                        nodesToAdd.add(currentNode.next);
                    }
                }
            }
        }

        return result;
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

/*
Time : O(N * k), N is the number of elements in the merged linked list.
Space : O(k) for storing pointers
select the least element from each of the k lists.
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode result = null;

        if(lists != null && lists.length > 0) {
            ListNode[] currentPointers = new ListNode[lists.length];
            for(int index = 0 ; index < lists.length ; index++) {
                currentPointers[index] = lists[index];
            }

            ListNode mergedListHead = null, mergedListCurr = null;

            ListNode nextEl = getNextElement(currentPointers);

            if(nextEl != null) {
                mergedListHead = nextEl;
                mergedListCurr = nextEl;

                nextEl = getNextElement(currentPointers);

                while(nextEl != null) {
                    mergedListCurr.next = nextEl;
                    mergedListCurr = mergedListCurr.next;
                }
            }

            result = mergedListHead;
        }

        return result;

    }

    private ListNode getNextElement(ListNode[] lists) {
        ListNode leastEl = null;

        int index = 0, minIndex = -1;
        for(ListNode currList : lists) {
            if(currList != null) {
                if(leastEl == null) {
                    leastEl = currList;
                    minIndex = index;
                }
                else if(currList.val < leastEl.val) {
                    leastEl = currList;
                    minIndex = index;
                }
            }

            index++;
        }

        if(minIndex != -1) {
            lists[minIndex] = lists[minIndex].next;
        }

        return leastEl;
    }
}

/*
Time : O(N * Log(k))
Space : O(Log(k))
The depth of recursion is Log(k) and at each level O(N) elements are merged.
Divide and conquer solution
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode result = null;

        if(lists != null && lists.length > 0) {

            result = mergeByDAndC(lists, 0, lists.length - 1);
        }

        return result;
    }

    private ListNode mergeByDAndC(ListNode[] lists, int startIndex, int endIndex) {

        ListNode mergedList = null;
        if(startIndex == endIndex) {
            mergedList = lists[startIndex];
        }
        else {
            int midIndex = (startIndex + endIndex) / 2;
            ListNode leftList = mergeByDAndC(lists, startIndex, midIndex);
            ListNode rightList = mergeByDAndC(lists, midIndex + 1, endIndex);

            mergedList = merge(leftList, rightList);
        }

        return mergedList;
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode mergedListHead = new ListNode();
        ListNode currentNode = mergedListHead;

        while(list1 != null && list2 != null) {
            if(list1.val < list2.val) {
                currentNode.next = list1;
                list1 = list1.next;
            }
            else {
                currentNode.next = list2;
                list2 = list2.next;
            }
            currentNode = currentNode.next;
        }

        if(list1 != null) {
            currentNode.next = list1;
        }
        else if(list2 != null) {
            currentNode.next = list2;
        }

        return mergedListHead.next;
    }
}



