package DP;

/*
    Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

    You have the following 3 operations permitted on a word:

    Insert a character
    Delete a character
    Replace a character
    Example 1:

    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation:
    horse -> rorse (replace 'h' with 'r')
    rorse -> rose (remove 'r')
    rose -> ros (remove 'e')
    Example 2:

    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Explanation:
    intention -> inention (remove 't')
    inention -> enention (replace 'i' with 'e')
    enention -> exention (replace 'n' with 'x')
    exention -> exection (replace 'n' with 'c')
    exection -> execution (insert 'u')
 */
public class EditDistance {
    // Solution 1: dp starting from 0
    public int minDistance(String word1, String word2) {
        int[][] dp = new int [word1.length()+1][word2.length()+1];
        for(int i = 0; i <= word1.length(); i++){
            for(int j = 0; j <= word2.length(); j++){
                if(i == 0){
                    dp[i][j] = j;
                } else if (j == 0){
                    dp[i][j] = i;
                } else if (word1.charAt(i-1) != word2.charAt(j-1)){
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1],dp[i-1][j]));
                } else{
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    // Solution 1.1: dp starting from end
    public int minDistance2(String word1, String word2) {
        if (word1 == null || word2 == null || word1.equals(word2)) {
            return 0;
        }
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int i = word1.length(); i >= 0; i--){
            for(int j = word2.length(); j >= 0; j--){
                if(i == word1.length()){
                    dp[i][j] = word2.length() - j;
                } else if(j == word2.length()){
                    dp[i][j] = word1.length() - i;
                } else{
                    if(word1.charAt(i) == word2.charAt(j)){
                        dp[i][j] = dp[i+1][j+1];
                    } else{
                        dp[i][j] = Math.min(dp[i+1][j+1], Math.min(dp[i+1][j], dp[i][j+1])) + 1;
                    }
                }

            }
        }
        return dp[0][0];
    }
}
