public class MergeTwoSortedLists {
}

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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode resultStart = new ListNode();
        ListNode resultEnd = resultStart;

        while(list1 != null && list2 != null) {

            if(list1.val > list2.val) {
                resultEnd.next = list2;
                list2 = list2.next;
            }
            else {
                resultEnd.next = list1;
                list1 = list1.next;
            }

            resultEnd = resultEnd.next;
        }

        resultEnd.next = (list1 == null) ? list2 : list1;

        return resultStart.next;
    }
}

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
class Solution_1 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if(list1 == null) {
            return list2;
        }
        else if(list2 == null) {
            return list1;
        }
        else if(list1.val > list2.val) {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
        else {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }

    }
}
