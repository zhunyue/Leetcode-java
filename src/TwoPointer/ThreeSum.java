package TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

    Note:

        The solution set must not contain duplicate triplets.

        Example:

            Given array nums = [-1, 0, 1, 2, -1, -4],

            A solution set is:
                [
                    [-1, 0, 1],
                    [-1, -1, 2]
                ]
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2; i++){
            if(i>0&&nums[i] == nums[i-1]) continue;
            int imple = 0 - nums[i];
            int left = i+1;
            int right = nums.length-1;
            while(left < right){
                if(nums[left] + nums[right] == imple){
                    List<Integer> tmpSum = new ArrayList<>();
                    tmpSum.add(nums[i]);
                    tmpSum.add(nums[left]);
                    tmpSum.add(nums[right]);
                    res.add(tmpSum);
                    left++;
                    right--;
                    while(left<right&&nums[left] == nums[left-1]) left++;
                    while(left<right&&nums[right] ==nums[right+1]) right--;
                } else if(nums[left] + nums[right] < imple){
                    left++;
                } else{
                    right--;
                }
            }
        }
        return res;
    }
}
