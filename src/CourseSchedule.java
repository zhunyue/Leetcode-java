import java.util.*;

/*
    There are a total of n courses you have to take, labeled from 0 to n-1.

    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

    Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

    Example 1:

    Input: 2, [[1,0]]
    Output: true
    Explanation: There are a total of 2 courses to take.
                 To take course 1 you should have finished course 0. So it is possible.
    Example 2:

    Input: 2, [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take.
                 To take course 1 you should have finished course 0, and to take course 0 you should
                 also have finished course 1. So it is impossible.
 */
public class CourseSchedule {
    /*
        DFS methods using visited and recursive array
        visited: record the nodes which has already visited
        recursive: record the nodes which is visited in an recursion
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] arr = new ArrayList[numCourses];
        int[] visited = new int[numCourses]; // 0 not visited; 1 cyclic; 2 non-cyclic
        for(int i = 0; i < numCourses; i++){
            arr[i] = new ArrayList<>();
        }
        for(int i = 0; i < prerequisites.length; i++){
            int[] pair = prerequisites[i];
            arr[pair[1]].add(pair[0]);
        }
        for(int i = 0; i < numCourses; i++){
            if(hasCycle(i, arr, visited)){
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(int i, ArrayList[] arr, int[] visited){
        if(visited[i] == 1) return true;
        if(visited[i] == 2) return false;
        ArrayList<Integer> list = arr[i];
        visited[i] = 1;
        for(int j = 0; j < list.size(); j++){
            int id = list.get(j);
            if(hasCycle(id, arr, visited)){
                return true;
            }
        }
        visited[i] = 2;
        return false;
    }

    /*
          BFS, Topological-sort
          key: store the graph in a hashmap and calculate indegree
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses];

        //Create the map from prerequisites to the courses and calculate indegree for each node
        for(int i = 0; i < prerequisites.length; i++){
            int key = prerequisites[i][1];
            int val = prerequisites[i][0];
            indegree[val]++;
            List<Integer> list = map.containsKey(key) ? map.get(key) : new ArrayList<>();
            list.add(val);
            map.put(key, list);
        }

        //queue to record nodes with indegree equals 0
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        //Calculate number of nodes with zero degree
        int count = 0;
        while(!queue.isEmpty()){
            int key = queue.poll();
            count ++;
            List<Integer> children = map.get(key);
            if(children != null){
                for(int i = 0; i < children.size(); i++){
                    int index = children.get(i);
                    indegree[index] --;
                    if(indegree[index] == 0)
                        queue.offer(index);
                }
            }

        }
        return count == numCourses;
    }
}
