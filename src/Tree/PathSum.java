package Tree;

/*
    Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

    Note: A leaf is a node with no children.

    Example:

    Given the below binary tree and sum = 22,

          5
         / \
        4   8
       /   / \
      11  13  4
     /  \      \
    7    2      1
    return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;
        return helper(root, sum, 0);
    }

    public boolean helper(TreeNode root, int sum, int cur){
        if(root.left == null && root.right == null){
            return (cur+root.val) == sum;
        } else if (root.left != null && root.right != null){
            return helper(root.left, sum, cur+root.val) || helper(root.right, sum, cur+root.val);
        } else if(root.left == null){
            return helper(root.right, sum, cur+root.val);
        } else{
            return helper(root.left, sum, cur+root.val);
        }
    }
}
