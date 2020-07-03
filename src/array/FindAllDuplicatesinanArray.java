package array;

import java.util.ArrayList;
import java.util.List;

/*
442. Find All Duplicates in an Array

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
 */
public class FindAllDuplicatesinanArray {
    /* Since [1,n] matches the position [0, n-1] in the array
     Every time we encounter a number i, we replace the numeber at position i to its negative
     If we encounter a number which is negative, it means we have visited this position before, and the location is a duplicate one, so we can add it to the list
      e.g [1,3,2,4,3], when we encounter 3 for the first time, we change num[2] which is 2 to -2 and get [1,3,-2,4,3]
      when we encounter the second 3, we also check num[2] and find a duplicate
      */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int n : nums){
            int positive_n = Math.abs(n);
            if(nums[positive_n - 1] < 0) {
                res.add(positive_n);
            } else {
                nums[positive_n - 1] *= -1;
            }
        }
        return res;
    }
}
