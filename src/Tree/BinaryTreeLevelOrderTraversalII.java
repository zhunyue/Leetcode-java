package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
107. Binary Tree Level Order Traversal II

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
 */
public class BinaryTreeLevelOrderTraversalII {

      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    // Solution 1: Iterative
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res=new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root == null) return res;
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode n = q.poll();
                list.add(n.val);
                if(n.left != null) q.offer(n.left);
                if(n.right != null) q.offer(n.right);
            }
            res.add(0, list);
        }
        return res;
    }

    // Solution 2: Recursive
    private void helper(TreeNode root, List<List<Integer>> res, int level){
        if(root == null) return;
        if(level > res.size()){
            res.add(0, new ArrayList<Integer>());
        }
        res.get(res.size() - level).add(root.val);
        helper(root.left, res, level+1);
        helper(root.right, res, level+1);
    }
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res=new LinkedList<>();
        if(root == null) return res;
        helper(root, res, 1);
        return res;
    }
}
