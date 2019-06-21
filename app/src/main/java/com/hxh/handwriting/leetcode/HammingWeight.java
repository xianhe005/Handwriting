package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/21
 * LeetCode 第 191 号问题：位 1 的个数
 */
public class HammingWeight {
    public static void test() {
        System.out.println(hammingWeight(8888));
        System.out.println(hammingWeight(0b11111110));
    }

    /**
     * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
     *
     * 该题比较简单，解法有挺多，有位移法、位操作法、查表法、二次查表法等方法。
     *
     * 观察一下 n 与 n-1 这两个数的二进制表示：对于 n-1 这个数的二进制来说，相对于 n 的二进制，它的最末位的一个 1 会变成 0，最末位一个 1 之后的 0 会全部变成 1，其它位相同不变。
     *
     * 比如 n = 8888，其二进制为 10001010111000
     *
     * 则 n - 1 = 8887 ，其二进制为 10001010110111
     *
     * 通过按位与操作后：n & (n-1) = 10001010110000
     *
     * 也就是说：通过 n&(n-1)这个操作，可以起到消除最后一个1的作用。
     *
     * 所以可以通过执行 n&(n-1) 操作来消除 n 末尾的 1 ，消除了多少次，就说明有多少个 1 。
     */
    private static int hammingWeight(int n) {
        int cnt = 0;
        while (n > 0) {
            cnt++;
            n = n & (n - 1);
        }
        return cnt;
    }
}
