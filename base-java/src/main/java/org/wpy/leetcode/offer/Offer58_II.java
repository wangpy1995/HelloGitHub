package org.wpy.leetcode.offer;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。比如，
 * 输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * <p>
 * 示例 1：
 * 输入: s = "abcdefg", k = 2
 * 输出:"cdefgab"
 * <p>
 * 示例 2：
 * 输入: s = "lrloseumgh", k = 6
 * 输出:"umghlrlose"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer58_II {
    public String reverseLeftWords(String s, int n) {
        if (s == null || n == 0) {
            return s;
        }
        int len = s.length();
        if (len <= 1 || len == n) {
            return s;
        }
        return s.substring(n) + s.substring(0, n);
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        System.out.println(new Offer58_II().reverseLeftWords(s, k));
    }
}
