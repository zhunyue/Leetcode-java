package Graph;

import java.util.*;

/*
    There are a total of n courses you have to take, labeled from 0 to n-1.

    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

    Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

    There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

    Example 1:

    Input: 2, [[1,0]]
    Output: [0,1]
    Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
                 course 0. So the correct course order is [0,1] .
    Example 2:

    Input: 4, [[1,0],[2,0],[3,1],[3,2]]
    Output: [0,1,2,3] or [0,2,1,3]
    Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
                 courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
                 So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
    Note:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
 */

public class CourseSchedule2 {
    /*
       Similar to Course Schedule problem, but we have to record the order when judging whether there is a circle
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        for(int i = 0; i < prerequisites.length; i++){
            int key = prerequisites[i][1];
            int val = prerequisites[i][0];
            indegree[val]++;
            List<Integer> list = map.containsKey(key) ? map.get(key) : new ArrayList<>();
            list.add(val);
            map.put(key, list);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i =  0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        int i = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            topologicalOrder[i++] = node;
            List<Integer> children = map.get(node);
            if(children != null){
                for(int j = 0; j < children.size(); j++){
                    int index = children.get(j);
                    indegree[index]--;
                    if(indegree[index] == 0){
                        queue.offer(index);
                    }
                }
            }
        }

        if(i == numCourses){
            return topologicalOrder;
        }
        return new int[0];
    }

    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        int[] visited = new int[numCourses];
        int[] recursive = new int[numCourses];
        List[] graph = new List[numCourses];
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < prerequisites.length; i++){
            int key = prerequisites[i][1];
            int val = prerequisites[i][0];
            graph[key].add(val);
        }

        for(int i = 0; i < numCourses; i++){
            if(visited[i] == 0 && isCycle(visited, recursive, graph, i, res)){
                return new int[0];
            }
        }

        int[] ans = new int[res.size()];
        for(int i = 0; i < ans.length; i++){
            ans[i] = res.get(i);
        }
        return ans;
    }

    public boolean isCycle(int[] visited, int[] recursive, List[] graph, int index, List<Integer> res){
        visited[index] = 1;
        recursive[index] = 1;
        List<Integer> children = graph[index];
        for(int i = 0; i < children.size(); i++){
            int vertex = children.get(i);
            if(recursive[vertex] == 1 || (visited[vertex] == 0 && isCycle(visited, recursive, graph, vertex, res))){
                return true;
            }
        }
        res.add(0, index);
        recursive[index] = 0;
        return false;

    }
}
