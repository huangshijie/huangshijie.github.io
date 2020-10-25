package com.huang.datastruct.tree;

/**
 * @Author : I325805
 * @Description: 二叉搜索树、二叉排序树、二叉查找树
 * 若左子树不为空，左子树节点都比根节点小，并且左子树也是二叉搜索树
 * 若右子树不为空，右子树节点都比根节点小，并且右子树也是二叉搜索树
 * 中序遍历是一个上升数组
 */
public class BinarySearchTree extends BinaryTree{
    @Override
    public void insertTreeNode(int val) {

    }

    @Override
    public void deleteTreeNode(int val) {

    }

    @Override
    public void searchTreeNode(int val) {

    }

    public class TreeNode{
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode (int val) {
            this.val = val;
        }
    }
}
