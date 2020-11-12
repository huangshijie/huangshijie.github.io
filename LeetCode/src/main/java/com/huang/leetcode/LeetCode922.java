package com.huang.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: I325805
 * @description:
 */
public class LeetCode922 {
    public int[] sortArrayByParityII(int[] A) {
        List<Integer> list0 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();

        for (int i = 0; i < A.length; i++) {
            if (i % 2 == 0 && A[i] % 2 != 0) {
                list0.add(i);
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (i % 2 != 0 && A[i] % 2 == 0) {
                list1.add(i);
            }
        }

        if (list0.size() == 0) {
            return A;
        }

        for (int i = 0; i < list0.size(); i++) {
            int tmp = A[list0.get(i)];
            A[list0.get(i)] = A[list1.get(i)];
            A[list1.get(i)] = tmp;
        }

        return A;
    }

    public static void main(String[] args) {
        int[] A = {4,2,5,7};

        LeetCode922 solution = new LeetCode922();
        System.out.println(solution.sortArrayByParityII(A).toString());
    }
}
