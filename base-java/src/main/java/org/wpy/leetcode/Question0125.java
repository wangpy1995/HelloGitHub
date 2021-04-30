package org.wpy.leetcode;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question0125 {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return true;
        }
        int len = s.length();
        if (len <= 1) {
            return true;
        }
        int j = 0;
        int i = len - 1;
        while (i > j) {
            char tail = s.charAt(i);
            if (tail < '0' || (tail > '9' && tail < 'A') || (tail > 'Z' && tail < 'a') || tail > 'z') {
                i--;
                continue;
            }
            char head = s.charAt(j);
            if (head < '0' || (head > '9' && head < 'A')||(head > 'Z' && head < 'a') || head > 'z') {
                j++;
                continue;
            }
            if (head != tail && (head ^ 0x20) != tail) {
                return false;
            }
            i--;
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Question0125().isPalindrome("ab_a"));
    }
}
