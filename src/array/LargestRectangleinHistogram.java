package array;

import java.util.Stack;

/*
    Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
    find the area of largest rectangle in the histogram.

    Example:

    Input: [2,1,5,6,2,3]
    Output: 10
 */
public class LargestRectangleinHistogram {
    /*
        Solution 1:
            1. Scan from left and right respectively to find the first on on left/right < current height
            2. Scan height array and left and right array to find the maximum area for each bar(make full use of)
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int max = 0;
        if(heights.length == 0)
            return 0;
        int[] smallL = new int[len];
        int[] smallR = new int[len];
        smallL[0] = -1;
        // Find the first one on the left which < current
        for(int i = 1; i < len; i++){
            int j = i - 1;
            while(j >= 0 && heights[j] >= heights[i]){
                j = smallL[j];
            }
            smallL[i] = j;
        }

        smallR[len - 1] = len;
        for(int i = len - 2; i >= 0; i--){
            int j = i + 1;
            while(j < len && heights[j] >= heights[i]){
                j = smallR[j];
            }
            smallR[i] = j;
        }

        for(int i = 0; i < len; i++){
            max = Math.max(max, heights[i]*(smallR[i]-smallL[i]-1));
        }
        return max;
    }

    /*
       Solution 2: Using a stack to store increading sequence. Once we encounter decrease, we will find the maximum
                   in increasing sequence.
    */
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        int max = 0;
        Stack<Integer> s = new Stack<>();
        s.push(-1);
        for(int i = 0; i <= len; i++){
            int rHeight = (i==len) ? -1 : heights[i];
            while(s.peek() != -1 && rHeight <= heights[s.peek()]){
                int cur_height = heights[s.pop()];
                int width = i - s.peek() - 1;
                max = Math.max(max, width * cur_height);
            }
            s.push(i);
        }
        return max;
    }
}
