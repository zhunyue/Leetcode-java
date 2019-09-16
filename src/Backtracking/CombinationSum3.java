package Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
    Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

    Note:

        All numbers will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:

            Input: k = 3, n = 7
            Output: [[1,2,4]]
        Example 2:

            Input: k = 3, n = 9
            Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(n, 1, res, cur, k); //start from 1 to 9
        return res;
    }
    private void helper(int target, int start, List<List<Integer>> res, List<Integer> cur, int k){
        /*
             If current size equals k, it should ends. And if the sum equals to target, it will be added to res List, otherwise, we just ignore it.
         */
        if(cur.size() == k){
            if(target == 0){
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        for(int i = start; i <= 9; i++){
            if(i > target){
                return;
            }
            cur.add(i);
            helper(target - i, i+1, res, cur, k);
            cur.remove(cur.size()-1);
        }

    }

}
