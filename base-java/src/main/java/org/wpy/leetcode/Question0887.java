package org.wpy.leetcode;

public class Question0887 {

    /**
     * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
     * 已知存在楼层 f ，满足0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
     * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
     * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
     *<p>
     * 示例 1：
     *<p>
     * 输入：k = 1, n = 2<p>
     * 输出：2<p>
     * 解释：
     * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。 
     * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。 
     * 如果它没碎，那么肯定能得出 f = 2 。 
     * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
     * <p>
     * 示例 2：
     *<p>
     * 输入：k = 2, n = 6<p>
     * 输出：3<p>
     * 示例 3：
     *<p>
     * 输入：k = 3, n = 14<p>
     * 输出：4<p>
     *<p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/super-egg-drop
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        for (int m = 1; m <= N; m++) {
            dp[0][m] = 0;
            for (int k = 1; k <= K; k++) {
                //求K个鸡蛋在m步内能测试的最大层数
                //[0,f) [f] (f,Nan)x
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
                if (dp[k][m] >= N) {
                    return m;
                }
            }
        }
        return N;
    }
}
