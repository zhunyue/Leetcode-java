package DP;

/*

64. Minimum Path Sum

    Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

    Note: You can only move either down or right at any point in time.

    Example:

    Input:
        [
        [1,3,1],
        [1,5,1],
        [4,2,1]
        ]
    Output: 7
    Explanation: Because the path 1→3→1→1→1 minimizes the sum.

 */
public class MinimumPathSum {
    /*
         Bottom-up DP
     */

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        int m = grid.length;
        int n = grid[0].length;
        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int i = 1; i < n; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) continue;
                if(i == 0){
                    dp[i][j] = grid[i][j] + dp[i][j-1];
                } else if(j == 0){
                    dp[i][j] = grid[i][j] + dp[i-1][j];
                } else{
                    dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m-1][n-1];
    }

    /*
        Top-down Memoization, DFS with memoization
     */

    public int minPathSum3(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        return helper(grid, 0, 0, dp);
    }

    public int helper(int[][] grid, int i, int j, int[][] dp) {
        if (i == grid.length || j == grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        dp[i][j] = grid[i][j] + Math.min(helper(grid, i + 1, j, dp), helper(grid, i, j + 1, dp));
        return dp[i][j];
    }
}
