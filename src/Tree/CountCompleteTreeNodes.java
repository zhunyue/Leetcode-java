package Tree;

/*
222. Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input:
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
 */
public class CountCompleteTreeNodes {

  // Definition for a binary tree node.
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    private int heightLeft(TreeNode root) {
        if(root == null) return 0;
        return 1 + heightLeft(root.left);
    }
    private int heightRight(TreeNode root) {
        if(root == null) return 0;
        return 1 + heightRight(root.right);
    }
    public int countNodes(TreeNode root) {
        int left = heightLeft(root);
        int right = heightRight(root);
        if(left == right){
            return (int)Math.pow(2, left) - 1;
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
}
