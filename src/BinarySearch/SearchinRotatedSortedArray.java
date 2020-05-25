package BinarySearch;

/*

33. Search in Rotated Sorted Array

    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

    (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

    You are given a target value to search. If found in the array return its index, otherwise return -1.

    You may assume no duplicate exists in the array.

    Your algorithm's runtime complexity must be in the order of O(log n).

    Example 1:

    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4
    Example 2:

    Input: nums = [4,5,6,7,0,1,2], target = 3
    Output: -1

 */
public class SearchinRotatedSortedArray {

    // Key point: if num[left] < num[right], then it is in increasing order in this range
    // we try to decide in which part we proceed if no match is found

    // Solution 1: check increasing range first
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target)
                return mid;
            else if(nums[left] <= nums[mid]){ // the left half is in increasing order
                if(target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else{
                    left = mid + 1;
                }
            } else{ // The right half is in increasing order
                if(target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }

    // Solution 2: Compare target with middle element first
    public int search2(int[] nums, int target) {
        if(nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] > target){ // try to find smaller value
                if(nums[right] < nums[mid] && nums[left] > target) // if the smallest to the left is larger than target and the end element is <= the middle element
                    left = mid + 1;
                else right = mid - 1;
            } else{    // try to find larger value
                if(nums[left] > nums[mid] && nums[right] < target) // if the end element is larger than target and the end element is <= the middle element
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return -1;
    }

}
