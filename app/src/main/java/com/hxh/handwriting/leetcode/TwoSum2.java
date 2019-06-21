package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/21
 * LeetCode 第 167 号问题：两数之和 II - 输入有序数组
 */
public class TwoSum2 {

    public static void test() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] indexs = twoSum(nums, target);
        if (indexs == null) {
            System.out.println("no two sum = " + target);
        } else {
            System.out.println(nums[indexs[0]] +
                    " 与 " +
                    nums[indexs[1]] +
                    " 之和等于目标数 " +
                    (nums[indexs[0]] + nums[indexs[1]]) +
                    " 。因此 index1 = " +
                    (indexs[0] + 1) +
                    ", index2 = " +
                    (indexs[1] + 1) +
                    " 。");
        }
    }

    private static int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int v = nums[i] + nums[j];
            if (target == v) {
                return new int[]{i, j};
            }
            if (target < v) {
                j--;
            } else {
                i++;
            }
        }
        return null;
    }
}
