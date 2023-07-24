package org.wpy.leetcode.hot100;

/**
 * 12. 整数转罗马数字
 */
public class Hot012 {
    public String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < 13; i++) {
            while (num >= nums[i]) {
                num -= nums[i];
                ans.append(roman[i]);
            }
        }
        return ans.toString();
    }

}
