package org.wpy.leetcode.offer;

/**
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109<= nums[i]<= 109
 * nums是一个非递减数组
 * -109<= target<= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer53_I {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            if (nums[0] != target) {
                return 0;
            } else {
                return 1;
            }
        }
        int left = 0, right = nums.length - 1;
        int pos = -1;

        if (target == nums[left]) {
            pos = left;
        } else if (target == nums[right]) {
            pos = right;
        } else {
            while (target > nums[left] && target < nums[right] && right - left > 1) {
                pos = (left + right) / 2;
                if (target > nums[pos]) {
                    left = pos;
                } else if (target < nums[pos]) {
                    right = pos;
                } else {
                    break;
                }
            }
            if (pos < 0) {
                return 0;
            }
        }
        int count = 0;
        for (int i = pos; i >= 0; i--) {
            if (target == nums[i]) {
                count++;
            } else {
                break;
            }
        }
        for (int i = pos + 1; i < nums.length; i++) {
            if (target == nums[i]) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
