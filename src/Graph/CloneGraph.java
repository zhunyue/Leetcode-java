package Graph;

/*
133. Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
        public Node(int _val) {
            val = _val;
        }
    };
    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        if(map.containsKey(node))
            return map.get(node);
        Node newNode = new Node(node.val);
        map.put(node, newNode);
        newNode.neighbors = new ArrayList<>();
        for(Node n : node.neighbors){
            newNode.neighbors.add(cloneGraph(n));
        }
        return newNode;
    }
}
