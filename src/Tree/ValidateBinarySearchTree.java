package Tree;

import java.util.ArrayList;
import java.util.List;

/*
    Given a binary tree, determine if it is a valid binary search tree (BST).

    Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.


    Example 1:

        2
       / \
      1   3

    Input: [2,1,3]
    Output: true
    Example 2:

        5
       / \
      1   4
         / \
        3   6

    Input: [5,1,4,null,null,3,6]
    Output: false
    Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBinarySearchTree {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    /*
         Inorder traversal
     */
    public boolean isValidBST(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        inorder(root,res);
        for(int i=0;i<res.size()-1;i++){
            if(res.get(i)>=res.get(i+1))
                return false;
        }
        return true;
    }
    public void inorder(TreeNode root, List<Integer> res){
        if(root==null)
            return;
        inorder(root.left,res);
        res.add(root.val);
        inorder(root.right,res);
    }

    /*
        Recursive
     */
    public boolean isValidBST2(TreeNode root) {
        return helper(root, null, null);
    }
    public boolean helper(TreeNode root, Integer min, Integer max){
        if(root == null){
            return true;
        }
        int val = root.val;
        if((min != null && val >= min) || (max != null && val <= max)){
            return false;
        }
        return helper(root.left, min, val) && helper(root.right, val, max);
    }
}
