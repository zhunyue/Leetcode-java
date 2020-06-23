package DP;

/*
    The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

    The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

    Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

    In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.



    Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

    For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

    -2 (K)	-3	3
    -5	-10	1
    10	30	-5 (P)


    Note:

    The knight's health has no upper bound.
    Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 */
public class DungeonGame {
    /*
        Top-down DP
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        return helper(dungeon, 0, 0, dp);
    }

    private int helper(int[][] d, int i, int j, int[][] dp){
        int m = d.length;
        int n = d[0].length;
        if(i == m - 1 && j == n - 1){
            return Math.max(1 - d[i][j], 1);
        }
        if(i >= m || j >= n){
            return Integer.MAX_VALUE;
        }
        if(dp[i][j] != 0){
            return dp[i][j];
        }
        int needed = Math.min(helper(d, i, j+1, dp), helper(d, i+1, j, dp)) - d[i][j];
        dp[i][j] = Math.max(needed, 1);
        return dp[i][j];
    }
    /*  Solution 2:  Bottom up DP
        dp[i][j]: Minimum steps needed to keep all step after i, j has at least initial >= 1
        dp[m-1][n-1]: if dungeon[m - 1][n - 1] > 1, then at least 1 to ensure alive before inde [m-1,n-1]; otherwise, we need  Math.abs(dungeon[m - 1][n - 1]) + 1
        dp[m-1][j](not on boarder): it should make its right cell qualify  dp[i][j] ------> dungeon[i][j]  ------> at least dp[i][j+1]. Therefore, dp[i][j] + dungeon[i][j] == dp[i][j+1]. And we still ensure at least 1;
        dp[i][n-1]: similar to dp[m-1][j];
        dp[i][j](not on boarder): we choose the minimum of its left and top
    */
    public int calculateMinimumHP2(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                if(i == m - 1 && j == n - 1){
                    dp[i][j] = Math.max(1, 1 - dungeon[i][j]);
                } else if(i == m - 1){
                    dp[i][j] = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
                } else if(j == n - 1){
                    dp[i][j] = dp[i][n - 1] = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
                } else{
                    int left = Math.max(dp[i][j+1] - dungeon[i][j], 1);
                    int top = Math.max(dp[i+1][j] - dungeon[i][j], 1);
                    dp[i][j] = Math.min(top, left);
                }
            }
        }
        return dp[0][0];
    }

}
