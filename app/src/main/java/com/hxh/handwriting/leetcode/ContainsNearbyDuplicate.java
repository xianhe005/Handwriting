package com.hxh.handwriting.leetcode;

import android.annotation.SuppressLint;

import java.util.HashMap;

/**
 * Created by HXH at 2019/6/21
 * LeetCode 第 219 号问题：存在重复元素 II
 */
public class ContainsNearbyDuplicate {

    public static void test() {
        int size = 10000;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = (i + 1) % 3;
        }

        long c = System.currentTimeMillis();
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 2));
        System.out.println(containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
        for (int i = 0; i < size; i++) {
            System.out.print(containsNearbyDuplicate(nums, 2));
            System.out.print(",");
        }
        System.out.println();
        System.out.println(System.currentTimeMillis() - c + "ms");
        System.out.println("=======================================");
        c = System.currentTimeMillis();
        System.out.println(containsNearbyDuplicate2(new int[]{1, 2, 3, 1}, 3));
        System.out.println(containsNearbyDuplicate2(new int[]{1, 2, 3, 1}, 2));
        System.out.println(containsNearbyDuplicate2(new int[]{1, 0, 1, 1}, 1));
        System.out.println(containsNearbyDuplicate2(new int[]{1, 2, 3, 1, 2, 3}, 2));
        for (int i = 0; i < size; i++) {
            System.out.print(containsNearbyDuplicate2(nums, 2));
            System.out.print(",");
        }
        System.out.println();
        System.out.println(System.currentTimeMillis() - c + "ms");
    }

    /**
     * Map实现
     */
    @SuppressLint("UseSparseArrays")
    private static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k < 1 || nums == null || nums.length < 2) return false;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!hashMap.containsKey(nums[i])) hashMap.put(nums[i], i);
            else {
                Integer integer = hashMap.get(nums[i]);
                assert integer != null;
                int temp = integer;
                if (i - temp <= k) return true;
                // 注意:更新下次的索引值
                hashMap.put(nums[i], i);
            }
        }
        return false;
    }

    // 双指针自己实现
    private static boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (k < 1 || nums == null || nums.length < 2) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < Math.min(nums.length, i + 1 + k); j++) {
                if (i != j && nums[i] == nums[j] && j - i <= k) {
                    return true;
                }
            }
        }
        return false;
    }
}
