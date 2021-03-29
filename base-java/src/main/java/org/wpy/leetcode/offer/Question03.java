package org.wpy.leetcode.offer;

public class Question03 {
    public int findRepeatNumber(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return nums[0];
        }
        /**
         * maxNum<len 通过交换位置实现重复元素查找
         */
        for (int i = 0; i < len; i++) {
            int tmp = nums[i];
            while (tmp != i) {
                if (nums[tmp] == tmp) {
                    return tmp;
                }
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] testNums = new int[]{2, 3, 1, 0, 3, 2, 5};
        System.out.println(new Question03().findRepeatNumber(testNums));
    }
}
