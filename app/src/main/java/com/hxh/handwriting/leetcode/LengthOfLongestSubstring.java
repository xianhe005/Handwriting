package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HXH at 2019/6/17
 * LeetCode 第 3 号问题：无重复字符的最长子串
 */
public class LengthOfLongestSubstring {

    public static void test() {
        String str = "abccafedbb";
        String subStr = lengthOfLongestSubstring(str);
        System.out.println(subStr);
    }

    private static String lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int strLength = s.length();
        int left = 0, right = 0;
        List<Character> result = new ArrayList<>();
        List<Character> list = new ArrayList<>();
        while (left < strLength && right < strLength) {
            if (list.contains(s.charAt(right))) {
                list.remove((Character) s.charAt(left));
                left++;
            } else {
                list.add(s.charAt(right));
                right++;
                maxLength = Math.max(maxLength, right - left);
            }
            if (list.size() > result.size()) {
                result.clear();
                result.addAll(list);
            }
        }
        return result.toString();
    }
}
