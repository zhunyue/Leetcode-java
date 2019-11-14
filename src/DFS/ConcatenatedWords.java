package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
    Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
    A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

    Example:
    Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

    Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

    Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
     "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
    "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 */
public class ConcatenatedWords {
    /*
        Solution 1: DFS with hashset, to see if each string from 0 could be formed
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        int minLen = Integer.MAX_VALUE;
        for(String s : words){
            if(s.length() > 0){
                set.add(s);
                minLen = Math.min(minLen, s.length());
            }
        }

        for(String s : words){
            if(helper(s, 0, minLen, set)){
                res.add(s);
            }
        }
        return res;
    }

    private boolean helper(String word, int start, int minLen, HashSet<String> set){
        for(int i = start + minLen; i <= word.length(); i++){
            String sub = word.substring(start, i);
            if(set.contains(sub) && (set.contains(word.substring(i)) || helper(word, i, minLen, set))){
                return true;
            }
        }

        return false;
    }

    class TrieNode{
        String word;
        TrieNode[] next;
        private TrieNode(){
            next = new TrieNode[26];
        }
    }

    /*
        Solution 2: Build Trie and use DFS, search in trie to see if the word could be combine
     */
    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        List<String> res = new ArrayList<>();
        //Build Trie
        TrieNode root = new TrieNode();
        for(String s : words){
            TrieNode p = root;
            for(char c : s.toCharArray()){
                if(p.next[c-'a'] == null){
                    p.next[c-'a'] = new TrieNode();
                }
                p = p.next[c-'a'];
            }
            p.word = s;
        }

        for(String s : words){
            helper(root, s, res, 0, 0);
        }

        return res;
    }

    //Check in trie to see if string s.substring(index) could be found
    private boolean helper(TrieNode root, String s, List<String> res, int index, int count){
        if(index == s.length() && count >= 2){
            res.add(s);
            return true;
        }
        TrieNode p = root;
        boolean found = false;

        for(int i = index; i < s.length(); i++){
            if(p.next[s.charAt(i) - 'a'] == null) return false;
            p = p.next[s.charAt(i) - 'a'];
            if(p.word != null && !p.word.equals(s)){
                found = helper(root, s, res, i + 1, count +1);
            }
            if(found) return true;
        }

        return found;

    }
}
