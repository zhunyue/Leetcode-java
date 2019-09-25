package DP;

import java.util.List;

public class Triangle {
    /*
        top-down solution: from top layer to bottom layer with memoization
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() <= 0 || triangle.get(0).size() <= 0)
            return 0;
        int rows = triangle.size();
        int maxcols = triangle.get(triangle.size()-1).size();
        int[][] dp = new int[rows][maxcols];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j< dp[0].length; j++){
                dp[i][j] = -1;
            }
        }
        return helper(triangle,0,0, dp);
    }
    public int helper(List<List<Integer>> res, int row, int index, int[][] dp){
        List<Integer> rowRecord = res.get(row);
        if(dp[row][index] != -1)
            return dp[row][index];
        if(row == res.size() - 1)
            return rowRecord.get(index);
        int sum = rowRecord.get(index);
        int sum1 = sum + helper(res, row+1, index, dp);
        int sum2 = sum + helper(res, row+1, index+1, dp);
        dp[row][index] = Math.min(sum1, sum2);
        return dp[row][index];
    }

    /*
        bottom-up solution: from the bottom layer, find the minimum sum and store it in an array
        In this solution, we use a 2D array, however, the first dimension is actually not necessary since dp[i+1][]
        is useless after dp[i] is calculated. Therefore, we could enhance the solution by using ony 1D array
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()+1][triangle.size()+1];
        for(int i = triangle.size() - 1; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    /*
      bottom-up solution using 1D array
   */
    public int minimumTotal3(List<List<Integer>> triangle) {
        int[] dp= new int[triangle.size()+1];
        for(int i = triangle.size() - 1; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
