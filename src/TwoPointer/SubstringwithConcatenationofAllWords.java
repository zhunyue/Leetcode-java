package TwoPointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    You are given a string, s, and a list of words, words, that are all of the same length.
    Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
    Example 1:

    Input:
      s = "barfoothefoobarman",
      words = ["foo","bar"]
    Output: [0,9]
    Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
    The output order does not matter, returning [9,0] is fine too.
    Example 2:

    Input:
      s = "wordgoodgoodgoodbestword",
      words = ["word","good","best","word"]
    Output: []
 */
public class SubstringwithConcatenationofAllWords {
    /*
            1. Go through all possible start point and see if it contains the four possible word
     */
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        if(s.length() == 0 || words.length == 0 )
            return res;
        int n = s.length(), num = words.length, len = words[0].length();
        for (int i = 0; i < n - num * len + 1; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while(j < num){
                String str = s.substring(i+j*len, i + (j+1)*len);
                if(map.containsKey(str)){
                    seen.put(str, seen.getOrDefault(str, 0)+1);
                    if (seen.get(str) > map.getOrDefault(str, 0)) {
                        break;
                    }
                } else{
                    break;
                }
                j++;
            }
            if(j == num){
                res.add(i);
            }
        }
        return res;
    }


    public List<Integer> findSubstring2(String s, String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        if(s.length() == 0 || words.length == 0 )
            return res;
        int n = s.length(), num = words.length, len = words[0].length();
        for(int i = 0; i < len; i++){
            for(int j = i; j <= s.length()-len*num; j += len){
                Map<String, Integer> currMap=new HashMap<>();
                int k=num-1;
                for(;k>=0;k--){
                    int start=j+k*len;
                    String substr=s.substring(start,start+len);
                    int m=currMap.getOrDefault(substr,0)+1;
                    if(m > map.getOrDefault(substr,0)){
                        j=start;
                        break;
                    }
                    currMap.put(substr,m);
                }
                if(-1==k) res.add(j);
            }
        }
        return res;
    }
}
