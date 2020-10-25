package com.huang.leetcode;

/**
 * @Author : I325805
 * @Description: 主要是利用了二叉搜索树的特性，左子树都比根节点小，右子树都比根节点大，并且左子树、右子树都是二叉搜索树。
 */
public class LeetCode530 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int pre;
    int ans;

    public void dfs(TreeNode root) {
        if(root == null) {
            return ;
        }
        dfs(root.left);
        if (pre != -1) {
            // 当前节点与他的父节点作比较，当前节点可以是父节点的左子节点，也可以是父节点的右子节点。
            ans = Math.min(ans, root.val - pre);
        }
        pre = root.val;
        dfs(root.right);
    }

    public int getMinimumDifference(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);

        return ans;
    }
}
