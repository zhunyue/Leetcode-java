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
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] memo = new int[m][n];
        return  helper(0,0,dungeon,memo);
    }
    public int helper(int x, int y, int[][] dungeon, int[][] memo){
        if(x == dungeon.length -1 && y == dungeon[0].length - 1){
            return Math.max(1, 1 - dungeon[x][y]);
        }
        int m = dungeon.length;
        int n = dungeon[0].length;
        if(x >= m || y >= n){
            return Integer.MAX_VALUE;
        }
        if(memo[x][y] > 0){
            return memo[x][y];
        }

        int remain = dungeon[x][y] - Math.min(helper(x+1,y,dungeon,memo),helper(x,y+1,dungeon,memo));
        if(remain >= 0){
            remain = -1;
        }
        memo[x][y] = (-1)*remain;
        return memo[x][y];
    }

    /*
        Bottom-up DP
     */
    public int calculateMinimumHP2(int[][] dungeon) {
        int m  = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = (dungeon[m-1][n-1] > 0) ? 1 : 1 - dungeon[m-1][n-1];
        for(int i = m-2; i >= 0; i--){
            int remain = dungeon[i][n-1] - dp[i+1][n-1];
            dp[i][n-1] = Math.max(1, (-1) * remain);
        }

        for(int i = n - 2; i >= 0; i--){
            int remain = dungeon[m-1][i] - dp[m-1][i+1];
            dp[m-1][i] = Math.max(1, (-1) * remain);
        }

        for(int i = m - 2; i >= 0; i--){
            for(int j = n - 2; j >= 0; j--){
                int right = Math.max(1, (-1) * (dungeon[i][j]  - dp[i][j+1]));
                int down = Math.max(1, (-1) * (dungeon[i][j]  - dp[i+1][j]));
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }

}
