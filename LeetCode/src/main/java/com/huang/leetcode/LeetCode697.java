package com.huang.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/degree-of-an-array/
 */
public class LeetCode697 {

    public static int findShortestSubArray(int[] nums) {

        if(nums == null || nums.length == 0) {
            return 0;
        }

        Map<String, ArrayList<Integer>> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int tmpIndex = nums[i];
            if(map.get(String.valueOf(tmpIndex)) == null){
                ArrayList<Integer> tmpList = new ArrayList<>();
                tmpList.add(i);
                map.put(String.valueOf(tmpIndex), tmpList);
            } else {
                ArrayList<Integer> tmpList = map.get(String.valueOf(tmpIndex)) ;
                tmpList.add(i);
                map.put(String.valueOf(tmpIndex), tmpList);
            }
        }

        int maxDepth = 1;
        int minSubStringLength = nums.length;
        for(Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
            ArrayList<Integer> tmpList = entry.getValue();

            if(tmpList.size() > maxDepth) {
                maxDepth = tmpList.size();
                minSubStringLength = tmpList.get(tmpList.size() - 1) - tmpList.get(0) + 1;
            }

            if(tmpList.size() == maxDepth) {
                maxDepth = tmpList.size();
                int tmpMin = tmpList.get(tmpList.size() - 1) - tmpList.get(0) + 1;
                if (minSubStringLength > tmpMin) {
                    minSubStringLength = tmpMin;
                }
            }
        }

        if(maxDepth == 1) {
            return nums.length;
        }

        return minSubStringLength;
    }

    public static void main (String[] args) {
        int[] nums = {1,2,2,3,1,4,2};

        System.out.println(findShortestSubArray(nums));
    }

}
