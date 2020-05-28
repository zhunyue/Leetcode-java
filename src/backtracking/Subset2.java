package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
 */

public class Subset2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, res, cur, 0);
        return res;
    }
    public void helper(int[] nums, List<List<Integer>> res, List<Integer> cur, int start){
        res.add(new ArrayList<>(cur));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1])
                continue;
            cur.add(nums[i]);
            helper(nums, res, cur, i+1);
            cur.remove(cur.size() - 1);
        }
    }
}
