package org.wpy.leetcode;

public class Question0240 {
    //以左下角为起点进行移动
    public boolean searchMatrix(int[][] matrix, int target) {
        if (null == matrix || matrix.length == 0 || matrix[0] == null) {
            return false;
        }
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int i = m;
        int j = 0;
        while (i >= 0 && j <= n) {
            if (matrix[i][j] < target) {
                j++;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                return true;
            }
        }
        return false;
    }
}
