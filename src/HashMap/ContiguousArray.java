package HashMap;

import java.util.Arrays;
import java.util.HashMap;

/*

525. Contiguous Array

Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.

 */
public class ContiguousArray {
    // Solution 1: Using one extra array with length = 2*len+1
    // General idea: keep a sum, every time we encounter 1, we increase sum by 1; otherwise, we decrease sum by 1
    // If we encounter 0, this means the number of 1 and 0 are the same from index 0 to current index
    // If we encounter the same sum more than once, the elements from last index(with the same sum) to here have the same 1 and 0
    // ex.  [ 0, 1, 0, 0, 0, 1, 1, 1], the sum will be -1, 0, -1, -2, -3, -2, -1, 0. We find -2 twice which means the number of 1 and 0 are the same in (3, 5]
    // Especially, we have to preset arr[nums.length] = -1 to simulate we have 0 in advance
    public int findMaxLength(int[] nums) {
        int len = 0, sum = nums.length;
        int[] arr = new int[nums.length * 2 + 1];
        Arrays.fill(arr, -2);
        arr[nums.length] = -1; // to assume sum == 0 appears at index = -1
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) sum--;
            else sum++;
            if(arr[sum] != -2){
                len = Math.max(len, i - arr[sum]);
            } else {
                arr[sum] = i;
            }
        }
        return len;
    }

    // Solution 2: Using HashMap, idea is similar
    public int findMaxLength2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int len = 0;
        map.put(0,-1);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                sum --;
            } else{
                sum ++;
            }
            if(!map.containsKey(sum)) {
                map.put(sum, i);
            } else {
                len = Math.max(len, i - map.get(sum));
            }
        }
        return len;
    }
}
