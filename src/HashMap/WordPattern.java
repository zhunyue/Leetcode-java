package HashMap;

import java.util.HashMap;

/*
    Given a pattern and a string str, find if str follows the same pattern.

    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

    Example 1:

    Input: pattern = "abba", str = "dog cat cat dog"
    Output: true
    Example 2:

    Input:pattern = "abba", str = "dog cat cat fish"
    Output: false
    Example 3:

    Input: pattern = "aaaa", str = "dog cat cat dog"
    Output: false
    Example 4:

    Input: pattern = "abba", str = "dog dog dog dog"
    Output: false
 */
public class WordPattern {
    /*
        Time Complexity : O(N)
     */
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if(pattern.length() != words.length)
            return false;
        HashMap<String, Integer> list = new HashMap<>();
        HashMap<Character, Integer> c = new HashMap<>();
        int index1 = 0;
        int index2 = 0;
        for(int i = 0; i < pattern.length(); i++){
            if(!list.containsKey(words[i])){
                list.put(words[i], index1++);
            }
            if(!c.containsKey(pattern.charAt(i))){
                c.put(pattern.charAt(i), index2++);
            }
            if(list.get(words[i]) != c.get(pattern.charAt(i)))
                return false;
        }

        return true;

    }
}
