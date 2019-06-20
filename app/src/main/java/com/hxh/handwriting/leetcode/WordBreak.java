package com.hxh.handwriting.leetcode;

import android.support.v4.content.res.TypedArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by HXH at 2019/6/20
 * LeetCode 第 139 号问题：单词拆分
 */
public class WordBreak {

    public static void test() {
        System.out.println(wordBreak("leetcode",
                Arrays.asList("leet", "code")));
        System.out.println(wordBreak("applepenapple",
                Arrays.asList("apple", "pen")));
        System.out.println(wordBreak("catsandog",
                Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    /**
     * 动态规划
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
     * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * 说明：
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * 示例 1：
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * 示例 2：
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     * 注意你可以重复使用字典中的单词。
     * 示例 3：
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     */
    private static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        /*for (boolean b : dp) {
            System.out.print(b + ",");
        }
        System.out.println();*/
        return dp[n];
    }
}
