package String;

import java.util.HashMap;

/*

383. Ransom Note

    Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

    Each letter in the magazine string can only be used once in your ransom note.



    Example 1:

    Input: ransomNote = "a", magazine = "b"
    Output: false
    Example 2:

    Input: ransomNote = "aa", magazine = "ab"
    Output: false
    Example 3:

    Input: ransomNote = "aa", magazine = "aab"
    Output: true

 */
public class RansomNote {

    // Solution 1: Using HashMap
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < magazine.length(); i++){
            int times = map.getOrDefault(magazine.charAt(i), 0) + 1;
            map.put(magazine.charAt(i), times);
        }
        for(int i = 0; i < ransomNote.length(); i++){
            char c = ransomNote.charAt(i);
            if(!map.containsKey(c) || map.get(c) <= 0){
                return false;
            }
            map.put(c, map.get(c) - 1);
        }
        return true;
    }

    // Solution 2: Using array
    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for(int i = 0; i < ransomNote.length(); i++){
            char c = ransomNote.charAt(i);
            int index = magazine.indexOf(ransomNote.charAt(i), arr[c - 'a']);
            if(index == -1){
                return false;
            }
            arr[c - 'a'] = index + 1;
        }
        return true;
    }

    // Solution 3: Using array
    public boolean canConstruct3(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for(int i = 0; i < magazine.length(); i++){
            arr[magazine.charAt(i) - 'a']++;
        }
        for(int i = 0; i < ransomNote.length(); i++){
            char c = ransomNote.charAt(i);
            if(arr[c - 'a'] <= 0){
                return false;
            }
            arr[c - 'a'] --;
        }
        return true;
    }
}
