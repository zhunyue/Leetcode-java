package DP;

public class BestTimetoBuyandSellStock3 {
    /*
          2 DP array
          left[i]: the max profit we can make from day 0 to day i
          right[i]: the max profit we can make from day 1 to the last day
     */
    public int maxProfit(int[] prices) {
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        int profit = 0;
        if(prices.length <= 0){
            return 0;
        }
        left[0] = 0;
        int min = prices[0];
        for(int i = 1; i < prices.length; i++){
            min = Math.min(prices[i], min);
            left[i] = Math.max(prices[i] - min, left[i-1]);
        }

        right[prices.length - 1] = 0;
        int max = prices[prices.length -1];
        for(int i = prices.length - 2; i >= 0; i--){
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i+1], max - prices[i]);
        }

        for(int i = 0; i < prices.length; i++){
            profit = Math.max(profit, left[i]+right[i]);
        }
        return profit;
    }

    /*
        Using four variables to store the profit we can make;
        we want to max the four variables and eventually we want to maximize sell2
     */
    public int maxProfit2(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0;
        int buy2 = Integer.MIN_VALUE, sell2 = 0;

        for(int price : prices){
            if(-price > buy1) buy1 = -price;
            if(price + buy1 > sell1) sell1 = price - buy1;
            if(sell1 - price > buy2) buy2 = sell1 - price;
            if(buy2 + price > sell2) sell2 = buy2 + price;
        }
        return sell2;
    }
}
