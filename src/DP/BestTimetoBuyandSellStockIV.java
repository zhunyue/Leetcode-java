package DP;

/*
188.
Say you have an array for which the i-th element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

 */
public class BestTimetoBuyandSellStockIV {
    /*
        1. The last transaction cannot be buy, we can choose to sell or no transaction
        2. local[i][j]: the maximum profit we can make at i-th day for at most j transactions and the last transaction happens
                        at the i-th day is a sell operation.
           if at (i-1)-th day, at most j transactions and the last operation is sell : local[i-1][j] + diff (delay the transaction to i-th day)
           if at (i-1)-th day, at most j-1th operation happens: global[i-1][j-1]+Math.max(diff,0)
                                if buy at i-1, then sell at i -  global[i-1][j-1] + diff
                                else if sell/no transaction, then buy and sell at i - global[i-1][j-1] + 0
        3. global[i][j] = Math.max(local[i][j], global[i-1][j]);
     */
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        int max = 0;
        if(k <= 0 || len < 2)
            return 0;
        // If k is large enough, then it is the same as we can transact as many as we want, therefore, we can use greedy
        if(k >= len/2){
            for(int i = 1; i < len; i++){
                int diff = prices[i] - prices[i-1];
                if( diff > 0){
                    max += diff;
                }
            }
            return max;
        }
        int[][] local = new int[len][k+1];
        int[][] global = new int[len][k+1];
        for(int i = 1; i < len; i++){
            int diff = prices[i] - prices[i-1];
            for(int j = 1; j <=k; j++){
                local[i][j] = Math.max(global[i-1][j-1]+Math.max(diff,0), local[i-1][j] + diff);
                global[i][j] = Math.max(local[i][j], global[i-1][j]);
            }
        }

        return global [len-1][k];
    }
}
