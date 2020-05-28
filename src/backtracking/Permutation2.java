package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Given a collection of numbers that might contain duplicates, return all possible unique permutations.

    Example:

        Input: [1,1,2]
        Output:
            [
                [1,1,2],
                [1,2,1],
                [2,1,1]
            ]
 */

public class Permutation2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        int[] times = new int[nums.length];
        helper(res, cur, nums, times);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> cur, int[] nums, int[] times){
        if(cur.size() == nums.length){
            res.add(new ArrayList<>(cur));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(times[i] == 0){
                if(i > 0 && nums[i] == nums[i-1] && times[i-1] == 0)
                    continue;
                times[i] = 1;
                cur.add(nums[i]);
                helper(res, cur, nums, times);
                times[i] = 0;
                cur.remove(cur.size()-1);
            }
        }
    }
}
