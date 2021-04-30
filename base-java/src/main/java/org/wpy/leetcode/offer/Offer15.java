package org.wpy.leetcode.offer;

/**
 * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。<p>
 * 示例 1：<p>
 * 输入：00000000000000000000000000001011<p>
 * 输出：3<p>
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。<p>
 * 示例 2：<p>
 * 输入：00000000000000000000000010000000<p>
 * 输出：1<p>
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。<p>
 * 示例 3：<p>
 * 输入：11111111111111111111111111111101<p>
 * 输出：31<p>
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。<p>
 * 提示：<p>
 * 输入必须是长度为 32 的 二进制串 。<p>
 *<p>
 * 来源：力扣（LeetCode）<p>
 * 链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof<p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer15 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    public static void main(String[] args) {
        int n = 0xfffffffd;
        System.out.println(new Offer15().hammingWeight(n));
    }
}
