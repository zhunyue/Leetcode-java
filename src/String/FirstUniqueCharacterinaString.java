package String;

/*

387. First Unique Character in a String

    Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
    
    Examples:

    s = "leetcode"
    return 0.

    s = "loveleetcode",
    return 2.
    Note: You may assume the string contain only lowercase letters.
 */
import java.util.HashMap;

public class FirstUniqueCharacterinaString {

    // Solution 1: Using HashMap to store the number of appearance
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            int times = map.getOrDefault(s.charAt(i), 0) + 1;
            map.put(s.charAt(i), times);
        }
        for(int i = 0; i < s.length(); i++){
            if(map.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    // Solution 2: Using Array to store the number of appearance
    public int firstUniqChar2(String s) {
        int[] arr = new int[26];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            arr[c - 'a']++;
        }
        for(int i = 0; i < s.length(); i++){
            if(arr[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }

    // Solution 3: Check first appearance and last appearance of characters in string
    public int firstUniqChar3(String s) {
        int res = s.length();
        for(char c = 'a'; c <= 'z'; c++){
            if(s.indexOf(c) != -1 && s.indexOf(c) == s.lastIndexOf(c))
                res = Math.min(res, s.indexOf(c));
        }
        return res == s.length() ? -1 : res;
    }
}
