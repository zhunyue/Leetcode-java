package BitManipulation;

/*

476. Number Complement

    Given a positive integer num, output its complement number. The complement strategy is to flip the bits of its binary representation.



    Example 1:

    Input: num = 5
    Output: 2
    Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
    Example 2:

    Input: num = 1
    Output: 0
    Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.


    Constraints:

    The given integer num is guaranteed to fit within the range of a 32-bit signed integer.
    num >= 1
    You could assume no leading zero bit in the integer’s binary representation.
    This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 */
public class NumberComplement {

    // General idea: Do XOR with all 1s (for example num = 525, then do xor 525^111)
    public int findComplement(int num) {
        int n = 0;
        while(n < num){
            n = (n << 1) | 1;
        }
        return n^num;
    }

    public int findComplement2(int num) {
        int n = 0;
        int res = num;
        while(res != 0){
            n++;
            res >>= 1;
        }
        int mask = (1 << n) - 1;
        return num^mask;
    }
}
