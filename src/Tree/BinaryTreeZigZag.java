package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */

public class BinaryTreeZigZag {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Boolean fromLeft = true;
        List<List<Integer>> res = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        if(root == null) return res;
        s.push(root);
        while(!s.isEmpty()){
            Stack<TreeNode> next = new Stack<>();
            List<Integer> tmp = new ArrayList<>();

            while(!s.isEmpty()){
                TreeNode node = s.pop();
                tmp.add(node.val);
                if(fromLeft){
                    if(node.left != null) next.push(node.left);
                    if(node.right != null) next.push(node.right);
                } else{
                    if(node.right != null) next.push(node.right);
                    if(node.left != null) next.push(node.left);
                }
            }
            res.add(tmp);
            s = next;
            fromLeft = !fromLeft;
        }
        return res;
    }
}
