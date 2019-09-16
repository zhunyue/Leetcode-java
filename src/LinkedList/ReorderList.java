package LinkedList;
import java.util.ArrayList;
import java.util.List;

/*
    Given a singly linked list L: L0→L1→…→Ln-1→Ln,
    reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

    You may not modify the values in the list's nodes, only nodes itself may be changed.

    Example 1:

        Given 1->2->3->4, reorder it to 1->4->2->3.
    Example 2:

        Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */

public class ReorderList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public static void reorderList(ListNode head) {

        if(head == null || head.next == null || head.next.next ==null)
            return;
        ListNode pre_last = head;
        ListNode last = head.next;
        while(last.next != null){
            last = last.next;
            pre_last = pre_last.next;
        }

        ListNode next = head.next;
        head.next = last;
        last.next = next;

        pre_last.next = null;

        reorderList(next);
    }

    /*
           Use list to store all the nodes, since we could retrieve nodes from list, it could be faster
     */
    public static void reorderList2(ListNode head) {

        List<ListNode> nodes = new ArrayList<>();
        ListNode dummy = head;
        while(dummy != null){
            nodes.add(dummy);
            dummy = dummy.next;
        }
        for(int i = 0; i < nodes.size() / 2; i++){
            nodes.get(i).next = nodes.get(nodes.size()-1-i);
            nodes.get(nodes.size()-1-i).next = nodes.get(i+1);
            nodes.get(i+1).next = null;
        }
    }

    /*
          1. Get middle point
          2. Split the list into 2 parts according to the list
          3. Reverse the second half
          4. Insert the second half into the one half
     */
    public static void reorderList3(ListNode head) {
        if(head == null || head.next == null)
            return;
        ListNode first = head;
        ListNode second = getMiddle(head);
        second = reverse(second);
        head = merge(first, second);
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev, curr, next;
        prev = null;
        curr = head;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }


    private static ListNode merge(ListNode first, ListNode second) {
        ListNode root = first;
        ListNode temp = null;
        ListNode next_node;
        while(second != null && first != null) {
            next_node = first.next;
            first.next = second;
            first = next_node;

            temp = second;
            next_node = second.next;
            second.next = first;
            second = next_node;
        }

        if(temp != null)
            temp.next = second;
        return root;
    }

    private static ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;
        return slow;
    }

    public static void print(ListNode head){
        System.out.println("The result is: ");
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode dummy = head;
        ListNode dummy1 = head;
        ListNode dummy2 = head;
        reorderList(dummy);
        print(dummy);
        reorderList2(dummy1);
        print(dummy1);
        reorderList3(dummy2);
        print(dummy2);
    }
}
