package Tree;

import java.util.Stack;

/*
    Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

    Calling next() will return the next smallest number in the BST.
 */
public class BinarySearchTreeIterator {


      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    class BSTIterator {
        Stack<TreeNode> s;
        private void pushLeft(TreeNode root) {
            while(root != null) {
                s.push(root);
                root = root.left;
            }
        }
        public BSTIterator(TreeNode root) {
            s = new Stack<>();
            pushLeft(root);
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = s.pop();
            pushLeft(node.right);
            return node.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !s.isEmpty();
        }
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
