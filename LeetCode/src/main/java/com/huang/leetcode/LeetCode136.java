package com.huang.leetcode;

public class LeetCode136 {
        public int singleNumber(int[] nums) {
            int single = 0;
            for (int num : nums) {
                single ^= num;
            }
            return single;
        }

    public static void main(String[] args) {
        int[] nums = {2,2,1};
        LeetCode136 solution = new LeetCode136();
        System.out.println(solution.singleNumber(nums));
    }
}
