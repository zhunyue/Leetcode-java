package BitManipulation;

/*
Reverse bits of a given 32 bits unsigned integer.



Example 1:

Input: 00000010100101000001111010011100
Output: 00111001011110000010100101000000
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
                so return 964176192 which its binary representation is 00111001011110000010100101000000.
Example 2:

Input: 11111111111111111111111111111101
Output: 10111111111111111111111111111111
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
             so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 */
public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res=0;
        for(int i=0;i<32;i++){
            res = (res <<= 1) | (n & 1); // move left, therefore the last bit in n will be the first at last
            n >>= 1;//To get the last bit every time
        }
        return res;
    }
}
