package Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*

451. Sort Characters By Frequency
    Given a string, sort it in decreasing order based on the frequency of characters.

    Example 1:
    Input:
    "tree"

    Output:
    "eert"

    Explanation:
    'e' appears twice while 'r' and 't' both appear once.
    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

    Example 2:
    Input:
    "cccaaa"

    Output:
    "cccaaa"

    Explanation:
    Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
    Note that "cacaca" is incorrect, as the same characters must be together.

    Example 3:
    Input:
    "Aabb"

    Output:
    "bbAa"

    Explanation:
    "bbaA" is also a valid answer, but "Aabb" is incorrect.
    Note that 'A' and 'a' are treated as two different characters.
 */
public class SortCharactersByFrequency {

    // Solution 1: HashMap + Heap
    // Store frequency in hashmap, and put hashmap entry to maximum heap
    public String frequencySort(String s) {
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Character, Integer>> pq =  new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int times = map.getOrDefault(c, 0) + 1;
            map.put(c, times);
        }
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            pq.offer(entry);
        }
        while(!pq.isEmpty()){
            Map.Entry<Character, Integer> entry = pq.poll();
            char c = entry.getKey();
            for(int i = 0; i < entry.getValue(); i++){
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // Solution 2: Using array to store frequency for each character and go through characters, put them into heap wrt arr[i]
    // arr[i] is the corresponding frequency
    public String frequencySort2(String s) {
        int len = s.length();
        int[] arr = new int[256];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            arr[c]++;
        }
        PriorityQueue<Character> pq = new PriorityQueue<>(
                (a, b) -> arr[b] - arr[a]
        );
        for(int i = 0; i < 256; i++){
            pq.offer((char) i);
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            char  c = pq.poll();
            int times = arr[c];
            for(int i = 0; i < times; i++){
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

