package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*

230. Kth Smallest Element in a BST

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.



Example 1:
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?



Constraints:

The number of elements of the BST is between 1 to 10^4.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 */
public class KthSmallestElementinaBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    //  Solution 1: Iterative inorder traversal
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        while(root != null | !s.isEmpty()){
            while(root != null) {
                s.push(root);
                root = root.left;
            }
            root = s.pop();
            k--;
            if(k == 0) return root.val;
            root = root.right;
        }
        return -1;
    }

    // Solution 2: Recursive inorder traversal
    public int kthSmallest2(TreeNode root, int k) {
        List<Integer> l = new ArrayList<>();
        inorder(l, root);
        return l.get(k - 1);
    }

    private void inorder(List<Integer> l, TreeNode root){
        if(root.left != null) inorder(l, root.left);
        l.add(root.val);
        if(root.right != null) inorder(l, root.right);
    }
}
