package DP;

/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?
 */
public class UniquePaths2 {
    /*
        If the cell is a obstacle , dp[i][j] should be 0
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m == 0 || n == 0 || obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i=1;i<m;i++) {
            if (obstacleGrid[i][0] == 0 && dp[i-1][0] == 1) {
                dp[i][0] = 1;
            }
        }

        for (int j=1;j<n;j++) {
            if (obstacleGrid[0][j] == 0 && dp[0][j-1] == 1) {
                dp[0][j] = 1;
            }
        }

        for (int i=1;i<m;i++) {
            for (int j=1;j<n;j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];

    }
}
