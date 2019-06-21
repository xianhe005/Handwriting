package com.hxh.handwriting.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by HXH at 2019/6/21
 * LeetCode 第 150 号问题：逆波兰表达式求值
 */
public class EvalRPN {
    public static void test() {
        System.out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
        //System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+", "+"}));
    }

    /**
     * 用数据结构栈来解决这个问题。
     *
     * 从前往后遍历数组
     * 遇到数字则压入栈中
     * 遇到符号，则把栈顶的两个数字拿出来运算，把结果再压入栈中
     * 遍历完整个数组，栈顶数字即为最终答案
     */
    private static int evalRPN(String[] arr) {
        List<String> operators = Arrays.asList("+", "-", "*", "/");
        if (arr.length < 3) {
            throw new IllegalArgumentException("error 1");
        }
        String str = arr[arr.length - 1];
        if (!operators.contains(str)) {
            throw new IllegalArgumentException("error 2");
        }
        Stack<String> stack = new Stack<>();
        stack.push(arr[0]);
        stack.push(arr[1]);
        for (int i = 2; i < arr.length; i++) {
            if (operators.contains(arr[i])) {
                int v1 = Integer.parseInt(stack.pop());
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("error 4");
                }
                int v2 = Integer.parseInt(stack.pop());
                if (arr[i].equals(operators.get(0))) {// +
                    stack.push(String.valueOf(v2 + v1));
                } else if (arr[i].equals(operators.get(1))) {// -
                    stack.push(String.valueOf(v2 - v1));
                } else if (arr[i].equals(operators.get(2))) {// *
                    stack.push(String.valueOf(v2 * v1));
                } else if (arr[i].equals(operators.get(3))) {// /
                    stack.push(String.valueOf(v2 / v1));
                }
            } else {
                stack.push(arr[i]);
            }
        }
        String val = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("error 3");
        }
        return Integer.parseInt(val);
    }
}
