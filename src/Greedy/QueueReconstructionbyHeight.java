package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
406. Queue Reconstruction by Height

    Suppose you have a random list of people standing in a queue.
    Each person is described by a pair of integers (h, k), where h
    is the height of the person and k is the number of people in front
    of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

    Note:
    The number of people is less than 1,100.

    Example

    Input:
    [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

    Output:
    [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionbyHeight {
    /* General idea: Sort the array according to the height (from hight to low), if height is the same, sort according to people
     in front of him ( low to high)
     Iterate through the sorted array, every time insert one at index k, since we are inserting actually the smallest one
     up to now, it will not influence people after him.
     p.s  if the lowest people stands at index k, then there are k people in front him who are taller than him.
    */


    // Solution 1 : Using sort
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2){
                return (p1[0] == p2[0]) ? p1[1] - p2[1] : p2[0] - p1[0];
            }
        });

        for(int i = 0; i < people.length; i++){
            res.add(people[i][1], people[i]);
        }
        return res.toArray(new int[people.length][2]);
    }

    // Solution 2: slightly different, using quicksort to sort
    public int[][] reconstructQueue2(int[][] people) {
        List<int[]> res = new ArrayList<>();
        quickSort(people, 0, people.length-1); // Sort according to height(high to low), if height is equal, sort according to k(low to high)
        for(int i = 0; i < people.length; i++){ // Every time we insert, it is the smallest one up to now, therefore, we insert at k, it is smaller than people after that, so t
            res.add(people[i][1], people[i]);
        }
        return res.toArray(new int[people.length][2]);
    }

    private void quickSort(int[][] people, int left, int right) {
        if(left >= right) return;
        int[] pivot = people[left];
        int l = left;
        int r = right;

        while( l < r){
            while( l < r && (people[r][0] < pivot[0] || (people[r][0] == pivot[0] && people[r][1] >= pivot[1]))) {
                r--;
            }
            if( l < r){
                people[l++] = people[r];
            }
            while( l < r && (people[l][0] > pivot[0] || (people[l][0] == pivot[0] && people[l][1] <= pivot[1]))){
                l++;
            }
            if(l < r){
                people[r--] = people[l];
            }
        }

        people[l] = pivot;
        quickSort(people, left, l - 1);
        quickSort(people, l + 1, right);

    }
}
