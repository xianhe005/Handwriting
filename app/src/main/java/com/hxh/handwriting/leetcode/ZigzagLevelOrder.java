package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by HXH at 2019/6/18
 * LeetCode 第 103 号问题：二叉树的锯齿形层次遍历
 */
public class ZigzagLevelOrder {

    public static void test() {
        InorderTraversal.TreeNode node1 = new InorderTraversal.TreeNode(15);
        InorderTraversal.TreeNode node2 = new InorderTraversal.TreeNode(7);
        InorderTraversal.TreeNode node3 = new InorderTraversal.TreeNode(node1, node2, 20);
        InorderTraversal.TreeNode node4 = new InorderTraversal.TreeNode(9);
        InorderTraversal.TreeNode node = new InorderTraversal.TreeNode(node4, node3, 3);
        List<List<Integer>> lists =new ArrayList<>();
        levelOrder(node, lists);
        System.out.println(lists);
    }

    // 使用队列及层级对应的个数，巧妙
    private static void levelOrder(InorderTraversal.TreeNode node, List<List<Integer>> lists) {
        Queue<InorderTraversal.TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int curCount = 1;
        int nextCount = 0;
        boolean fromRight = true;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            InorderTraversal.TreeNode poll = queue.poll();
            list.add(poll.data);
            curCount--;

            if (fromRight) {
                if (poll.right != null) {
                    queue.offer(poll.right);
                    nextCount++;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                    nextCount++;
                }
            } else {
                if (poll.left != null) {
                    queue.offer(poll.left);
                    nextCount++;
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                    nextCount++;
                }
            }

            if (curCount == 0) {
                lists.add(list);
                curCount = nextCount;
                nextCount = 0;
                fromRight = !fromRight;
                list = new ArrayList<>();
            }
        }
    }
}
