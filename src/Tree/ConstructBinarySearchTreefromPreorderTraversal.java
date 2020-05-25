package Tree;

/*

1008. Construct Binary Search Tree from Preorder Traversal

    Return the root node of a binary search tree that matches the given preorder traversal.

    (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

    It's guaranteed that for the given test cases there is always possible to find a binary search tree with the given requirements.

    Example 1:

    Input: [8,5,1,7,10,12]
    Output: [8,5,10,1,7,null,12]

    Constraints:

        1 <= preorder.length <= 100
        1 <= preorder[i] <= 10^8
        The values of preorder are distinct.

 */
public class ConstructBinarySearchTreefromPreorderTraversal {



     // Definition for a binary tree node.
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

    // Solution 1: When doing recursion, find the number of elements to the left
    // Time complexity: O(n^2)
        public TreeNode bstFromPreorder(int[] preorder) {
            return helper(preorder, 0, preorder.length - 1);
        }

        public TreeNode helper(int[] order, int s, int e){
            int val = order[s];
            TreeNode root = new TreeNode(val);
            int count = 0;
            for(int i = s; i <= e; i++){
                if(order[i] < val) count++;
            }
            if(count > 0)
                root.left = helper(order, s + 1, s + count);
            if(count < e - s)
                root.right = helper(order, s + count + 1, e);
            return root;
        }

    // Solution 2: When doing recursion, keep an upper limit
    // Time complexity: O(n)
    int preIndex = 0; // The element to be visited
    public TreeNode bstFromPreorder2(int[] preorder) {
        return constructBST(preorder,Integer.MAX_VALUE);
    }

    TreeNode constructBST(int[] preOrder,int val){ // val is the the upper limit
        if (preIndex==preOrder.length || val<preOrder[preIndex])
            return null;
        TreeNode root=new TreeNode(preOrder[preIndex++]);
        root.left=constructBST(preOrder,root.val);
        root.right=constructBST(preOrder,val);
        return root;
    }
}
