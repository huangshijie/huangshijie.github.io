package com.huang.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解题思路
 * 1. 构造前缀树，然后遍历前缀树，找到最后一个和数组大小一样的节点
 *
 * 2. 遍历String数组，从每个
 *
 */

public class LeetCode14 {

    // 方法一：纵向扫描
    public String longestCommonPrefix(String[] strs) {
        String result = "";

        if(strs.length == 0) {
            return "";
        }

        if(strs.length == 1) {
            return strs[0];
        }

        for(int i = 0; i <= strs[0].length(); i++) {
            for(int j = 0; j < strs.length; j++) {
                if(i > strs[j].length()) {
                    return result;
                }

                if(!strs[0].substring(0, i).equals(strs[j].substring(0, i))) {
                    return result;
                }
            }
            result = strs[0].substring(0, i);
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode14 solution = new LeetCode14();
        String[] strs = {"c","c"};
        System.out.println(solution.longestCommonPrefix(strs));

        System.out.println("c".substring(0,1));
    }
}
