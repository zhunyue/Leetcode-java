package slidingwindow;

import java.util.HashMap;

/*
Given a string s, find the length of the longest substring without repeating characters.



Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /*
        Idea:
          - Use HashMap to store the index of character: the latest index which does not have dupliation
          - Use sliding window to expand string, if duplicate detected:
            move the left pointer to the character after duplicate position
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            char right = s.charAt(j);
            if(map.containsKey(right) && map.get(right) >= i) {
                i = map.get(right) + 1;
            }
            maxLen = Math.max(maxLen, j - i + 1);
            map.put(right, j);
        }
        return maxLen;
    }

}
