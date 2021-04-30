package org.wpy.leetcode.offer;

public class Offer10_I {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return mulMatrix(n);
        }
    }

    public int mulMatrix(int n) {
        //使用long型是为了防止乘法溢出
        //fib基础矩阵 |f(n+1), f(n)| = |f(n)+f(n-1), f(n)| = |f(n), f(n-1)| * |1, 1|
        //                                                                                                  |1, 0|
        long a00 = 1, a01 = 1, a10 = 1, a11 = 0;
        //单位矩阵
        long c00 = 1, c01 = 0, c10 = 0, c11 = 1;
        //矩阵乘积中间结果
        long b00, b01, b10, b11;
        while (n > 0) {
            if ((n & 1) != 0) {
                b00 = (c00 * a00 + c01 * a10) % 1000000007;
                b01 = (c00 * a01 + c01 * a11) % 1000000007;
                b10 = (c10 * a00 + c11 * a10) % 1000000007;
                b11 = (c10 * a01 + c11 * a11) % 1000000007;
                c00 = b00;
                c01 = b01;
                c10 = b10;
                c11 = b11;
            }
            b00 = (a00 * a00 + a01 * a10) % 1000000007;
            b01 = (a00 * a01 + a01 * a11) % 1000000007;
            b10 = (a10 * a00 + a11 * a10) % 1000000007;
            b11 = (a10 * a01 + a11 * a11) % 1000000007;
            a00 = b00;
            a01 = b01;
            a10 = b10;
            a11 = b11;
            n >>= 1;
        }
        return (int)c01;
    }


    public static void main(String[] args) {
        Offer10_I off = new Offer10_I();
        System.out.println(off.fib(47));
    }
}
