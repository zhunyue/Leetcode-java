package Tree;

import java.util.ArrayList;
import java.util.List;

/*
    Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

    Note: A leaf is a node with no children.

    Example:

    Given the below binary tree and sum = 22,

          5
         / \
        4   8
       /   / \
      11  13  4
     /  \    / \
    7    2  5   1
    Return:

    [
       [5,4,11,2],
       [5,8,4,5]
    ]
 */
public class PathSum2 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if(root == null)
            return res;
        helper(root, sum, res, cur, 0);
        return res;
    }

    public void helper(TreeNode root, int target, List<List<Integer>> res, List<Integer> cur, int curSum){
        curSum += root.val;
        cur.add(root.val);
        if(root.left == null && root.right == null){
            if(curSum == target){
                res.add(new ArrayList<>(cur));
            }
        }
        if(root.left != null){
            helper(root.left, target, res, cur, curSum);
            cur.remove(cur.size()-1);
        }
        if(root.right != null){
            helper(root.right, target, res, cur, curSum);
            cur.remove(cur.size()-1);
        }
    }
}
