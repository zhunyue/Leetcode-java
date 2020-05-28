package Backtracking;

/*
    Given a 2D board and a word, find if the word exists in the grid.

    The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

    Example:

        board =
        [
              ['A','B','C','E'],
              ['S','F','C','S'],
             ['A','D','E','E']
        ]

        Given word = "ABCCED", return true.
        Given word = "SEE", return true.
        Given word = "ABCB", return false.
 */

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int[][] used = new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(helper(board, word, used, 0,i,j))
                    return true;
            }
        }
        return false;
    }

    public boolean helper(char[][] board, String word, int[][] used, int index, int row, int col){
        if(index >= word.length()){
            return true;
        }
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || used[row][col] ==1)
            return false;
        if(board[row][col] == word.charAt(index)){
            used[row][col] = 1;
            boolean res=helper(board,word,used,index+1,row+1,col)||
                    helper(board,word,used,index+1,row-1,col)||
                    helper(board,word,used,index+1,row,col+1)||
                    helper(board,word,used,index+1,row,col-1);
            used[row][col] = 0;
            return res;
        }
        return false;
    }
}
