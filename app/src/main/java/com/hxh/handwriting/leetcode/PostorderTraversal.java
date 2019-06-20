package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by HXH at 2019/6/20
 * LeetCode 第 145 号问题：二叉树的后序遍历
 */
public class PostorderTraversal {

    public static void test() {
        InorderTraversal.TreeNode node5 = new InorderTraversal.TreeNode(5);
        InorderTraversal.TreeNode node4 = new InorderTraversal.TreeNode(4);
        InorderTraversal.TreeNode node3 = new InorderTraversal.TreeNode(node4, node5, 3);
        InorderTraversal.TreeNode node2 = new InorderTraversal.TreeNode(node3, null, 2);
        InorderTraversal.TreeNode node1 = new InorderTraversal.TreeNode(null, node2, 1);
        List<Integer> list = postorderTraversal(node1);
        System.out.println(list);
    }

    private static List<Integer> postorderTraversal(InorderTraversal.TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
        Stack<InorderTraversal.TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            InorderTraversal.TreeNode node = stack.pop();
            if(node.left != null) stack.push(node.left);//和传统先序遍历不一样，先将左结点入栈
            if(node.right != null) stack.push(node.right);//后将右结点入栈
            res.add(0,node.data);                        //逆序添加结点值
        }
        return res;
    }
}
