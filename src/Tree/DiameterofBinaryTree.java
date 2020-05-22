package Tree;

/*

543. Diameter of Binary Tree

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

 */
public class DiameterofBinaryTree {

      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
      // Solution 1: DFS;
      // Diameter can come from three situation: the left tree height, the right tree height, the path go through root
        int res;
        public int diameterOfBinaryTree(TreeNode root) {
            helper(root);
            return res;
        }
        //Calculate the height
        public int helper(TreeNode root){
            if(root == null)
                return 0;
            int left = helper(root.left);
            int right = helper(root.right);
            res = Math.max(res, left + right);
            return Math.max(left, right) + 1;
        }

        // Solution 2: DFS, similar but slightly different in implementatino
        public int diameterOfBinaryTree2(TreeNode root) {
            if(root == null)
                return 0;
            int left = helper(root.left);
            int right = helper(root.right);
            int l_d = diameterOfBinaryTree(root.left);
            int r_d = diameterOfBinaryTree(root.right);
            return Math.max(left + right,
                    Math.max(l_d, r_d));
        }
        //Calculate the height
        public int helper2(TreeNode root){
            if(root == null)
                return 0;
            int left = helper(root.left);
            int right = helper(root.right);
            return Math.max(left, right) + 1;
        }
}
