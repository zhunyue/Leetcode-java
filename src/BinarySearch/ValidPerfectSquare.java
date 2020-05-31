package BinarySearch;

/*

367. Valid Perfect Square

Given a positive integer num, write a function which returns True if num is a perfect square else False.

Follow up: Do not use any built-in library function such as sqrt.



Example 1:

Input: num = 16
Output: true
Example 2:

Input: num = 14
Output: false


Constraints:

1 <= num <= 2^31 - 1

 */

public class ValidPerfectSquare {

    // Solution: The square root of a number located in the range [0, num/2]
    public boolean isPerfectSquare(int num) {
        if(num == 1) return true;
        int left = 1;
        int right = num/2;
        while(left <= right){
            int mid = (left + right) / 2;
            long prod = (long) mid * mid;
            if(prod == num)
                return true;
            else if (prod < num){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
