package Greedy;

/*
392. Is Subsequence

    Given a string s and a string t, check if s is subsequence of t.

    A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

    Follow up:
    If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

    Credits:
    Special thanks to @pbrother for adding this problem and creating all test cases.



    Example 1:
    Input: s = "abc", t = "ahbgdc"
    Output: true

    Example 2:
    Input: s = "axc", t = "ahbgdc"
    Output: false


    Constraints:

    0 <= s.length <= 100
    0 <= t.length <= 10^4
    Both strings consists only of lowercase characters.
 */
public class IsSubsequence {

    // Solution 1
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while(i < s.length() && j < t.length()){
            if(s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
        }
        return i == s.length();
    }


    // Solution 2
    public boolean isSubsequence2(String s, String t) {
        return helper(s, t, 0 ,0);
    }

    private boolean helper(String s, String t, int i, int j){
        if(i == s.length()) return true;
        if(j == t.length()) return false;
        if(s.charAt(i) == t.charAt(j)){
            return helper(s, t, i + 1, j + 1);
        } else{
            return helper(s, t, i, j + 1);
        }
    }

    // Solution 3
    public boolean isSubsequence3(String s, String t) {
        boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];
        for(int i = 0; i <= s.length(); i++) {
            for(int j = 0; j <= t.length(); j++) {
                if (i == 0){
                    dp[i][j] = true;
                } else if (j == 0) {
                    dp[i][j] = false;
                } else if (s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    //Solution 4
    public boolean isSubsequence4(String s, String t) {
        int cur = -1; // The last confirmed index
        for(int i = 0; i < s.length(); i++) {
            cur = t.indexOf(s.charAt(i), cur + 1);
            if(cur == -1) return false;
        }
        return true;
    }
}
