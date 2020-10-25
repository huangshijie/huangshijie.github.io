package com.huang.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : I325805
 * @Description:
 */
public class LeetCode925 {
    public static boolean isLongPressedName(String name, String typed) {

        // 记录每一位的char的值，以及这个char在前后出现的次数
        // 只有每一位在name和typed中出现的顺序保持一致
        // 并且typed的个数大于name中同一位置、同一值
        List<Character> nameChar = new ArrayList<>();
        int[] nameIndex = new int[name.length()];

        int i=0;
        char cur = name.charAt(0);
        nameIndex[0] = 0;
        int j=0;
        for(;i<name.length();i++){
            if(cur == name.charAt(i)) {
                nameIndex[j]++;
            } else {
                j++;
                nameChar.add(name.charAt(i));
                nameIndex[j] = 1;
                cur = name.charAt(i);
            }
        }

        List<Character> typedChar = new ArrayList<>();
        int[] typedIndex = new int[typed.length()];

        i = 0;
        j = 0;
        cur = typed.charAt(0);
        typedIndex[0]=0;
        for(;i<typed.length();i++){
            if(cur == typed.charAt(i)){
                typedIndex[j]++;
            }else{
                j++;
                typedChar.add(typed.charAt(i));
                cur = typed.charAt(i);
                typedIndex[j]=1;
            }
        }

        if(nameChar.size()!=typedChar.size()) {
            return false;
        }

        for(i=0;i<nameChar.size();i++){
            if(nameChar.get(i)!=typedChar.get(i)||nameIndex[i]>typedIndex[i]){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String name = "alex";
        String typed = "aalexxr";
        System.out.println(isLongPressedName(name, typed));
    }
}
