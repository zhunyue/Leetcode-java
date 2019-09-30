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
    public int maxPathSum(TreeNode root) {
        int[] maxSum = {root.val};
        findMax(root, maxSum);
        return maxSum[0];
    }

    // return path sum start from node root
    public int findMax(TreeNode root, int[] maxSum){
        if(root == null){
            return 0;
        }

        int left = findMax(root.left, maxSum);
        int right = findMax(root.right, maxSum);
        maxSum[0] = Math.max(maxSum[0], root.val); // only root
        maxSum[0] = Math.max(maxSum[0], root.val + left);  // root and left subtree
        maxSum[0] = Math.max(maxSum[0], root.val + right); // root and right subtree
        maxSum[0] = Math.max(maxSum[0], root.val + left + right); // root, left subtree, right subtree
        return root.val + Math.max(0, Math.max(left, right));
    }
}
