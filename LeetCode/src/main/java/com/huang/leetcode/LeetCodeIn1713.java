package com.huang.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LeetCodeIn1713 {

    public static void main(String[] agrs) {
        String[] dictionary = {"looked","just","like","her","brother"};
        String sentence = "jesslookedjustliketimherbrotherbrothera";
        LeetCodeIn1713 solution = new LeetCodeIn1713();
        System.out.println(solution.respace(dictionary, sentence));
    }

    public int respace(String[] dictionary, String sentence) {

        if(sentence == null) {
            return 0;
        }
        int n = sentence.length();

        if(n == 0) {
            return 0;
        }

        Trie root = new Trie();
        for(String word:dictionary) {
            root.insert(word);
        }

        int[] dp = new int[n+1];
        Arrays.fill(dp, 0);
        for(int i=1; i<=n;i++) {
            for(int j = i;j>0;j--){
                String word = sentence.substring(j, i);
                if(root.isWord(word)) {
                    dp[i] = dp[i-word.length()] + 1;
                    break;
                }
            }
            if(dp[i]==0) {
                dp[i] = dp[i-1];
            }
        }
        return dp[n];
    }

    class Trie {
        public Trie[] nexts;
        public boolean isEnd;

        public Trie() {
            nexts = new Trie[26];
            isEnd = false;
        }

        // 倒叙插入,为了判断当前字符是否
        public void insert(String word) {
            Trie root = this;
            for (int i = 0; i < word.length(); ++i) {
                int index = word.charAt(i) - 'a';
                if (root.nexts[index] == null) {
                    root.nexts[index] = new Trie();
                }
                root = root.nexts[index];
            }
            root.isEnd = true;
        }

        public boolean isWord(String word) {
            Trie root = this;
            for (int i = 0; i < word.length(); ++i) {
                int index = word.charAt(i) - 'a';
                if (root.nexts[index] == null) {
                    return false;
                } else {
                    root = root.nexts[index];
                }
            }
            if(root.isEnd){
                return true;
            } else {
                return false;
            }
        }
    }
}
