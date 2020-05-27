package DP;

/*

221. Maximal Square

    Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

    Example:

    Input:

    1 0 1 0 0
    1 0 1 1 1
    1 1 1 1 1
    1 0 0 1 0

    Output: 4
 */
public class MaximalSquare {

    // Solution: DP. dp[i][j]: the maximum square end in (i, j)
    // if (i, j) is '1'. We find the minimum of the dp[i-1][j], dp[i][j-1], dp[i-1][j-1]
    // Suppose the minimum value is n, this means the n element to its left is '1', at least n+1 element above it is '1'
    // and n elements in the diagonal is '1'. Therefore, we the maximum here is n+1
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = (matrix[i][j] == '1') ? 1 : 0;
                } else if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max * max;

    }
}
