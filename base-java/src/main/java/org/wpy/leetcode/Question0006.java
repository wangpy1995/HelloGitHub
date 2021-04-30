package org.wpy.leetcode;

public class Question0006 {
    public String convert(String s, int numRows) {
        if (s == null) {
            return null;
        }
        int len = s.length();
        if (s.length() <= 1 || numRows == 1) {
            return s;
        } else {
            StringBuilder sb = new StringBuilder();
            int g = ((numRows - 1) << 1);
            for (int i = 0; i < numRows; i++) {
                int j = i;
                int m = g - 2 * i;
                while (j < len) {
                    sb.append(s.charAt(j));
                    if (i != 0 && i != numRows - 1) {
                        int next = j + m;
                        if (next < len) {
                            sb.append(s.charAt(next));
                        }
                    }
                    j += g;
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String s = "AB";
        System.out.println(new Question0006().convert(s, 2));
    }
}
