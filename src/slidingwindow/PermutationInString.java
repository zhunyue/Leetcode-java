package slidingwindow;

import java.util.Arrays;

/*
567. Permutation in String

    Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.



    Example 1:
    Input: s1 = "ab" s2 = "eidbaooo"
    Output: True
    Explanation: s2 contains one permutation of s1 ("ba").

    Example 2:
    Input:s1= "ab" s2 = "eidboaoo"
    Output: False


    Constraints:
        The input strings only contain lower case letters.
        The length of both given strings is in range [1, 10,000].
 */
public class PermutationInString {

    // Solution 1: Using two pointer to indicate the start and end index
    public boolean checkInclusion(String s1, String s2) {

        int len1 = s1.length(), len2 = s2.length();
        if(len1 > len2) return false;
        int[] map1 = new int[26];
        for(int i = 0; i < len1; i++){
            int id = s1.charAt(i) - 'a';
            map1[id]++;
        }
        for(int i = 0; i <= len2 - len1; i++){
            int[] map2 = new int[26];
            for(int j = 0; j < len1; j++){
                int id = map2[s2.charAt(i + j) - 'a']++;
            }
            if(Arrays.equals(map1, map2)) return true;
        }
        return false;
    }

    // Solution 2: Remove the element by subtracting by 1
    public boolean checkInclusion2(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if(len1 > len2) return  false;
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for(int i = 0; i < len1; i++){
            int id = s1.charAt(i) - 'a';
            map1[id]++;
        }

        for(int i = 0; i < len2; i++){
            int id = s2.charAt(i) - 'a';
            map2[id]++;
            if(i >= len1){
                int removeId = i - len1;
                map2[s2.charAt(removeId) - 'a']--; //remove from head
            }
            if(Arrays.equals(map1, map2)){
                return true;
            }
        }
        return false;

    }
}
