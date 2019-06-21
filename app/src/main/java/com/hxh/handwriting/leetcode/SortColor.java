package com.hxh.handwriting.leetcode;

import java.util.Arrays;

/**
 * Created by HXH at 2019/6/18
 * LeetCode 第 75 号问题：颜色分类
 */
public class SortColor {

    public static void test() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println("===========================");
        nums = new int[]{2, 0, 2, 1, 1, 0};
        sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }

    // 三路快速排序的思想
    // 对整个数组只遍历了一遍
    // 时间复杂度: O(n)
    // 空间复杂度: O(1)
    private static void sortColors(int[] nums) {
        int zero = -1;          // [0...zero] == 0
        int two = nums.length;  // [two...n-1] == 2
        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                two--;
                swap(nums, i, two);
            } else { // nums[i] == 0
                zero++;
                swap(nums, zero, i);
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 很巧妙
    private static void sortColors2(int[] nums) {
        int length = nums.length;
        if (length == 0 || length == 1) {
            return;
        }
        int[] num = new int[3];
        for (int now : nums) {
            num[now]++;
        }
        for (int i = 0; i < num[0]; i++) {
            nums[i] = 0;
        }
        for (int i = num[0]; i < num[0] + num[1]; i++) {
            nums[i] = 1;
        }
        for (int i = num[0] + num[1]; i < length; i++) {
            nums[i] = 2;
        }
    }

}
