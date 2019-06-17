package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/17
 * LeetCode 第 66 号问题：加一
 */
public class PlusOne {

    public static void test() {
        int[] result = plusOne(new int[]{1, 2, 3});
        for (int i : result) {
            System.out.print(i + ",");
        }
        System.out.println();

        result = plusOne(new int[]{4,3,2,1});
        for (int i : result) {
            System.out.print(i + ",");
        }
        System.out.println();

        result = plusOne(new int[]{8,9});
        for (int i : result) {
            System.out.print(i + ",");
        }
        System.out.println();

        result = plusOne(new int[]{9,9});
        for (int i : result) {
            System.out.print(i + ",");
        }
        System.out.println();

        result = plusOne(new int[]{9,9,9,9,9});
        for (int i : result) {
            System.out.print(i + ",");
        }
        System.out.println();
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
