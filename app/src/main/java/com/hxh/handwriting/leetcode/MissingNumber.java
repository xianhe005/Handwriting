package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/24
 * LeetCode 第 268 号问题：缺失数字
 */
public class MissingNumber {

    public static void test() {
        System.out.println(missingNumber1(new int[]{3, 0, 1}));
        System.out.println(missingNumber2(new int[]{3, 0, 1}));
        System.out.println(missingNumber3(new int[]{3, 0, 1}));
        System.out.println(missingNumber1(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(missingNumber2(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(missingNumber3(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    // 异或法
    private static int missingNumber1(int[] nums) {
        int res = 0;
        int i = 0;
        //注意数组越界情况
        for (; i < nums.length; i++) {
            // i 表示完整数组中的数字，与原数组中的数字 nums[i] 进行异或，再与保存的结果异或
            res = res ^ i ^ nums[i];
        }
        //最后需要与循环中无法使用到的那个最大的数异或
        return res ^ i;
    }

    // 异或法
    private static int missingNumber2(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; ++i) {
            res ^= nums[i];
            res ^= i;
        }
        return res;
    }

    // 求和法
    private static int missingNumber3(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }
}
