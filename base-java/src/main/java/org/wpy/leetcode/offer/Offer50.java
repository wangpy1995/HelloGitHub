package org.wpy.leetcode.offer;

public class Offer50 {

    public char firstUniqChar(String s) {
        if (s == null || s.isEmpty()) {
            return ' ';
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            count[idx] += 1;
        }
        for (int j = 0; j < s.length(); j++) {
            int idx = s.charAt(j) - 'a';
            if (count[idx] == 1) {
                return s.charAt(j);
            }
        }
        return ' ';
    }
}
