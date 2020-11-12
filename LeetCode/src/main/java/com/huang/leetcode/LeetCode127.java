package com.huang.leetcode;

import java.util.*;

/**
 * @author : I325805
 * @description:
 */
public class LeetCode127 {
    public boolean getOnlyOneChartMismatched(String beginWord, String endWord) {
        if(beginWord.length() != endWord.length()){
            return false;
        }
        char[] beginWordCharList = beginWord.toCharArray();
        char[] endWordCharList = endWord.toCharArray();
        boolean isDifferenceShowOnce = false;

        for(int i=0; i<beginWordCharList.length;i++) {
            if(beginWordCharList[i] != endWordCharList[i]) {
                if(!isDifferenceShowOnce){
                    isDifferenceShowOnce = true;
                } else {
                    return false;
                }
            }
        }

        return isDifferenceShowOnce;
    }

    public int ladderLengthHelper(String beginWord, String endWord, List<String> wordList) {
        if (wordList.isEmpty()) {
            return 0;
        }
        if (wordList.size() == 1) {
            return getOnlyOneChartMismatched(beginWord, wordList.get(0)) ? 1 : 0;
        }
        Map<String, ArrayList<String>> matchedWordList = new HashMap();
        for (String word : wordList) {
            if (getOnlyOneChartMismatched(word, endWord)) {
                ArrayList<String> copyList = new ArrayList<>();
                copyList.addAll(wordList);
                copyList.remove(word);
                matchedWordList.put(word, copyList);
            }
        }
        int result = 0;
        for (String key : matchedWordList.keySet()) {
            result += ladderLengthHelper(beginWord, key, matchedWordList.get(key));
        }
        return result > 0 ? 1 : 0;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        return ladderLengthHelper(beginWord, endWord, wordList);
    }
}
