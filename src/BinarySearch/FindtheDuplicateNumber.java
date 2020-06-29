package BinarySearch;

/*
287. Find the Duplicate Number

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2

Example 2:
Input: [3,1,3,4,2]
Output: 3

Note:
    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n2).
    There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindtheDuplicateNumber {
    // Find the number from [1, nums.length] using binary search
    // if every number appear once, the amount of numbers <= target should be target
    // if count <= mid then we search in [mid + 1, right]
    // otherwise, it count > mid, then there is at least one duplicate on left side, search in [left, mid - 1]
    public int findDuplicate(int[] nums) {
        int left = 1;
        int right = nums.length;
        while(left <= right){
            int mid = (left + right) / 2;
            int count = 0;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] <= mid) count++;
            }
            if(count <= mid){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return left;
    }

    /*
        Basic idea: form a linked list with the given array and find the circle
        e.g. [2,6,4,1,3,1,5] with index[0,1,2,3,4,5,6] represent a map: 0 lead to a next value 2, 1 lead to a next value 6, 2 lead to a next value 4 and so on.
             Therefore, starting from 0 we will 0 -> 2 -> 4 -> 3 -> 1 -> 6 -> 5
                                                                    |         |
                                                                    |---------|
        Step 1: Find the intersect point in the circle using fast and slow pointer
                F is the distance to the circle entrance, a is the dis(entrance, intersect), and C is the circle length.
                        F            a
                |---------------|---------|
                                |         |
                                |         |   ->  The circle has length C
                                |         |
                                |---------|
                Suppose the slow pointer go thourgh F+a and the fast go through F+a+nC
                We have 2 * (F + a) =  F + a + nC
        Step 2: Find the entrance to the circle.
                Fast pointer start from 0, slow pointer start from the intersection point
                After F step, fast locate at F and slow locate at F + a + F = 2F + a = F + nC
                Therefore they are at the entrance after F steps.
    */

    public int findDuplicate2(int[] nums) {
        int fast = nums[nums[0]], slow = nums[0];
        while(slow != fast){
            fast = nums[nums[fast]];
            slow = nums[slow];
        }
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
