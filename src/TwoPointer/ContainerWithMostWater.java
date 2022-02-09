package TwoPointer;

/*
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.


Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example 2:
Input: height = [1,1]
Output: 1

Constraints:
n == height.length
2 <= n <= 105
0 <= height[i] <= 104
 */

public class ContainerWithMostWater {
    /*
      Idea: Use 2 pointers. To find maximum area, 2 things we need to understand
        - For any pair of start and end, it is bounded by shorter height
        - Given the same shorter height, the longer the width (end - start), the larger the area.

      So we calculate the current area. For the shorter side, it is already the maximum area it can hold. We then move this pointer
     */
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, area);
            if(height[left] < height[right]) {
                left++;
            } else {
                right --;
            }
        }
        return max;
    }
}
