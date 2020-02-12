package Backtracking;

/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {
    public static List<List<String>> solveQueens(int n){
        List<List<String>> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        helper(n, 0, res, cur);
        return res;
    }

    public static void helper(int n, int row, List<List<String>> res, List<String> cur){
        if(row == n){
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = 0; i < n; i++){
            if(isValid(n, row, i, cur)){
                char[] c = new char[n];
                Arrays.fill(c, '.');
                c[i] = 'Q';
                cur.add(String.valueOf(c));

                helper(n, row+1, res, cur);
                cur.remove(cur.size()-1);
            }
        }
    }

    public static boolean isValid(int n, int row, int col, List<String> cur){
        for(int i = 0; i < row; i++){
            if(cur.get(i).charAt(col) == 'Q') return false;
        }

        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--){
            if(cur.get(i).charAt(j) == 'Q') return false;
        }

        for(int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++){
            if(cur.get(i).charAt(j) == 'Q') return false;
        }
        return true;
    }
}
