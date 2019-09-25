package DP;

public class MaximumProductSubarray {
    /*
        Using dp method and constant space
        Since the product could be negative and negative times negative could be a larger positive number,
         we have to record both min and max product. And max[i] would be the maximum product that ends in i and
         min[i] is the minimum product that ends in i. We further improve it by only using constant space - two
         variables min and max
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int curmin = nums[0];
        int curmax = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            int tmp = curmax;
            curmax = Math.max(Math.max(curmax*nums[i], nums[i]), curmin*nums[i]);
            curmin = Math.min(Math.min(curmin*nums[i], nums[i]), tmp*nums[i]);
            max = Math.max(curmax, max);
        }
        return max;
    }
}
