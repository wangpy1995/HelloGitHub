package org.wpy.leetcode;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question0169 {

    /**
     * 由于多数元素次数大于n/2，所以把相邻的不一样的数都消去即可
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int res = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (res != nums[i]) {
                count--;
                if (count == 0) {
                    res = nums[i + 1];
                }
            } else {
                count++;
            }
        }
        return res;
    }
}
