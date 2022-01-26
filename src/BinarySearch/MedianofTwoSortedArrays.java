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
         Iteration using binary search
         Idea:  We find a partition to ensure that nums1[0:i-1], nums2[0:j-1] is always smaller than nums1[i:m-1] and nums2[j,n-1]
           - If total array length m+n is even, we need to meet below condition:
              left part length = right part length: i + j = m - i + n - j
              max of left part always smaller than right part min value: max(nums1[i-1], nums2[j-1]) <= min(nums1[i], nums2[j])
              median = (max(nums1[i-1], nums2[j-1]) + min(nums1[i], nums2[j])) / 2
           - If total array length is odd:
              left part length = right part length + 1: i + j = m - i + n - j + 1
              max of left part always smaller than right part min value: max(nums1[i-1], nums2[j-1]) <= min(nums1[i], nums2[j])
              median = max(nums1[i-1], nums2[j-1])

           Combining above, we need to find a partition position i in nums1 and meet below condition:
               j = (m+n+1) / 2 - i (Since if m+n is even, plus 1 will not impact final result)
               max(nums1[i-1], nums2[j-1]) <= min(nums1[i], nums2[j])

     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0;
        int right = m;
        while (left <= right) {
            int partitionX = left + (right - left) / 2;
            int partitionY = (m+n+1)/2 - partitionX;
            int maxLeftX = (partitionX == 0) ?  Integer.MIN_VALUE : nums1[partitionX - 1];
            int maxLeftY = (partitionY == 0) ?  Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];
            if(maxLeftX <= minRightY && maxLeftY <= minRightX) {
                int leftMax = Math.max(maxLeftX, maxLeftY);
                if ((m + n) % 2 == 0) {
                    int rightMin = Math.min(minRightX, minRightY);
                    return (leftMax + rightMin)/2.0;
                } else {
                    return leftMax;
                }
            } else if (maxLeftX > minRightY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }
        return 0.0;

    }

    /*
         Recursive using binary search
         Idea:
            1. problem can translate to find the kth large element in the 2 sorted array
            2. How to find k-th element in two sorted array?
                - First Compare nums1[k/2] and nums2[k/2]:
                    if nums1[k/2] < nums2[k/2]: means nums1[0]...nums1[k/2] is the smallest k/2 element. We need to find the other k/2 element in nums1[k/2+1]...nums1[m-1] and nums2
                    if nums2[k/2] < nums1[k/2]:  means nums2[0]...nums2[k/2] is the smallest k/2 element. We need to find the other k/2 element in nums2[k/2+1]...nums2[m-1] and nums1
                - Recursively, we will compare k/4 th element in the arrays try to find the k/2th large element
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // If length is even
        if ((m+n) % 2 == 0) {
            return (findKthElement(nums1, 0, m - 1, nums2, 0, n-1, (m+n)/2) + findKthElement(nums1, 0, m - 1, nums2, 0, n-1, (m+n)/2 + 1)) / 2.0;
        } else {
            return findKthElement(nums1, 0, m - 1, nums2, 0, n-1, (m+n)/2 + 1);
        }
    }

    private int findKthElement(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2, int k) {
        int len1 = e1 - s1 + 1;
        int len2 = e2 - s2 + 1;
        if (len1 == 0) {
            return nums2[s2+k-1];
        }
        if (len2 == 0) {
            return nums1[s1+k-1];
        }
        if(k == 1) {
            return Math.min(nums1[s1], nums2[s2]);
        }

        int i = s1 + Math.min(k/2, len1) - 1;
        int j = s2 + Math.min(k/2, len2) - 1;
        if (nums1[i] < nums2[j]) {
            return findKthElement(nums1, i+1, e1, nums2, s2, e2, k - (i-s1+1));
        } else {
            return findKthElement(nums1, s1, e1, nums2, j+1, e2, k - (j-s2+1));
        }

    }

    /*
         Merge two arrays and then find the median
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] combined = new int[m+n];
        int i = 0;
        int j = 0;
        int k = 0;
        for(; i < m && j < n;){
            if(nums1[i] < nums2[j]) {
                combined[k++] = nums1[i++];
            } else {
                combined[k++] = nums2[j++];
            }
        }
        while (i < m) {
            combined[k++] = nums1[i++];
        }
        while (j < n) {
            combined[k++] = nums2[j++];
        }

        int index = (m+n)/2;
        if((m+n)%2 == 0) {
            double n1 = combined[index];
            double n2 = combined[index-1];
            return (n1+n2)/2;
        } else {
            return combined[index];
        }
    }
}
