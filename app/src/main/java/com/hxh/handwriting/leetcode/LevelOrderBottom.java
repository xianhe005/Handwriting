package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by HXH at 2019/6/18
 * LeetCode 第 107 号问题：二叉树的层次遍历 II
 */
public class LevelOrderBottom {

    public static void test() {
        InorderTraversal.TreeNode node1 = new InorderTraversal.TreeNode(15);
        InorderTraversal.TreeNode node2 = new InorderTraversal.TreeNode(7);
        InorderTraversal.TreeNode node3 = new InorderTraversal.TreeNode(node1, node2, 20);
        InorderTraversal.TreeNode node4 = new InorderTraversal.TreeNode(9);
        InorderTraversal.TreeNode node = new InorderTraversal.TreeNode(node4, node3, 3);
        List<List<Integer>> lists = levelOrder(node);
        System.out.println(lists);
        System.out.println("=============================");
        lists = new ArrayList<>();
        levelOrder(node, lists);
        System.out.println(lists);
    }

    private static List<List<Integer>> levelOrder(final InorderTraversal.TreeNode node) {
        List<List<Integer>> lists = new ArrayList<>();
        List<InorderTraversal.TreeNode> list = new ArrayList<InorderTraversal.TreeNode>() {{
            add(node);
        }};
        while (!list.isEmpty()) {
            List<Integer> integers = new ArrayList<>();
            List<InorderTraversal.TreeNode> nList = new ArrayList<>();
            for (InorderTraversal.TreeNode n : list) {
                integers.add(n.data);
                if (n.left != null) {
                    nList.add(n.left);
                }
                if (n.right != null) {
                    nList.add(n.right);
                }
            }
            lists.add(0, integers);
            list = nList;
        }
        return lists;
    }

    // 使用队列及层级对应的个数，巧妙
    private static void levelOrder(InorderTraversal.TreeNode node, List<List<Integer>> lists) {
        Queue<InorderTraversal.TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int curCount = 1;
        int nextCount = 0;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            InorderTraversal.TreeNode poll = queue.poll();
            list.add(poll.data);
            curCount--;

            if (poll.left != null) {
                queue.offer(poll.left);
                nextCount++;
            }
            if (poll.right != null) {
                queue.offer(poll.right);
                nextCount++;
            }

            if (curCount == 0) {
                lists.add(0, list);
                curCount = nextCount;
                nextCount = 0;
                list = new ArrayList<>();
            }
        }
    }
}
