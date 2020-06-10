package Tree;

import java.util.LinkedList;
import java.util.Queue;

/*
226. Invert Binary Tree

Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
 */
public class InvertBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
          }
      }

      // Solution 1: Recursive solution - DFS
      public TreeNode invertTree(TreeNode root) {
            if(root == null) return root;
            helper(root);
            return root;
        }

        public TreeNode helper(TreeNode root) {
            if (root == null) return null;
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = helper(right);
            root.right = helper(left);
            return root;
        }

        // Solution 2: Iterative solution - BFS
        public TreeNode invertTree2(TreeNode root) {
            if (root == null) return null;
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode current = queue.poll();
                TreeNode temp = current.left;
                current.left = current.right;
                current.right = temp;
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            return root;
        }
}
