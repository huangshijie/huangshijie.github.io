package com.huang.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : I325805
 * @description:
 */
public class LeetCode1207 {
    public boolean uniqueOccurrences(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<arr.length;i++) {
            Integer tmp = new Integer(arr[i]);
            if(map.keySet().contains(tmp)){
                Integer value = map.get(tmp);
                value++;
                map.put(tmp, value);
            }else{
                map.put(tmp, 0);
            }
        }

        Set<Integer> valueSet = new HashSet<>();
        for(Integer key : map.keySet()){
            if(valueSet.contains(map.get(key))) {
                return false;
            }else{
                valueSet.add(map.get(key));
            }
        }

        return true;
    }
}
