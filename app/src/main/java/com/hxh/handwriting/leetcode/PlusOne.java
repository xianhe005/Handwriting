package com.hxh.handwriting.leetcode;

import java.util.Arrays;

/**
 * Created by HXH at 2019/6/17
 * LeetCode 第 66 号问题：加一
 */
public class PlusOne {

    public static void test() {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(plusOne(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(plusOne(new int[]{8, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9, 9, 9})));
    }

    private static int[] plusOne(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            int val = array[i] + 1;
            if (val >= 10) {
                array[i] = val - 10;
            } else {
                array[i] = val;
                return array;
            }
        }
        int[] nArray = new int[array.length + 1];
        nArray[0] = 1;
        System.arraycopy(array, 0, nArray, 1, array.length);
        return nArray;
    }
}
