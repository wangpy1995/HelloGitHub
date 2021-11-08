package org.wpy.leetcode.offer;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * 
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 * 示例2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 * 
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 10000
 *
 * 通过次数177,295提交次数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer53_II {
    public int missingNumber(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //n==2
        if (nums.length == 1) {
            return 1 - nums[0];
        }
        int right = nums.length - 1, left = 0;
        //缺尾
        if(right == nums[right]){
            return nums.length;
        }
        while (right - left >= 0) {
            int mid = (left + right) / 2;
            if (mid == nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
