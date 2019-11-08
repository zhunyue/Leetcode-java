package Graph;

import java.util.*;

/*
    There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

    Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

    Example 1:
    Input:
    n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
    src = 0, dst = 2, k = 1
    Output: 200
    The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.

    Example 2:
    Input:
    n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
    src = 0, dst = 2, k = 0
    Output: 500
    The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 */
public class CheapestFlightsWithinKStops {

    /*
        Use Dijkstra based on Priority queue, check whether the length is less than k

     */
    class Node{
        int city;
        int stop;
        int cost;

        Node(int stop, int cost, int city){
            this.city = city;
            this.stop = stop;
            this.cost = cost;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // store the graph in a list
        // each element i in the list is a map containing the end point for the start point i key = end and value = cost
        List<Map<Integer, Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new HashMap<>());
        }
        for(int i = 0; i < flights.length; i++){
            graph.get(flights[i][0]).put(flights[i][1], flights[i][2]);
        }

        Queue<Node> q = new PriorityQueue<Node>(new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2){
                return o1.cost - o2.cost;
            }
        });

        q.add(new Node(-1, 0, src));
        Set<Integer> settle = new HashSet<>();
        while(!q.isEmpty()){
            Node node = q.poll();
            if(node.city == dst){
                return node.cost;
            }
            settle.add(node.city);
            if(node.stop < K){
                for(Map.Entry<Integer, Integer> entry: graph.get(node.city).entrySet()){
                    if(!settle.contains(entry.getKey())){
                        q.add(new Node(node.stop + 1, node.cost + entry.getValue(), entry.getKey()));
                    }
                }
            }
        }
        return -1;
    }
}
