package org.wpy.leetcode.hot100;

public class Hot011 {
    //不停向内移动短板并计算最大面积
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int i = 0;
        int j = height.length - 1;
        int maxS = 0;
        while (i != j) {
            int width = j - i;
            int s;
            if (height[i] < height[j]) {
                s = height[i] * width;
                i++;
            } else {
                s = height[j] * width;
                j--;
            }
            if (s > maxS) {
                maxS = s;
            }
        }
        return maxS;
    }
}
