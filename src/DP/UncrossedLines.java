package DP;

/*
1035. Uncrossed Lines

    We write the integers of A and B (in the order they are given) on two separate horizontal lines.

    Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
    A[i] == B[j];
    The line we draw does not intersect any other connecting (non-horizontal) line.

    Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

    Return the maximum number of connecting lines we can draw in this way.



    Example 1:
    Input: A = [1,4,2], B = [1,2,4]
    Output: 2
    Explanation: We can draw 2 uncrossed lines as in the diagram.
    We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.

    Example 2:
    Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
    Output: 3

    Example 3:
    Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
    Output: 2

 */
public class UncrossedLines {
    // Solution: bottom up DP
    public int maxUncrossedLines(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];
        for(int i = A.length - 1; i >= 0; i--){
            for(int j = B.length - 1; j >= 0; j--){
                if(A[i] == B[j]){
                    dp[i][j] = dp[i+1][j+1] + 1;
                } else{
                    dp[i][j] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }
        return dp[0][0];
    }

    // Solution: top down DP
    public int maxUncrossedLines2(int[] A, int[] B) {
        int[][] dp = new int[A.length][B.length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B.length; j++){
                dp[i][j] = -1;
            }
        }
        return helper(A, B, 0, 0, dp);
    }

    public int helper(int[] A, int[] B, int i, int j, int[][] dp){
        if(i == A.length || j == B.length){
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        if(A[i] == B[j]){
            dp[i][j] = 1 + helper(A, B, i + 1, j + 1, dp);
            return dp[i][j];
        }
        else{
            dp[i][j] = Math.max(helper(A, B, i + 1, j, dp),  helper(A, B, i, j + 1, dp));
            return dp[i][j];
        }

    }
}
