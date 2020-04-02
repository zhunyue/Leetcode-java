package LinkedList;

/*
    Sort a linked list in O(n log n) time using constant space complexity.
 */
public class SortList {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    //Method1: refer to insertion sort list problem

    //Method2: merge sort
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode preSlow = null;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        preSlow.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        return merge(l1, l2);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode res = new ListNode (-1);
        ListNode dummy = res;
        while(l1!=null && l2!=null){
            if(l1.val < l2.val){
                res.next = l1;
                l1 = l1.next;
            } else{
                res.next = l2;
                l2 = l2.next;
            }
            res = res.next;
        }
        if(l1 != null){
            res.next = l1;
        } else if(l2 != null) {
            res.next = l2;
        }
        return dummy.next;
    }
}
