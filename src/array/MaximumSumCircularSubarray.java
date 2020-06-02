package array;

/*

918. Maximum Sum Circular Subarray

    Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.

    Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)

    Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)



    Example 1:
    Input: [1,-2,3,-2]
    Output: 3
    Explanation: Subarray [3] has maximum sum 3

    Example 2:
    Input: [5,-3,5]
    Output: 10
    Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10

    Example 3:
    Input: [3,-1,2,-1]
    Output: 4
    Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4

    Example 4:
    Input: [3,-2,2,-3]
    Output: 3
    Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3

    Example 5:
    Input: [-2,-3,-1]
    Output: -1
    Explanation: Subarray [-1] has maximum sum -1

    Note:
        -30000 <= A[i] <= 30000
        1 <= A.length <= 30000
 */
public class MaximumSumCircularSubarray {

    // Solution 1: Deal with two cases ( with circle and without circle)
    // case 1 : without circle, we calculate maximum subarry sum directly
    // case 2: with circle, we calculate the accumulative sum from position 0 and and add the maximum subarry sum to the right(stop at the last index)
    // Finally, we choose the maximum among the two case
    public int maxSubarraySumCircular(int[] A) {
        int cur = A[0]; // The maximum subarry sum for every position
        int res = A[0]; // Record the maximum sum

        // The case that the maximum array without circular
        for(int i = 1; i < A.length; i++){
            cur = Math.max(A[i], cur + A[i]);
            res = Math.max(res, cur);
        }

        // The case that the maximum array with circular
        int[] sumFromRight = new int[A.length]; // The accumulative sum from the end to position i
        sumFromRight[A.length - 1] = A[A.length - 1];
        for(int i = A.length - 2; i >= 0; i--){
            sumFromRight[i] = sumFromRight[i + 1] + A[i];
        }

        int[] maxFromRight = new int[A.length]; // The maximum continuous subarray sum from the end and end in i
        maxFromRight[A.length - 1] = A[A.length - 1];
        for(int i = A.length - 2; i >= 0; i--){
            maxFromRight[i] = Math.max(maxFromRight[i + 1], sumFromRight[i]);
        }

        int leftSum = 0; // Record the accumulative sum to position i
        for(int i = 0; i < A.length - 1; i++){
            leftSum += A[i];
            res = Math.max(res, leftSum + maxFromRight[i+1]);
        }

        return res;
    }

    // Solution 2: Similar to the previous one
    // case 1 : without circle, we calculate maximum subarry sum directly and record it in variable max
    // case 2: with circle, we use total sum minus the minimum subarray sum
    // Finally, we choose the maximum among the two case
    public int maxSubarraySumCircular2(int[] A) {
        int max = Integer.MIN_VALUE; // The maximum continuous sum
        int min = Integer.MAX_VALUE; // The minimum continuous sum
        int total = 0; // The total sum
        int curr1 = 0; // Record the maximum continuous sum at last index
        int curr2 = 0; // Record the minimum continuous sum at last index

        for(int i = 0; i < A.length; i++){

            curr1 += A[i];
            max = Math.max(max, curr1); // case 1 answer
            curr1 = Math.max(curr1, 0);

            curr2 += A[i];
            min = Math.min(min, curr2);
            curr2 = Math.min(curr2, 0);

            total += A[i];
        }
        return max < 0 ? max : Math.max(max, total - min);
    }

}
