package Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*

380. Insert Delete GetRandom O(1)

    Design a data structure that supports all following operations in average O(1) time.

    insert(val): Inserts an item val to the set if not already present.
    remove(val): Removes an item val from the set if present.
    getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
    Example:

    // Init an empty set.
    RandomizedSet randomSet = new RandomizedSet();

    // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomSet.insert(1);

    // Returns false as 2 does not exist in the set.
    randomSet.remove(2);

    // Inserts 2 to the set, returns true. Set now contains [1,2].
    randomSet.insert(2);

    // getRandom should return either 1 or 2 randomly.
    randomSet.getRandom();

    // Removes 1 from the set, returns true. Set now contains [2].
    randomSet.remove(1);

    // 2 was already in the set, so return false.
    randomSet.insert(2);

    // Since 2 is the only number in the set, getRandom always return 2.
    randomSet.getRandom();
 */
public class InsertDeleteGetRandomO1 {

    //            Insert    Remove  Get
    // ArrayList  O(n)      O(n)    O(1)    average
    // HashMap    O(1)      O(1)    O(1)    average
    // Solution 1: Using ArrayList
    class RandomizedSet {
        ArrayList<Integer> list;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            list = new ArrayList<>();
        }

        public void print(){
            for(int i = 0; i < list.size(); i++){
                System.out.print(list.get(i) + ", ");
            }
            System.out.println("");
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            // print();
            if(list.contains(val)){
                return false;
            } else {
                list.add(val);
                return true;
            }


        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!list.contains(val)) {
                return false;
            } else {
                list.remove(list.indexOf(val));
                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            Random rand = new Random();
            int rand1 = rand.nextInt(list.size());
            return list.get(rand1);
        }
    }

    // Solution 2: HashMap
    class RandomizedSet2 {
        int[] arr;
        int index;
        HashMap<Integer, Integer> map = new HashMap<>();
        Random rand;
        /** Initialize your data structure here. */
        public RandomizedSet2() {
            arr = new int[10000];
            map = new HashMap<>();
            index = -1;
            rand = new Random();
        }


        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val)){
                return false;
            } else {
                arr[++index] = val;
                map.put(val, index);
                return true;
            }


        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val)) {
                return false;
            } else {
                int curId = map.get(val);
                map.remove(val);
                if(curId != index){
                    arr[curId] = arr[index];
                    map.put(arr[curId], curId);
                }
                index--;
                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            rand = new Random();
            int rand1 = rand.nextInt(map.size());
            return arr[rand1];
        }
    }
}
