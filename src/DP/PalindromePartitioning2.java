package DP;
/*
    Problem Description:

    Given a string s, partition s such that every substring of the partition is a palindrome.

    Return the minimum cuts needed for a palindrome partitioning of s.

    Example:
      Input: "aab"
      Output: 1
      Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

public class PalindromePartitioning2 {
    public int minCut(String s) {
        int[][] isPalindrome = new int[s.length()][s.length()];
        int[] dp = new int[s.length()+1];
        dp[0] = -1;
        for(int i=0; i<s.length(); i++){
            dp[i+1] = i;
            for(int j=0;j<=i;j++){
                if (s.charAt(j)==s.charAt(i)&&(i-j<2||isPalindrome[i-1][j+1]==1)){
                    isPalindrome[i][j]=1;
                    dp[i+1]=Math.min(dp[i+1],dp[j]+1);
                }
            }
        }
        return dp[s.length()];
    }
}
