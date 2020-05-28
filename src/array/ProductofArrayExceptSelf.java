package array;

/*

238. Product of Array Except Self

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

 */
public class ProductofArrayExceptSelf {

    //Solution 1: Using two more array to store product
    public int[] productExceptSelf(int[] nums) {
        int[] forward = new int[nums.length]; // product from nums[0] to num[i]
        int[] backward = new int[nums.length]; // product from num[end] to num[i]
        int[] output = new int[nums.length];
        int prod = 1;
        for(int i = 0; i < nums.length; i++){
            prod *= nums[i];
            forward[i] = prod;
        }
        prod = 1;
        for(int i = nums.length - 1; i >= 0; i--){
            prod *= nums[i];
            backward[i] = prod;
        }
        output[0] = backward[1];
        output[nums.length - 1] = forward[nums.length - 2];
        for(int i = 1; i < nums.length - 1; i++){
            output[i] = forward[i-1] * backward[i+1];
        }
        return output;
    }

    // Solution 2: Using O(1) space
    // res to store the product from num[0] to num[i]; and we traverse from the end to the beginning and use a variable to store the product up to i-th element
    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = nums[0];
        int prod = 1;
        for(int i = 1; i < len; i++){
            res[i] = res[i-1] * nums[i];
        }
        res[len - 1] = res[len - 2];
        for(int i = len - 1; i > 1; i--){
            prod *= nums[i];
            res[i - 1] = prod * res[i - 2];
        }
        res[0] = prod * nums[1];
        return res;
    }

}
