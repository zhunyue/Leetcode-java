package String;

/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */
public class LongestPalindromicSubstring {
  /*
    Solution 1:
    Idea: Brute Force
          We search for all substring and check if it is Palindrome.
          How to check if Palindrome? We check all pairs a[0] a[n-1], a[1], a[n-2] and see if they are the same

        *** TIME LIMIT EXCEED ***
   */
  public String longestPalindrome(String s) {
      int maxLen = 0;
      String maxStr = null;
      for (int i = 0; i < s.length(); i++) {
          for (int j = 0; j < s.length(); j++) {
              if (isPalindrome(s, i, j)) {
                  if (j - i + 1 > maxLen) {
                      maxLen = j - i + 1;
                      maxStr = s.substring(i, j+1);
                  }
              }
          }
      }
      return maxStr;
  }

    private boolean isPalindrome(String str, int s, int e){
        int len = e - s + 1;
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(s+i) != str.charAt(e-i)) {
                return false;
            }
        }
        return true;
    }

    /*
      Solution 2:
      Idea: DP solution. Let dp[i][j] represent if substring from i to j (included) is a Palindrome
            dp[i][j] = true if dp[i+1][j-1] && s.charAt(i) == s.charAt(j)
            dp[i][j] = false Otherwise

     */
    public String longestPalindrome2(String s) {
        boolean dp[][] = new boolean[s.length()][s.length()];
        if (s.length() <= 0) return null;
        int left = 0;
        int right = 0;
        int maxLen = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (j == i + 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }
                if (dp[i][j] && maxLen < j - i + 1) {
                    maxLen = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    /*
      Solution 3: Expand from center
      Idea: For each character we expand from two center: s.charAt(i) and s.charAt(i), s.charAt(i+1). We try to expand as:
            (xxxxx) s[i] (xxxxx) and (xxxxx) s[i], s[i+1] (xxxxx)
     */
    public String longestPalindrome3(String s) {
        int maxLen = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        // Here we minus exta 2 because in the final iteration of loop, if we are at boundary, we will still expand one time
        return right - left - 1 - 2;
    }

}
