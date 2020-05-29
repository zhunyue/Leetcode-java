package array;

import java.util.HashMap;

/*

169. Majority Element

    Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

    You may assume that the array is non-empty and the majority element always exist in the array.

    Example 1:

    Input: [3,2,3]
    Output: 3
    Example 2:

    Input: [2,2,1,1,1,2,2]
    Output: 2
 */
public class MajorityElementSolution {

    // Solution 1: Using hashmap
    public int majorityElement(int[] nums) {
        int len = nums.length/2;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n : nums){
            int times = map.getOrDefault(n, 0) + 1;
            map.put(n, times);
            if(times > len)
                return n;
        }
        return -1;
    }

    // Solution 2: Divide and Conquer
    public int majorityElement2(int[] nums) {
        if(nums.length == 0) return -1;
        return helper(nums, 0, nums.length - 1);
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    // Return the majority element in range [start, end]
    private int helper(int[] nums, int start, int end){
        if(start == end) return nums[start];
        int mid = (end + start) / 2;
        int left = helper(nums, start, mid);
        int right = helper(nums, mid + 1, end);
        if(left == right) return left;
        int left_count = countInRange(nums, left, start, end);
        int right_count = countInRange(nums, right, start, end);
        return left_count > right_count ? left : right;
    }

    // Solution 3: Boyer-Moore Voting Algorithm
    // Intuition:    If we had some way of counting instances of the majority element as +1
    // and instances of any other element as -1, summing them would make it obvious that the majority element is indeed the majority element.
    public int majorityElement3(int[] nums) {
        if(nums.length == 0) return -1;
        int count = 0;
        Integer candidate = null;
        for(int n : nums){
            if(count == 0)
                candidate = n;
            if(n == candidate) count++;
            else count--;
        }
        return candidate;
    }
}

