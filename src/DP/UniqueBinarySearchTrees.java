package DP;

/*
    Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

    Example:
    Input: 3
    Output: 5

    Explanation:
    Given n = 3, there are a total of 5 unique BST's:

       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3
 */
public class UniqueBinarySearchTrees {
    /*
         dp[i]: number of unique BSTs using number 1,2,3...i;
         for 1,2,3...i, we could pick any number k within the range as the root, and the total number of
         unique BST when k is the root will be dp[k-1] * dp[i-k] (left subtree times right subtree)
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int k = 1; k <= i; k++){
                dp[i] += dp[k-1] * dp[i-k];
            }
        }
        return dp[n];
    }
}
