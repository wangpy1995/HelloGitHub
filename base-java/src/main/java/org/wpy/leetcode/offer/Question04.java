package org.wpy.leetcode.offer;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * 现有矩阵 matrix 如下：
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target=5，返回true。
 * <p>
 * 给定target=20，返回false。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Question04 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        int colLen = matrix.length;
        if (colLen == 0 || matrix[0] == null) {
            return false;
        }
        int rowLen = matrix[0].length;
        if (rowLen == 0) {
            return false;
        }

        // 以左下或者右上为顶点，可以看作二叉树
        int i = colLen - 1, j = 0;
        while (i >= 0 && j < rowLen) {
            int num = matrix[i][j];
            if (num == target) {
                return true;
            } else if (target > num) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15, 16},
                {2, 5, 8, 12, 19, 21},
                {3, 6, 9, 16, 22, 25},
                {10, 13, 14, 17, 24, 32},
                {18, 21, 23, 26, 30, 41}};
        Question04 q = new Question04();
        System.out.println(q.findNumberIn2DArray(matrix, 25));
    }
}
