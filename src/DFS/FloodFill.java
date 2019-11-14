package DFS;

/*
    An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

    Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

    To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

    At the end, return the modified image.

    Example 1:
    Input:
    image = [[1,1,1],[1,1,0],[1,0,1]]
    sr = 1, sc = 1, newColor = 2
    Output: [[2,2,2],[2,2,0],[2,0,1]]
    Explanation:
    From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
    by a path of the same color as the starting pixel are colored with the new color.
    Note the bottom corner is not colored 2, because it is not 4-directionally connected
    to the starting pixel.
 */
public class FloodFill {
    /*
        DFS
        Time Complexity: O(N)
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //int[][] visited = new int[image.length][image[0].length];
        int target = image[sr][sc];
        if(target != newColor)
            helper(image, sr, sc, target, newColor);
        return image;
    }

    public void helper(int[][] image, int r, int c, int target, int newColor){
        if(r >= image.length || c >= image[0].length || r < 0 || c < 0){
            return;
        }

        if(image[r][c] == target){
            image[r][c] = newColor;
            helper(image, r+1, c, target, newColor);
            helper(image, r-1, c, target, newColor);
            helper(image, r, c+1, target, newColor);
            helper(image, r, c-1, target, newColor);
        }
    }
}
