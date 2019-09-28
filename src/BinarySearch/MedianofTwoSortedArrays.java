package BinarySearch;

/*
    There are two sorted arrays nums1 and nums2 of size m and n respectively.

    Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

    You may assume nums1 and nums2 cannot be both empty.

    Example 1:

    nums1 = [1, 3]
    nums2 = [2]

    The median is 2.0
    Example 2:

    nums1 = [1, 2]
    nums2 = [3, 4]

    The median is (2 + 3)/2 = 2.5
 */
public class MedianofTwoSortedArrays {
    /*
         Recursive using binary search
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if (m > n) {
            int[] temp = nums1; nums1 = nums2; nums2 = temp;
            int tmp = m; m = n; n = tmp;
        }
        int left = 0, right = m, halfLen = (m + n + 1) / 2;
        while(left <= right){
            int i = left + (right - left)/2;
            int j = halfLen - i;
            if(i < right && nums2[j-1] > nums1[i]){
                left = i + 1;
            } else if(i > left && nums1[i-1] > nums2[j]){
                right = i - 1;
            } else{
                int maxLeft = 0;
                if(i == 0){
                    maxLeft = nums2[j-1];
                } else if(j == 0){
                    maxLeft = nums1[i-1];
                } else{
                    maxLeft = Math.max(nums1[i-1], nums2[j-1]);
                }
                if((m+n)%2 == 1){
                    return maxLeft;
                }

                int minRight = 0;
                System.out.println("j is "+j);
                if(i == m){
                    minRight = nums2[j];
                } else if(j == n){
                    minRight = nums1[i];
                } else{
                    minRight = Math.min(nums1[i], nums2[j]);
                }
                return (maxLeft + minRight)/2.0;
            }
        }
        return 0.0;
    }

    /*
         Merge two arrays and then find the median
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] newArray = new int[len1 + len2];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < len1 && j < len2){
            if(nums1[i] <= nums2[j]){
                newArray[k++] = nums1[i];
                i++;
            } else{
                newArray[k++] = nums2[j];
                j++;
            }
        }
        while(i < len1){
            newArray[k++] = nums1[i];
            i++;
        }
        while(j < len2){
            newArray[k++] = nums2[j];
            j++;
        }
        int mid = (len1+len2)/2;
        if((len1 + len2)%2 == 0){
            return (newArray[mid]+newArray[mid-1])/2.0;
        } else{
            return newArray[mid];
        }

    }
}
