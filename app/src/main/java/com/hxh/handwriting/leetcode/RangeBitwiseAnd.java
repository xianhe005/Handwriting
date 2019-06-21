package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/21
 * LeetCode 第 201 号问题：数字范围按位与
 */
public class RangeBitwiseAnd {

    public static void test() {
        long c = System.currentTimeMillis();
        System.out.println(rangeBitwiseAnd(5,7));
        System.out.println(rangeBitwiseAnd(0,1));
        System.out.println(rangeBitwiseAnd(26, 30));
        System.out.println(rangeBitwiseAnd(2, 1234567891));
        System.out.println(System.currentTimeMillis() - c + "ms");
        System.out.println("=============================================");
        c = System.currentTimeMillis();
        System.out.println(rangeBitwiseAnd2(5,7));
        System.out.println(rangeBitwiseAnd2(0,1));
        System.out.println(rangeBitwiseAnd2(26, 30));
        System.out.println(rangeBitwiseAnd2(2, 1234567891));
        System.out.println(System.currentTimeMillis() - c + "ms");
    }

    /**
     * 原始方法,低效
     */
    private static int rangeBitwiseAnd2(int m, int n) {
        int d = m;
        while (m <= n) {
            d &= m;
            m++;
        }
        return d;
    }

    /**
     * 高效方法
     */
    private static int rangeBitwiseAnd(int m, int n) {
        int d = Integer.MAX_VALUE;
        while ((m & d) != (n & d)) {
            d <<= 1;
        }
        return m & d;
    }
}
