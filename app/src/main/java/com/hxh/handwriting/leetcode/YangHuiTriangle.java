package com.hxh.handwriting.leetcode;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HXH at 2019/6/18
 * LeetCode第118号问题：杨辉三角
 */
public class YangHuiTriangle {

    public static void test() {
        for (List<Integer> list : generate(5, 3)) {
            System.out.println(list);
        }
        System.out.println("===============================");
        List<List<Integer>> lists = generate(20, 1);
        int maxLen = lists.get(lists.size() - 1).toString().length();
        for (List<Integer> list : lists) {
            String x = list.toString();
            int spaceNum = (maxLen - x.length()) / 2;
            for (int i = 0; i < spaceNum; i++) {
                System.out.print(" ");
            }
            System.out.println(x);
        }
    }

    private static @NonNull
    List<List<Integer>> generate(int row, final int top) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<Integer>() {{
            add(top);
        }};
        lists.add(list);
        for (int i = 2; i <= row; i++) {
            List<Integer> nList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    nList.add(top);
                } else {
                    nList.add(list.get(j - 1) + list.get(j));
                }
            }
            lists.add(nList);
            list = nList;
        }
        return lists;
    }
}
