package DP;

import java.util.ArrayList;
import java.util.List;

/*
    Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

    Example:
    Input: 3
    Output:
    [
      [1,null,3,2],
      [3,2,null,1],
      [3,1,null,null,2],
      [2,1,3],
      [1,null,2,null,3]
    ]

    Explanation:
    The above output corresponds to the 5 unique BST's shown below:

       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3
 */

public class UniqueBinarySearchTrees2 {
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }

   /*
         Memoization: an array in which tree[i][j] stores the List of BST trees using numbers from i to j
         Find all possible solutions
   */

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[][] trees = new List[n][n];
        List<TreeNode> res = helper(trees, 1, n);
        return res;
    }

    public List<TreeNode> helper(List<TreeNode>[][] trees, int start, int end){
        List<TreeNode> res = new ArrayList<>();
        if(start > end){
            res.add(null);
            return res;
        }
        if(trees[start-1][end-1] != null){
            return trees[start-1][end-1];
        }
        for(int i = start; i <= end; i++){
            List<TreeNode> left = helper(trees, start, i-1);
            List<TreeNode> right = helper(trees, i+1, end);
            for(TreeNode l : left){
                for(TreeNode r : right){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        trees[start-1][end-1] = res;
        return res;
    }
}

