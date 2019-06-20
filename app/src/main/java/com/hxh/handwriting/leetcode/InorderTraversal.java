package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HXH at 2019/6/18
 * LeetCode 第 94 号问题：二叉树的中序遍历
 */
public class InorderTraversal {

    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int data;

        TreeNode(int data) {
            this.data = data;
        }

        TreeNode(TreeNode left, TreeNode right, int data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }
    }

    public static void test() {
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(node4, node5, 3);
        TreeNode node2 = new TreeNode(node3, null, 2);
        TreeNode node1 = new TreeNode(null, node2, 1);
        List<Integer> list = new ArrayList<>();
        inorderTraversal(node1, list);
        System.out.println(list);
    }

    // 递归实现很巧妙
    private static void inorderTraversal(TreeNode root, List<Integer> res) {
        if (root.left != null) inorderTraversal(root.left, res);
        res.add(root.data);
        if (root.right != null) inorderTraversal(root.right, res);
    }
}
