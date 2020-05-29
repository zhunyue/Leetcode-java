package array;

/*

1232. Check If It Is a Straight Line

    You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
    Check if these points make a straight line in the XY plane.

    Example 1:
    Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
    Output: true

    Example 2:
    Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
    Output: false

    Constraints:
    2 <= coordinates.length <= 1000
    coordinates[i].length == 2
    -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
    coordinates contains no duplicate point.
 */
public class CheckIfItIsaStraightLine {

    // Solution 1: Using cross product to calculate
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2) return true;
        for(int i = 1; i < coordinates.length - 1; i++){
            int x1 = coordinates[i - 1][0], y1 = coordinates[i - 1][1];
            int x2 = coordinates[i][0], y2 = coordinates[i][1];
            int x3 = coordinates[i + 1][0], y3 = coordinates[i + 1][1];
            if((y2 - y1) * (x3 - x2) != (y3 - y2) * (x2 - x1))
                return false;
        }
        return true;
    }

    // Solution 2: Calculate slope and compare with the first two
    private double calculateSlope(int[] p1, int[] p2) {
        double deltaY = p2[1] - p1[1];
        double deltaX = p2[0] - p1[0];
        if(deltaX == 0) {
            return Integer.MAX_VALUE;
        }
        return deltaY/deltaX;
    }
    public boolean checkStraightLine2(int[][] coordinates) {
        if (coordinates.length == 2) return true;
        double slope = calculateSlope(coordinates[0], coordinates[1]);
        for(int i = 2; i < coordinates.length; i++) {
            if(calculateSlope(coordinates[i], coordinates[i-1]) != slope)
                return false;
        }
        return true;
    }
}
