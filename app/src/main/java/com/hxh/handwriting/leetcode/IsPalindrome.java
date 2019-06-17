package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/17
 * LeetCode 第 9 号问题：回文数
 */
public class IsPalindrome {

    public static void test() {
        System.out.println(isPalindrome(1234321));
        System.out.println(isPalindrome(12343212));
        System.out.println(isPalindrome2(1234321));
        System.out.println(isPalindrome2(12343212));
        System.out.println(isPalindrome3(1234321));
        System.out.println(isPalindrome3(12343212));
    }

    private static boolean isPalindrome(int x) {
        return new StringBuffer(x + "").reverse().toString().equals(String.valueOf(x));
    }

    private static boolean isPalindrome2(int x) {
        //边界判断
        if (x < 0) return false;
        int div = 1;
        //
        while (x / div >= 10) div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    private static boolean isPalindrome3(int x) {
        //思考：这里大家可以思考一下，为什么末尾为 0 就可以直接返回 false
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
