package Stack;

import java.util.Stack;

/*
155. Min Stack

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.


Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

 */
public class MinStack {
    //Solution 1: Using LinkedList to store node, each node contains the value and current minimum value
        private class Node{
            int val;
            int min;
            Node next;
            public Node(int v, int m){
                this.val = v;
                this.min = m;
                this.next = null;
            }
        }

        Node head;
        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            Node n;
            if(head == null){
                n = new Node(x,x);
            }else {
                n = new Node(x, Math.min(head.min, x));
                n.next = head;
            }
            head = n;
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        //Solution 2: Using 2 stack, one to store value and one for minimum value
        //O(n) space, O(n) time
        class MinStack2 {
            private Stack<Integer> st;
            private Stack<Integer> min;

            /** initialize your data structure here. */
            public MinStack2() {
                st = new Stack<Integer> ();
                min = new Stack<Integer> ();
                min.push(Integer.MAX_VALUE);
            }

            public void push(int x) {
                if( x < min.peek()){
                    min.push(x);
                } else{
                    min.push(min.peek());
                }
                st.push(x);
            }

            public void pop() {
                st.pop();
                min.pop();
            }

            public int top() {
                return st.peek();
            }

            public int getMin() {
                return min.peek();
            }
        }

        //Solution 3: Using one stack, similar to solution 2
        // O(n) time complexity and O(1) space complexity
        class MinStack3 {
            int min = Integer.MAX_VALUE;
            Stack<Integer> s;
            /** initialize your data structure here. */
            public MinStack3() {
                s = new Stack<>();
            }

            public void push(int x) {

                if(x <= min){
                    s.push(min);
                    min = x;
                }
                s.push(x);
            }

            public void pop() {
                if(s.pop() == min) {
                    min = s.pop();
                }
            }

            public int top() {
                return s.peek();
            }

            public int getMin() {
                return min;
            }
        }
}
