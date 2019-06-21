package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/21
 * LeetCode第 172 号问题：阶乘后的零
 */
public class TrailingZeroes {

    public static void test() {
        System.out.println(trailingZeroes(4));
        System.out.println(trailingZeroes(5));
        System.out.println(trailingZeroes(10));
        System.out.println(trailingZeroes(11));
    }

    private static int trailingZeroes(int n) {
        int count = 0;
        while (n != 0) {
            n /= 5;
            count += n;
        }
        return count;
    }
}
