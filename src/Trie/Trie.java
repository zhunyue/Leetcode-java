package Trie;

public class Trie {
    class TreeNode {
        TreeNode[] children;
        boolean isLeaf;
        TreeNode(){
            this.children = new TreeNode[26];
            this.isLeaf = false;
        }
    }
    TreeNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode t = root;
        for(int i = 0; i < word.length(); i++){
            int id = word.charAt(i) - 'a';
            if(t.children[id] == null){
                t.children[id] = new TreeNode();
            }
            t = t.children[id];
        }
        t.isLeaf = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode t = root;
        for(int i = 0; i < word.length(); i++){
            int id = word.charAt(i) - 'a';
            if(t.children[id] == null) return false;
            t = t.children[id];
        }
        return t.isLeaf;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode t = root;
        for(int i = 0; i < prefix.length(); i++){
            int id = prefix.charAt(i) - 'a';
            if(t.children[id] == null) return false;
            t = t.children[id];
        }
        return true;
    }
}

