package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

    Each number in candidates may only be used once in the combination.

    Note:

        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        Example 1:

            Input: candidates = [10,1,2,7,6,1,5], target = 8,
            A solution set is:
                [
                    [1, 7],
                    [1, 2, 5],
                    [2, 6],
                    [1, 1, 6]
                ]
       Example 2:

            Input: candidates = [2,5,2,1,2], target = 5,
            A solution set is:
                [
                    [1,2,2],
                    [5]
                ]
 */
public class CombinationSum2 {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); //sort the array so that if the current number is greater than target, we could stop calculating
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
            if(i > start && candidates[i] == candidates [i-1]){
                continue;
            }
            cur.add(candidates[i]);
            helper(candidates, target - candidates[i], i + 1, res, cur);  //Here, we start from i+1 to avoid using candidates[i] for more than onece
            cur.remove(cur.size() - 1);
        }
    }

    public static void main(String[] args){
        int[] candidates = {10,1,2,7,6,1,5};
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
