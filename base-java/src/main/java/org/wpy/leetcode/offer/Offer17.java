package org.wpy.leetcode.offer;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。<p>
 *<p>
 * 示例 1:<p>
 *<p>
 * 输入: n = 1<p>
 * 输出: [1,2,3,4,5,6,7,8,9]<p>
 * <p>
 *<p>
 * 说明：<p>
 *<p>
 * 用返回一个整数列表来代替打印<p>
 * n 为正整数<p>
 *<p>
 * 来源：力扣（LeetCode）<p>
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof<p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer17 {
    public int[] printNumbers(int n) {
        //1.确定数组容量
        int size = (int) Math.pow(10,n);
        int[] res = new int[size-1];
        //2.往数组中添加数字
        for(int i=0;i<size-1;i++){
            res[i] = i+1;
        }
        return res;
    }
}
