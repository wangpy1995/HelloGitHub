package org.wpy.leetcode;

import org.junit.Test;

public class TestQuestion0167 {
    Question0167 q = new Question0167();

    @Test
    public void testTwoSumA() {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] idx = q.twoSum(numbers, target);
        System.out.println(idx[0] + ", " + idx[1]);
    }

    @Test
    public void testTwoSumB() {
        int[] numbers = {2, 3, 4};
        int target = 6;
        int[] idx = q.twoSum(numbers, target);
        System.out.println(idx[0] + ", " + idx[1]);
    }

    @Test
    public void testTwoSumC() {
        int[] numbers = {-1, 0};
        int target = -1;
        int[] idx = q.twoSum(numbers, target);
        System.out.println(idx[0] + ", " + idx[1]);
    }
}
