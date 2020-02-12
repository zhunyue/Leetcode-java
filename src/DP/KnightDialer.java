package DP;

/*
A chess knight can move as indicated in the chess diagram below:
                            1  2  3
                            4  5  6
                            7  8  9
                               0
This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.

Example 1:

Input: 1
Output: 10
 */
public class KnightDialer {
    /*
        Solution: DP with N * 10 grid where dp[i][j] represent the distinct numbers we can find with i steps from number j
     */
    public int knightDialer(int N) {
        int base = 1000000007;
        if(N == 1) return 10;
        //move is an array of next moves for each number
        int[][] move = {{4,6}, {6,8}, {7,9}, {4,8}, {3,9,0}, {}, {1,7,0}, {2,6}, {1,3}, {2,4}};
        int[][] dp = new int[N][10];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < 10; j++){
                if(i == 0){
                    dp[i][j] = 1;
                } else{
                    for(int n: move[j]){
                        dp[i][j] = (dp[i][j] + dp[i-1][n]) % base;
                    }
                }
            }
        }

        int res = 0;
        for(int i = 0; i < 10; i++){
            res = (res + dp[N-1][i]) % base;
        }
        return res;
    }
}
