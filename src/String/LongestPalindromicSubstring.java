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
    /*   Expand aound center
            In fact, we could solve it in O(n^2)time using only constant space.
            We observe that a palindrome mirrors around its center.
            Therefore, a palindrome can be expanded from its center, and there are only 2n−1 such centers.
            It is 2n-1 because we could expand from one single character or if the next character is the same, like "bb", we can also expand it.
            !!!!! One thing to remember: do not return substring, return start and end position instead. (It will be faster)

    */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int left = 0;
        int right = 0;
        for(int i = 0; i< s.length(); i++){
            int len1 = expand(s, i ,i);
            int len2 = expand(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len > right - left){
                left = i - (len - 1) / 2;
                right = i + len / 2;
            }
        }
        return s.substring(left, right+1);
    }

    public int expand(String s, int left, int right){
        int L = left;
        int R = right;
        while(L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R-L-1;

    }
    /*   DP
             Consider the case "ababa".
             If we already knew that "bab" is a palindrome, it is obvious that "ababa" must be a palindrome
             since the two left and right end letters are the same.
             There for we could have a 2-D array dp[][] where dp[i][j] represent if substring(i, j+1) is a palindrome string
    */
    public String longestPalindrome2(String s) {
        boolean dp[][] = new boolean[s.length()][s.length()];
        if(s.length() <= 0){
            return "";
        }
        int maxLen = 0;
        int left = 0;
        int right = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            for(int j = i; j < s.length(); j++){
                if(s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1] == true)){
                    dp[i][j] = true;
                }
                if(dp[i][j] && maxLen <= j - i + 1) {
                    maxLen = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right+1);
    }
}
