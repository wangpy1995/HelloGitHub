package org.wpy.leetcode.offer;

/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？<p>
 * 示例 1:<p>
 * 输入: <p>
 * [<p>
 * [1,3,1],<p>
 * [1,5,1],<p>
 * [4,2,1]<p>
 * ]<p>
 * 输出: 12<p>
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物<p>
 * <p>
 * 提示：<p>
 * 0 < grid.length <= 200<p>
 * 0 < grid[0].length <= 200<p>
 * <p>
 * 来源：力扣（LeetCode）<p>
 * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof<p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer47 {

    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[j] = Math.max(dp[j], dp[j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[n];
    }

}
