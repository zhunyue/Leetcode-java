package HashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
 */
public class GroupAnagrams {
    //Solution 1: sort every string and use that as a key in map, the value of map is the id of the list in the result list
    // Time complexity: O(nklogk); k is the number of letters in string, n is the size of strs
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, Integer> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for(String s: strs){
            char[] charArr = s.toCharArray();
            Arrays.sort(charArr);
            String key = String.valueOf(charArr);
            if(!map.containsKey(key)){
                map.put(key, res.size());
                res.add(new ArrayList<>());
            }
            res.get(map.get(key)).add(s);
        }
        return res;
    }

    //Solution 2: Using a combination of the number of every letter as a key
    //Time complexity: O(nk)
    public String getID(String s){
        StringBuilder sb = new StringBuilder();
        int[] letters = new int[26];
        for(int i = 0; i < s.length(); i++){
            letters[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < 26; i++){
            sb.append("#");
            sb.append(letters[i]);
        }
        return sb.toString();
    }
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(String s: strs){
            String sID = getID(s);
            if(map.containsKey(sID)){
                int listID = map.get(sID);
                res.get(listID).add(s);
            } else{
                map.put(sID, res.size());
                List<String> l = new ArrayList<>();
                l.add(s);
                res.add(l);
            }
        }
        return res;
    }
}
