package Greedy;

/*
    Given an array of non-negative integers, you are initially positioned at the first index of the array.

    Each element in the array represents your maximum jump length at that position.

    Your goal is to reach the last index in the minimum number of jumps.

    Example:

    Input: [2,3,1,1,4]
    Output: 2
    Explanation: The minimum number of jumps to reach the last index is 2.
        Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        int len = nums.length;
        int res = 0, i = 0, cur = 0;
        while(cur < len -1){
            res++;
            int pre = cur;
            for(; i <= pre; i++){
                cur = Math.max(cur, i + nums[i]);
            }
        }
        return res;
    }
}
