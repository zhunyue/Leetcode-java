package BitManipulation;

/*
231. Power of Two

    Given an integer, write a function to determine if it is a power of two.

    Example 1:
    Input: 1
    Output: true
    Explanation: 20 = 1

    Example 2:
    Input: 16
    Output: true
    Explanation: 24 = 16

    Example 3:
    Input: 218
    Output: false
 */
public class PowerofTwo {
    // Solution 1: Iteratively divide by 2
    public boolean isPowerOfTwo(int n) {
        if(n == 1) return true;
        if(n <= 0) return false;
        // Each time, divide by 2, return false if we encounter an odd number
        while(n >= 2){
            if(n % 2 != 0)
                return false;
            n = n / 2;
        }
        return true;
    }

    // Solution 2: Bit manipulation
    // 2^n will be in the form 1xxxxxxx (a 1 followed by some zeroes)
    //           n - 1 will be 01111111
    // if a number is 2^n, the AND result will be one, otherwise not
    public boolean isPowerOfTwo2(int n) {
        if(n == 1) return true;
        if(n <= 0) return false;
        n = n & (n - 1);
        return n == 0;
    }
}
