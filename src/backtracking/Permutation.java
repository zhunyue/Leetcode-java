package Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    Given a collection of distinct integers, return all possible permutations.

    Example:

        Input: [1,2,3]
        Output:
            [
                [1,2,3],
                [1,3,2],
                [2,1,3],
                [2,3,1],
                [3,1,2],
                [3,2,1]
            ]
 */

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        int[] time = new int[nums.length];
        helper(res, cur, nums, time);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> cur, int[] nums, int[] times){
        if(cur.size() == nums.length){
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(times[i] == 0){
                times[i] = 1;
                cur.add(nums[i]);
                helper(res, cur, nums, times);
                cur.remove(cur.size()-1);
                times[i] = 0;
            }
        }
    }
}
