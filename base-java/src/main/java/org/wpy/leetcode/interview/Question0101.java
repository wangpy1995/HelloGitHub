package org.wpy.leetcode.interview;

public class Question0101 {
    public boolean isUnique(String astr) {
        if (astr == null) {
            return true;
        }
        int len = astr.length();
        if (len <= 1) {
            return true;
        }
        int mask = 0;
        for (int i = 0; i < len; i++) {
            int idx = astr.charAt(i) - (int) ('a');
            int newMask = mask | (1 << idx);
            if (newMask == mask) {
                return false;
            } else {
                mask = newMask;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String astr = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(new Question0101().isUnique(astr));
    }
}
