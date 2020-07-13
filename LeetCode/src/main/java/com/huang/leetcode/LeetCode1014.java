package com.huang.leetcode;

/**
* 这道题的关键还是在于，i<j这个限制上，A[i]+A[j]+i-j，很容易就能想到将相同下标的归到一起，(A[i]+i)+(A[j]-j)
* 那要求(A[i]+i)+(A[j]-j)的最大值，就是相当于求在j景点上，他前面[0, j-1]中间哪个i景点中A[i]+i是最大的。
* 正好可以遍历的时候，求前i个值中的最大值，一起做了
**/

public class LeetCode1014 {
    public int maxScoreSightseeingPair(int[] A) {
        int ans = 0, mx = A[0] + 0;
        for (int j = 1; j < A.length; ++j) {
            ans = Math.max(ans, mx + A[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, A[j] + j);
        }
        return ans;
    }
}
