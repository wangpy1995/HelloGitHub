package org.wpy.leetcode.hot100;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * 20. 有效的括号
 */
public class Hot020 {
    public boolean isValid(String s) {
        Map<Character, Character> map = Map.of(
                '(', ')',
                '[', ']',
                '{', '}');
        Deque<Character> stack = new ArrayDeque<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            Character cur = s.charAt(i);
            Character top = stack.peek();
            if (cur == map.get(top)) {
                stack.pop();
            } else {
                stack.push(cur);
            }
        }
        return stack.isEmpty();
    }
}
