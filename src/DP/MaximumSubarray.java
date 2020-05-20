package DP;
/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {
    //Solution 1: Using dp to find the maximum sum ending with i-th element, O(n) time, O(n) space
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i = 1; i < length; i++){
            dp[i] = Math.max(nums[i], nums[i]+dp[i-1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    //Sulution 2: Similar to the above one, but using O(1) space
    //If previous sum if negative, then we set sum = num[i]
    public int maxSubArray2(int[] nums) {
        int length = nums.length;
        int sum = 0;
        int max = nums[0];
        for(int num : nums) {
            if(sum < 0)
                sum = num;
            else
                sum += num;
            max = Math.max(max, sum);
        }
        return max;
    }

    //Solution 3: Using Divide and Conquer
    // Find mid point, then find the left maximum sum, right maximum sum, the sum across the mid point
    // Choose the maximum among the three
    // O(nlogn) time complexity
    public int midSum(int[] nums, int start, int end, int mid){
        int sum = 0;
        int left = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        for(int i = mid; i >= start; i--){
            sum += nums[i];
            if(sum > left)
                left = sum;
        }
        sum = 0;
        for(int i = mid+1; i <= end; i++){
            sum += nums[i];
            if(sum > right)
                right = sum;
        }
        return Math.max(left + right, Math.max(left, right));

    }
    public int maxSubArray(int[] nums, int start, int end){
        if(start == end)
            return nums[start];
        int mid = (start + end) / 2;
        int left = maxSubArray(nums, start, mid);
        int right =  maxSubArray(nums, mid + 1, end);
        int middleSum = midSum(nums, start, end, mid);
        return Math.max(middleSum, Math.max(left, right));
    }
    public int maxSubArray3(int[] nums) {
        return maxSubArray(nums, 0, nums.length-1);
    }

}
