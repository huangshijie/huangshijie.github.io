package com.huang.leetcode;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权,非商业转载请注明出处。
 *
 * 解题思路：
 * 很容易就联想到动态规划,并且很容易推到动态规划公式：f(n) = f(n-1) + f(n-2), 如果直接return climbStairs(n-1)+climbStairs(n-2),
 * 就会超时。
 *
 * 所以从这个动态规划转移公式入手：
 * f(n) = f(n-1) + f(n-2)
 * f(n-1) = f(n-2) + f(n-3)
 * f(n-2) = f(n-3) + f(n-4)
 * 从中发现规律,这是一个“滚动数组”,整个公式往”右移“
 *
 * 这也是斐波那契数列的解法。
 *
 * 还有可以考虑矩阵快速幂,
 */

public class LeetCode70 {
    public int climbStairs(int n) {
        int p = 0, q = 1, r = 0;
        for (int i = 0; i < n; i++) {
            p = q + r;
            r = q;
            q = p;
        }
        return p;
    }

    public static void main (String[] args) {
        LeetCode70 solution = new LeetCode70();

        System.out.println(solution.climbStairs(45));
    }
}
