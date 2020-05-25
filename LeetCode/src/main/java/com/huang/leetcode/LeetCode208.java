package com.huang.leetcode;

public class LeetCode208 {
     class Trie {

        private boolean end;
        private Trie[] nexts;
        private String value;

        /** Initialize your data structure here. */
        public Trie(boolean end, Trie[] nexts, String value) {
            this.end = end;
            this.nexts = nexts;
            this.value = value;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {

            if(word.length() == 1) {
                this.end = true;
            }

            if(word.length() > 0) {
                char c = word.charAt(0);
                if (this.nexts[c - 'a'] == null) {
                    this.nexts[c - 'a'] = new Trie(false, null, String.valueOf(c) ) ;
                }
                this.nexts[c - 'a'].insert(word.substring(1, word.length()));
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return true;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return true;
        }
    }
}
