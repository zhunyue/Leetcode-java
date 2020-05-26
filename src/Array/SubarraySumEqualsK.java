package Array;

import java.util.HashMap;

/*

560. Subarray Sum Equals K

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2


Constraints:

The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

 */
public class SubarraySumEqualsK {
    // General idea: If nums[j] - nums[i] == k, then the sum between i, j is k
    // Solution 1: Brute force with extra space
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        if(nums.length == 0) return 0;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            sum[i] = sum[i-1] + nums[i];
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                int s = (i == j)? sum[j] : sum[j] - sum[i];
                if(s == k) count++;
            }
        }
        return count;
    }

    //Solution 2: Using HashMap to store sum
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
