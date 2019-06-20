package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/20
 * LeetCode136.只出现一次的数字
 */
public class SingleNumber {

    public static void test() {
        System.out.println(singleNumber(new int[]{4, 1, 2, 2, 1}));
        System.out.println(singleNumber(new int[]{4, 1, 0, 2, 2, 1, 4}));
        //System.out.println(singleNumber(new int[]{4, 1, 2, 2, 1, 4}));
        System.out.println(singleNumber(new int[]{4, 1, 2, 2, 1, 4, 0, 0}));
    }

    // 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
    private static int singleNumber(int[] nums) {
        int n = 0;
        int zeroCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            }
            n ^= num;
        }
        if ( n == 0 && zeroCount == 0) {
            //System.out.println("no single");
            throw new IllegalArgumentException("no single");
        }
        if (n == 0 && zeroCount == 2) {
            //System.out.println("no single, contain two zero");
            throw new IllegalArgumentException("no single, contain two zero");
        }
        return n;
    }

    // 有一个 n 个元素的数组，除了两个数只出现一次外，其余元素都出现两次，
    // 让你找出这两个只出现一次的数分别是几，要求时间复杂度为 O(n) 且再开辟的内存空间固定(与 n 无关)。
    private static int singleNumber2(int[] nums) {
        int n = 0;
        int zeroCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            }
            n ^= num;
        }
        if ( n == 0 && zeroCount == 0) {
            //System.out.println("no single");
            throw new IllegalArgumentException("no single");
        }
        if (n == 0 && zeroCount == 2) {
            //System.out.println("no single, contain two zero");
            throw new IllegalArgumentException("no single, contain two zero");
        }
        if (n == 0) {
            throw new IllegalArgumentException("no two single");
        }

        // TODO: 2019/6/20 这里如何提取出单独的两个值？未实现

        return n;
    }
}
