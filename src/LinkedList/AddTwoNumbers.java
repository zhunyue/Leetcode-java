package LinkedList;

/*
2. Add Two Numbers
Medium

15976

3426

Add to List

Share
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 */
public class AddTwoNumbers {
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
    /*
          Idea: Add by digits, store carry in a variable and apply in next iteration
     */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // pre to store the previous node
            ListNode pre = new ListNode(0);
            // head to return list
            ListNode head = pre;
            // the carry number, either 1 or 0
            int carry = 0;
            while (l1 != null || l2 != null) {
                int val1 = (l1 != null) ? l1.val : 0;
                int val2 = (l2 != null) ? l2.val : 0;
                //get the sum
                int sum = val1 + val2 + carry;
                // create the node with value
                ListNode node = new ListNode(sum % 10);
                // update list
                pre.next = node;
                pre = node;
                // get the new carry number
                carry = sum / 10;
                // update pointer
                if(l1 != null)
                    l1 = l1.next;
                if(l2 != null)
                    l2 = l2.next;
            }
            if (carry == 1) {
                pre.next = new ListNode(1);
            }
            return head.next;
        }
}
