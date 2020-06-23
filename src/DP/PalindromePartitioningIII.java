package DP;

/*
1278. Palindrome Partitioning III

You are given a string s containing lowercase letters and an integer k. You need to :

First, change some characters of s to other lowercase English letters.
Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
Return the minimal number of characters that you need to change to divide the string.

Example 1:
Input: s = "abc", k = 2
Output: 1
Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.

Example 2:
Input: s = "aabbc", k = 3
Output: 0
Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.

Example 3:
Input: s = "leetcode", k = 8
Output: 0

Constraints:

1 <= k <= s.length <= 100.
s only contains lowercase English letters.
 */
public class PalindromePartitioningIII {
    private int dis(String s, int i, int j) {
        int count = 0;
        while(i < j){
            if(s.charAt(i - 1) != s.charAt(j - 1)){
                count++;
            }
            i++;
            j--;
        }
        return count;
    }
    public int palindromePartition(String s, int k) {
        if(s.length() == k) return 0;

        int[][] cost = new int[s.length() + 1][s.length() + 1]; // steps needed to convert substring [i-1,j-1] to Palindrome

        for(int i = s.length(); i > 0; i--){
            for(int j = i + 1; j <= s.length(); j++){
                cost[i][j] = cost[i+1][j-1] + (s.charAt(i-1) == s.charAt(j-1) ? 0 : 1);
            }
        }
        // dp[i][j]: end in i-th element in string and split into k part
        // dp[m][j-1] is the minimum steps needed for the string end in m-th position
        // and split into j-1 part, cost[m+1][i] is the steps needed to convert substring [m+1, i] to palindrome.
        // find the minimum split position m such that dp[m][j - 1] + cost[m+1][i] is the smallest .
        int[][] dp = new int[s.length() + 1][k+1];
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= i && j <= k; j++){
                dp[i][j] = Integer.MAX_VALUE;
                if(j == 1) dp[i][j] = cost[1][i];
                else {
                    for(int m = 1; m < i; m++){
                        dp[i][j] = Math.min(dp[i][j], dp[m][j - 1] + cost[m+1][i]);
                    }
                }
            }
        }
        return dp[s.length()][k];

    }

}
