package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
60. Permutation Sequence

    The set [1,2,3,...,n] contains a total of n! unique permutations.

    By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

    "123"
    "132"
    "213"
    "231"
    "312"
    "321"
    Given n and k, return the kth permutation sequence.

    Note:

    Given n will be between 1 and 9 inclusive.
    Given k will be between 1 and n! inclusive.
    Example 1:
    Input: n = 3, k = 3
    Output: "213"

    Example 2:
    Input: n = 4, k = 9
    Output: "2314"
 */
public class PermutationSequence {
    // Solution 1: Brute force, try all permutations and find the kth permutation(starting with 1)
    int count = 0;
    String ans = null;
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder("");
        for(int i = 1; i <= n; i++){
            sb.append(i);
        }
        helper("", sb.toString(), k);
        return ans;
    }

    private void helper(String pre, String s, int k){
        if(count == k) return;
        if(s.length() == 0) {
            count++;
            if(count == k){
                ans = pre;
                return;
            }
        }
        for(int i = 0; i < s.length(); i++){
            helper(pre + s.charAt(i), s.substring(0, i) + s.substring(i + 1), k);
        }
    }

    // Solution 2: Math solution
    /*
    Suppuse we have [1, 2, 3, 4, ..., n], for every number starting with i, there are (n-1)! permutations. And
    for every permutation, there are n bits in the number.
    We will have a list {1, 2, 3, ..., n} and a fact array which indicate the factoria, fact[i] = 1*2*3...*i
    We are going to fix one bit at one time. For the first bit, there are (n-1)! possible numbers followed by the first
    bit for every choice:
            1 followed by permutations composed of (2,3,...n)
            2 followed by permutations somposed of (1,3,...n)
            ...
    Therefore, if we are looking for the k-th number(here, k starting from 0), it should locate in the k/fact(n-1) th group;
    Similarly, when fixing the second bit, it locate in the k/fact(n-2) th group and so on. But to remember update k every time.
     */
    public String getPermutation2(int n, int k) {
        int[] fact = new int[n];
        List<Integer> nums = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        fact[0] = 1;
        nums.add(1);
        for(int i = 1; i < n; i++){
            nums.add(i+1);
            fact[i] = fact[i-1] * i;
        }
        k--;
        for(int i = n - 1 ; i >= 0; i--){
            int index = k / fact[i];
            sb.append(nums.get(index));
            nums.remove(index);
            k -= index * fact[i];
            // k %= fact[i]; //the same function as k -= index * fact[i];
        }
        return sb.toString();
    }

    //Solution 3: Similar to solution 2 but implemented in another way
    public String getPermutation3(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[n];
        k--;
        int fact = 1;
        for(int i = 1; i < n; i++){
            fact *= i;
        }
        for(int i = 0; i < n; i++){
            int index = k / fact; //get the index
            //k %= fact;
            k -= index * fact;
            for(int j = 0; j < n; j++){  //Find the index-th unsed character and use that one
                if(used[j] == false){
                    if(index == 0) {
                        used[j] = true;
                        sb.append((char)('0' + j + 1));
                        break;
                    } else{
                        index--;
                    }

                }
            }
            if(i < n - 1){
                fact = fact / (n - 1 - i);
            }

        }
        return sb.toString();
    }
}
