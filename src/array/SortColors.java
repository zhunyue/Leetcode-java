package array;

/*

 */
public class SortColors {

    // Solution 1: count the total amount of 0, 1, and 2. And put them in array
    public void sortColors(int[] nums) {
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        for(int n : nums){
            if(n == 0) zeros++;
            else if (n == 1) ones++;
            else twos++;
        }
        for(int i = 0; i < nums.length; i++){
            if(i < zeros) nums[i] = 0;
            else if(i >= zeros && i < zeros + ones) nums[i] = 1;
            else nums[i] = 2;
        }
    }

    // Solution 2: keep two pointer, place 0 in the front and 2 in the end
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public void sortColors2(int[] nums) {
        int left = 0;  // The left pointer which is the left most uncertain position
        int right = nums.length - 1; // The right pointer which is the right most uncertain position
        int cur = 0;
        while(cur <= right){
            if(nums[cur] == 0){
                swap(nums, cur, left);
                left++;
                cur++;
            } else if(nums[cur] == 2){
                swap(nums, cur, right);
                right--;
            } else {
                cur++;
            }
        }
    }
}
