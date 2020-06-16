package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
368. Largest Divisible Subset

    Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:

    Si % Sj = 0 or Sj % Si = 0.

    If there are multiple solutions, return any subset is fine.

    Example 1:
    Input: [1,2,3]
    Output: [1,2] (of course, [1,3] will also be ok)

    Example 2:
    Input: [1,2,4,8]
    Output: [1,2,4,8]
 */
public class LargestDivisibleSubset {
    //Solution 1: Find one with the maximum number of divisible subset and restore the subset
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int[] largest = new int[nums.length]; // The amount of numbers in largest divisible subset including nums[i]
        int[] next = new int[nums.length]; // next[i]: The next id for i which lead to largest divisible subset including nums[i]
        int max = 0; // The amount of numbers in largest divisible subset
        int start = -1; // The start index in the largest divisible subset
        Arrays.sort(nums);
        Arrays.fill(next, -1);
        for(int i = 0; i < nums.length; i++){
            for(int j = i - 1; j >= 0; j--){
                if(nums[i] % nums[j] == 0 && largest[j] > largest[i]){
                    largest[i] = largest[j];
                    next[i] = j;
                }
            }
            largest[i]++;
            if(largest[i] > max) {
                max = largest[i];
                start = i;
            }
        }

        while(max > 0){
            res.add(nums[start]);
            start = next[start];
            max--;
        }
        return res;
    }

    //Solution 2: similar idea, but prioritize the implementation of loop
    public List<Integer> largestDivisibleSubset2(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);
        int[] longest = new int[N];
        int[] next = new int[N];
        for(int i = 0; i < N; i++) {
            longest[i] = 1;
            next[i] = i;
        }
        for(int i = N - 1; i >= 0; i--) {
            int maxId = -1;
            int maxSize = 0;
            int limit = 2 * nums[i];
            for (int j = N-1; j > i && nums[j] >= limit; j--) {
                if (nums[j] % nums[i] == 0) {
                    if (longest[j] > maxSize) {
                        maxSize = longest[j];
                        maxId = j;
                    }
                }
            }
            if(maxId != -1){
                longest[i] = 1 + maxSize;
                next[i] = maxId;
            }


        }

        int maxLen = -1;
        int start = -1;
        for(int i = 0; i < N; i++) {
            if (longest[i] > maxLen) {
                start = i;
                maxLen = longest[i];
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        while(maxLen > 0){
            res.add(nums[start]);
            start = next[start];
            maxLen--;
        }
        return res;
    }
}
