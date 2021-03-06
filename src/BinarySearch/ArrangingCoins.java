package BinarySearch;

/*
441. Arranging Coins

You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:
n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.

Example 2:
n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
 */
public class ArrangingCoins {
    // Solution 1: Straightforward go through
    public int arrangeCoins(int n) {
        int sum = 0;
        int step = 1;
        while(n >= step) {
            sum++;
            n-= step;
            step++;
        }
        return sum;
    }

    // Solution 2: Binary search
    // Calculate sum from 1 to k
    private long calSum(int k){
        return (long)(1+k)*k/2;
    }
    public int arrangeCoins2(int n) {
        int left = 0;
        int right = n;
        while(left <= right){
            int mid = (left + right) / 2;
            if(calSum(mid) <= n){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return left - 1;
    }
}
