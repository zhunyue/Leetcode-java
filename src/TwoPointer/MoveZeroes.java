package TwoPointer;

/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
public class MoveZeroes {
    //Solution1: Move all nonZero element to the front and fill the remaining with 0
    //Time complexity: O(n)
    public void moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != 0)
                nums[count++] = nums[i];
        }
        while (count < nums.length){
            nums[count] = 0;
            count++;
        }
    }

    //Solution2: Similarly, using two pointers, one for iteration, one for keeping the last non zero position
    // LastNonZero is the position that marks the last element that we recognized as non-zero
    //Time complexity: O(n) . But if the input looks like [0,0,0,0,1], this one performs better than the one above
    public void moveZeroes2(int[] nums) {
        int lastNonZero = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                int tmp = nums[lastNonZero++];
                nums[lastNonZero - 1] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}
