package sort;

import java.util.Arrays;

/*
    Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

    Return 0 if the array contains less than 2 elements.

    Example 1:

    Input: [3,6,9,1]
    Output: 3
    Explanation: The sorted form of the array is [1,3,6,9], either
                 (3,6) or (6,9) has the maximum difference 3.
    Example 2:

    Input: [10]
    Output: 0
    Explanation: The array contains less than 2 elements, therefore return 0.
 */

public class MaximumGap {
    /*
        1. Array sort: O(n logn)
     */
    public int maximumGap(int[] nums) {
        int gap = 0;
        if(nums.length < 2){
            return 0;
        }
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i+1] - nums[i] > gap){
                gap = nums[i+1] - nums[i];
            }
        }
        return gap;
    }
    /*
        2. Using Bucket
            - Find the maximum and minimum value and the uniform gap = (max - min) / (n - 1)
            - Create buckets w.r.t gap size, each bucket i stores value min * i <= x < min*(i+1)
            - Assign numbers to buckets and find the min and max value within each bucket
            - The maximum gap would happen between buckets(Just be careful some bucket may be empty)
     */
    public int maximumGap1(int[] nums) {
        int n = nums.length;
        if(n < 2) return 0;
        int min = nums[0];
        int max = nums[0];
        for(int num : nums){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int gap = (max - min) / (n - 1);
        if(gap == 0) gap++;
        int len = (max - min) / gap + 1;
        int[] tmin = new int[len];
        int[] tmax = new int[len];
        for(int i = 0; i < n; i++){
            int index = (nums[i] - min) / gap;
            tmax[index] = Math.max(tmax[index], nums[i]);
            if(tmin[index] == 0 || nums[i] < tmin[index]) tmin[index] = nums[i];
        }
        for(int x:tmax){
            System.out.println(x);
        }
        for(int x:tmin){
            System.out.println(x);
        }
        int maxRes = 0;
        for(int i = 0;i < len;i++){
            if(maxRes < tmin[i]-min) maxRes = tmin[i]-min;
            // If some bucket is empty, it would be zero and we the min value would be the last bucket's min value
            if(tmax[i] != 0) min = tmax[i];
        }
        return maxRes;
    }

    /*
        3. Use Radix Sort
     */
    public int maximumGap2(int[] nums) {
        int max = 0;
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        radixsort(nums, n);
        for(int i = 0; i < n - 1; i++){
            max = Math.max(nums[i+1] - nums[i], max);
        }
        return max;
    }

    public void radixsort(int[] nums, int n){
        int max = nums[0];
        for(int num : nums){
            max = Math.max(num, max);
        }
        for(int exp = 1; max / exp > 0; exp *= 10){
            countSort(nums, n, exp);
        }
    }

    public void countSort(int[] arr, int n, int exp){
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[ (arr[i]/exp)%10 ]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--)
        {
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }
}
