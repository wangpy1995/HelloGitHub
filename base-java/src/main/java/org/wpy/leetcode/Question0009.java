package org.wpy.leetcode;

public class Question0009 {
    /**
     * 判断回文数
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        } else {
            int reversedNum = 0;
            int temp = x;
            while (temp / 10 > 0) {
                int num = temp % 10;
                reversedNum = reversedNum * 10 + num;
                temp /= 10;
            }
            reversedNum = reversedNum * 10 + temp;
            if (reversedNum == x) {
                return true;
            } else {
                return false;
            }
        }
    }
}
