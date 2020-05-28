package Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    Given a set of distinct integers, nums, return all possible subsets (the power set).

    Note: The solution set must not contain duplicate subsets.

    Example:

        Input: nums = [1,2,3]
        Output:
        [
            [3],
            [1],
            [2],
            [1,2,3],
            [1,3],
            [2,3],
            [1,2],
            []
        ]
 */

public class SubSet {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(nums, res, cur, 0);
        return res;
    }
    private void helper(int[] nums, List<List<Integer>> res, List<Integer> cur, int start){
        res.add(new ArrayList<>(cur));
        for(int i = start; i < nums.length; i++){
            cur.add(nums[i]);
            helper(nums, res, cur, i+1);
            cur.remove(cur.size() - 1);
        }
    }
}
