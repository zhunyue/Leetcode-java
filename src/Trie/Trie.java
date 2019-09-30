package Trie;

public class Trie {
        class TrieNode {
            TrieNode[] children;
            boolean isLeaf;

            public TrieNode(){
                children = new TrieNode[26];
            }
        }

        private TrieNode root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode tmp = root;
            for(int i = 0; i < word.length(); i++){
                int index = word.charAt(i) - 'a';
                if(tmp.children[index] == null){
                    tmp.children[index] = new TrieNode();
                }
                tmp = tmp.children[index];
            }
            tmp.isLeaf = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode node = searchWord(word);
            return node != null && node.isLeaf;
        }

        public TrieNode searchWord(String word){
            TrieNode tmp = root;
            for(int i = 0; i < word.length(); i++){
                int index = word.charAt(i) - 'a';
                if(tmp.children[index] == null){
                    return null;
                } else{
                    tmp = tmp.children[index];
                }
            }
            return tmp;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode node = searchWord(prefix);
            return node != null;
        }
}

