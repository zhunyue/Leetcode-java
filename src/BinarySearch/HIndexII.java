package BinarySearch;

/*
275. H-Index II

Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
             received 0, 1, 3, 5, 6 citations respectively.
             Since the researcher has 3 papers with at least 3 citations each and the remaining
             two with no more than 3 citations each, her h-index is 3.
Note:

If there are several possible values for h, the maximum one is taken as the h-index.
 */
public class HIndexII {
    // Solution 1: O(n), find the maximum length to the right which is less or equal the pivot value
    // search in the array
    public int hIndex(int[] citations) {
        if(citations.length == 0) return 0;
        for(int i = 0; i < citations.length; i++){
            if(citations[i] >= citations.length - i)
                return citations.length - i;
        }
        return 0;
    }

    // Solution 2: O(log n), using binary search in range[0, n-1]
    public int hIndex2(int[] citations) {
        int n = citations.length;
        int left = 0;
        int right = n - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(citations[mid] == n - mid){
                return n - mid;
            } else if (citations[mid] < n - mid){ // The desired element should be greater or equal to n - pivot
                left = mid + 1;
            } else { // The desired element should be less or equal to n - pivot
                right = mid - 1;
            }
        }
        return n - left;
    }

}
