package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/19
 * LeetCode 第 125 号问题：验证回文串
 */
public class IsPalindromeStr {

    public static void test() {
        System.out.println(isPalindrome(null));
        System.out.println(isPalindrome(""));
        System.out.println(isPalindrome("  "));
        System.out.println(isPalindrome("xxxs"));
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
    }

    private static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
