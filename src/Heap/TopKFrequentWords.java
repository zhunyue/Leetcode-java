package Heap;

/*
    Given a non-empty list of words, return the k most frequent elements.

    Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

    Example 1:
    Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
    Output: ["i", "love"]
    Explanation: "i" and "love" are the two most frequent words.
        Note that "i" comes before "love" due to a lower alphabetical order.
    Example 2:
    Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
    Output: ["the", "is", "sunny", "day"]
    Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
        with the number of occurrence being 4, 3, 2 and 1 respectively.
 */

import java.util.*;

public class TopKFrequentWords {
    /*
         Heap
         Time Complexity: O(N*logk)
         Space Complexity: O(N)
     */
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String s : words){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<String>(new Comparator<String>(){
            @Override
            public int compare(String a, String b){
                if(map.get(a) < map.get(b)){
                    return 1;
                } else if(map.get(a) == map.get(b)){
                    return a.compareTo(b);
                } else{
                    return -1;
                }
            }
        }
        );
        for(String s : map.keySet()){
            pq.offer(s);
        }
        List<String> res = new ArrayList<>();
        for(int i = 0; i < k; i++){
            res.add(pq.poll());
        }
        return res;
    }

    /*
        HashMap
        Time Complexity: O(N*logN)
        Space Complexity: O(N)
     */
    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String word : words){
            int times = map.containsKey(word)? map.get(word)+1 : 1;
            map.put(word, times);
        }
        List<String> candidates = new ArrayList(map.keySet());
        Collections.sort(candidates, (w1, w2) -> map.get(w1).equals(map.get(w2)) ?
                w1.compareTo(w2) : map.get(w2) - map.get(w1));
        List<String> res = new ArrayList<>();
        for(int i = 0; i < k; i++){
            res.add(candidates.get(i));
        }
        return res;
    }
}
