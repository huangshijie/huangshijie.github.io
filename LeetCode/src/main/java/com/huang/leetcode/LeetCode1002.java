package com.huang.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author : I325805
 * @Description:
 */
public class LeetCode1002 {
    public List<String> commonChars(String[] A) {
        List<String> result = new ArrayList<>();
        int[] minfreq = new int[26];
        Arrays.fill(minfreq, Integer.MAX_VALUE);
        for(String word : A) {
            int[] freq = new int[26];
            for(int i = 0; i < word.length(); i++) {
                char a = word.charAt(i);
                freq[a-'a']++;
            }
            for (int i = 0; i < 26; ++i) {
                minfreq[i] = Math.min(minfreq[i], freq[i]);
            }
        }

        for(int i =0; i <26;i++) {
            for(int j = 0; j < minfreq[i];j++){
                result.add(String.valueOf((char)i+'a'));
            }
        }

        return result;
    }
}
