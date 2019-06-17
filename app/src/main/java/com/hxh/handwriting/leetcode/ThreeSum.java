package com.hxh.handwriting.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HXH at 2019/6/17
 * LeetCode 第 15 号问题：三数之和
 */
public class ThreeSum {

    public static void test() {
        int[] nums = {1, 2, 2, 3, -1, -2, -3, 4, -4, 0};
        List<List<Integer>> list = threeSum(nums);
        System.out.println(list);
        /*for (int[] ns : list) {
            System.out.println(ns[0] + "," + ns[1] + "," + ns[2]);
            System.out.println();
        }*/
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        // log
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();

        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int target = 0 - nums[k];
            int i = k + 1, j = nums.length - 1;
            while (i < j) {
                if (nums[i] + nums[j] == target) {
                    List<Integer> li = new ArrayList<>();
                    li.add(nums[k]);
                    li.add(nums[i]);
                    li.add(nums[j]);
                    list.add(li);
                    while (i < j && nums[i] == nums[i + 1]) {
                        i++;
                    }
                    while (i < j && nums[j] == nums[j - 1]) {
                        j--;
                    }
                    i++;
                    j--;
                } else if (nums[i] + nums[j] < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return list;
    }
}
