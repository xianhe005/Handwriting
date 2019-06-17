package com.hxh.handwriting.leetcode;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HXH at 2019/6/17
 * LeetCode 第 1 号问题：两数之和
 */
public class TwoSum {

    public static void test() {
        int[] array = {2, 7, 10, 3};
        int[] nums = twoSum(array, 9);
        if (nums == null) {
            System.out.println("no two sum");
        } else {
            System.out.println(nums[0] + "," + nums[1]);
        }
        System.out.println("============================================");
        nums = twoSum(array, 10);
        if (nums == null) {
            System.out.println("no two sum");
        } else {
            System.out.println(nums[0] + "," + nums[1]);
        }
        System.out.println("============================================");
        nums = twoSum(array, 11);
        if (nums == null) {
            System.out.println("no two sum");
        } else {
            System.out.println(nums[0] + "," + nums[1]);
        }
    }

    private static int[] twoSum(int[] array, int target) {
        @SuppressLint("UseSparseArrays") Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int v = array[i];
            Integer ti = map.get(target - v);
            if (ti != null) {
                return new int[]{ti, i};
            }
            map.put(v, i);
        }
        return null;
    }
}
