package BinarySearch;

import java.util.*;

/*
1044. Longest Duplicate Substring

Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)

Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)

Example 1:
Input: "banana"
Output: "ana"

Example 2:
Input: "abcd"
Output: ""
 */
public class LongestDuplicateSubstring {
    // Binary search + Rabin-Karp 's algorithm
    // Rabin-Karp 's algorithm: Using hash(usually transfer to a x-base, 26-base for example,
    // store every hash we have seen before. When encounter a new substring first check if the hash exist
    // If so, check every substring with the same hash to see if the 2 string match
    // Hash example "abcd", when check abc, we have abc -> 012 -> 0*26*26 + 1*26 + 2*1
    // After we move to get "bcd" which is 123 we have (0*26*26 + 1*26 + 2*1)*26 - 0*26*26*26 + 3*1
    private String findDup(String s, int len){
        if(len <= 0) return null;
        String res = null;
        long hash = 0L;
        long pow = 1; // highest bit pow, for example, we have 123, the highest pow is 26^2
        long MOD = 1000003; // Large number. Preferably prime to reduce spurious hits
        Map<Long, List<Integer>> hashes = new HashMap<>();

        // initial hash for substring [0, len - 1] and calculate pow
        for(int i = 0; i < len; i++){
            hash = hash * 26 + (s.charAt(i) - 'a');
            hash = hash % MOD;
            pow = (pow * 26) % MOD;
        }
        hashes.put(hash, new ArrayList<>(Arrays.asList(0)));

        // Go through other substring with length = len
        for(int i = len; i < s.length(); i++){
            long remove = (s.charAt(i - len) - 'a') * pow;
            long add = s.charAt(i) - 'a';
            hash = (hash * 26) % MOD;
            hash = (hash - remove) % MOD;
            hash = (hash + MOD) % MOD; // This is in case our hash went below 0
            hash = (hash + add) % MOD;

            // If seen this hash before
            if(hashes.containsKey(hash)){
                String ans = s.substring(i - len + 1, i + 1);  // current string ending in i with length len
                for(int start : hashes.get(hash)){
                    String other = s.substring(start, start + len);
                    if(ans.equals(other)){
                        return ans;
                    }
                }
                hashes.get(hash).add(i - len + 1);
            } else{
                hashes.put(hash, new ArrayList<>(Arrays.asList(i - len + 1)));
            }
        }
        return res;

    }

    public String longestDupSubstring(String S) {
        String res = "";
        int left = 0;
        int right = S.length();
        while(left <= right) {
            int mid = (left + right) / 2;
            String tmp = findDup(S, mid);
            if(tmp != null){
                res = tmp;
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return res;
    }

    // Solution 2: Using Trie
    // For every i, try to find the maximum duplicate substring starting from i
    // For example, "banana",  when starting from i, we try to find a suplicate substring with maximum length
    // e.g. start from index 3 with 'a', 'a' already exist, then we compare index 2 and index 4, they are the same,
    // then compare index 3 and index 5. Then we reach the end and return a maximum length of 3.
    private String S;
    public String longestDupSubstring2(String S) {
        this.S = S;
        int maxLo = 0, maxLength = 0;
        TrieNode root = new TrieNode(0, 0);
        for (int i = 1; i + maxLength < S.length(); i++) {
            int len = addNew(root, i);
            if (len > maxLength) {
                maxLength = len;
                maxLo = i;
            }
        }
        return S.substring(maxLo, maxLo + maxLength);
    }

    private boolean isLeaf(TrieNode node) {
        return node.next == null;
    }

    private int getIndex(int i, int depth) {
        return S.charAt(i + depth) - 'a';
    }

    // return the maximum length starting from i
    private int addNew(TrieNode node, int i) {
        int depth = node.depth;
        if (i + depth == S.length()) return depth;
        if (isLeaf(node)) {
            node.next = new TrieNode[26];
            node.next[getIndex(node.i, node.depth)] = new TrieNode(node.i, depth + 1);
        }
        int c = getIndex(i, node.depth);
        TrieNode x = node.next[c];
        if (x == null) {
            node.next[c] = new TrieNode(i, depth + 1);
            return depth;
        }
        return addNew(x, i);
    }

    private static class TrieNode {
        private TrieNode[] next;
        private int i;
        private int depth;

        public TrieNode(int i, int depth) {
            this.i = i;
            this.depth = depth;
        }
    }
}
