package BinarySearchTree;

/*
700. Search in a Binary Search Tree

Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the node's value equals the given value. Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.

For example,

Given the tree:
        4
       / \
      2   7
     / \
    1   3

And the value to search: 2
You should return this subtree:

      2
     / \
    1   3
In the example above, if we want to search the value 5, since there is no node with value 5, we should return NULL.

Note that an empty tree is represented by NULL, therefore you would see the expected output (serialized tree format) as [], not null.
 */
public class SearchinaBinarySearchTree {

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
    // Solution 1: Iteration
    public TreeNode searchBST(TreeNode root, int val) {
        while(root != null){
            if(root.val == val) return root;
            if(root.val > val) root = root.left;
            else root = root.right;
        }
        return null;
    }

    // Solution 2: Recursion
    public TreeNode searchBST2(TreeNode root, int val) {
        return helper(root, val);
    }

    private TreeNode helper(TreeNode root, int val){
        if(root == null || root.val == val) return root;
        if(root.val > val) return helper(root.left, val);
        else return helper(root.right, val);
    }
}