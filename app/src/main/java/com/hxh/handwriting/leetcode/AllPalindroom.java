package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HXH at 2019/6/20
 * LeetCode 第 131 号问题：分割回文串
 */
public class AllPalindroom {

    private List<List<String>> res = new ArrayList<>();

    public static void test() {
        AllPalindroom allPalindroom = new AllPalindroom();
        //allPalindroom.partition("aadfaffe");
        allPalindroom.partition("aad");
        System.out.println(allPalindroom.res);
    }

    private void partition(String s) {
        if (s == null || s.length() == 0)
            return;
        dfs(s, new ArrayList<String>(), 0);
    }

    // 递归，很难理解 深度优先搜索DFS算法
    private void dfs(String s, List<String> remain, int left) {
        if (left == s.length()) {  //判断终止条件
            res.add(new ArrayList<>(remain));  //添加到结果中
            return;
        }
        for (int right = left; right < s.length(); right++) {  //从left开始，依次判断left->right是不是回文串
            if (isPalindroom(s, left, right)) {  //判断是否是回文串
                remain.add(s.substring(left, right + 1));   //添加到当前回文串到list中
                dfs(s, remain, right + 1);  //从right+1开始继续递归，寻找回文串
                remain.remove(remain.size() - 1);  //回溯，从而寻找更长的回文串
            }
        }
    }

    /**
     * 判断是否是回文串
     */
    private boolean isPalindroom(String s, int left, int right) {
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }
}
