package Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Problem Description:

   Given a string s, partition s such that every substring of the partition is a palindrome.

   Return all possible palindrome partitioning of s.

   Example:

     Input: "aab"
     Output:
     [
       ["aa","b"],
       ["a","a","b"]
     ]
*/

public class PalindromePartitioning {
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        int[][] isPalindrome = new int [s.length()][s.length()];
        for(int i=s.length()-1; i>=0; i--){
            for(int j = i; j<s.length(); j++){
                if(s.charAt(i)==s.charAt(j)){
                    if(j<=i+2){
                        isPalindrome[i][j]=1;
                    }
                    else{
                        isPalindrome[i][j] = isPalindrome[i+1][j-1];
                    }
                }
            }
        }
        dfs(0, s.length()-1, s, isPalindrome, new ArrayList<>(), res);
        return res;
    }
    private static void dfs(int start, int end, String s, int[][] isPalindrome, List<String> cur, List<List<String>> res){
        if(start>end){
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i=start; i<=end; i++){
            if(isPalindrome[start][i]==1){
                cur.add(s.substring(start,i+1));
                dfs(i+1,end,s,isPalindrome,cur,res);
                cur.remove(cur.size()-1);
            }
        }
    }
}
