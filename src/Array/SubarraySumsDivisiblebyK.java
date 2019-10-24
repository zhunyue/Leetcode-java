package Array;

/*
    Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

    Example 1:

    Input: A = [4,5,0,-2,-3,1], K = 5
    Output: 7
    Explanation: There are 7 subarrays with a sum divisible by K = 5:
    [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 */
public class SubarraySumsDivisiblebyK {
    /*
         Two ways to solve this problem:
         1. Go through all possible solutions (Time Out)
         2. Use mod property
            2.1 Calulate sumArr in which sumArr[i] indicates sum from 0 to i
            2.2 Calculate mod in each position
            2.3 Loop over every possible mod value, e.g mod[i] = x, it means we have x positions whose sum % k == x,
                then we just need to choose 2 from the x number. We have C(x, 2) = x*(x-1) / 2 possible solution
     */
    public int subarraysDivByK(int[] A, int K) {
        int n = A.length;
        int[] sumArr = new int[A.length+1]; // store the sum
        int[] modArr = new int[K]; //store the number of mods which equals to i
        int res = 0;
        for(int i = 0; i < A.length; i++){
            sumArr[i+1] = sumArr[i] + A[i];
        }

        for(int s : sumArr){
            int mod = (s % K + K) % K;
            modArr[mod] ++;
        }

        for(int mod : modArr){
            res += mod * (mod-1) / 2;
        }
        return res;

        // int count = 0;
        // int sum = 0;
        // Map<Integer, Integer> map = new HashMap<>();
        // for(int a : A){
        //     sum += a;
        //     int remainder = sum % K;
        //     if (remainder < 0) {
        //         remainder += K;
        //     }
        //     if(remainder == 0){
        //         count ++;
        //     }
        //     int countOfMod = map.getOrDefault(remainder, 0);
        //     count += countOfMod;
        //     map.put(remainder, ++countOfMod);
        // }
        // return count;

    }
}
