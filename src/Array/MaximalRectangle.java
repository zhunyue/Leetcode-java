package Array;

/*
    Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

    Example:

    Input:
    [
      ["1","0","1","0","0"],
      ["1","0","1","1","1"],
      ["1","1","1","1","1"],
      ["1","0","0","1","0"]
    ]
    Output: 6
 */
public class MaximalRectangle {
    /*
        Solution 1: This problem is similar to problem 84. We just have to go through each row and convert the problem to problem 84.
        Using stack
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[] heights = new int[m+1];
        int res = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                heights[j] = matrix[i][j] == '1' ? heights[j]+1 : 0;
            }
            int row_max = maxArea(heights);
            res = Math.max(res, row_max);
        }
        return res;
    }

    public int maxArea(int[] heights){
        int n = heights.length;
        int[] stack = new int[n+1];
        int top = 0;
        stack[top] = -1;
        int res = 0;
        for(int i=0; i<n; i++) {
            while(top>0 && heights[i]<heights[stack[top]]) {
                int h = heights[stack[top--]];
                res = Math.max(res, h*(i-stack[top]-1));
            }
            stack[++top] = i;
        }
        return res;
    }



    /*
        Solution 2: Similar to solution1, but using DP
     */
    public int maximalRectangle2(char[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){

                if(i == 0){
                    dp[i][j] = (matrix[i][j] == '0') ? 0 : 1 ;
                } else{
                    dp[i][j] = (matrix[i][j] == '0') ? 0 : dp[i-1][j] + 1;
                }
                int min = dp[i][j];
                for(int k = j; k >= 0; k--){
                    if(matrix[i][k] == '0') break;
                    if(dp[i][k] < min){
                        min = dp[i][k];
                    }
                    max = Math.max(max, min * (j - k + 1));
                }

            }
        }
        return max;
    }
}
