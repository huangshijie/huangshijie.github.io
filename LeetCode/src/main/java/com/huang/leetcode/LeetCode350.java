package com.huang.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 */

public class LeetCode350 {

//    public int[] intersect(int[] nums1, int[] nums2) {
//        HashMap<Integer, Integer> nums1Map = new HashMap<>();
//        HashMap<Integer, Integer> nums2Map = new HashMap<>();
//
//        for(int i = 0; i<nums1.length;i++) {
//            Integer key = new Integer(nums1[i]);
//            if (!nums1Map.containsKey(key)) {
//                nums1Map.put(key, 1);
//            } else {
//               int count = nums1Map.get(key);
//                count++;
//               nums1Map.put(key, count);
//            }
//        }
//        for(int i = 0; i<nums2.length;i++) {
//            Integer key = new Integer(nums2[i]);
//            if (!nums2Map.containsKey(key)) {
//                nums2Map.put(key, 1);
//            } else {
//                int count = nums2Map.get(key);
//                count++;
//                nums2Map.put(key, count);
//            }
//        }
//        int length = nums1.length<nums2.length?nums1.length:nums2.length;
//        ArrayList<Integer> tmp = new ArrayList<>();
//
//        for(Integer key : nums1Map.keySet()) {
//            if(nums2Map.containsKey(key)) {
//                int min = nums1Map.get(key) < nums2Map.get(key)? nums1Map.get(key) : nums2Map.get(key);
//                for(int m = 0; m<min;m++) {
//                    tmp.add(key);
//                }
//            }
//        }
//        int[] result = new int[tmp.size()];
//        int j = 0;
//        for(Integer value:tmp) {
//            result[j] = value;
//            j++;
//        }
//        return result;
//    }

    public int[] intersect(int[] nums1, int[] nums2) {


        HashMap<Integer, Integer> record = new HashMap<>();

        return null;

    }

    public static void main(String[] args) {
        LeetCode350 solution = new LeetCode350();
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};


        System.out.println(solution.intersect(nums1, nums2));
    }
}
