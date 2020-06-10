package BinarySearch;

import java.util.Random;

/*
528. Random Pick with Weight

Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

For example, given an input list of values [1, 9], when we pick up a number out of it, the chance is that 9 times out of 10 we should pick the number 9 as the answer.

Example 1:
Input:
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]

Example 2:
Input:
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
 */
public class RandomPickwithWeight {

    // General idea: for example [1,3,5]
    // we will get a sum [1, 4, 9],further we can understand imaging we have a list [ 0,1,1,1,2,2,2,2,2] each sum[i] is the index
    // therefore, if we get an random number within [0], we choose 0, if [1, 3] we choose 1, if [4, 8] we choose 2
    // which is equal to find the first one that is larger than the random number

    // Solution 1: linear search
    int[] sum;
    public void Solution(int[] w) {
        sum = new int[w.length];
        sum[0] = w[0];
        for(int i = 1; i < w.length; i++){
            sum[i] = sum[i-1] + w[i];
        }
    }

    public int pickIndex() {
        double target = sum[sum.length - 1] * Math.random();
        for(int i = 0; i < sum.length; i++){
            if(sum[i] > target) return i;
        }
        return -1;
    }

   // Solution 2: Binary search
    public void Solution2(int[] w) {
        sum = new int[w.length];
        sum[0] = w[0];
        for(int i = 1; i < w.length; i++){
            sum[i] = sum[i-1] + w[i];
        }
    }

    public int pickIndex2() {
        double target = sum[sum.length - 1] * Math.random();
        int left = 0;
        int right = sum.length - 1;
        int res = -1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(sum[mid] <= target){
                left = mid + 1;
            } else{
                res = mid;
                right = mid - 1;
            }
        }
        return res;
    }

    // Solution 3: Using extra space to store the new list
    int[] lr;    // sample space array to pick a number
    Random r;    // for random no generation
    int[] p;     // probability array
    double s =0;
    int k =0;
    public void Solution3(int[] w) {
        lr = new int[10000];
        p = new int[w.length];
        r = new Random();
        for(int i =0; i< w.length; i++)      //calculating sum
            s+=w[i];

        for(int i =0; i< w.length; i++)      // calculating probability for each sum
            p[i] = (int)((w[i]/s)*1000);

        for(int i =0;i< p.length; i++){      // creating sample space based on probability
            for(int j =0; j<=p[i]; j++){
                lr[k++] =i;
            }
        }
    }

    public int pickIndex3() {
        int i = r.nextInt(k);
        return lr[i];                        // picking a random element from the sample space array
    }
}
