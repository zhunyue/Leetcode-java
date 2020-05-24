package DFS;

import java.util.*;

/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */
public class NumberofIslands {
    // Solution1: DFS, traverse every "1", and mark all its connected vertices as "0"
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m == 0)
            return 0;
        int n = grid[0].length;
        int total = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    total ++;
                    helper(i,j,grid);
                }
            }
        }
        return total;
    }

    public void helper(int i, int j, char[][] grid) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        helper(i-1,j,grid);
        helper(i+1,j,grid);
        helper(i,j-1,grid);
        helper(i,j+1,grid);
    }

    //Solution2: BFS to traverse neighbors which are not visited of every "1"
    public int numIslands2(char[][] grid) {
        int m = grid.length;
        if(m <= 0) return 0;
        int n = grid[0].length;
        int total = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    System.out.println("************" + i + ", "+j);
                    helper(grid, i, j);
                    total++;
                }
            }
        }
        return total;
    }

    public boolean isValid(char[][] grid, int nX, int nY){
        int m = grid.length;
        int n = grid[0].length;
        if(nX<0||nX>=m||nY<0||nY>=n) return false;
        return true;
    }
    public void helper(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
            return;
        Queue<List<Integer>> q = new LinkedList<>();
        q.add(new ArrayList<>(Arrays.asList(i,j)));
        grid[i][j] = '0';
        while(q.size() > 0){
            List<Integer> l = q.poll();
            i = l.get(0);
            j = l.get(1);
            System.out.println(i + ", "+j);
            if(isValid(grid, i-1, j) && grid[i-1][j] == '1'){
                grid[i-1][j] = '0';
                q.add(new ArrayList<>(Arrays.asList(i-1,j)));
            }
            if(isValid(grid, i+1, j) && grid[i+1][j] == '1'){
                grid[i+1][j] = '0';
                q.add(new ArrayList<>(Arrays.asList(i+1,j)));
            }
            if(isValid(grid, i, j-1) && grid[i][j-1] == '1'){
                grid[i][j-1] = '0';
                q.add(new ArrayList<>(Arrays.asList(i,j-1)));
            }
            if(isValid(grid, i, j+1) && grid[i][j+1] == '1'){
                grid[i][j+1] = '0';
                q.add(new ArrayList<>(Arrays.asList(i,j+1)));
            }
        }
    }
}
