package org.wpy.leetcode.hot100;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 */
public class Hot017 {
    //队列思想，避开递归回溯
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Character, String> map = Map.of(
                '2', "abc",
                '3', "def",
                '4', "ghi",
                '5', "jkl",
                '6', "mno",
                '7', "pqrs",
                '8', "tuv",
                '9', "wxyz"
        );
        LinkedList<String> queue = new LinkedList<>();
        queue.add("");
        for (int i = 0; i < digits.length(); i++) {
            Character num = digits.charAt(i);
            String str = map.get(num);
            int len = queue.size();
            for (int j = 0; j < len; j++) {
                String tmp = queue.poll();
                for (int k = 0; k < str.length(); k++) {
                    queue.add(tmp + str.charAt(k));
                }
            }
        }
        return queue;
    }
}
