package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/18
 * LeetCode 第 101 号问题：对称二叉树
 */
public class IsSymmetric {

    public static void test() {
        InorderTraversal.TreeNode node1 = new InorderTraversal.TreeNode(3);
        InorderTraversal.TreeNode node2 = new InorderTraversal.TreeNode(4);
        InorderTraversal.TreeNode node3 = new InorderTraversal.TreeNode(node1, node2, 2);

        InorderTraversal.TreeNode node4 = new InorderTraversal.TreeNode(4);
        InorderTraversal.TreeNode node5 = new InorderTraversal.TreeNode(3);
        InorderTraversal.TreeNode node6 = new InorderTraversal.TreeNode(node4, node5, 2);

        InorderTraversal.TreeNode node = new InorderTraversal.TreeNode(node3, node6, 1);
        boolean b = isSymmetric(node.left, node.right);
        System.out.println(b);
    }

    private static boolean isSymmetric(InorderTraversal.TreeNode n1, InorderTraversal.TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        return n1.data == n2.data && isSymmetric(n1.left, n2.right)
                && isSymmetric(n1.right, n2.left);
    }
}
