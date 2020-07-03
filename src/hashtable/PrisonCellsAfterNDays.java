package hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
957. Prison Cells After N Days

There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)



Example 1:
Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]
Explanation:
The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

Example 2:
Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
Output: [0,0,1,1,1,1,1,0]
 */
public class PrisonCellsAfterNDays {
    // Solution 1: Brute force, TLE
    private void update(int[] arr){
        int[] copy = arr.clone();
        for(int i = 0; i < arr.length; i++) {
            if(i == 0 || i == arr.length - 1 || copy[i-1] != copy[i+1]) arr[i] = 0;
            else{
                arr[i] = 1;
            }
        }
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        for(int i = 0; i < N; i++){
            update(cells);
        }
        return cells;
    }

    // Solution 2: Using hashset to detect circle
    private int[] update2(int[] cells){
        int[] next = new int[cells.length];
        for(int i = 1; i < cells.length - 1; i++){
            next[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return next;
    }
    public int[] prisonAfterNDays2(int[] cells, int N) {
        Set<String> seen = new HashSet<>();
        boolean cycle = false;
        int len = 0;
        for(int i = 0; i < N; i++){
            int[] next = update2(cells);
            String key = Arrays.toString(next);
            if(seen.contains(key)){
                cycle = true;
                break;
            }
            seen.add(key);
            len++;
            cells = next;
        }
        if (cycle)
            return prisonAfterNDays2(cells, N % len);
        return cells;
    }

    // Solution 3: Similar to Solution 2 but using HashMap

    public int[] prisonAfterNDays3(int[] cells, int N) {
        HashMap<String, Integer> map = new HashMap<>();
        String str = Arrays.toString(cells);
        for(int i = 0; i < N; i++){
            map.put(str, i);
            cells = update2(cells);
            str = Arrays.toString(cells);
            if(map.containsKey(str)){
                int daysbefore = i - map.get(str) + 1; // the length of the circle
                int daysleft = N - (i + 1); // total days left
                return prisonAfterNDays3(cells,daysleft % daysbefore);
            }
        }
        return cells;
    }
}
