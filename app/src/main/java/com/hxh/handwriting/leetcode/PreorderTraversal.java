package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by HXH at 2019/6/20
 * LeetCode 第 144 号问题：二叉树的前序遍历
 */
public class PreorderTraversal {

    public static void test() {
        InorderTraversal.TreeNode node5 = new InorderTraversal.TreeNode(5);
        InorderTraversal.TreeNode node4 = new InorderTraversal.TreeNode(4);
        InorderTraversal.TreeNode node3 = new InorderTraversal.TreeNode(node4, node5, 3);
        InorderTraversal.TreeNode node2 = new InorderTraversal.TreeNode(node3, null, 2);
        InorderTraversal.TreeNode node1 = new InorderTraversal.TreeNode(null, node2, 1);
        List<Integer> list = preorderTraversal(node1);
        System.out.println(list);
    }

    private static List<Integer> preorderTraversal(InorderTraversal.TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        //非递归前序遍历，需要借助栈
        Stack<InorderTraversal.TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            InorderTraversal.TreeNode temp = stack.pop();
            list.add(temp.data);
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        return list;
    }
}
