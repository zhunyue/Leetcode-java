package Tree;

/*
    Given a non-empty binary tree, find the maximum path sum.

    For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

    Example 1:

    Input: [1,2,3]

           1
          / \
         2   3

    Output: 6
    Example 2:

    Input: [-10,9,20,null,null,15,7]

       -10
       / \
      9  20
        /  \
       15   7

    Output: 42
 */
public class BinaryTreeMaximumPathSum {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;

    }

    public int helper(TreeNode root){
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        int tmax = Integer.MIN_VALUE;
        tmax = Math.max(tmax, left + root.val);
        tmax = Math.max(tmax, right + root.val);
        tmax = Math.max(tmax, root.val);
        max = Math.max(Math.max(max, tmax), right + left + root.val);
        return tmax;

    }
}
