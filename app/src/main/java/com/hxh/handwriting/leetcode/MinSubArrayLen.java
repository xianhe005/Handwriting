package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/21
 * LeetCode 第 209 号问题：长度最小的子数组
 */
public class MinSubArrayLen {

    public static void test() {
        System.out.println(minSubArrayLen(new int[]{2, 3, 1, 2, 4, 3}, 7));
        System.out.println("=======================");
        System.out.println(minSubArrayLen(new int[]{2, 3, 1, 2, 4, 3}, 6));
        System.out.println("=======================");
        System.out.println(minSubArrayLen(new int[]{2, 3, 1, 2, 4, 3}, 4));
        System.out.println("=======================");
        System.out.println(minSubArrayLen(new int[]{2, 3, 1, 2, 4, 3}, 5));
        System.out.println("=======================");
        System.out.println(minSubArrayLen(new int[]{2, 3, 1, 2, 4, 3}, 8));
        System.out.println("=======================");
        System.out.println(minSubArrayLen(new int[]{2, 3, 1, 2, 4, 3}, 11));
    }

    private static int minSubArrayLen(int[] nums, int s) {
        int result = nums.length + 1;
        int l = 0;
        int r = 0;
        int sum = 0;
        int indexL = -1;
        int indexR = -1;
        while (l < nums.length) {
            if (r < nums.length && sum < s) {
                sum += nums[r];
                r++;
            } else {// r已经到头 或者 sum >= s
                sum -= nums[l];
                l++;
            }
            if (sum >= s) {
                if (r - l < result) {
                    result = r - l;
                    indexL = l;
                    indexR = r - 1;
                }
            }
        }

        if (result == nums.length + 1) {
            return 0;
        }
        System.out.println(indexL + "|" + indexR);
        for (int i = indexL; i <= indexR; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
        return result;
    }
}
