package DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*

886. Possible Bipartition

    Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

    Each person may dislike some other people, and they should not go into the same group.

    Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

    Return true if and only if it is possible to split everyone into two groups in this way.



    Example 1:

    Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
    Output: true
    Explanation: group1 [1,4], group2 [2,3]
    Example 2:

    Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
    Output: false
    Example 3:

    Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
    Output: false


    Note:

    1 <= N <= 2000
    0 <= dislikes.length <= 10000
    1 <= dislikes[i][j] <= N
    dislikes[i][0] < dislikes[i][1]
    There does not exist i != j for which dislikes[i] == dislikes[j].

 */
public class PossibleBipartition {

    // Solution 1: DFS
    public boolean possibleBipartition(int N, int[][] dislikes) {
        ArrayList<Integer>[] graph = new ArrayList[N+1]; // graph[i] is a list of dislikes of i
        Map<Integer, Integer> map = new HashMap<>(); // the person id and the corresponding group
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] e : dislikes){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        for(int n = 1; n <= N; n++){
            if(!map.containsKey(n) && !helper(n, 0, map, graph))
                return false;
        }
        return true;
    }

    //Every time we put one people, we try to put all his dislikes into another group
    public boolean helper(int node, int c, Map<Integer, Integer> map, ArrayList<Integer>[] graph){
        if(map.containsKey(node))
            return map.get(node) == c;
        map.put(node, c);
        for(int n : graph[node]){
            if(!helper(n, c^1, map, graph))
                return false;
        }
        return true;
    }

    // Solution 2: Union Find

    public boolean possibleBipartition2(int N, int[][] dislikes) {
        int[] ids = new int[N+1]; // The class of person i
        for(int i = 1; i <= N; i++){
            ids[i] = i;
        }

        for(int[] dis : dislikes){
            int one = dis[0];
            int two = dis[1];
            if(ids[one] == one && ids[two] == two) { // if both of them are not assinged
                ids[one] = two;
                ids[two] = one;
            } else if (ids[one] != one && ids[two] == two){ //one has been assigned but two has not
                ids[two] = ids[ids[one]]; //Assign two to the opposite group of one's
            } else if (ids[one] == one && ids[two] != two){
                ids[one] = ids[ids[two]]; //Assign one to the opposite group of two's
            } else if(ids[one] == ids[two]){
                return false;
            }
        }
        return true;
    }

}
