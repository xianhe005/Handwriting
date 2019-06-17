package com.hxh.handwriting.leetcode;

import java.util.Stack;

/**
 * Created by HXH at 2019/6/17
 * LeetCode 第 20 号问题：有效的括号
 */
public class BracketsValid {

    public static void test() {
        System.out.println(bracketsValid("()"));
        System.out.println(bracketsValid("()[]{}"));
        System.out.println(bracketsValid("(]"));
        System.out.println(bracketsValid("([)]"));
        System.out.println(bracketsValid("{[]}"));
    }

    private static boolean bracketsValid(String s) {
        // 思想：栈
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {//为空直接压栈
                stack.push(c);
            } else {
                // 40:'(' 41:')' 91:'[' 93:']' 123:'{' 125:'}'
                if (stack.peek() - c == -1 || stack.peek() - c == -2) {
                    stack.pop(); // 匹配就出栈
                } else {
                    stack.push(c); // 没匹配就压栈
                }
            }
        }
        // 全部匹配,栈会被弹完为空
        return stack.isEmpty();
    }
}
