package HashMap;

import java.util.*;

/*
    Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

    get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

    The cache is initialized with a positive capacity.

    Follow up:
    Could you do both operations in O(1) time complexity?

    Example:
            LRUCache cache = new LRUCache( 2 );
            cache.put(1, 1);
            cache.put(2, 2);
            cache.get(1);       // returns 1
            cache.put(3, 3);    // evicts key 2
            cache.get(2);       // returns -1 (not found)
            cache.put(4, 4);    // evicts key 1
            cache.get(1);       // returns -1 (not found)
            cache.get(3);       // returns 3
            cache.get(4);       // returns 4
 */
public class LRUCache {
    /*
        Solution 1: Using LinkedHashMap (we could specify order method, it is insert order in default, and we can set it to last access order)
                    - LinkedHashMap(int capacity, float fillRatio, boolean Order) if Order is true, it is last access order
     */
    class LRU {
        LinkedHashMap<Integer, Integer> map;
        int capacity;

        public LRU(int capacity) {
            this.capacity = capacity;
            this.map = new LinkedHashMap<>(capacity,1,true);  // set the order to last access order, filling ratio 1 and capacity
            /*
                 map = new LinkedHashMap<Integer,Integer>(capacity, 0.75f, true) {
                    @Override
                    protected boolean removeEldestEntry(Map.Entry eldest) {
                        return size() > capacity;
                    }
                };
             */
        }

        public int get(int key) {
            return map.get(key)==null ? -1 : map.get(key);  // since we are using last access order, LinkedHashMap will handle the order
            /*  return map.getOrDefault(key, -1); */
        }

        public void put(int key, int value) {
            map.put(key, value);
            if(map.size() > capacity){
                Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
                it.next();
                it.remove();
            }
            /* map.put(key, value); */
        }
    }

    /*
        Solution 2: Using hashmap for get and put operation and a LinkedList to maintain the order
            - get: if the element already exist, we remove it and add it to the node in the linked list (do not modify hashmap)
            - put: use get function to check whether the element exist
                    if not exist : return -1;
                    if exist: if the map reaches the maximum capacity, we remove the existing node from both hashmap and linkedlist first;
                              then we add the node to the linkedlist tail and the hashmap
     */
    class LRUCache2 {
        class node{
            int key = 0;
            int value = 0;
            node prev = null;
            node next = null;
            node(int k, int v){
                this.key = k;
                this.value = v;
            }
        }
        HashMap<Integer, node> map;
        int capacity;
        node head;
        node tail;
        public LRUCache2(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            head = new node(-1, -1);
            tail = new node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        private void removeNode(node n){
            node pre = n.prev;
            node next = n.next;
            pre.next = next;
            next.prev = pre;
            n.next = null;
            n.prev = null;
        }

        public void moveToTail(node n){
            node originalTail = tail.prev;
            n.next = tail;
            originalTail.next = n;
            n.prev = originalTail;
            tail.prev = n;
        }

        public int get(int key) {
            node n = map.getOrDefault(key, null);
            if(n == null) return -1;
            removeNode(n);
            moveToTail(n);
            return n.value;
        }

        public void put(int key, int value) {
            if(get(key) != -1){
                map.get(key).value = value;
                return;
            }
            if(map.size() == capacity){
                node headNode = head.next;
                removeNode(headNode);
                map.remove(headNode.key);
            }
            node insert = new node(key, value);
            map.put(key, insert);
            moveToTail(insert);
        }
    }

    // Solution 3: Using single List
    class Node{
        int key;
        int val;
        Node(int k, int v){
            this.key = k;
            this.val = v;
        }
    }

    List<Node> l = new ArrayList<>();
    int size;
    public LRUCache(int capacity) {
        size = capacity;
    }

    public void print(List<Node> l){
        for(int i = 0; i < l.size(); i++){
            System.out.print("(" + l.get(i).key + ", " + l.get(i).val + ")");
        }
        System.out.println(" ");
    }

    public int findIndex(List<Node> l, int key){
        for(int i = 0; i < l.size(); i++){
            if(l.get(i).key == key){
                return i;
            }
        }
        return -1;
    }

    public int get(int key) {
        // System.out.println("Get " + key);
        // print(l);
        int id = findIndex(l, key);
        if(id != -1){
            int val = l.get(id).val;
            Node n = new Node(key, val);
            l.remove(id);
            l.add(n);
            return val;
        } else{
            return -1;
        }
    }

    public void put(int key, int value) {
        // System.out.println("Put " + key + ", " + value);
        // System.out.println("before: ");
        // print(l);
        int idx = findIndex(l, key);
        Node n1 = new Node(key, value);
        if(idx != -1){
            l.remove(idx);
            l.add(n1);
        } else{
            if(l.size() < size){
                // Add directly
                l.add(n1);
            } else{
                //remove the first one
                l.remove(0);
                l.add(n1);
            }
        }

        // print(l);
    }
}
