package TwoPointer;

import java.util.HashSet;
import java.util.Set;

/*
Write an algorithm to determine if a number n is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Return True if n is a happy number, and False if not.

Example:

Input: 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */
public class HappyNumber {
    //General idea: doing calculation to see if we can reach a result of 1 or get into a loop
    public int squareSum(int n){
        int sum = 0;
        while(n != 0){
            int digit = n % 10;
            sum += (digit*digit);
            n /= 10;
        }
        return sum;
    }

    //Solution 1: use slow and fast pointer, if there is a number repeat, slow and fast pointer will meet

    public boolean isHappy(int n) {
        int slow = n;
        int fast = squareSum(slow);
        while(fast != 1 && slow != fast){
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }
        return fast == 1;
    }

    //solution 2: Keep a set to check if there is a loop happen (a number repeat)
    public boolean isHappy2(int n) {
        Set<Integer> set = new HashSet();
        set.add(n);
        while(true) {
            n = squareSum(n);
            if(n == 1)
                return true;
            else if(set.contains(n))
                return false;
            else{
                set.add(n);
            }
        }
    }
}
