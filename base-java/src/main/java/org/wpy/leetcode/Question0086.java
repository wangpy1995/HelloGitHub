package org.wpy.leetcode;

import java.util.Arrays;

/**
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question0086 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int j = m - 1;
        int k = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (j >= 0 && k < 0) {
                nums1[i] = nums1[j];
                j--;
            }
            if (j < 0 && k >= 0) {
                nums1[i] = nums2[k];
                k--;
            }
            if (j >= 0 && k >= 0) {
                if (nums1[j] > nums2[k]) {
                    nums1[i] = nums1[j];
                    j--;
                } else {
                    nums1[i] = nums2[k];
                    k--;
                }
            }
        }
    }

    public static void main(String[] args) {
        int m = 1, n = 1;
        int[] nums1 = {2,0};
        int[] nums2 = {1};
        new Question0086().merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}
