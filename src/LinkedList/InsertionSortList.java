package LinkedList;

/*
Sort a linked list using insertion sort.
With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 */

  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while(head != null){
            if(prev.val > head.val) {
                prev = dummy;
            }
            while(prev.next != null && prev.next.val < head.val) {
                prev = prev.next;
            }
            //insert current node between prev and prev.next
            ListNode nextNode=head.next;
            head.next=(prev.next);
            prev.next=(head);
            head=nextNode;
        }

        return dummy.next;

    }
}
