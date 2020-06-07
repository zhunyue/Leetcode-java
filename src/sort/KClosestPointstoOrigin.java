package sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
973. K Closest Points to Origin

    We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

    (Here, the distance between two points on a plane is the Euclidean distance.)

    You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

    Example 1:
    Input: points = [[1,3],[-2,2]], K = 1
    Output: [[-2,2]]
    Explanation:
    The distance between (1, 3) and the origin is sqrt(10).
    The distance between (-2, 2) and the origin is sqrt(8).
    Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
    We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

    Example 2:
    Input: points = [[3,3],[5,-1],[-2,4]], K = 2
    Output: [[3,3],[-2,4]]
    (The answer [[-2,4],[3,3]] would also be accepted.)


    Note:
        1 <= K <= points.length <= 10000
        -10000 < points[i][0] < 10000
        -10000 < points[i][1] < 10000
 */
public class KClosestPointstoOrigin {
    // Solution 1: min heap
    public class Pair{
        int id;
        int dis;
        Pair(int i, int d){
            id = i;
            dis = d;
        }
    }
    public int dis(int[] x){
        return x[0] * x[0] + x[1] * x[1];
    }
    public int[][] kClosest(int[][] points, int K) {
        int dist = 0;
        int[][] res = new int[K][2];
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(
                (a, b) -> a.dis - b.dis
        );
        for(int i = 0; i < points.length; i++){
            dist = dis(points[i]);
            pq.offer(new Pair(i, dist));
        }
        while(pq.size() > 0 && K >= 1){
            Pair p = pq.poll();
            res[K - 1] = points[p.id];
            K--;
        }
        return res;
    }

    // Solution 2: Sort
    public int[][] kClosest2(int[][] points, int K) {
        int[] dist = new int[points.length];
        int[][] res = new int[K][2];

        for(int i = 0; i < points.length; i++){
            dist[i] = dis(points[i]);
        }
        Arrays.sort(dist);
        int disK = dist[K - 1];
        int id = 0;
        for(int i = 0; i < points.length; i++){
            if(dis(points[i]) <= disK){
                res[id++] = points[i];
            }
        }
        return res;
    }

    // Solution 3: Quicksort
    private int compare(int[] p, int dist){
        int newDist = p[0] * p[0] + p[1] * p[1];
        return newDist - dist;
    }

    private void quickSort(int[][] points, int start, int end, int K){
        int pi = -1;
        while(pi != K - 1){
            if(pi < K - 1){
                start = pi + 1;
                pi = partition(points, start, end);
            }else if(pi > K - 1){
                end = pi - 1;
                pi = partition(points, start, end);
            }
        }
    }

    private int partition(int[][] points, int start, int end){
        int[] pivot = points[start];
        int dist = dis(pivot);
        while(start < end){
            while(start < end && compare(points[end], dist) >= 0){
                end--;
            }
            if(start < end){
                points[start++] = points[end];
            }
            while(start < end && compare(points[start], dist) <= 0){
                start++;
            }
            if(start < end){
                points[end--] = points[start];
            }
        }
        points[start] = pivot;
        return start;
    }

    public int[][] kClosest3(int[][] points, int K) {
        quickSort(points, 0, points.length - 1, K);
        return Arrays.copyOf(points, K);
    }
}
