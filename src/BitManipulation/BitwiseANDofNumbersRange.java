package BitManipulation;

/*

201. Bitwise AND of Numbers Range

    Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

    Example 1:

    Input: [5,7]
    Output: 4
    Example 2:

    Input: [0,1]
    Output: 0

 */
public class BitwiseANDofNumbersRange {

    // Solution: find bits in common for m and n
    // Since m and n are the lower limits and upper limits of the range, after we find the common part, the remaining part will AND to 0
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while(m != n){
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m <<= i;
    }

}
