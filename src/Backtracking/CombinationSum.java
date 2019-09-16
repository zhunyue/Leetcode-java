package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

    The same repeated number may be chosen from candidates unlimited number of times.

    Note:

        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:

            Input: candidates = [2,3,6,7], target = 7,
            A solution set is:
            [
                [7],
                [2,2,3]
            ]
 */

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(candidates, target, 0, res, cur);
        return res;
     }

     private static void helper(int[] candidates, int target, int start, List<List<Integer>> res, List<Integer> cur){
         if(target == 0){
             res.add(new ArrayList<>(cur));
             return;
         }
         for(int i = start; i < candidates.length; i++){
             if(candidates[i] > target){
                 return;
             }
             cur.add(candidates[i]);
             helper(candidates, target - candidates[i], i, res, cur);
             cur.remove(cur.size() - 1);
         }
     }

    public static void main(String[] args){
        int[] candidates = {2, 3, 5};
        int target = 8;
        List<List<Integer>> res = combinationSum(candidates, target);
        for(int i = 0; i < res.size(); i++){
            for(int j = 0; j < res.get(i).size(); j++){
                System.out.print(res.get(i).get(j)+" ");
            }
            System.out.println(" ");
        }
    }
}
