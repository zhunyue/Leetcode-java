package DP;

/*
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

    /*
        Top-down Memoization
     */
    private static int[][] memo;
    public int minPathSum2(int[][] grid) {
        memo = new int[grid.length][grid[0].length];

        /** Mark all locations as "unsolved-yet" */
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }

        int x = grid.length - 1;
        int y = grid[0].length - 1;
        return helper(grid, x, y);
    }

    private int helper(int[][] grid, int x, int y) {
        if (x == 0 && y == 0) return grid[x][y];
        if (x < 0 || y < 0) return Integer.MAX_VALUE;

        if (memo[x][y] != -1) return memo[x][y];

        memo[x][y] = grid[x][y] + Math.min(helper(grid, x - 1, y), helper(grid, x, y - 1));

        return memo[x][y];
    }
}
