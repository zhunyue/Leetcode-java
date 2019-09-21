package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]

 */
public class InorderTraversal {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  /*
        Recursive
   */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res){
        if(root != null){
            if(root.left != null){
                helper(root.left, res);
            }
            res.add(root.val);
            if(root.right != null){
                helper(root.right, res);
            }
        }
    }

      /*
        Iterative
   */
      public List<Integer> inorderTraversal2(TreeNode root) {
          List<Integer> res = new ArrayList<>();
          Stack<TreeNode> s = new Stack<>();
          TreeNode cur = root;
          while(cur != null || !s.isEmpty()){
              while(cur != null){
                  s.push(cur);
                  cur = cur.left;
              }
              cur =s.pop();
              res.add(cur.val);
              cur = cur.right;
          }
          return res;
      }

}
