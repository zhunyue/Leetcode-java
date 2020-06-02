package BinarySearch;

/*

540. Single Element in a Sorted Array


    You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.

    Follow up: Your solution should run in O(log n) time and O(1) space.

    Example 1:
    Input: nums = [1,1,2,3,3,4,4,8,8]
    Output: 2

    Example 2:
    Input: nums = [3,3,7,7,10,11,11]
    Output: 10

    Constraints:
        1 <= nums.length <= 10^5
        0 <= nums[i] <= 10^5
 */
public class SingleElementinaSortedArray {

    // Solution 1 : Direct search
    // Time complexity: O(n)
    public int singleNonDuplicate(int[] nums) {
        if(nums.length == 1) return nums[0];
        for(int i = 0; i < nums.length; i++){
            if(i == 0){
                if(nums[i] != nums[i+1])
                    return nums[i];
            } else if(i == nums.length - 1){
                if(nums[i] != nums[i - 1])
                    return nums[i];
            } else {
                if(nums[i + 1] != nums[i] && nums[i] != nums[i - 1])
                    return nums[i];
            }

        }
        return -1;
    }
    // Slight change: increase the step from 1 to 2, since each other element will duplicate exactly twice
    public int singleNonDuplicate2(int[] nums) {
        if(nums.length == 1) return nums[0];
        for(int i = 0; i < nums.length; i+=2){
            if(i == 0){
                if(nums[i] != nums[i+1])
                    return nums[i];
            } else if(i == nums.length - 1){
                if(nums[i] != nums[i - 1])
                    return nums[i];
            } else {
                if(nums[i + 1] != nums[i] && nums[i] != nums[i - 1])
                    return nums[i];
            }

        }
        return -1;
    }

    // Solution 2: Binary search
    // id:  0 1 2 3 4 5 6 7 8 9 10
    // num: 1 1 2 2 3 3 4 5 5 6 6
    //  4 is the single element, and we can see, for each element before 4, it first appear at even position and end at next odd posistion
    // For each element appear after 4, it first appear at odd position and end at next even position
    // Therefore, for each odd id mid with num[mid], if num[mid] == num[mid - 1], we should search in the right, otherwise left
    // For each even element id with num[mid], if num[mid] == num[mid - 1], we should search in the left and we can skip the duplicate one
    public int singleNonDuplicate3(int[] nums) {
        int left = 0;
        int right = nums.length;
        while(left < right - 1){
            int mid = (left + right) / 2;
            if(nums[mid] != nums[mid+1] && nums[mid] != nums[mid-1]){
                return nums[mid];
            }
            if(mid % 2 == 0){
                if(nums[mid] == nums[mid - 1])
                    right = mid - 2; // skip the duplicte one, for example, we detect 1 1 2 2 3 3 4 5 5 6 6 at the second 5, we can set right to 4
                else
                    left = mid;
            } else {
                if(nums[mid] == nums[mid - 1])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return nums[left];
    }
}
