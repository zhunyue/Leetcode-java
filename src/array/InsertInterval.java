package array;

import java.util.ArrayList;
import java.util.List;

/*
    Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

    You may assume that the intervals were initially sorted according to their start times.

    Example 1:

    Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
    Output: [[1,5],[6,9]]
    Example 2:

    Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    Output: [[1,2],[3,10],[12,16]]
    Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<int[]>();
        int size = intervals.length;
        // If the intervals are empty, then just insert the newInterval
        if (size == 0 || newInterval[1] < intervals[0][0]) {
            int[][] result = new int[1 + size][];
            result[0] = newInterval;
            for (int i = 0; i < size; i++) {
                result[1 + i] = intervals[i];
            }
            return result;
        }

        // 1. Find the first one that the start point is greater than target interval
        int k = findFirstStartGreater(intervals, newInterval);
        int[] target = newInterval;
        // 2. Search from k to the end, to find all the overlap intervals and merge all of them
        // After the loop, i would be the first unmerged interval after the target interval
        int i = k;
        for(; i < intervals.length; i++){
            int[] j = intervals[i];
            if(j[0] > target[1]){
                break;
            }
            if(j[0] <= target[1]){
                target[0] = Math.min(j[0], target[0]);
                target[1] = Math.max(j[1], target[1]);
            }
        }

        // 2. Check if interval[k-1] is overlap, if so, merge them and update k
        if(k-1 >= 0 && intervals[k-1][1] >= target[0]){
            target[0] = Math.min(intervals[k-1][0], target[0]);
            target[1] = Math.max(intervals[k-1][1], target[1]);
            k--;
        }

        // 3. Store the merged list to a List
        for(int a = 0; a < k; a++){
            res.add(intervals[a]);
        }
        res.add(target);
        for(; i < intervals.length; i++){
            res.add(intervals[i]);
        }

        // 4. Convert the list to an array
        int[][] resArr = new int[res.size()][2];
        resArr = res.toArray(resArr);
        return resArr;
    }

    public int findFirstStartGreater(int[][] intervals, int[] newInterval){
        int left = 0;
        int right = intervals.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(intervals[mid][0] <= newInterval[0]){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return right + 1;
    }
}
