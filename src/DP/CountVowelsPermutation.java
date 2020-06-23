package DP;

/*
1220. Count Vowels Permutation

Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
Each vowel 'a' may only be followed by an 'e'.
Each vowel 'e' may only be followed by an 'a' or an 'i'.
Each vowel 'i' may not be followed by another 'i'.
Each vowel 'o' may only be followed by an 'i' or a 'u'.
Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:
Input: n = 1
Output: 5
Explanation: All possible strings are: "a", "e", "i" , "o" and "u".

Example 2:
Input: n = 2
Output: 10
Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".

Example 3:
Input: n = 5
Output: 68
 */
public class CountVowelsPermutation {
    // Solution 1: using 2-D array
    // dp[i][j]: the possible permutations ending in j-th vowel of length i
    // O(n) space, O(n) time
    public int countVowelPermutation(int n) {
        long[][] dp = new long[n][5];
        int mod = 1000000007;
        int res = 0;
        for(int i = 0; i < 5; i++) {
            dp[0][i] = 1;
        }
        for(int i = 1; i < n; i++){
            dp[i][0] = dp[i-1][1] + dp[i-1][4] + dp[i-1][2];
            dp[i][1] = dp[i-1][0] + dp[i-1][2];
            dp[i][2] = dp[i-1][1] + dp[i-1][3];
            dp[i][3] = dp[i-1][2];
            dp[i][4] = dp[i-1][3] + dp[i-1][2];
            for(int j = 0; j < 5; j++) {
                dp[i][j] %= mod;
            }
        }
        for(int i = 0; i < 5; i++){
            res += dp[n-1][i];
            res %= mod;
        }
        return res;
    }

    // Solution 2: Using constant space
    // O(1) space, O(n) time
    public int countVowelPermutation2(int n) {
        if(n == 1) return 5;
        int mod = 1000000007;
        int aCount = 1, eCount = 1, iCount = 1, oCount = 1, uCount = 1;
        for(int i = 2; i <= n; i++){
            int new_a = (eCount + iCount) % mod + uCount;
            int new_e = aCount + iCount;
            int new_i = eCount + oCount;
            int new_o = iCount;
            int new_u = oCount + iCount;
            aCount = new_a % mod;
            eCount = new_e % mod;
            iCount = new_i % mod;
            oCount = new_o % mod;
            uCount = new_u % mod;

        }
        int res = (((((((aCount + eCount) % mod) + iCount) % mod) + oCount) % mod) + uCount) % mod;
        return res;
    }
}
