package Greedy;

/*
    Given an array of non-negative integers, you are initially positioned at the first index of the array.

    Each element in the array represents your maximum jump length at that position.

    Determine if you are able to reach the last index.

    Example 1:

    Input: [2,3,1,1,4]
    Output: true
    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
    Example 2:

    Input: [3,2,1,0,4]
    Output: false
    Explanation: You will always arrive at index 3 no matter what. Its maximum
                 jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {
    /*
         Greedy method: start from the right most point, we go through every point and see if it could reach the end
         In this process, if it could reach the current left most point that could reach the end, it could reach the end
         Therefore, in the end, we only need to judege lastPost == 0
     */
    public boolean canJump(int[] nums) {
        int lastPos = nums.length;
        for(int i = nums.length; i >= 0; i--){
            if(nums[i] + i >= lastPos){
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public boolean canJump2(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length && i <= max; i++) {
            max = Math.max(max, i + nums[i]);
        }
        return max >= nums.length - 1;
    }
    /*
        Bottom-up DP
     */
    public boolean canJump3(int[] nums) {
        int[] memo = new int[nums.length];
        memo[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            int farPos = Math.min(i + nums[i], nums.length - 1);
            for(int j = i + 1; j <= farPos; j++){
                if(memo[j] == 1){
                    memo[i] = 1;
                    break;
                }
            }
        }
        return memo[0] == 1;
    }

    /*
        Top-Down DP
     */
    public boolean canJump4(int[] nums) {
        int[] dp = new int[nums.length]; // 0 for unknown, 1 for unreacheable, 2 for reacheable
        dp[nums.length - 1] = 2;
        return helper2(nums, 0, dp);
    }

    public boolean helper2(int[] nums, int start, int[] dp){
        if(dp[start] == 2) return true;
        else if(dp[start] == 1) return false;
        int range = Math.min(nums[start] + start, nums.length - 1);
        for(int i = start + 1; i <= range; i++){
            if(helper2(nums, i, dp)){
                dp[start] = 2;
                return true;
            }
        }
        dp[start] = 1;
        return false;

    }
}
