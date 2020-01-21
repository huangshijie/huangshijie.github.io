package com.huang.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/brick-wall/
 */

public class LeetCode554 {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> result = new HashMap<>();

        for (List<Integer> row : wall) {
            int tmpSum = 0;
            for(int i = 0; i<row.size()-1; i++) {
                tmpSum += row.get(i);

                if(result.get(tmpSum) == null) {
                    result.put(tmpSum, 1);
                } else {
                    int t = result.get(tmpSum);
                    result.put(tmpSum, t++);
                }
            }
        }

        int least = wall.size();

        for(Map.Entry<Integer, Integer> entry : result.entrySet()) {
            least = entry.getValue() != null && entry.getValue() < least ? entry.getValue():least;
        }
        return least;
    }
}
