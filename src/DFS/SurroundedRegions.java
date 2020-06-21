package DFS;

/*
130. Surrounded Regions

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:
X X X X
X X X X
X X X X
X O X X

Explanation:
    Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not
    flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be
    flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class SurroundedRegions {
    // General idea: expand all 'O's from border and mark those 'O' as '#' which means these are not surrounded by 'X'
    public void expand(char[][] board,int i,int j, boolean[][] visited){
        if(i >= 0 && i < board.length && j >= 0 && j<board[0].length && !visited[i][j] && board[i][j] == 'O') {
            visited[i][j] = true;
            board[i][j] = '#';
            expand(board, i + 1, j, visited);
            expand(board, i - 1, j, visited);
            expand(board, i, j + 1, visited);
            expand(board, i, j - 1, visited);
        }
    }

    /* Expand all 'O' on the board, and mark the expanded O as #
    At last, all the # are the 'O's that is not surrounded by 'X', therefore replace these '#' by 'O'
    And all remaining 'O's are surrounded by X
    */
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;
        boolean[][] visited = new boolean[board.length][board[0].length];

        for(int i = 0; i < board.length; i++){
            expand(board, i, 0, visited);
            expand(board, i, board[0].length - 1, visited);
        }

        for(int j = 0; j < board[0].length; j++){
            expand(board, 0, j, visited);
            expand(board, board.length - 1, j, visited);
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 'O')      // Change surrounded '0' to 'X'
                    board[i][j] = 'X';
                else if(board[i][j] == '#')    // Change '#' (not surrounded) back to '0'
                    board[i][j] = 'O';
            }
        }
    }
}
