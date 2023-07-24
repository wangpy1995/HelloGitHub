package org.wpy.leetcode.hot100;

import org.junit.Test;

import java.util.List;

public class TestHot017 {
    @Test
    public void testLetterCombinations() {
        Hot017 q = new Hot017();
        List<String> results = q.letterCombinations("23");
        for (String str : results) {
            System.out.println(str);
        }
        results = q.letterCombinations("");
        for (String str : results) {
            System.out.println(str);
        }
        results = q.letterCombinations("2");
        for (String str : results) {
            System.out.println(str);
        }

    }
}
