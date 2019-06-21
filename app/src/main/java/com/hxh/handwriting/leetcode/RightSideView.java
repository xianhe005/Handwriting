package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by HXH at 2019/6/21
 * LeetCode 第 199 号问题：二叉树的右视图
 */
public class RightSideView {

    public static void test() {

        // 右视图
        //    1           <---
        //   / \
        //  2   3         <---
        //  \    \
        //   5    4       <---
        //  /
        // 6              <---
        // [1, 3, 4, 6]
        InorderTraversal.TreeNode node6 = new InorderTraversal.TreeNode(6);
        InorderTraversal.TreeNode node5 = new InorderTraversal.TreeNode(node6, null, 5);
        InorderTraversal.TreeNode node2 = new InorderTraversal.TreeNode(null, node5, 2);

        InorderTraversal.TreeNode node4 = new InorderTraversal.TreeNode(4);
        InorderTraversal.TreeNode node3 = new InorderTraversal.TreeNode(null, node4, 3);

        InorderTraversal.TreeNode root = new InorderTraversal.TreeNode(node2, node3, 1);
        System.out.println(rightSideView(root));
        System.out.println(rightSideView2(root));
    }

    /**
     * 队列实现
     */
    private static List<Integer> rightSideView(InorderTraversal.TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null)
            return ret;
        Queue<InorderTraversal.TreeNode> q = new LinkedList<>();
        q.add(root);
        // 层次遍历，只需要记录本层结点个数即可
        int curNum = 1;
        // 下一层的节点数
        int nextNum = 0;
        while (!q.isEmpty()) {
            InorderTraversal.TreeNode node = q.poll();
            if (curNum == 1)
                ret.add(node.data);
            curNum--;
            if (node.left != null) {
                q.offer(node.left);
                nextNum++;
            }
            if (node.right != null) {
                q.offer(node.right);
                nextNum++;
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
            }
        }
        return ret;
    }

    /**
     * 递归实现
     */
    private static List<Integer> rightSideView2(InorderTraversal.TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        rightSideView(root, 0, ret);
        return ret;
    }

    private static void rightSideView(InorderTraversal.TreeNode root,
                              int level, List<Integer> ret) {
        if (root == null)
            return;
        // 当等于size的时候说明是最右侧结点，直接加入即可
        if (ret.size() == level)
            ret.add(root.data);

        rightSideView(root.right, level + 1, ret);
        rightSideView(root.left, level + 1, ret);
    }
}
