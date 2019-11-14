package Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a binary tree with N nodes, each node has a different value from {1, ..., N}.

A node in this binary tree can be flipped by swapping the left child and the right child of that node.

Consider the sequence of N values reported by a preorder traversal starting from the root.  Call such a sequence of N values the voyage of the tree.

(Recall that a preorder traversal of a node means we report the current node's value, then preorder-traverse the left child, then preorder-traverse the right child.)

Our goal is to flip the least number of nodes in the tree so that the voyage of the tree matches the voyage we are given.

If we can do so, then return a list of the values of all nodes flipped.  You may return the answer in any order.

If we cannot do so, then return the list [-1].
 */
public class FlipBinaryTreeToMatchPreorderTraversal {
    /*
        DFS:
            If the current node does not match the one in voyage, return false
            If left child val != v[next] , visit right child then left child
            If any of the child is not feasible, return false
        Time Complexity : O(N)
     */
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    int cur = 0;
    List<Integer> res = new ArrayList<>();
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        return helper(root, voyage) ? res : Arrays.asList(-1);
    }

    private boolean helper(TreeNode root, int[] v){
        if(root == null) return true;
        if(root.val != v[cur++]){
            return false;
        }
        if(root.left != null && root.left.val != v[cur]){
            res.add(root.val);
            return helper(root.right, v) && helper(root.left, v);
        }
        return helper(root.left, v) && helper(root.right, v);

    }
}
