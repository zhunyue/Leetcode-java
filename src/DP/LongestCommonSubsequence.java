package DP;

import java.util.Arrays;

/*
    Given two strings text1 and text2, return the length of their longest common subsequence.

    A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

    If there is no common subsequence, return 0.



    Example 1:

    Input: text1 = "abcde", text2 = "ace"
    Output: 3
    Explanation: The longest common subsequence is "ace" and its length is 3.
    Example 2:

    Input: text1 = "abc", text2 = "abc"
    Output: 3
    Explanation: The longest common subsequence is "abc" and its length is 3.
    Example 3:

    Input: text1 = "abc", text2 = "def"
    Output: 0
    Explanation: There is no such common subsequence, so the result is 0.
 */
public class LongestCommonSubsequence {

    // Solution1 : DP
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1+1][len2+1];
        dp[0][0] = 0;
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[len1][len2];
    }

    // Solution 2: Backtracking using memo
    public int longestCommonSubsequence2(String text1, String text2) {
        int[][] memo = new int[text1.length()][text2.length()];
        for(int[] m: memo){
            Arrays.fill(m, -1);
        }
        return helper(text1, text2, 0, 0, memo); // the maximum length starting from 0 at text1 and 0 at text2
    }

    public int helper(String t1, String t2, int s1, int s2, int[][] memo){
        if(s1 >= t1.length() || s2 >= t2.length()) return 0;
        if(memo[s1][s2] != -1){
            return memo[s1][s2];
        }
        if(t1.charAt(s1) == t2.charAt(s2)){
            memo[s1][s2] = helper(t1, t2, s1+1, s2+1, memo) + 1;
        } else{
            memo[s1][s2] = Math.max(helper(t1, t2, s1+1, s2, memo), helper(t1, t2, s1, s2+1, memo));
        }
        return memo[s1][s2];
    }
}
