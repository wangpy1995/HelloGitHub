package org.wpy.leetcode;

/**
 * 给你两个整数，n 和 start 。<p>
 * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。<p>
 * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。<p>
 * <p>
 * 示例 1：<p>
 * 输入：n = 5, start = 0<p>
 * 输出：8<p>
 * 解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。<p>
 * "^" 为按位异或 XOR 运算符。<p>
 * 示例 2：<p>
 * 输入：n = 4, start = 3<p>
 * 输出：8<p>
 * 解释：数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8.<p>
 * 示例 3：<p>
 * 输入：n = 1, start = 7<p>
 * 输出：7<p>
 * 示例 4：<p>
 * 输入：n = 10, start = 5<p>
 * 输出：2<p>
 * <p>
 * 提示：<p>
 * 1 <= n <= 1000<p>
 * 0 <= start <= 1000<p>
 * n == nums.length<p>
 * <p>
 * 来源：力扣（LeetCode）<p>
 * 链接：https://leetcode-cn.com/problems/xor-operation-in-an-array<p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question1486 {
    /**
     * 记 ⊕ 为异或运算，异或运算满足以下性质：
     * <p>
     * 1. x⊕x=0；<p>
     * 2. x⊕y=y⊕x（交换律）；<p>
     * 3. (x⊕y)⊕z=x⊕(y⊕z)（结合律）；<p>
     * 4. x⊕y⊕y=x（自反性）；<p>
     * 5. 4i⊕(4i+1)⊕(4i+2)⊕(4i+3)=0。<p>
     *
     * @param x
     * @return
     */
    private int sumXor(int x) {
        if (x % 4 == 0) {
            return x;
        }
        if (x % 4 == 1) {
            return 1;
        }
        if (x % 4 == 2) {
            return x + 1;
        }
        return 0;
    }


    public int xorOperation(int n, int start) {
        int s = start >> 1;
        // 当start和n均为奇数时最后一位才为1
        int e = n & start & 1;
        // start⊕(start+2)⊕(start+4)⊕⋯⊕(start+2(n−1)) = (s⊕(s+1)⊕(s+2)⊕⋯⊕(s+n−1))×2+e
        // e表示运算结果最低位
        int front = sumXor(s - 1) ^ sumXor(s + n - 1);
        return (front << 1) | e;
    }
}
