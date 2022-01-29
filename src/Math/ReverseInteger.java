package Math;

/*
7. Reverse Integer

Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).



Example 1:

Input: x = 123
Output: 321

Example 2:

Input: x = -123
Output: -321

Example 3:

Input: x = 120
Output: 21


Constraints:

-231 <= x <= 231 - 1
 */
public class ReverseInteger {
    /*
       Idea: get the last digit and apply to the res variable to construct answer
       *** Attention to check for boundary
     */
    public int reverse(int x) {
        int res = 0;
        boolean isNegative = false;
        if(x < 0) {
            isNegative = true;
            x = x * (-1);
        }

        while (x > 0) {
            int num = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE / 10 && num > 7)) return 0;
            res = res * 10 + num;
        }
        if (isNegative) {
            return res * (-1);
        }
        return res;

    }
}
