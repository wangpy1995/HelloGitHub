package org.wpy.leetcode.hot100;

import org.junit.Test;

public class TestHot011 {
    Hot011 h = new Hot011();

    @Test
    public void testMaxArea(){
        int[] heightsA = {1,8,6,2,5,4,8,3,7};
        int[] heightsB = {1,1};
        assert h.maxArea(heightsA) == 49;
        assert h.maxArea(heightsB) == 1;
    }
}
