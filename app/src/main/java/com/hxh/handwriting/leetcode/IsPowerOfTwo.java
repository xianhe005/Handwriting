package com.hxh.handwriting.leetcode;

/**
 * Created by HXH at 2019/6/21
 * LeetCode 第 231 号问题：2 的幂
 */
public class IsPowerOfTwo {

    public static void test() {
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(15));
        System.out.println(isPowerOfTwo2(16));
        System.out.println(isPowerOfTwo2(15));
    }

    // 2的次方数都只有一个 1 ，剩下的都是 0 。根据这个特点，
    // 只需要每次判断最低位是否为 1 ，然后向右移位，最后统计 1 的个数即可判断是否是 2 的次方数。
    private static boolean isPowerOfTwo(int n) {
        int cnt = 0;
        while (n > 0) {
            cnt += (n & 1);
            n >>= 1;
        }
        return cnt == 1;
    }

    // 巧妙的解法
    // 如果一个数是 2 的次方数的话，那么它的二进数必然是最高位为1，其它都为 0 ，
    // 那么如果此时我们减 1 的话，则最高位会降一位，其余为 0 的位现在都为变为 1，
    // 那么我们把两数相与，就会得到 0。
    //
    // 比如 2 的 3 次方为 8，二进制位 1000 ，那么 8 - 1 = 7，其中 7 的二进制位 0111。
    private static boolean isPowerOfTwo2(int n) {
        return (n > 0) && (n & (n - 1)) == 0;
    }
}
