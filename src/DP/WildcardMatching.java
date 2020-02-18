package DP;

/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.

Example
Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
 */
public class WildcardMatching {
    /*
        Solution 1: DP
        dp[i][j] indicates whether the string from first i characters in string could match the string from the first j characters in p
        dp[0][0] = true;
        dp[i][0] would be false if i != 0
        dp[0][i] = dp[0][i-1] if the j-th character in p is *



    */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int i = 0; i <= s.length(); i++){
            for(int j = 1; j <= p.length(); j++){
                if(i == 0){
                    if(p.charAt(j - 1) == '*'){
                        dp[i][j] = dp[i][j-1];
                    }
                } else{
                    if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?'){
                        dp[i][j] = dp[i-1][j-1];
                    } else if (p.charAt(j - 1) == '*'){
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    }
                }

            }
        }
        return dp[s.length()][p.length()];
    }

    /*
        Solution 2: Recursion
        Keep moving pointers if the character matches;
        record the star position and how long the s substring match the star if it is a star in p;
        otherwise, change i and j if we have a star recorded or return false if no star is recorded

     */
    public boolean isMatch2(String s, String p) {
        int lenS = s.length();
        int lenP = p.length();
        int  i = 0, j = 0;
        int sMatch = -1; //Record the match in String s from last star
        int pStar = -1; //Record the position of last star

        while(i < lenS){
            if(j < lenP && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')){
                i++;
                j++;
            } else if(j < lenP && p.charAt(j) == '*'){
                sMatch = i;
                pStar = j;
                j++;
            } else if(pStar == -1){
                return false;
            } else{
                i = sMatch++;
                j = pStar+1;
            }
        }

        while(j < lenP && p.charAt(j) == '*'){
            j++;
        }

        return j == lenP;

    }
}
