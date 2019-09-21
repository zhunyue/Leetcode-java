package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostOrderTraversal {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root);
        return res;
    }
    private static void helper(List<Integer> res, TreeNode root){
        if (root != null){
            if(root.left != null){
                helper(res, root.left);
            }
            if(root.right != null){
                helper(res, root.right);
            }
            res.add(root.val);
        }
    }
/*
     Iterative -1
 */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode last = root;
        while(cur != null){
            stack.push(cur);
            cur = cur.left;
        }
        while(!stack.isEmpty()){
            cur = stack.pop();
            if(cur.right != null && last != cur.right){
                stack.push(cur);
                cur = cur.right;
                while(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }

            } else{
                res.add(cur.val);
                last=cur;

            }
        }
        return res;
    }

    /*
        Iterative - 2
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode last = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if(cur.right != null && last != cur.right){
                cur = cur.right;
            } else{
                res.add(cur.val);
                last = stack.pop();
                cur = null;
            }
        }
        return res;
    }
}
