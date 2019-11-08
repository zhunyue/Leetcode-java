package TwoPointer;

/*
    Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

    Example:

    Input: S = "ADOBECODEBANC", T = "ABC"
    Output: "BANC"
    Note:

    If there is no such window in S that covers all characters in T, return the empty string "".
    If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    /*
        Similar to problem 209
        Keep expanding sliding window until it contains all the characters in T, and then try to minimize the window by left++
     */

    /*
        Method 1
     */
    public String minWindow(String s, String t) {
        int[] count = new int[128];
        int left = 0, total = 0, minLeft = -1, minLen = Integer.MAX_VALUE;
        for(char c : t.toCharArray()){
            count[c] ++;
        }

        for(int right = 0; right < s.length(); right ++){
            // if the character exist in t, total is the number of characters that appears in t
            if(--count[s.charAt(right)] >= 0){
                total++;
            }
            while(total == t.length()){
                if(minLen > right - left + 1){
                    minLeft = left;
                    minLen = right - left + 1;
                }
                //if the left character exist in t, then after left+1, total will decrease by 1
                //because we have -1 before for each character, at least, it is 1 if it appears in t,
                // therefore, it will be 0 after -1, so the count will be greater than -1
                if(++count[s.charAt(left++)] > 0) total --;
            }
        }
        return minLeft == -1 ? "" : s.substring(minLeft, minLen + minLeft);
    }

    /*
         Method 2
     */
    public String minWindow2(String s, String t) {
        //establish the dict
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            int count = map.getOrDefault(t.charAt(i), 0);
            map.put(t.charAt(i), count+1);
        }

        int total = map.size();
        int left = 0, right = 0;
        int formed = 0;

        Map<Character, Integer> cur = new HashMap<>();
        int start = 0;
        int end = 0;
        int wLen = Integer.MAX_VALUE;

        while(right < s.length()){
            char c = s.charAt(right);
            int count = cur.getOrDefault(c, 0);
            cur.put(c, count + 1);

            if(map.containsKey(c) && cur.get(c).intValue() == map.get(c).intValue()){
                formed++;
            }

            while(left <= right && formed == total){
                c = s.charAt(left);
                if(right - left + 1 < wLen){
                    wLen = right - left + 1;
                    start = left;
                    end = right;
                }
                cur.put(c, cur.get(c) - 1);
                if(map.containsKey(c) && cur.get(c).intValue() < map.get(c).intValue()){
                    formed --;
                }

                left++;
            }
            right ++;
        }
        return wLen == Integer.MAX_VALUE ? "" : s.substring(start, end + 1);
    }

}
