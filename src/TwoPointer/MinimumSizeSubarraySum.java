package TwoPointer;

public class MinimumSizeSubarraySum {
    /*
        Brute Force O(n^2)
     */
    public int minSubArrayLen(int s, int[] nums) {
        int[] sum = new int[nums.length];
        int len = nums.length + 1;
        if(len == 1){
            return 0;
        }
        sum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            sum[i] = sum[i-1] + nums[i];
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                if(sum[j] - sum[i] + nums[i] >= s && j - i + 1 < len){
                    len = j-i+1;
                    break;
                }
            }
        }
        return len > nums.length? 0 : len;
    }

    /*
        Using Binary search - O(n log n)
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int[] sum = new int[nums.length];
        int len = nums.length + 1;
        if(len == 1){
            return 0;
        }
        sum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            sum[i] = sum[i-1] + nums[i];
        }
        for(int i = 0; i < nums.length; i++){
            int pos = find(i, s, sum, nums);
            if(pos < nums.length){
                len = Math.min(pos - i + 1, len);
            }
        }
        return len > nums.length ? 0 : len ;
    }

    //Find the first one in array that sum >= s
    public int find(int n, int target, int[] sum, int[] nums){
        int left = n;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(sum[mid] - sum[n] + nums[n] >= target){
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return right + 1;
    }


    /*
        Using two pointer, which act as sliding window
        We expand the window until sum >= s and then try to minimize such window by remove the first one
        in current window
     */
    public int minSubArrayLen3(int s, int[] nums) {
        int left = 0, right = 0, sum = 0, min = Integer.MAX_VALUE;
        for(; right < nums.length; right++){
            sum += nums[right];
            while(sum - nums[left] >= s){
                sum -= nums[left];
                left++;
            }
            if(sum >= s){
                min = Math.min(min, right - left + 1);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
