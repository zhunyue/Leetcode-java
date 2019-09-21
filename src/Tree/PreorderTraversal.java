package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversal {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  /*
      Iterative
   */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !s.isEmpty()){
            while(cur != null){
                res.add(cur.val);
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            cur = cur.right;
        }
        return res;
    }

    /*
        Recursive
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root);
        return res;
    }
    public void helper(List<Integer> res, TreeNode root){
        if(root != null){
            res.add(root.val);
            if(root.left != null)
                helper(res, root.left);
            if(root.right != null)
                helper(res, root.right);
        }
    }
}
