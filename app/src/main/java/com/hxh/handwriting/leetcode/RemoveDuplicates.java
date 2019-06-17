package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/17
 * LeetCode 第 26 号问题：删除排序数组中的重复项
 */
public class RemoveDuplicates {

    public static void test() {
        int[] array = {1, 1, 1, 2, 2, 3, 3, 3, 4, 5, 5, 6, 6, 7};
        int length = removeDuplicates(array);
        for (int i = 0; i < length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }

    private static int removeDuplicates(int[] nums) {
        /*int length = nums.length;
        int move = 0;//累计需要前移的位置个数
        for (int i = 1; i < length; i++) {
            if (nums[i] == nums[i - 1]) {
                move++;
            }
            if (move > 0) {
                nums[i - move] = nums[i];
            }
        }
        return length - move;*/
        // 下面这个比上面更优
        int back = 0;
        for (int front = 1; front < nums.length; front++) {
            if (nums[back] != nums[front]) {
                back++;
                nums[back] = nums[front];
            }
        }
        return back + 1;
    }
}
