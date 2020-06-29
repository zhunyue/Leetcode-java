package DFS;

import java.util.*;

/*
332. Reconstruct Itinerary

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
One must use all the tickets once and only once.

Example 1:
Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

Example 2:
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
 */
public class ReconstructItinerary {
    // Solution 1: Recursive
    private void helper(String s, List<String> res, HashMap<String, PriorityQueue<String>> map){
        PriorityQueue<String> arrivals = map.get(s);
        while(arrivals != null && arrivals.size() > 0){
            helper(arrivals.poll(), res, map);
        }
        res.add(0, s);
    }
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for(List<String> t : tickets){
            String start = t.get(0);
            String end = t.get(1);
            if(map.containsKey(start)){
                map.get(start).add(end);
            } else{
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.offer(end);
                map.put(start, pq);
            }
        }
        helper("JFK", res, map);
        return res;
    }

    // Solution 2: Iterative using stack
    public List<String> findItinerary2(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for(List<String> t : tickets){
            String start = t.get(0);
            String end = t.get(1);
            if(map.containsKey(start)){
                map.get(start).add(end);
            } else{
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.offer(end);
                map.put(start, pq);
            }
        }
        Stack<String> s = new Stack<>();
        s.push("JFK");
        while(!s.isEmpty()){
            while(map.containsKey(s.peek()) && !map.get(s.peek()).isEmpty()){
                s.push(map.get(s.peek()).poll());
            }
            res.add(0, s.pop());

        }

        return res;
    }
}
