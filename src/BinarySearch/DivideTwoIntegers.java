package BinarySearch;

/*
    Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

    Return the quotient after dividing dividend by divisor.

    The integer division should truncate toward zero.

    Example 1:

    Input: dividend = 10, divisor = 3
    Output: 3
    Example 2:

    Input: dividend = 7, divisor = -3
    Output: -2
 */
public class DivideTwoIntegers {
    /*
         1. Handle boundary case (Integer.MIN_VALUE, Integer.MAX_VALUE)
         2. Improve efficiency - using binary search(double step size when possible)
     */
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }
        int sign = 1;
        if((dividend < 0 && divisor > 0) ||(dividend > 0 && divisor < 0)){
            sign = -1;
        }
        long res = helper(Math.abs((long) dividend), Math.abs((long) divisor));
        if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int) res * sign;
    }

    public long helper(long dividend, long divisor){
        if(dividend < divisor){
            return 0;
        }
        long sum = divisor;
        int step = 1;
        while ((sum + sum) <= dividend){
            sum += sum;
            step += step;
        }
        return step + helper(dividend - sum, divisor);
    }
}
