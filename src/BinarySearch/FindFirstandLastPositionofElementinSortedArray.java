package BinarySearch;

/*

    Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

    Your algorithm's runtime complexity must be in the order of O(log n).

    If the target is not found in the array, return [-1, -1].

    Example 1:

    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]
    Example 2:

    Input: nums = [5,7,7,8,8,10], target = 6
    Output: [-1,-1]

 */
public class FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[2];
        // find first >=
        int mid = 0;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        int firstLE = right + 1;
        if(firstLE > nums.length - 1 || nums[firstLE] > target)
            return new int[] {-1, -1};
        else{
            res[0] = firstLE;
        }

        //find first >
        left = firstLE;
        right = nums.length - 1;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(nums[mid] <= target){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        res[1] = right;
        return res;

    }
}
