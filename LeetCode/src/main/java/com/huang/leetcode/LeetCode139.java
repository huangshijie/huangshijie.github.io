package com.huang.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode139 {

    public boolean wordBreak(String s, List<String> wordDict) {

        if(s.length() == 0) {
            return true;
        }

        boolean result = false;
        for(String word : wordDict) {
            int length = word.length();
            if (length <= s.length()) {
                String tmp = s.substring(0, length);
                if(tmp.equals(word)) {
                    result = wordBreak(s.substring(length), wordDict);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");

        String s = "leetcode";

        LeetCode139 solution = new LeetCode139();
        System.out.println(solution.wordBreak(s, wordDict));
    }

}
