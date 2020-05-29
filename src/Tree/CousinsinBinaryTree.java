package Tree;

import java.util.LinkedList;
import java.util.Queue;

/*

993. Cousins in Binary Tree

    In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

    Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

    We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

    Return true if and only if the nodes corresponding to the values x and y are cousins.

    Example 1:


    Input: root = [1,2,3,4], x = 4, y = 3
    Output: false
    Example 2:


    Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
    Output: true
    Example 3:



    Input: root = [1,2,3,null,4], x = 2, y = 3
    Output: false


    Constraints:

    The number of nodes in the tree will be between 2 and 100.
    Each node has a unique integer value from 1 to 100.
 */
public class CousinsinBinaryTree {

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

    // General idea: BFS

    // Solution 1: Iterative method using queue
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(q.size() > 0){
            TreeNode x_p = null;
            TreeNode y_p = null;
            for(int i = q.size(); i > 0; i--){
                TreeNode node = q.poll();
                if (node.left != null) {
                    if (node.left.val == x) {
                        x_p = node;
                    }
                    if (node.left.val == y) {
                        y_p = node;
                    }
                    q.offer(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x) {
                        x_p = node;
                    }
                    if (node.right.val == y) {
                        y_p = node;
                    }
                    q.offer(node.right);
                }
                if(x_p != null && x_p == y_p){
                    return false;
                }
            }
            if(x_p != null && y_p != null){
                return true;
            }
        }
        return false;
    }

    // Solution 2: Recursive method
    class Node{
        TreeNode parent;
        int level;
        Node(TreeNode n, int l){
            this.parent = n;
            this.level = l;
        }
    }
    public boolean isCousins2(TreeNode root, int x, int y) {
        Node n1 = findParentHelper(root, 0, x, null);
        Node n2 = findParentHelper(root, 0, y, null);
        return n1.level == n2.level && n1.parent != n2.parent;
    }

    private Node findParentHelper(TreeNode root, int level, int target, TreeNode parent){
        if(root == null) return null;
        if(root.val == target) return new Node(parent, level);
        Node left = findParentHelper(root.left, level + 1, target, root);
        Node right = findParentHelper(root.right, level + 1, target, root);
        return left == null ? right : left;
    }
}
