package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/19
 * LeetCode 第 110 号问题：平衡二叉树
 */
public class BalancedNodeTree {

    private static boolean isBalanced = true;

    public static void test() {
        InorderTraversal.TreeNode node1 = new InorderTraversal.TreeNode(15);
        InorderTraversal.TreeNode node2 = new InorderTraversal.TreeNode(7);
        InorderTraversal.TreeNode node3 = new InorderTraversal.TreeNode(node1, node2, 20);
        InorderTraversal.TreeNode node4 = new InorderTraversal.TreeNode(9);
        InorderTraversal.TreeNode node = new InorderTraversal.TreeNode(node4, node3, 3);
        InorderTraversal.TreeNode node_ = new InorderTraversal.TreeNode(null, node3, 3);

        isBalanced(node);
        System.out.println(isBalanced);
        System.out.println("======================");
        isBalanced = true;
        isBalanced(node_);
        System.out.println(isBalanced);
    }

    private static void isBalanced(InorderTraversal.TreeNode root) {
        getDepth(root);
    }

    private static int getDepth(InorderTraversal.TreeNode root) {
        if (root == null)
            return 0;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
            return -1;
        }
        return right > left ? right + 1 : left + 1;
    }
}
