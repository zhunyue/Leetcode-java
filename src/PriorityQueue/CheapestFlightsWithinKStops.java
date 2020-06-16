package PriorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
787. Cheapest Flights Within K Stops
Medium

There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is
to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 */
public class CheapestFlightsWithinKStops {
    // Solution 1: Using priorityqueue, pick up the one with the least cost
    // if hos exceed, skip that
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] cost = new int[n][n];
        for(int[] f:flights){
            cost[f[0]][f[1]] = f[2];
        }
        Queue<int[]> q = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        q.add(new int[] {0, src, K + 1});
        while(!q.isEmpty()) {
            int[] entry = q.poll();
            int c = entry[0];
            int cur = entry[1];
            int stops = entry[2];
            if (cur == dst) return c;

            if(stops > 0) {
                for(int j = 0; j < n; j++) {
                    if(cost[cur][j] != 0){
                        q.add(new int[] {c + cost[cur][j], j, stops - 1});
                    }
                }
            }
        }
        return -1;
    }

    // Solution 2: Bellman-Ford
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        // Go through 0, 1, ... K stops and chose the minimum
        for(int i = 0; i <= K; i++){
            int[] temp = Arrays.copyOf(prices, n);
            for(int[] f : flights){
                int cur = f[0];
                int next = f[1];
                int cost = f[2];
                if(prices[cur] == Integer.MAX_VALUE) continue;
                temp[next] = Math.min(temp[next], prices[cur] + cost);
            }
            prices = temp;
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    public class city{
        int city;
        int cost;
        int hops;
        public city(int id, int distance, int hops ) {
            this.city = id;
            this.cost = distance;
            this.hops = hops;
        }
    }

    // Solution 3: Similar to solution 1, using priority queue but with more conditions when adding to priority queue
    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for(int i = 0 ; i < flights.length; i++) {
            graph[flights[i][0]][flights[i][1]] = flights[i][2];
        }

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        int[] hops = new int[n];
        Arrays.fill(hops, Integer.MAX_VALUE);
        cost[src] = 0;
        hops[src]=0;

        PriorityQueue<city> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new city(src, 0, 0));

        while(!pq.isEmpty()){
            city cur = pq.poll();
            if(cur.city == dst) return cur.cost;
            if(cur.hops == K + 1) continue;
            int[] next = graph[cur.city];
            for(int i = 0; i < n; i++){
                if(next[i] != 0) {
                    int new_cost = cur.cost + next[i];
                    int new_hop = cur.hops + 1;
                    if(new_cost < cost[i]) {
                        pq.offer(new city(i, new_cost, new_hop));
                        cost[i] = new_cost;
                    } else if (new_hop < hops[i]) {
                        pq.offer(new city(i,new_cost,new_hop));
                        hops[i] = new_hop;
                    }
                }
            }
        }
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }
}
