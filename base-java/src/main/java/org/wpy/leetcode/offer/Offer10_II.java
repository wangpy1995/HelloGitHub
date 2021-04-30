package org.wpy.leetcode.offer;

/**
 * <p>
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。<p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。<p>
 * 示例 1：<p>
 * 输入：n = 2<p>
 * 输出：2<p>
 * 示例 2：<p>
 * 输入：n = 7<p>
 * 输出：21<p>
 * 示例 3：<p>
 * 输入：n = 0<p>
 * 输出：1<p>
 * 提示：<p>
 * 0 <= n <= 100<p>
 * 来源：力扣（LeetCode）<p>
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof<p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。<p>
 */
public class Offer10_II {
    /**
     * 到第n级只有两种方式：从n-2级走两步到第n级，或者从n-1级走一步到第n级。<p>
     * 假设到第n-2级的方式有 f(n-2)种，到第n-1级的方式有f(n-1)种，<p>
     * 则到第n级的方式有：f(n-2)*1+f(n-1)*1=f(n-2)+f(n-1)。<p>
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n == 0 || n == 1) {
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
        n=n+1;
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
        System.out.println(new Offer10_II().numWays(7));
    }
}
