package array;

/*
    Given an unsorted integer array, find the smallest missing positive integer.

    Example 1:

    Input: [1,2,0]
    Output: 3
    Example 2:

    Input: [3,4,-1,1]
    Output: 2
    Example 3:

    Input: [7,8,9,11,12]
    Output: 1
 */
public class FirstMissingPositive {

    /*
        Solution: place each number to the correct position, we ignore negative, zero and any values that are larger than the length
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length;){
            if(nums[i] > 0 && nums[i] != i+1 && nums[i] <= nums.length){
                if(nums[nums[i] -1] != nums[i]){
                    int tmp = nums[nums[i] -1];
                    nums[nums[i] -1] = nums[i];
                    nums[i] = tmp;
                } else{
                    i++;
                }
            } else{
                i++;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i+1){
                return i+1;
            }
        }
        return nums.length+1;
    }

}
