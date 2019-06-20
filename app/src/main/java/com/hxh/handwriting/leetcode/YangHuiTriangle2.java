package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HXH at 2019/6/18
 * LeetCode第119号问题：杨辉三角II
 */
public class YangHuiTriangle2 {

    public static void test() {
        System.out.println(generate(5));
        System.out.println(generate(3));
    }

    private static List<Integer> generate(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        long index = 1;
        for (int i = 0; i <= rowIndex; i++) {
            res.add((int) index);
            index = index * (rowIndex - i) / (i + 1);
        }
        return res;
    }
}
